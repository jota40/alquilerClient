package es.jota.alquiler.gwt.client.display.home;

import com.google.gwt.cell.client.SafeHtmlCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import es.jota.alquiler.gwt.client.gin.Gin;
import es.jota.alquiler.gwt.client.place.vivienda.ViviendaPlaceCrear;
import es.jota.alquiler.gwt.client.place.vivienda.ViviendaPlaceEditar;
import es.jota.alquiler.gwt.client.place.vivienda.ViviendaPlaceVer;
import es.jota.utils.gwt.client.enums.AccionEnum;
import es.jota.utils.gwt.client.enums.ColorEnum;
import es.jota.utils.gwt.client.enums.IconoEnum;
import es.jota.utils.gwt.client.table.AccionesCell;
import es.jota.utils.gwt.client.table.paginator.MyPager.MyPagerView;
import es.jota.utils.gwt.client.utils.UtilClient;
import jota.server.dto.ViviendaDtoDown;

public class HomeDisplayImpl extends Composite implements HomeDisplay {

	private static HomeDisplayUiBinder uiBinder = GWT.create(HomeDisplayUiBinder.class);
	interface HomeDisplayUiBinder extends UiBinder<Widget, HomeDisplayImpl>{}

	@UiField Anchor misViviendas;
	@UiField AnchorElement create;

	@UiField(provided=true) CellTable<ViviendaDtoDown> tabla;
	@UiField MyPagerView paginator;

	private HomeDisplay.Presenter presenter;

	@Override
	public void setPresenter( HomeDisplay.Presenter Presenter ) {
		this.presenter = Presenter;
	}

	public HomeDisplayImpl() {
		tabla = new CellTable<ViviendaDtoDown>(1);
		initTableColumns();
		initWidget(uiBinder.createAndBindUi(this));
		create.setHref( ViviendaPlaceCrear.getUrl() );
	}

	@Override
	public void configure() {
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
		    @Override
		    public void execute() {
				UtilClient.JS.show( "configurable", false );
				UtilClient.JS.show( Gin.INSTANCE.getSecurityGwtUtils().getRol().name(), true );
			}
		});
	}

	@UiHandler("misViviendas")
	public void misViviendasOnClick(ClickEvent event) {
		if ( isMisViviendas() ) {
			misViviendas.getElement().removeClassName("active");
		} else {
			misViviendas.getElement().addClassName("active");
		}
		presenter.refresh();
	}

	@UiHandler("reset")
	public void resetOnClick(ClickEvent event) {
		presenter.reset();
	}

	@UiHandler("refresh")
	public void refreshOnClick(ClickEvent event) {
		presenter.refresh();
	}

	private void initTableColumns() {
		Column<ViviendaDtoDown, SafeHtml> nombreColumn = new Column<ViviendaDtoDown, SafeHtml>( new SafeHtmlCell()) {
			@Override
			public SafeHtml getValue( ViviendaDtoDown object) {
				return new SafeHtmlBuilder().appendEscapedLines( object.getNombre() ).toSafeHtml(); 
			}
		};

		Column<ViviendaDtoDown, SafeHtml> situacionColumn = new Column<ViviendaDtoDown, SafeHtml>( new SafeHtmlCell()) {
			@Override
			public SafeHtml getValue( ViviendaDtoDown object) {
				return new SafeHtmlBuilder().appendEscapedLines( object.getSituacion() ).toSafeHtml(); 
			}
		};

		Column<ViviendaDtoDown, SafeHtml> resumenColumn = new Column<ViviendaDtoDown, SafeHtml>( new SafeHtmlCell()) {
			@Override
			public SafeHtml getValue( ViviendaDtoDown object) {
				return UtilClient.Cadena.string2SafeHtml( object.getResumen() ); 
			}
		};

		Column<ViviendaDtoDown, SafeHtml> capacidadColumn = new Column<ViviendaDtoDown, SafeHtml>( new SafeHtmlCell()) {
			@Override
			public SafeHtml getValue( ViviendaDtoDown object) {
				return UtilClient.Cadena.string2SafeHtml( UtilClient.Numeros.number2String( object.getCapacidad() ) ); 
			}
		};

		AccionesCell accionesCell = new AccionesCell(){
			@Override
			protected void doAction( String accionId, String accionValue ) {
				try {
					Long idVivienda = Long.parseLong( accionValue );
					switch ( AccionEnum.getEnum( accionId ) ) {
						case BORRAR: presenter.borrar( idVivienda );
						break;
					}
				} catch (Exception e) {
				}
			}
		};
		Column<ViviendaDtoDown, SafeHtml> accionesColumn = new Column<ViviendaDtoDown, SafeHtml>( accionesCell ) {
			@Override
			public SafeHtml getValue( ViviendaDtoDown object ) {
				Long id = object.getId();
				SafeHtmlBuilder shb = new SafeHtmlBuilder();
				if ( object.isMia() ) {
					shb.append( UtilClient.TEMPLATES.accionDelegate( AccionEnum.BORRAR.getId().toString(), id.toString(), AccionEnum.BORRAR.getColor().getBtn(), AccionEnum.BORRAR.getIcono().get() ) );
					shb.append( UtilClient.TEMPLATES.accionLink( ViviendaPlaceEditar.getUrl( id, object.getIdUsuario() ), ColorEnum.AZUL.getBtn(), IconoEnum.EDITAR.get() ) );
				}
				shb.append( UtilClient.TEMPLATES.accionLink( ViviendaPlaceVer.getUrl( id ), ColorEnum.VERDE.getBtn(), IconoEnum.VER.get() ) );
				return shb.toSafeHtml();
			}
		};

//		nombreColumn.setSortable(true);

		tabla.addColumn( nombreColumn, "Nombre");
		tabla.addColumn( situacionColumn, "Situación");
		tabla.addColumn( resumenColumn, "Resumen");
		tabla.addColumn( capacidadColumn, "Capacidad");
		tabla.addColumn( accionesColumn, "Acciones");
	}

	@Override
	public CellTable<ViviendaDtoDown> getTabla() {
		return tabla;
	}

	@Override
	public MyPagerView getPagerDisplay() {
		return paginator;
	}

	@Override
	public boolean isMisViviendas() {
		return misViviendas.getElement().hasClassName("active");
	}
}
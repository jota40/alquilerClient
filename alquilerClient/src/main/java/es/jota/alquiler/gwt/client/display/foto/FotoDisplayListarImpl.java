package es.jota.alquiler.gwt.client.display.foto;

import com.google.gwt.cell.client.SafeHtmlCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import es.jota.alquiler.gwt.client.place.foto.FotoPlaceCrear;
import es.jota.alquiler.gwt.client.place.foto.FotoPlaceEditar;
import es.jota.alquiler.gwt.client.place.foto.FotoPlaceVer;
import es.jota.utils.gwt.client.enums.AccionEnum;
import es.jota.utils.gwt.client.enums.ColorEnum;
import es.jota.utils.gwt.client.enums.IconoEnum;
import es.jota.utils.gwt.client.table.AccionesCell;
import es.jota.utils.gwt.client.table.paginator.MyPager.MyPagerView;
import es.jota.utils.gwt.client.utils.UtilClient;
import jota.server.dto.FotoDtoDown;

public class FotoDisplayListarImpl extends Composite implements FotoDisplayListar {

	private static FotoDisplayListarUiBinder uiBinder = GWT.create(FotoDisplayListarUiBinder.class);
	interface FotoDisplayListarUiBinder extends UiBinder<Widget, FotoDisplayListarImpl>{}

	@UiField AnchorElement create;

	@UiField(provided=true) CellTable<FotoDtoDown> tabla;
	@UiField MyPagerView paginator;

	private FotoDisplayListar.Presenter presenter;

	@Override
	public void setPresenter( FotoDisplayListar.Presenter Presenter ) {
		this.presenter = Presenter;
	}

	public FotoDisplayListarImpl() {
		tabla = new CellTable<FotoDtoDown>(1);
		initTableColumns();
		initWidget(uiBinder.createAndBindUi(this));
		create.setHref( FotoPlaceCrear.getUrl() );
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
		Column<FotoDtoDown, SafeHtml> fotoColumn = new Column<FotoDtoDown, SafeHtml>( new SafeHtmlCell()) {
			@Override
			public SafeHtml getValue( FotoDtoDown object) {
				return new SafeHtmlBuilder().appendHtmlConstant( "<img src='/getUploadedFoto.spring?id=" + object.getId()  +"'/>" ).toSafeHtml(); 
			}
		};
		Column<FotoDtoDown, SafeHtml> tituloColumn = new Column<FotoDtoDown, SafeHtml>( new SafeHtmlCell()) {
			@Override
			public SafeHtml getValue( FotoDtoDown object) {
				return new SafeHtmlBuilder().appendEscapedLines( object.getTitulo() ).toSafeHtml(); 
			}
		};

		Column<FotoDtoDown, SafeHtml> tagColumn = new Column<FotoDtoDown, SafeHtml>( new SafeHtmlCell()) {
			@Override
			public SafeHtml getValue( FotoDtoDown object) {
				return new SafeHtmlBuilder().appendEscapedLines( object.getTagNombre() ).toSafeHtml(); 
			}
		};

		Column<FotoDtoDown, SafeHtml> viviendaColumn = new Column<FotoDtoDown, SafeHtml>( new SafeHtmlCell()) {
			@Override
			public SafeHtml getValue( FotoDtoDown object) {
				return UtilClient.Cadena.string2SafeHtml( object.getViviendaNombre() ); 
			}
		};

		Column<FotoDtoDown, SafeHtml> ordenColumn = new Column<FotoDtoDown, SafeHtml>( new SafeHtmlCell()) {
			@Override
			public SafeHtml getValue( FotoDtoDown object) {
				return UtilClient.Cadena.string2SafeHtml( UtilClient.Numeros.number2String( object.getOrden() ) ); 
			}
		};

		AccionesCell accionesCell = new AccionesCell(){
			@Override
			protected void doAction( String accionId, String accionValue ) {
				try {
					Long id = Long.parseLong( accionValue );
					switch ( AccionEnum.getEnum( accionId ) ) {
						case BORRAR: presenter.borrar( id );
						break;
					}
				} catch (Exception e) {
				}
			}
		};
		Column<FotoDtoDown, SafeHtml> accionesColumn = new Column<FotoDtoDown, SafeHtml>( accionesCell ) {
			@Override
			public SafeHtml getValue( FotoDtoDown object ) {
				Long id = object.getId();
				SafeHtmlBuilder shb = new SafeHtmlBuilder();
				if ( object.isMia() ) {
					shb.append( UtilClient.TEMPLATES.accionDelegate( AccionEnum.BORRAR.getId().toString(), id.toString(), AccionEnum.BORRAR.getColor().getBtn(), AccionEnum.BORRAR.getIcono().get() ) );
					shb.append( UtilClient.TEMPLATES.accionLink( FotoPlaceEditar.getUrl( id ), ColorEnum.AZUL.getBtn(), IconoEnum.EDITAR.get() ) );
				}
				shb.append( UtilClient.TEMPLATES.accionLink( FotoPlaceVer.getUrl( id ), ColorEnum.VERDE.getBtn(), IconoEnum.VER.get() ) );
				return shb.toSafeHtml();
			}
		};

//		nombreColumn.setSortable(true);

		tabla.addColumn( fotoColumn, "Foto");
		tabla.addColumn( tituloColumn, "Título");
		tabla.addColumn( tagColumn, "Tag");
		tabla.addColumn( viviendaColumn, "Vivienda");
		tabla.addColumn( ordenColumn, "Orden");
		tabla.addColumn( accionesColumn, "Acciones");
	}
	
	@Override
	public CellTable<FotoDtoDown> getTabla() {
		return tabla;
	}

	@Override
	public MyPagerView getPagerDisplay() {
		return paginator;
	}
}
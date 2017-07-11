package es.jota.alquiler.gwt.client.activity.vivienda;

import es.jota.alquiler.gwt.client.display.vivienda.ViviendaDisplayCrear;
import es.jota.alquiler.gwt.client.gin.Gin;
import es.jota.alquiler.gwt.client.gin.GinDisplay;
import es.jota.alquiler.gwt.client.gin.GinService;
import es.jota.alquiler.gwt.client.interfaces.IDoAfterInsert;
import es.jota.alquiler.gwt.client.my.MyAbstractActivity;
import es.jota.alquiler.gwt.client.my.MyAsyncCallback;
import es.jota.alquiler.gwt.client.place.home.HomePlace;
import es.jota.alquiler.gwt.client.place.vivienda.ViviendaPlaceCrear;
import es.jota.alquiler.gwt.client.place.vivienda.ViviendaPlaceEditar;
import jota.server.dto.ViviendaDtoUp;

public class ViviendaActivityCrear extends MyAbstractActivity<ViviendaPlaceCrear> implements ViviendaDisplayCrear.Presenter {
	
	private ViviendaDisplayCrear display;

	public ViviendaActivityCrear() {
		display = GinDisplay.INSTANCE.getViviendaDisplayCrear();
		display.setPresenter( this );
	}

	@Override
	public void start() {
		cleanForm();
		containerWidget.setWidget(display.asWidget());
	}

	private void cleanForm() {
		display.setNombre( null );
		display.setSituacion( null );
		display.setResumen( null );
		display.setCapacidad( null );
	}

	@Override
	public void createYListar() {
		create( new IDoAfterInsert<Long>() {
			@Override
			public void exec( Long idVivienda ) {
				goTo( HomePlace.instance() );
			}
		});
	}

	@Override
	public void createYNuevo() {
		create( new IDoAfterInsert<Long>() {
			@Override
			public void exec( Long idVivienda ) {
				start();
			}
		});
	}

	@Override
	public void create() {
		create( new IDoAfterInsert<Long>() {
			@Override
			public void exec( Long idVivienda ) {
				goTo( ViviendaPlaceEditar.instance( idVivienda, Gin.INSTANCE.getSecurityGwtUtils().getIdUsuario() ) );
			}
		});
	}


	private void create( final IDoAfterInsert<Long> doAfterInsert ) {
		if ( validate() ) {
			GinService.INSTANCE.getViviendaGwtServiceAsync().create( fillObject(), new MyAsyncCallback<Long>( "Vivienda", "Insertando", null, "Insertado" ) {
				@Override
				public void handleOnSuccess( Long idVivienda ) {
					doAfterInsert.exec( idVivienda );
				}
			});
		}
	}

	private boolean validate() {
		boolean dev = display.validate();
		return dev;
	}

	private ViviendaDtoUp fillObject() {
		ViviendaDtoUp vivenda = new ViviendaDtoUp();
		vivenda.setNombre( display.getNombre() );
		vivenda.setSituacion( display.getSituacion() );
		vivenda.setResumen( display.getResumen() );
		vivenda.setCapacidad( display.getCapacidad() );
		return vivenda;
	}
}
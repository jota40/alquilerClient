package es.jota.alquiler.gwt.client.activity.vivienda;

import es.jota.alquiler.gwt.client.display.vivienda.ViviendaDisplayEditar;
import es.jota.alquiler.gwt.client.gin.GinActivity;
import es.jota.alquiler.gwt.client.gin.GinDisplay;
import es.jota.alquiler.gwt.client.gin.GinService;
import es.jota.alquiler.gwt.client.interfaces.IDoAfterUpdate;
import es.jota.alquiler.gwt.client.my.MyAbstractActivity;
import es.jota.alquiler.gwt.client.my.MyAsyncCallback;
import es.jota.alquiler.gwt.client.place.home.HomePlace;
import es.jota.alquiler.gwt.client.place.vivienda.ViviendaPlaceCrear;
import es.jota.alquiler.gwt.client.place.vivienda.ViviendaPlaceEditar;
import jota.server.dto.ViviendaDtoDown;
import jota.server.dto.ViviendaDtoUp;

public class ViviendaActivityEditar extends MyAbstractActivity<ViviendaPlaceEditar> implements ViviendaDisplayEditar.Presenter {

	private ViviendaDisplayEditar display;

	private Long id;

	public ViviendaActivityEditar() {
		display = GinDisplay.INSTANCE.getViviendaDisplayEditar();
		display.setPresenter( this );
	}

	@Override
	public void start() {
		edit();
	}

	private void edit() {
		GinService.INSTANCE.getViviendaGwtServiceAsync().read( place.getId(), new MyAsyncCallback<ViviendaDtoDown>( "", "Leyendo, id = " + place.getId() ) {
			@Override
			public void handleOnSuccess( ViviendaDtoDown vivienda ) {
				if ( vivienda != null ) {
					id = vivienda.getId();
					fillForm( vivienda );
					containerWidget.setWidget(display.asWidget());
				} else {
					//TODO que hacer si no se encuentra la vivienda?
				}
			}
		});
	}

	private void fillForm( ViviendaDtoDown vivenda ) {
		display.setVersion( vivenda.getVersion() );
		display.setNombre( vivenda.getNombre() );
		display.setSituacion( vivenda.getSituacion() );
		display.setResumen( vivenda.getResumen() );
		display.setCapacidad( vivenda.getCapacidad() );

		GinActivity.INSTANCE.getFotoViviendaActivityEditar().setPlace( place ).start( display.getBottom(), globalEventBus );
	}

	@Override
	public void updateYListar() {
		update( new IDoAfterUpdate() {
			@Override
			public void exec() {
				goTo( HomePlace.instance() );
			}
		});
	}

	@Override
	public void updateYNuevo() {
		update( new IDoAfterUpdate() {
			@Override
			public void exec() {
				goTo( ViviendaPlaceCrear.instance() );
			}
		});
	}

	@Override
	public void update() {
		update( new IDoAfterUpdate() {
			@Override
			public void exec() {
				edit();
			}
		});
	}

	private void update( final IDoAfterUpdate doAfterUpdate ) {
		if ( validate() ) {
			GinService.INSTANCE.getViviendaGwtServiceAsync().update( id, fillObject(), new MyAsyncCallback<Void>( "", "Modificando, id = " + place.getId(), null, "Modificado" ) {
				@Override
				public void handleOnSuccess( Void viviendaDto ) {
					doAfterUpdate.exec();
				}
			});
		}
	}

	private boolean validate() {
		boolean dev = display.validate();
		return dev;
	}

	private ViviendaDtoUp fillObject() {
		ViviendaDtoUp vivienda = new ViviendaDtoUp();
		vivienda.setVersion( display.getVersion() );
		vivienda.setNombre( display.getNombre() );
		vivienda.setSituacion( display.getSituacion() );
		vivienda.setResumen( display.getResumen() );
		vivienda.setCapacidad( display.getCapacidad() );
		return vivienda;
	}
}
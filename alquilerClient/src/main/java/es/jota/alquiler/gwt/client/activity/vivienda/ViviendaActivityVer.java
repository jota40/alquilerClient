package es.jota.alquiler.gwt.client.activity.vivienda;

import es.jota.alquiler.gwt.client.display.vivienda.ViviendaDisplayVer;
import es.jota.alquiler.gwt.client.gin.GinActivity;
import es.jota.alquiler.gwt.client.gin.GinDisplay;
import es.jota.alquiler.gwt.client.gin.GinService;
import es.jota.alquiler.gwt.client.my.MyAbstractActivity;
import es.jota.alquiler.gwt.client.my.MyAsyncCallback;
import es.jota.alquiler.gwt.client.place.vivienda.ViviendaPlaceVer;
import jota.server.dto.ViviendaDtoDown;

public class ViviendaActivityVer extends MyAbstractActivity<ViviendaPlaceVer> implements ViviendaDisplayVer.Presenter {
	
	private ViviendaDisplayVer display;
	private MyAbstractActivity<ViviendaPlaceVer> activiy;

	public ViviendaActivityVer() {
		display = GinDisplay.INSTANCE.getViviendaDisplayVer();
		display.setPresenter( this );

//		display.setListarUrl( ViviendaPlaceListar.getUrl() );
//		display.setNuevoUrl( ViviendaPlaceCrear.getUrl() );
	}

	@Override
	public void start() {
		ver();
	}

	public void ver() {
		GinService.INSTANCE.getViviendaGwtServiceAsync().read( place.getId(), new MyAsyncCallback<ViviendaDtoDown>( "", "Leyendo, id = " + place.getId() ) {
			@Override
			public void handleOnSuccess( ViviendaDtoDown viviendaDto ) {
				if ( viviendaDto != null ) {
					fillForm( viviendaDto );
					containerWidget.setWidget(display.asWidget());
				} else {
					//TODO que hacer si no se encuentra la vivienda?
				}
			}
		});
	}

	private void fillForm( ViviendaDtoDown vivenda ) {
//		display.setEditarUrl( ViviendaPlaceEditar.getUrl( vivendaDto.getId(), vivendaDto.getIdUsuario() ) );
		display.setNombre( vivenda.getNombre() );
		display.setResumen( vivenda.getSituacion() );
		display.setSituacion( vivenda.getResumen() );
		display.setCapacidad( vivenda.getCapacidad() );
		
		GinActivity.INSTANCE.getFotoViviendaActivityVer().setPlace( place ).start( display.getBottom(), globalEventBus );
	}
}
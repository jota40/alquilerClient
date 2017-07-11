package es.jota.alquiler.gwt.client.activity.foto;

import es.jota.alquiler.gwt.client.display.foto.FotoDisplayVer;
import es.jota.alquiler.gwt.client.gin.GinDisplay;
import es.jota.alquiler.gwt.client.gin.GinService;
import es.jota.alquiler.gwt.client.my.MyAbstractActivity;
import es.jota.alquiler.gwt.client.my.MyAsyncCallback;
import es.jota.alquiler.gwt.client.place.foto.FotoPlaceVer;
import jota.server.dto.FotoDtoDown;

public class FotoActivityVer extends MyAbstractActivity<FotoPlaceVer> implements FotoDisplayVer.Presenter {
	
	private FotoDisplayVer display;

	public FotoActivityVer() {
		display = GinDisplay.INSTANCE.getFotoDisplayVer();
		display.setPresenter( this );
	}

	@Override
	public void start() {
		ver();
	}

	public void ver() {
		GinService.INSTANCE.getFotoGwtServiceAsync().read( place.getId(), new MyAsyncCallback<FotoDtoDown>( "", "Leyendo, id = " + place.getId() ) {
			@Override
			public void handleOnSuccess( FotoDtoDown foto ) {
				if ( foto != null ) {
					fillForm( foto );
					containerWidget.setWidget(display.asWidget());
				} else {
					//TODO que hacer si no se encuentra la foto?
				}
			}
		});
	}

	private void fillForm( FotoDtoDown foto ) {
		display.setFoto(  "/getUploadedFoto.spring?id=" + foto.getId()  );
		display.setNombre( foto.getNombreOriginal() );
		display.setType( foto.getType() );
		display.setSize( foto.getSize() );
		display.setTitulo( foto.getTitulo() );
		display.setTag( foto.getTagNombre() );
		display.setVivienda( foto.getVivienda().getNombre() );
		display.setOrden( foto.getOrden() );
	}
}
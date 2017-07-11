package es.jota.alquiler.gwt.client.activity.fotoVivienda;

import java.util.List;

import com.google.gwt.user.client.ui.HTMLPanel;

import es.jota.alquiler.gwt.client.display.fotoVivienda.FotoViviendaDisplayVer;
import es.jota.alquiler.gwt.client.gin.GinDisplay;
import es.jota.alquiler.gwt.client.gin.GinService;
import es.jota.alquiler.gwt.client.my.MyAbstractActivity;
import es.jota.alquiler.gwt.client.my.MyAsyncCallback;
import es.jota.alquiler.gwt.client.place.vivienda.ViviendaPlaceVer;
import jota.server.dto.FotoDtoDown;

public class FotoViviendaActivityVer extends MyAbstractActivity<ViviendaPlaceVer> implements FotoViviendaDisplayVer.Presenter {
	
	private HTMLPanel display;

	public FotoViviendaActivityVer() {
		display = new HTMLPanel("");
		display.setStyleName("row");
	}

	@Override
	public void start() {
		display.clear();
		listar();
	}

	public void listar() {
		GinService.INSTANCE.getFotoGwtServiceAsync().findFotosDeMiVivienda( place.getId(), 0, 0, new MyAsyncCallback<List<FotoDtoDown>>( "", "Leyendo, id = " + place.getId() ) {
			@Override
			public void handleOnSuccess( List<FotoDtoDown> fotos ) {
				if ( fotos != null ) {
					fillForm( fotos );
					containerWidget.setWidget( display );
				} else {
					//TODO que hacer si no se encuentra la foto?
				}
			}
		});
	}

	private void fillForm( List<FotoDtoDown> fotos ) {
		for (FotoDtoDown foto : fotos) {
			fillFoto( foto );
		}
	}

	private void fillFoto( FotoDtoDown foto ) {
		FotoViviendaDisplayVer fotoDisplay = GinDisplay.INSTANCE.getFotoViviendaDisplayVer();
		fotoDisplay.setFoto(  "/getUploadedFoto.spring?id=" + foto.getId()  );
		fotoDisplay.setTitulo( foto.getTitulo() );
		fotoDisplay.setPresenter( this );
		display.add( fotoDisplay );
	}
}
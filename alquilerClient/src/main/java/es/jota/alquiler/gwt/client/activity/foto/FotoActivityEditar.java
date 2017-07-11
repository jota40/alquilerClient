package es.jota.alquiler.gwt.client.activity.foto;

import es.jota.alquiler.gwt.client.display.foto.FotoDisplayEditar;
import es.jota.alquiler.gwt.client.gin.GinDisplay;
import es.jota.alquiler.gwt.client.gin.GinService;
import es.jota.alquiler.gwt.client.interfaces.IDoAfterUpdate;
import es.jota.alquiler.gwt.client.my.MyAbstractActivity;
import es.jota.alquiler.gwt.client.my.MyAsyncCallback;
import es.jota.alquiler.gwt.client.place.foto.FotoPlaceCrear;
import es.jota.alquiler.gwt.client.place.foto.FotoPlaceEditar;
import es.jota.alquiler.gwt.client.place.home.HomePlace;
import jota.server.dto.FotoDtoDown;
import jota.server.dto.FotoDtoUp;

public class FotoActivityEditar extends MyAbstractActivity<FotoPlaceEditar> implements FotoDisplayEditar.Presenter {

	private FotoDisplayEditar display;

	private Long id;

	public FotoActivityEditar() {
		display = GinDisplay.INSTANCE.getFotoDisplayEditar();
		display.setPresenter( this );
	}

	@Override
	public void start() {
		edit();
	}

	private void edit() {
		GinService.INSTANCE.getFotoGwtServiceAsync().read( place.getId(), new MyAsyncCallback<FotoDtoDown>( "", "Leyendo, id = " + place.getId() ) {
			@Override
			public void handleOnSuccess( FotoDtoDown fotoDto ) {
				if ( fotoDto != null ) {
					id = fotoDto.getId();
					fillForm( fotoDto );
					containerWidget.setWidget(display.asWidget());
				} else {
					//TODO que hacer si no se encuentra la foto?
				}
			}
		});
	}

	private void fillForm( FotoDtoDown foto ) {
		display.setVersion( foto.getVersion() );
		display.setFoto( "/getUploadedFoto.spring?id=" + foto.getId() );
		display.setNombre( foto.getNombreOriginal() );
		display.setType( foto.getType() );
		display.setSize( foto.getSize() );
		display.setTitulo( foto.getTitulo() );
		display.setTag( foto.getTag() );
		display.setVivienda( foto.getVivienda() );
		display.setOrden( foto.getOrden() );
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
				goTo( FotoPlaceCrear.instance() );
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
			GinService.INSTANCE.getFotoGwtServiceAsync().update( id, fillObject(), display.getIdTag(), display.getIdVivienda(), new MyAsyncCallback<Void>( "", "Modificando, id = " + place.getId(), null, "Modificado" ) {
				@Override
				public void handleOnSuccess( Void fotoDto ) {
					doAfterUpdate.exec();
				}
			});
		}
	}

	private boolean validate() {
		boolean dev = display.validate();
		return dev;
	}

	private FotoDtoUp fillObject() {
		FotoDtoUp foto = new FotoDtoUp();
		foto.setVersion( display.getVersion() );
		foto.setTitulo( display.getTitulo() );
		foto.setOrden( display.getOrden() );
		return foto;
	}
}
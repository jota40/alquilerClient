package es.jota.alquiler.gwt.client.activity.foto;

import es.jota.alquiler.gwt.client.display.foto.FotoDisplayCrear;
import es.jota.alquiler.gwt.client.gin.GinDisplay;
import es.jota.alquiler.gwt.client.gin.GinService;
import es.jota.alquiler.gwt.client.interfaces.IDoAfterInsert;
import es.jota.alquiler.gwt.client.my.MyAbstractActivity;
import es.jota.alquiler.gwt.client.my.MyAsyncCallback;
import es.jota.alquiler.gwt.client.place.foto.FotoPlaceCrear;
import es.jota.alquiler.gwt.client.place.foto.FotoPlaceEditar;
import es.jota.alquiler.gwt.client.place.home.HomePlace;
import jota.server.dto.FotoDtoUp;

public class FotoActivityCrear extends MyAbstractActivity<FotoPlaceCrear> implements FotoDisplayCrear.Presenter {
	
	private FotoDisplayCrear display;

	public FotoActivityCrear() {
		display = GinDisplay.INSTANCE.getFotoDisplayCrear();
		display.setPresenter( this );
	}

	@Override
	public void start() {
		cleanForm();
		containerWidget.setWidget(display.asWidget());
	}

	private void cleanForm() {
		display.cleanFoto();
		display.setTitulo( null );
		display.setTag( null );
		display.setVivienda( null );
		display.setOrden( null );
	}

	@Override
	public void createYListar() {
		create( new IDoAfterInsert<Long>() {
			@Override
			public void exec( Long idFoto ) {
				goTo( HomePlace.instance() );
			}
		});
	}

	@Override
	public void createYNuevo() {
		create( new IDoAfterInsert<Long>() {
			@Override
			public void exec( Long idFoto ) {
				start();
			}
		});
	}

	@Override
	public void create() {
		create( new IDoAfterInsert<Long>() {
			@Override
			public void exec( Long idFoto ) {
				goTo( FotoPlaceEditar.instance( idFoto ) );
			}
		});
	}


	private void create( final IDoAfterInsert<Long> doAfterInsert ) {
		if ( validate() ) {
			GinService.INSTANCE.getFotoGwtServiceAsync().create( fillObject(), display.getIdTag(), display.getIdVivienda(), new MyAsyncCallback<Long>( "Foto", "Insertando", null, "Insertado" ) {
				@Override
				public void handleOnSuccess( Long idFoto ) {
					display.cleanFoto();
					doAfterInsert.exec( idFoto );
				}
			});
		}
	}

	private boolean validate() {
		boolean dev = display.validate();
		return dev;
	}

	private FotoDtoUp fillObject() {
		FotoDtoUp fotoDto = new FotoDtoUp();
		fotoDto.setTitulo( display.getTitulo() );
		fotoDto.setMd5( display.getMd5() );
		fotoDto.setOrden( display.getOrden() );
		return fotoDto;
	}
}
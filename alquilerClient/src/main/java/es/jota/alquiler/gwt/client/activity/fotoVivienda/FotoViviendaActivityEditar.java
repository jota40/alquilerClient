package es.jota.alquiler.gwt.client.activity.fotoVivienda;

import java.util.List;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

import es.jota.alquiler.gwt.client.display.fotoVivienda.FotoViviendaDisplayCrear;
import es.jota.alquiler.gwt.client.display.fotoVivienda.FotoViviendaDisplayEditar;
import es.jota.alquiler.gwt.client.gin.GinDisplay;
import es.jota.alquiler.gwt.client.gin.GinService;
import es.jota.alquiler.gwt.client.my.MyAbstractActivity;
import es.jota.alquiler.gwt.client.my.MyAsyncCallback;
import es.jota.alquiler.gwt.client.place.vivienda.ViviendaPlaceEditar;
import jota.server.dto.FotoDtoDown;
import jota.server.dto.FotoDtoUp;
import jota.server.enumerations.TagEnum;

public class FotoViviendaActivityEditar extends MyAbstractActivity<ViviendaPlaceEditar> implements FotoViviendaDisplayEditar.Presenter, FotoViviendaDisplayCrear.Presenter {

	private HTMLPanel display;

	private Long id;

	public FotoViviendaActivityEditar() {
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
			display.add( fillFoto( foto ) );
		}
		display.add( cleanFoto() );
	}

	private FotoViviendaDisplayEditar fillFoto( FotoDtoDown foto ) {
		FotoViviendaDisplayEditar fotoDisplay = GinDisplay.INSTANCE.getFotoViviendaDisplayEditar();
		fotoDisplay.setVersion( foto.getVersion() );
		fotoDisplay.setId( foto.getId() );
		fotoDisplay.setFoto(  "/getUploadedFoto.spring?id=" + foto.getId() );
		fotoDisplay.setTitulo( foto.getTitulo() );
		fotoDisplay.setOrden( foto.getOrden() );
		fotoDisplay.setPresenter( this );
		return fotoDisplay;
	}

	private FotoViviendaDisplayCrear cleanFoto() {
		FotoViviendaDisplayCrear fotoDisplay = GinDisplay.INSTANCE.getFotoViviendaDisplayCrear();
		fotoDisplay.cleanFoto();
		fotoDisplay.setTitulo( null );
		fotoDisplay.setOrden( null );
		fotoDisplay.setPresenter( this );
		return fotoDisplay;
	}

	@Override
	public void update( final FotoViviendaDisplayEditar fotoDisplay ) {
		if ( fotoDisplay.validate() ) {
			GinService.INSTANCE.getFotoGwtServiceAsync().update( fotoDisplay.getId(), fillObject( fotoDisplay ), TagEnum.UNDEFINED.ordinal(), place.getId(), new MyAsyncCallback<Void>( "", "Modificando, id = " + fotoDisplay.getId(), null, "Modificado" ) {
				@Override
				public void handleOnSuccess( Void fotoDto ) {
					GinService.INSTANCE.getFotoGwtServiceAsync().read( fotoDisplay.getId(), new MyAsyncCallback<FotoDtoDown>( "", "Leyendo, id = " + fotoDisplay.getId() ) {
						@Override
						public void handleOnSuccess( FotoDtoDown foto ) {
							if ( foto != null ) {
								display.addAndReplaceElement(fillFoto( foto ), fotoDisplay.asWidget().getElement());
							} else {
								//TODO que hacer si no se encuentra la foto?
							}
						}
					});
				}
			});
		}
	}

	private FotoDtoUp fillObject( FotoViviendaDisplayEditar display ) {
		FotoDtoUp foto = new FotoDtoUp();
		foto.setVersion( display.getVersion() );
		foto.setTitulo( display.getTitulo() );
		foto.setOrden( display.getOrden() );
		return foto;
	}

	@Override
	public void delete( final FotoViviendaDisplayEditar displayFoto ) {
		GinService.INSTANCE.getFotoGwtServiceAsync().delete( displayFoto.getId(), new MyAsyncCallback<Void>( "Foto", "Borrando, id = " + displayFoto.getId() ) {
			@Override
			public void handleOnSuccess(Void result) {
				display.remove( displayFoto );
			}
		});
	}

	@Override
	public void create( final FotoViviendaDisplayCrear fotoDisplay ) {
		if ( fotoDisplay.validate() ) {
			GinService.INSTANCE.getFotoGwtServiceAsync().create( fillObject( fotoDisplay ), TagEnum.UNDEFINED.ordinal(), place.getId(), new MyAsyncCallback<Long>( "Foto", "Insertando", null, "Insertado" ) {
				@Override
				public void handleOnSuccess( Long idFoto ) {
					GinService.INSTANCE.getFotoGwtServiceAsync().read( idFoto, new MyAsyncCallback<FotoDtoDown>( "", "Leyendo, id = " + idFoto ) {
						@Override
						public void handleOnSuccess( FotoDtoDown foto ) {
							if ( foto != null ) {
								((Widget)fotoDisplay).removeFromParent();
								display.add( fillFoto( foto ) );
								display.add( fotoDisplay );
								cleanFoto();
							} else {
								//TODO que hacer si no se encuentra la foto?
							}
						}
					});
				}
			});
		}
	}

	private FotoDtoUp fillObject( FotoViviendaDisplayCrear display ) {
		FotoDtoUp foto = new FotoDtoUp();
		foto.setMd5( display.getMd5() );
		foto.setTitulo( display.getTitulo() );
		foto.setOrden( display.getOrden() );
		return foto;
	}
}
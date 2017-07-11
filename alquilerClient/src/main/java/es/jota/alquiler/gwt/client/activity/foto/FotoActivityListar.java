package es.jota.alquiler.gwt.client.activity.foto;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import es.jota.alquiler.gwt.client.display.foto.FotoDisplayListar;
import es.jota.alquiler.gwt.client.gin.GinDisplay;
import es.jota.alquiler.gwt.client.gin.GinService;
import es.jota.alquiler.gwt.client.my.MyAbstractActivity;
import es.jota.alquiler.gwt.client.my.MyAsyncCallback;
import es.jota.alquiler.gwt.client.place.foto.FotoPlaceListar;
import es.jota.utils.gwt.client.table.paginator.MyPagerScroller;
import jota.server.dto.FotoDtoDown;

public class FotoActivityListar extends MyAbstractActivity<FotoPlaceListar> implements FotoDisplayListar.Presenter {
	
	private FotoDisplayListar display;
	private MyPagerScroller<FotoDtoDown> pager;
	private boolean canRefresh;

	public FotoActivityListar() {
		canRefresh = false;
		display = GinDisplay.INSTANCE.getFotoDisplayListar();
		display.setPresenter( this );

		pager = new MyPagerScroller<FotoDtoDown>( display.getTabla(), display.getPagerDisplay() ) {

			@Override
			public void loadAsyncData( int start, int size, AsyncCallback<List<FotoDtoDown>> asyncCallback ) {
				GinService.INSTANCE.getFotoGwtServiceAsync().findMisFotos( start, size, asyncCallback );
			}
		};
	}

	@Override
	public void start() {
		if ( canRefresh ) {
			refresh();
		}
		canRefresh = true;
		containerWidget.setWidget(display.asWidget());
	}

	@Override
	public void reset() {
		pager.reset();
	}

	@Override
	public void refresh() {
		pager.refresh();
	}

	@Override
	public void borrar( Long id ) {
		GinService.INSTANCE.getFotoGwtServiceAsync().delete( id, new MyAsyncCallback<Void>( "Foto", "Borrando, id = " + id ) {
			@Override
			public void handleOnSuccess(Void result) {
				refresh();
			}
		});
	}
}
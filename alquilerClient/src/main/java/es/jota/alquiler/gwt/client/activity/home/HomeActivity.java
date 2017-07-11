package es.jota.alquiler.gwt.client.activity.home;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import es.jota.alquiler.gwt.client.display.home.HomeDisplay;
import es.jota.alquiler.gwt.client.gin.GinDisplay;
import es.jota.alquiler.gwt.client.gin.GinService;
import es.jota.alquiler.gwt.client.my.MyAbstractActivity;
import es.jota.alquiler.gwt.client.my.MyAsyncCallback;
import es.jota.alquiler.gwt.client.place.home.HomePlace;
import es.jota.utils.gwt.client.table.paginator.MyPagerScroller;
import jota.server.dto.ViviendaDtoDown;

public class HomeActivity extends MyAbstractActivity<HomePlace> implements HomeDisplay.Presenter {
	
	private HomeDisplay display;
	private MyPagerScroller<ViviendaDtoDown> pager;
	private boolean canRefresh;

	public HomeActivity() {
		canRefresh = false;
		display = GinDisplay.INSTANCE.getHomeDisplay();
		display.setPresenter( this );

		pager = new MyPagerScroller<ViviendaDtoDown>( display.getTabla(), display.getPagerDisplay() ) {
			@Override
			public void loadAsyncData( int start, int size, AsyncCallback<List<ViviendaDtoDown>> asyncCallback ) {
				if ( display.isMisViviendas() ) {
					GinService.INSTANCE.getViviendaGwtServiceAsync().findMisVivendas( start, size, asyncCallback );
				} else {
					GinService.INSTANCE.getViviendaGwtServiceAsync().findAlquilables( start, size, asyncCallback );
				}
			}
		};
	}

	@Override
	public void start() {
		if ( canRefresh ) {
			refresh();
		}
		canRefresh = true;
		display.configure();
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
		GinService.INSTANCE.getViviendaGwtServiceAsync().delete( id, new MyAsyncCallback<Void>( "Vivienda", "Borrando, id = " + id ) {
			@Override
			public void handleOnSuccess(Void result) {
				refresh();
			}
		});
	}
}
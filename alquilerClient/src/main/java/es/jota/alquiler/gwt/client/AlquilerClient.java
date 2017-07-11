package es.jota.alquiler.gwt.client;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.i18n.client.Messages;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

import es.jota.alquiler.gwt.client.gin.Gin;
import es.jota.alquiler.gwt.client.gin.GinService;
import es.jota.alquiler.gwt.client.mapper.AppPlaceHistoryMapper;
import es.jota.alquiler.gwt.client.mapper.AsideActivityMapper;
import es.jota.alquiler.gwt.client.mapper.BreadcrumbActivityMapper;
import es.jota.alquiler.gwt.client.mapper.LogActivityMapper;
import es.jota.alquiler.gwt.client.mapper.LoginActivityMapper;
import es.jota.alquiler.gwt.client.mapper.MainActivityMapper;
import es.jota.alquiler.gwt.client.my.MyAsyncCallback;
import es.jota.alquiler.gwt.client.place.home.HomePlace;
import es.jota.alquiler.gwt.shared.SecurityGwtUtils;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class AlquilerClient implements EntryPoint {

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final Messages messages = GWT.create(Messages.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		GinService.INSTANCE.getUsuarioGwtServiceAsync().getSecurityGwtUtils( new MyAsyncCallback<SecurityGwtUtils>("","") {
			@Override
			public void handleOnSuccess( SecurityGwtUtils result ) {
				Gin.INSTANCE.getSecurityGwtUtils().update( result );
				init();
			}
		});
	}

	private void init() {
		SimplePanel login = new SimplePanel();
		login.setStyleName("navbar-form navbar-right");
		RootPanel.get("top").add(login);

		SimplePanel breadcrumb = new SimplePanel();
		breadcrumb.setStyleName("col-md-12");
		RootPanel.get("breadcrumbDiv").add(breadcrumb);

		SimplePanel aside = new SimplePanel();
		aside.setStyleName("col-md-2");
		RootPanel.get("row").add(aside);

		SimplePanel main = new SimplePanel();
		main.setStyleName("col-md-10");
		RootPanel.get("row").add(main);

		SimplePanel logs = new SimplePanel();
		RootPanel.get("log").add(logs);

		EventBus eventBus = Gin.INSTANCE.getEventBus();
		PlaceController placeController = Gin.INSTANCE.getPlaceController();

		// login activity manager
		ActivityMapper loginActivityMapper = new LoginActivityMapper();
		ActivityManager loginActivityManager = new ActivityManager( loginActivityMapper, eventBus );
		loginActivityManager.setDisplay( login );

		// breadcrumb activity manager
		ActivityMapper breadcrumbActivityMapper = new BreadcrumbActivityMapper();
		ActivityManager breadcrumbActivityManager = new ActivityManager( breadcrumbActivityMapper, eventBus );
		breadcrumbActivityManager.setDisplay( breadcrumb );

		// aside activity manager
		ActivityMapper asideActivityMapper = new AsideActivityMapper();
		ActivityManager asideActivityManager = new ActivityManager( asideActivityMapper, eventBus );
		asideActivityManager.setDisplay( aside );

		// main activity manager
		ActivityMapper mainActivityMapper = new MainActivityMapper();
		ActivityManager mainActivityManager = new ActivityManager( mainActivityMapper, eventBus );
		mainActivityManager.setDisplay( main );
		
		// Start PlaceHistoryHandler with our PlaceHistoryMapper
		AppPlaceHistoryMapper historyMapper= GWT.create(AppPlaceHistoryMapper.class);
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
		historyHandler.register(placeController, eventBus, HomePlace.instance());

		// log activity manager
		ActivityMapper logActivityMapper = new LogActivityMapper();
		ActivityManager logActivityManager = new ActivityManager(logActivityMapper, eventBus);
		logActivityManager.setDisplay(logs);

		// Goes to place represented on URL or default place
        historyHandler.handleCurrentHistory();
	}
}

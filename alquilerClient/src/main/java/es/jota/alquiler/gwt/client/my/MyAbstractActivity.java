package es.jota.alquiler.gwt.client.my;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import es.jota.alquiler.gwt.client.event.SecurityGwtUtilsUpdatedEvent;
import es.jota.alquiler.gwt.client.gin.Gin;

public abstract class MyAbstractActivity<P> extends AbstractActivity {
	protected EventBus globalEventBus = Gin.INSTANCE.getEventBus();
	private PlaceController placeController = Gin.INSTANCE.getPlaceController();
	protected P place;
	protected AcceptsOneWidget containerWidget;

	protected void goTo( Place place ){
		placeController.goTo( place );
	}
	public MyAbstractActivity<P> setPlace(P place) {
		this.place = place;
		return this;
	}

	@Override
	public void start( AcceptsOneWidget containerWidget, EventBus eventBus ) {
		this.containerWidget = containerWidget;
		SecurityGwtUtilsUpdatedEvent.register( Gin.INSTANCE.getEventBus(), new SecurityGwtUtilsUpdatedEvent.Handler() {
			@Override
			public void securityGwtUtilsUpdated( int action ) {
				onSecurityGwtUtilsUpdated( action );
			}
		});
		start();
	}

	/**
	 * Metodo que se invoca cuando se actuliza el securityGwtUtils, y provocar que reinicien los activities
	 * @param action LOGIN = 0, LOGOUT = 1;
	 */
	protected void onSecurityGwtUtilsUpdated( int action ) {
		start();
	}

	public abstract void start();
}
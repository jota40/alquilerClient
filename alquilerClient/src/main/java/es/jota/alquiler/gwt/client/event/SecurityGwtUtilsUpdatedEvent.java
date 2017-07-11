package es.jota.alquiler.gwt.client.event;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

public class SecurityGwtUtilsUpdatedEvent extends GwtEvent<SecurityGwtUtilsUpdatedEvent.Handler> {

	private static final Type<Handler> TYPE = new Type<Handler>();

	public interface Handler extends EventHandler {
		void securityGwtUtilsUpdated( int action);
	}
	
	public static final int LOGIN = 0;
	public static final int LOGOUT = 1;

	private int action;

	private SecurityGwtUtilsUpdatedEvent( int action ) {
		this.action = action;
	}

	@Override
	public Type<Handler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(Handler handler) {
		handler.securityGwtUtilsUpdated( action );
	}

	public static void throwSecurityGwtUtilsUpdated( EventBus bus, int action ) {
		bus.fireEvent( new SecurityGwtUtilsUpdatedEvent( action ) );
	}

	public static HandlerRegistration register( EventBus bus, Handler handler) {
		return bus.addHandler(TYPE, handler);
	}
}
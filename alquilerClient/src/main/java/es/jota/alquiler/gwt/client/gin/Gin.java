package es.jota.alquiler.gwt.client.gin;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceController;

import es.jota.alquiler.gwt.client.gin.module.GinModule;
import es.jota.alquiler.gwt.shared.SecurityGwtUtils;

@GinModules(GinModule.class)
public interface Gin extends Ginjector {
	public static final Gin INSTANCE = GWT.create(Gin.class);

	public SecurityGwtUtils getSecurityGwtUtils();
    public EventBus getEventBus();
    public PlaceController getPlaceController();
}
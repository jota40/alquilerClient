package es.jota.alquiler.gwt.client.gin.module;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import es.jota.alquiler.gwt.shared.SecurityGwtUtils;

public class GinModule extends AbstractGinModule {
	@Override
	protected void configure() {
		bind(SecurityGwtUtils.class).in(Singleton.class);
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
		bind(PlaceController.class).toProvider(PlaceProvider.class).in(Singleton.class);
	}

	static class PlaceProvider implements Provider<PlaceController> {
		@Inject
		EventBus eventBus;
		@Override
		public PlaceController get() {
			return new PlaceController(eventBus);
		}
	}
}
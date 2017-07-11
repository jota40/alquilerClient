package es.jota.alquiler.gwt.client.gin.module;

import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import es.jota.alquiler.gwt.client.service.FotoGwtService;
import es.jota.alquiler.gwt.client.service.FotoGwtServiceAsync;
import es.jota.alquiler.gwt.client.service.UsuarioGwtService;
import es.jota.alquiler.gwt.client.service.UsuarioGwtServiceAsync;
import es.jota.alquiler.gwt.client.service.ViviendaGwtService;
import es.jota.alquiler.gwt.client.service.ViviendaGwtServiceAsync;

public class GinServiceModule extends AbstractGinModule {
	@Override
	protected void configure() {
		bind(ViviendaGwtServiceAsync.class).toProvider( ViviendaGwtServiceAsyncProvider.class).in(Singleton.class);
		bind(UsuarioGwtServiceAsync.class).toProvider( UsuarioGwtServiceAsyncProvider.class).in(Singleton.class);
		bind(FotoGwtServiceAsync.class).toProvider( FotoGwtServiceAsyncProvider.class).in(Singleton.class);
	}

	static class ViviendaGwtServiceAsyncProvider implements Provider<ViviendaGwtServiceAsync> {
		@Override
		public ViviendaGwtServiceAsync get() {
			return GWT.create(ViviendaGwtService.class);
		}
	}

	static class UsuarioGwtServiceAsyncProvider implements Provider<UsuarioGwtServiceAsync> {
		@Override
		public UsuarioGwtServiceAsync get() {
			return GWT.create(UsuarioGwtService.class);
		}
	}

	static class FotoGwtServiceAsyncProvider implements Provider<FotoGwtServiceAsync> {
		@Override
		public FotoGwtServiceAsync get() {
			return GWT.create(FotoGwtService.class);
		}
	}
}
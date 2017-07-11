package es.jota.alquiler.gwt.client.gin;

import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

import es.jota.alquiler.gwt.client.gin.module.GinServiceModule;
import es.jota.alquiler.gwt.client.service.FotoGwtServiceAsync;
import es.jota.alquiler.gwt.client.service.UsuarioGwtServiceAsync;
import es.jota.alquiler.gwt.client.service.ViviendaGwtServiceAsync;

@GinModules(GinServiceModule.class)
public interface GinService extends Ginjector {
	public static final GinService INSTANCE = GWT.create(GinService.class);

	public ViviendaGwtServiceAsync getViviendaGwtServiceAsync();
	public UsuarioGwtServiceAsync getUsuarioGwtServiceAsync();
	public FotoGwtServiceAsync getFotoGwtServiceAsync();
}
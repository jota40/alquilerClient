package es.jota.alquiler.gwt.client.gin.module;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

import es.jota.alquiler.gwt.client.display.aside.AsideDisplay;
import es.jota.alquiler.gwt.client.display.aside.AsideDisplayImpl;
import es.jota.alquiler.gwt.client.display.breadcrumb.BreadcrumbDisplay;
import es.jota.alquiler.gwt.client.display.breadcrumb.BreadcrumbDisplayImpl;
import es.jota.alquiler.gwt.client.display.foto.FotoDisplayCrear;
import es.jota.alquiler.gwt.client.display.foto.FotoDisplayCrearImpl;
import es.jota.alquiler.gwt.client.display.foto.FotoDisplayEditar;
import es.jota.alquiler.gwt.client.display.foto.FotoDisplayEditarImpl;
import es.jota.alquiler.gwt.client.display.foto.FotoDisplayListar;
import es.jota.alquiler.gwt.client.display.foto.FotoDisplayListarImpl;
import es.jota.alquiler.gwt.client.display.foto.FotoDisplayVer;
import es.jota.alquiler.gwt.client.display.foto.FotoDisplayVerImpl;
import es.jota.alquiler.gwt.client.display.fotoVivienda.FotoViviendaDisplayCrear;
import es.jota.alquiler.gwt.client.display.fotoVivienda.FotoViviendaDisplayCrearImpl;
import es.jota.alquiler.gwt.client.display.fotoVivienda.FotoViviendaDisplayEditar;
import es.jota.alquiler.gwt.client.display.fotoVivienda.FotoViviendaDisplayEditarImpl;
import es.jota.alquiler.gwt.client.display.fotoVivienda.FotoViviendaDisplayVer;
import es.jota.alquiler.gwt.client.display.fotoVivienda.FotoViviendaDisplayVerImpl;
import es.jota.alquiler.gwt.client.display.home.HomeDisplay;
import es.jota.alquiler.gwt.client.display.home.HomeDisplayImpl;
import es.jota.alquiler.gwt.client.display.login.LoginDisplay;
import es.jota.alquiler.gwt.client.display.login.LoginDisplayImpl;
import es.jota.alquiler.gwt.client.display.login.LogoutDisplay;
import es.jota.alquiler.gwt.client.display.login.LogoutDisplayImpl;
import es.jota.alquiler.gwt.client.display.logs.LogDisplay;
import es.jota.alquiler.gwt.client.display.logs.LogDisplayImpl;
import es.jota.alquiler.gwt.client.display.security.SecurityDisplay;
import es.jota.alquiler.gwt.client.display.security.SecurityDisplayImpl;
import es.jota.alquiler.gwt.client.display.vivienda.ViviendaDisplayCrear;
import es.jota.alquiler.gwt.client.display.vivienda.ViviendaDisplayCrearImpl;
import es.jota.alquiler.gwt.client.display.vivienda.ViviendaDisplayEditar;
import es.jota.alquiler.gwt.client.display.vivienda.ViviendaDisplayEditarImpl;
import es.jota.alquiler.gwt.client.display.vivienda.ViviendaDisplayVer;
import es.jota.alquiler.gwt.client.display.vivienda.ViviendaDisplayVerImpl;

public class GinDisplayModule extends AbstractGinModule {
	@Override
	protected void configure() {
		bind(HomeDisplay.class).to(HomeDisplayImpl.class).in(Singleton.class);
		bind(LoginDisplay.class).to(LoginDisplayImpl.class).in(Singleton.class);
		bind(LogoutDisplay.class).to(LogoutDisplayImpl.class).in(Singleton.class);

		bind(ViviendaDisplayCrear.class).to(ViviendaDisplayCrearImpl.class).in(Singleton.class);
		bind(ViviendaDisplayEditar.class).to(ViviendaDisplayEditarImpl.class).in(Singleton.class);
		bind(ViviendaDisplayVer.class).to(ViviendaDisplayVerImpl.class).in(Singleton.class);

		bind(BreadcrumbDisplay.class).to(BreadcrumbDisplayImpl.class).in(Singleton.class);
		
		bind(SecurityDisplay.class).to(SecurityDisplayImpl.class).in(Singleton.class);
		
		bind(AsideDisplay.class).to(AsideDisplayImpl.class).in(Singleton.class);
		
		bind(FotoDisplayCrear.class).to(FotoDisplayCrearImpl.class).in(Singleton.class);
		bind(FotoDisplayEditar.class).to(FotoDisplayEditarImpl.class).in(Singleton.class);
		bind(FotoDisplayListar.class).to(FotoDisplayListarImpl.class).in(Singleton.class);
		bind(FotoDisplayVer.class).to(FotoDisplayVerImpl.class).in(Singleton.class);
		
		bind(LogDisplay.class).to(LogDisplayImpl.class).in(Singleton.class);

		bind(FotoViviendaDisplayCrear.class).to(FotoViviendaDisplayCrearImpl.class).in(Singleton.class);

		//NO Singletons
		bind(FotoViviendaDisplayVer.class).to(FotoViviendaDisplayVerImpl.class);
		bind(FotoViviendaDisplayEditar.class).to(FotoViviendaDisplayEditarImpl.class);
		
	}
}
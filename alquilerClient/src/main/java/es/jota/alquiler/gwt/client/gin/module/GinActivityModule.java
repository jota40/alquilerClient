package es.jota.alquiler.gwt.client.gin.module;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;

import es.jota.alquiler.gwt.client.activity.aside.AsideActivity;
import es.jota.alquiler.gwt.client.activity.breadcrumb.BreadcrumbsActivity;
import es.jota.alquiler.gwt.client.activity.foto.FotoActivityCrear;
import es.jota.alquiler.gwt.client.activity.foto.FotoActivityEditar;
import es.jota.alquiler.gwt.client.activity.foto.FotoActivityListar;
import es.jota.alquiler.gwt.client.activity.foto.FotoActivityVer;
import es.jota.alquiler.gwt.client.activity.fotoVivienda.FotoViviendaActivityEditar;
import es.jota.alquiler.gwt.client.activity.fotoVivienda.FotoViviendaActivityVer;
import es.jota.alquiler.gwt.client.activity.home.HomeActivity;
import es.jota.alquiler.gwt.client.activity.login.LoginActivity;
import es.jota.alquiler.gwt.client.activity.logs.LogActivity;
import es.jota.alquiler.gwt.client.activity.security.SecurityActivity;
import es.jota.alquiler.gwt.client.activity.vivienda.ViviendaActivityCrear;
import es.jota.alquiler.gwt.client.activity.vivienda.ViviendaActivityEditar;
import es.jota.alquiler.gwt.client.activity.vivienda.ViviendaActivityVer;

public class GinActivityModule extends AbstractGinModule {
	@Override
	protected void configure() {
		bind(HomeActivity.class).in(Singleton.class);
		bind(LoginActivity.class).in(Singleton.class);

		bind(ViviendaActivityCrear.class).in(Singleton.class);
		bind(ViviendaActivityEditar.class).in(Singleton.class);
		bind(ViviendaActivityVer.class).in(Singleton.class);

		bind(SecurityActivity.class).in(Singleton.class);

		bind(FotoActivityCrear.class).in(Singleton.class);
		bind(FotoActivityEditar.class).in(Singleton.class);
		bind(FotoActivityListar.class).in(Singleton.class);
		bind(FotoActivityVer.class).in(Singleton.class);

		bind(LogActivity.class).in(Singleton.class);

		bind(FotoViviendaActivityVer.class).in(Singleton.class);
		bind(FotoViviendaActivityEditar.class).in(Singleton.class);

		//NO Singletons
		bind(BreadcrumbsActivity.class);

		bind(AsideActivity.class);
	}
}
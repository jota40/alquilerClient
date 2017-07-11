package es.jota.alquiler.gwt.client.gin;

import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

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
import es.jota.alquiler.gwt.client.gin.module.GinActivityModule;

@GinModules(GinActivityModule.class)
public interface GinActivity extends Ginjector {
	public static final GinActivity INSTANCE = GWT.create(GinActivity.class);

	public HomeActivity getHomeActivity();
	public LoginActivity getLoginActivity();

	public ViviendaActivityCrear getViviendaActivityCrear();
	public ViviendaActivityEditar getViviendaActivityEditar();
	public ViviendaActivityVer getViviendaActivityVer();

	public BreadcrumbsActivity getBreadcrumbsActivity();

	public SecurityActivity getSecurityActivity();

	public AsideActivity getAsideActivity();

	public FotoActivityCrear getFotoActivityCrear();
	public FotoActivityEditar getFotoActivityEditar();
	public FotoActivityListar getFotoActivityListar();
	public FotoActivityVer getFotoActivityVer();

	public LogActivity getLogsActivity();

	public FotoViviendaActivityVer getFotoViviendaActivityVer();
	public FotoViviendaActivityEditar getFotoViviendaActivityEditar();
}
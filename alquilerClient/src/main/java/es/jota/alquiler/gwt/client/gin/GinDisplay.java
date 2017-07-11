package es.jota.alquiler.gwt.client.gin;

import com.google.gwt.core.client.GWT;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

import es.jota.alquiler.gwt.client.display.aside.AsideDisplay;
import es.jota.alquiler.gwt.client.display.breadcrumb.BreadcrumbDisplay;
import es.jota.alquiler.gwt.client.display.foto.FotoDisplayCrear;
import es.jota.alquiler.gwt.client.display.foto.FotoDisplayEditar;
import es.jota.alquiler.gwt.client.display.foto.FotoDisplayListar;
import es.jota.alquiler.gwt.client.display.foto.FotoDisplayVer;
import es.jota.alquiler.gwt.client.display.fotoVivienda.FotoViviendaDisplayCrear;
import es.jota.alquiler.gwt.client.display.fotoVivienda.FotoViviendaDisplayEditar;
import es.jota.alquiler.gwt.client.display.fotoVivienda.FotoViviendaDisplayVer;
import es.jota.alquiler.gwt.client.display.home.HomeDisplay;
import es.jota.alquiler.gwt.client.display.login.LoginDisplay;
import es.jota.alquiler.gwt.client.display.login.LogoutDisplay;
import es.jota.alquiler.gwt.client.display.logs.LogDisplay;
import es.jota.alquiler.gwt.client.display.security.SecurityDisplay;
import es.jota.alquiler.gwt.client.display.vivienda.ViviendaDisplayCrear;
import es.jota.alquiler.gwt.client.display.vivienda.ViviendaDisplayEditar;
import es.jota.alquiler.gwt.client.display.vivienda.ViviendaDisplayVer;
import es.jota.alquiler.gwt.client.gin.module.GinDisplayModule;

@GinModules(GinDisplayModule.class)
public interface GinDisplay extends Ginjector {
	public static final GinDisplay INSTANCE = GWT.create(GinDisplay.class);

    public HomeDisplay getHomeDisplay();
	public LoginDisplay getLoginDisplay();
	public LogoutDisplay getLogoutDisplay();
	
	public ViviendaDisplayCrear getViviendaDisplayCrear();
	public ViviendaDisplayEditar getViviendaDisplayEditar();
	public ViviendaDisplayVer getViviendaDisplayVer();

	public BreadcrumbDisplay getBreadcrumbDisplay();
	
	public SecurityDisplay getSecurityDisplay();

	public AsideDisplay getAsideDisplay();

	public FotoDisplayCrear getFotoDisplayCrear();
	public FotoDisplayEditar getFotoDisplayEditar();
	public FotoDisplayListar  getFotoDisplayListar();
	public FotoDisplayVer getFotoDisplayVer();

	public LogDisplay getLogView();

	public FotoViviendaDisplayVer getFotoViviendaDisplayVer();
	public FotoViviendaDisplayEditar getFotoViviendaDisplayEditar();
	public FotoViviendaDisplayCrear getFotoViviendaDisplayCrear();
}
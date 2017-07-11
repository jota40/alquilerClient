package es.jota.alquiler.gwt.client.display.aside;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

import es.jota.alquiler.gwt.client.gin.Gin;
import es.jota.alquiler.gwt.client.place.foto.FotoPlaceListar;
import es.jota.alquiler.gwt.client.place.home.HomePlace;
import es.jota.alquiler.gwt.client.place.vivienda.ViviendaPlace;
import es.jota.utils.gwt.client.my.MyPlace;
import es.jota.utils.gwt.client.utils.UtilClient;
import es.jota.utils.gwt.client.widget.display.NavItem;

public class AsideDisplayImpl extends Composite implements AsideDisplay {

	private static AsideDisplayUiBinder uiBinder = GWT.create(AsideDisplayUiBinder.class);
	interface AsideDisplayUiBinder extends UiBinder<Widget, AsideDisplayImpl>{}

	@UiField HTMLPanel menu;
	
	@UiField NavItem home;

	@UiField NavItem foto;

	private AsideDisplay.Presenter presenter;

	@Override
	public void setPresenter( AsideDisplay.Presenter Presenter ) {
		this.presenter = Presenter;
	}

	public AsideDisplayImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		menu.getElement().setId("menu");
		home.setHref( HomePlace.getUrl() );

		foto.setHref( FotoPlaceListar.getUrl() );
	}

	@Override
	public void checkPlace( final MyPlace place ) {
		for (Widget widget : menu) {
			((NavItem)widget).checkActive(place);
		}
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
		    @Override
		    public void execute() {
		    	configureJS();
	    		UtilClient.JS.show( "always", true );
	    		if ( place instanceof ViviendaPlace ) {
	    			UtilClient.JS.show( ViviendaPlace.class.getSimpleName(), true );
	    		} else {
		    		UtilClient.JS.show( Gin.INSTANCE.getSecurityGwtUtils().getRol().name(), true );
		    	}
			}
		});
	}

	private static native void configureJS() /*-{
		$wnd.jQuery( "#menu > li ").hide()
	}-*/;

}
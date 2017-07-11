package es.jota.alquiler.gwt.client.activity.logs;

import es.jota.alquiler.gwt.client.display.logs.AlertDisplay;
import es.jota.alquiler.gwt.client.display.logs.AlertDisplayImpl;
import es.jota.alquiler.gwt.client.display.logs.LogDisplay;
import es.jota.alquiler.gwt.client.gin.GinDisplay;
import es.jota.alquiler.gwt.client.my.MyAbstractActivity;
import es.jota.utils.gwt.client.my.MyPlace;

public class LogActivity extends MyAbstractActivity<MyPlace> implements LogDisplay.Presenter {

	private LogDisplay display = GinDisplay.INSTANCE.getLogView();

	public LogActivity() {
	}

	@Override
	public void start() {
		containerWidget.setWidget(display.asWidget());
	}

	public AlertDisplay crearAlert( String titulo, String texto ) {
		AlertDisplay alert = new AlertDisplayImpl( titulo, texto );
		display.getPanel().add( alert );
		return alert;
	}
}
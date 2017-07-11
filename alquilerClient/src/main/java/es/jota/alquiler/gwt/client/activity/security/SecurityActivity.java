package es.jota.alquiler.gwt.client.activity.security;

import com.google.gwt.user.client.Timer;

import es.jota.alquiler.gwt.client.gin.GinDisplay;
import es.jota.alquiler.gwt.client.my.MyAbstractActivity;
import es.jota.utils.gwt.client.my.MyPlace;

public class SecurityActivity extends MyAbstractActivity<MyPlace> {

	public SecurityActivity() {
	}

	@Override
	public void start() {
		containerWidget.setWidget( GinDisplay.INSTANCE.getSecurityDisplay() );
		Timer t = new Timer() {
			@Override
			public void run() {
				goTo(place);
			}
		};
		t.schedule(500);
	}
}
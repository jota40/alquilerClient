package es.jota.alquiler.gwt.client.activity.aside;

import es.jota.alquiler.gwt.client.display.aside.AsideDisplay;
import es.jota.alquiler.gwt.client.gin.GinDisplay;
import es.jota.alquiler.gwt.client.my.MyAbstractActivity;
import es.jota.utils.gwt.client.my.MyPlace;

public class AsideActivity extends MyAbstractActivity<MyPlace> implements AsideDisplay.Presenter {
	
	private AsideDisplay display = GinDisplay.INSTANCE.getAsideDisplay();

	public AsideActivity() {
		display.setPresenter( this );
	}

	@Override
	public void start() {
		display.checkPlace( place );
		containerWidget.setWidget(display.asWidget());
	}
}
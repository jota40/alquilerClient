package es.jota.alquiler.gwt.client.activity.breadcrumb;

import es.jota.alquiler.gwt.client.display.breadcrumb.BreadcrumbDisplay;
import es.jota.alquiler.gwt.client.gin.GinDisplay;
import es.jota.alquiler.gwt.client.my.MyAbstractActivity;
import es.jota.utils.gwt.client.my.MyPlace;

public class BreadcrumbsActivity extends MyAbstractActivity<MyPlace> implements BreadcrumbDisplay.Presenter {
	
	private BreadcrumbDisplay display = GinDisplay.INSTANCE.getBreadcrumbDisplay();
	
	public BreadcrumbsActivity() {
		display.setPresenter( this );
	/*	globalEventBus.addHandler(PlaceChangeEvent.TYPE, new PlaceChangeEvent.Handler() {
			@Override
			public void onPlaceChange(PlaceChangeEvent event) {
				place = event.getNewPlace();
				fillForm();
			}
		});*/
	}

	@Override
	public void start() {
		containerWidget.setWidget( display.asWidget() );
		fillForm();
	}

	private void fillForm() {
		display.setBreadcrumbs( place.getBreadcrumbs() );
	}
}
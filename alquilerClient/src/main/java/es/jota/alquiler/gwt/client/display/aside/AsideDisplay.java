package es.jota.alquiler.gwt.client.display.aside;

import com.google.gwt.user.client.ui.IsWidget;

import es.jota.utils.gwt.client.my.MyPlace;

public interface AsideDisplay extends IsWidget {
	void setPresenter( AsideDisplay.Presenter Presenter );
	public interface Presenter {
	}
	void checkPlace( MyPlace place );
}
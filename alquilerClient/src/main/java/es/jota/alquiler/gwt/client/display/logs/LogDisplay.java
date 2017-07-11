package es.jota.alquiler.gwt.client.display.logs;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;

public interface LogDisplay extends IsWidget {
	void setPresenter(Presenter presenter);
	
	public interface Presenter {
	}

	HTMLPanel getPanel();
	void showBackground();
	void hideBackground();
}

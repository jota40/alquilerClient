package es.jota.alquiler.gwt.client.display.logs;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class LogDisplayImpl extends Composite implements LogDisplay {

	private static LogDisplayUiBinder uiBinder = GWT.create(LogDisplayUiBinder.class);
	interface LogDisplayUiBinder extends UiBinder<Widget, LogDisplayImpl> {}
	
	@UiField HTMLPanel panel;
	
	private Presenter presenter;

	public LogDisplayImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		panel.getElement().setAttribute("style", "top:70px; left: 10px; padding: 0;");
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
	
	@Override
	public HTMLPanel getPanel() {
		return panel;
	}

	@Override
	public void showBackground() {
		panel.getElement().setAttribute("style", "top:70px; left: 10px; padding: 10px 5px;background-color: rgba(200, 200, 200, 0.99);");
	}

	@Override
	public void hideBackground() {
		animateBackground( panel.getElement(), "rgba(200, 200, 200, 0)", 10000 );
	}

	public native void animateBackground( Element element, String color, int duration ) /*-{
		$wnd.jQuery(element).animate({ backgroundColor: color }, 9000 )
	}-*/;
}
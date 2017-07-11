package es.jota.alquiler.gwt.client.display.security;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class SecurityDisplayImpl extends Composite implements SecurityDisplay {

	private static SecurityDisplayUiBinder uiBinder = GWT.create(SecurityDisplayUiBinder.class);
	interface SecurityDisplayUiBinder extends UiBinder<Widget, SecurityDisplayImpl>{}
	
	public SecurityDisplayImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}
}
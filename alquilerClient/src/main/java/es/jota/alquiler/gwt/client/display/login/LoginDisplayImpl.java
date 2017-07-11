package es.jota.alquiler.gwt.client.display.login;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class LoginDisplayImpl extends Composite implements LoginDisplay {

	private static LoginDisplayUiBinder uiBinder = GWT.create(LoginDisplayUiBinder.class);
	interface LoginDisplayUiBinder extends UiBinder<Widget, LoginDisplayImpl>{}

	@UiField SpanElement errorMsg;
	@UiField InputElement login;
	@UiField InputElement pass;

	private LoginDisplay.Presenter presenter;

	@Override
	public void setPresenter( LoginDisplay.Presenter Presenter ) {
		this.presenter = Presenter;
	}

	public LoginDisplayImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public String getLogin() {
		return login.getValue();
	}
	@Override
	public void setLogin( String login ) {
		this.login.setValue( login );
	}

	@Override
	public String getPass() {
		return pass.getValue();
	}
	@Override
	public void setPass( String pass ) {
		this.pass.setValue( pass );
	}

	@UiHandler("conectar")
	public void concetar(ClickEvent event) {
		presenter.login();;
	}

	@Override
	public void showError() {
		errorMsg.removeClassName("hide");
	}

	@Override
	public void hideError() {
		errorMsg.addClassName("hide");
	}

	@Override
	public void focus() {
		login.focus();
	}
}
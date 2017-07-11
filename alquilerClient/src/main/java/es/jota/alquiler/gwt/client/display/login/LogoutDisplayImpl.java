package es.jota.alquiler.gwt.client.display.login;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import jota.server.enumerations.RolEnum;

public class LogoutDisplayImpl extends Composite implements LogoutDisplay {

	private static LogoutDisplayUiBinder uiBinder = GWT.create(LogoutDisplayUiBinder.class);
	interface LogoutDisplayUiBinder extends UiBinder<Widget, LogoutDisplayImpl>{}

	@UiField SpanElement errorMsg;
	@UiField AnchorElement usuario;
	@UiField Element rol;

	private LogoutDisplay.Presenter presenter;

	@Override
	public void setPresenter( LogoutDisplay.Presenter Presenter ) {
		this.presenter = Presenter;
	}

	public LogoutDisplayImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiHandler("desconectar")
	public void concetar(ClickEvent event) {
		presenter.logout();
	}

	@Override
	public void setUsuario( String usuario ) {
		this.usuario.setInnerHTML( usuario );
	}

	@Override
	public void setRol( RolEnum rol ) {
		this.rol.setInnerHTML( rol == null ? "" : rol.name() );
	}

	@Override
	public void showError() {
		errorMsg.removeClassName("hide");
	}

	@Override
	public void hideError() {
		errorMsg.addClassName("hide");
	}
}
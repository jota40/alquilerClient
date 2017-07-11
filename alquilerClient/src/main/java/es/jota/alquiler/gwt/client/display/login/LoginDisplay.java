package es.jota.alquiler.gwt.client.display.login;

import com.google.gwt.user.client.ui.IsWidget;

public interface LoginDisplay extends IsWidget {
	void setPresenter( LoginDisplay.Presenter Presenter );
	public interface Presenter {
		void login();
	}
	String getLogin();
	String getPass();

	void setLogin( String login );
	void setPass( String pass );

	void showError();
	void hideError();

	void focus();
}
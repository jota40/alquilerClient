package es.jota.alquiler.gwt.client.display.login;

import com.google.gwt.user.client.ui.IsWidget;

import jota.server.enumerations.RolEnum;

public interface LogoutDisplay extends IsWidget {
	void setPresenter( LogoutDisplay.Presenter Presenter );
	public interface Presenter {
		void logout();
	}
	void setUsuario( String usuario );
	void setRol( RolEnum rol );

	void showError();
	void hideError();

}
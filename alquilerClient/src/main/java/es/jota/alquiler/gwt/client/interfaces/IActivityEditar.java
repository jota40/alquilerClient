package es.jota.alquiler.gwt.client.interfaces;

import com.google.gwt.user.client.ui.IsWidget;

public interface IActivityEditar extends IsWidget {
	public interface Presenter {
		void updateYListar();
		void updateYNuevo();
		void update();
	}

	void setListarUrl( String url );
	void setNuevoUrl( String url );
	void setVerUrl( String url);
}
package es.jota.alquiler.gwt.client.interfaces;

import com.google.gwt.user.client.ui.IsWidget;

public interface IActivityCrear extends IsWidget {
	public interface Presenter {
		void createYListar();
		void createYNuevo();
		void create();
	}
}
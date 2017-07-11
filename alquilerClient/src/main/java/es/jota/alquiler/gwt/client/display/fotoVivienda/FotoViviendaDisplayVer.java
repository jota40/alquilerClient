package es.jota.alquiler.gwt.client.display.fotoVivienda;

import com.google.gwt.user.client.ui.IsWidget;

import es.jota.alquiler.gwt.client.interfaces.IActivityVer;

public interface FotoViviendaDisplayVer extends IsWidget {
	void setPresenter( FotoViviendaDisplayVer.Presenter Presenter );
	public interface Presenter extends IActivityVer.Presenter {
	}

	void setFoto( String url );
	void setTitulo( String titulo );
}
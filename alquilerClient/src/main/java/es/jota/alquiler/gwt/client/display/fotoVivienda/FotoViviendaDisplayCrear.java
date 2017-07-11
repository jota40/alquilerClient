package es.jota.alquiler.gwt.client.display.fotoVivienda;

import com.google.gwt.user.client.ui.IsWidget;

public interface FotoViviendaDisplayCrear extends IsWidget {
	void setPresenter( FotoViviendaDisplayCrear.Presenter Presenter );
	public interface Presenter {
		void create( FotoViviendaDisplayCrear display );
	}

	void cleanFoto();
	String getTitulo();
	Integer getOrden();
	String getMd5();

	void setTitulo( String titulo );
	void setOrden( Integer orden );

	boolean validate();
}
package es.jota.alquiler.gwt.client.display.fotoVivienda;

import com.google.gwt.user.client.ui.IsWidget;

public interface FotoViviendaDisplayEditar extends IsWidget {
	void setPresenter( FotoViviendaDisplayEditar.Presenter Presenter );
	public interface Presenter {
		void update( FotoViviendaDisplayEditar display );
		void delete( FotoViviendaDisplayEditar display );
	}

	Long getVersion();
	Long getId();
	String getTitulo();
	Integer getOrden();

	void setVersion( Long version );
	void setId( Long id );
	void setFoto( String url );
	void setTitulo( String titulo );
	void setOrden( Integer orden );

	boolean validate();
}
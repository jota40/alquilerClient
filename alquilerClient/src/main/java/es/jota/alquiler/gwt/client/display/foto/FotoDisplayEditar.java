package es.jota.alquiler.gwt.client.display.foto;

import com.google.gwt.user.client.ui.IsWidget;

import es.jota.alquiler.gwt.client.interfaces.IActivityEditar;
import jota.server.dto.ViviendaDtoDown;
import jota.server.enumerations.TagEnum;

public interface FotoDisplayEditar extends IsWidget {
	void setPresenter( FotoDisplayEditar.Presenter Presenter );
	public interface Presenter extends IActivityEditar.Presenter {
	}

	void setVersion( Long version );
	void setFoto( String url );
	void setNombre( String nombre );
	void setType( String type );
	void setSize( Long size );
	void setTitulo( String titulo );
	void setTag( TagEnum tag );
	void setVivienda( ViviendaDtoDown vivienda );
	void setOrden( Integer orden );
	
	Long getVersion();
	String getTitulo();
	Integer getIdTag();
	Long getIdVivienda();
	Integer getOrden();
	
	boolean validate();
}
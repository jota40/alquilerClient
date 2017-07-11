package es.jota.alquiler.gwt.client.display.foto;

import com.google.gwt.user.client.ui.IsWidget;

import es.jota.alquiler.gwt.client.interfaces.IActivityCrear;
import jota.server.dto.ViviendaDtoDown;
import jota.server.enumerations.TagEnum;

public interface FotoDisplayCrear extends IsWidget {
	void setPresenter( FotoDisplayCrear.Presenter Presenter );
	public interface Presenter extends IActivityCrear.Presenter {
	}

	void cleanFoto();
	void setTitulo( String titulo );
	void setTag( TagEnum tag );
	void setVivienda( ViviendaDtoDown vivienda );
	void setOrden( Integer orden );
	
	String getMd5();
	String getTitulo();
	Integer getIdTag();
	Long getIdVivienda();
	Integer getOrden();

	boolean validate();
}
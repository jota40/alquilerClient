package es.jota.alquiler.gwt.client.display.vivienda;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;

import es.jota.alquiler.gwt.client.interfaces.IActivityEditar;

public interface ViviendaDisplayEditar extends IsWidget {
	void setPresenter( ViviendaDisplayEditar.Presenter Presenter );
	public interface Presenter extends IActivityEditar.Presenter {
	}

	void setVersion( Long version );
	void setNombre( String nombre );
	void setSituacion(String situacion);
	void setResumen( String descripcion );
	void setCapacidad( Integer cantidad );
	
	Long getVersion();
	String getNombre();
	String getSituacion();
	String getResumen();
	Integer getCapacidad();
	
	boolean validate();
	AcceptsOneWidget getBottom();
}
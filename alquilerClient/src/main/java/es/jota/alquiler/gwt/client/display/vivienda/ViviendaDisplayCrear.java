package es.jota.alquiler.gwt.client.display.vivienda;

import com.google.gwt.user.client.ui.IsWidget;

import es.jota.alquiler.gwt.client.interfaces.IActivityCrear;

public interface ViviendaDisplayCrear extends IsWidget {
	void setPresenter( ViviendaDisplayCrear.Presenter Presenter );
	public interface Presenter extends IActivityCrear.Presenter {
	}

	void setNombre( String nombre );
	void setSituacion(String situacion);
	void setResumen( String descripcion );
	void setCapacidad( Integer cantidad );
	
	String getNombre();
	String getSituacion();
	String getResumen();
	Integer getCapacidad();

	boolean validate();
}
package es.jota.alquiler.gwt.client.display.vivienda;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;

import es.jota.alquiler.gwt.client.interfaces.IActivityVer;

public interface ViviendaDisplayVer extends IsWidget {
	void setPresenter( ViviendaDisplayVer.Presenter Presenter );
	public interface Presenter extends IActivityVer.Presenter {
	}

	void setNombre( String nombre );
	void setResumen( String resumen );
	void setSituacion( String descripcion );
	void setCapacidad( Integer capacidad );
	
	AcceptsOneWidget getBottom();
}
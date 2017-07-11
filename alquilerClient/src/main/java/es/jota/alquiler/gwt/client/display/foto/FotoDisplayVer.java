package es.jota.alquiler.gwt.client.display.foto;

import com.google.gwt.user.client.ui.IsWidget;

import es.jota.alquiler.gwt.client.interfaces.IActivityVer;

public interface FotoDisplayVer extends IsWidget {
	void setPresenter( FotoDisplayVer.Presenter Presenter );
	public interface Presenter extends IActivityVer.Presenter {
	}

	void setFoto( String url );
	void setNombre( String nombre );
	void setType( String type );
	void setSize( Long size );
	void setTitulo( String titulo );
	void setTag( String tag );
	void setVivienda( String vivenda );
	void setOrden( Integer orden );
}
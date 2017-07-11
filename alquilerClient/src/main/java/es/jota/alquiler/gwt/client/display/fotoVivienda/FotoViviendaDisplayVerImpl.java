package es.jota.alquiler.gwt.client.display.fotoVivienda;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class FotoViviendaDisplayVerImpl extends Composite implements FotoViviendaDisplayVer {

	private static FotoViviendaDisplayVerUiBinder uiBinder = GWT.create(FotoViviendaDisplayVerUiBinder.class);
	interface FotoViviendaDisplayVerUiBinder extends UiBinder<Widget, FotoViviendaDisplayVerImpl>{}

	@UiField ImageElement foto;
	@UiField HeadingElement titulo;

	private FotoViviendaDisplayVer.Presenter presenter;

	@Override
	public void setPresenter( FotoViviendaDisplayVer.Presenter Presenter ) {
		this.presenter = Presenter;
	}

	public FotoViviendaDisplayVerImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}


	@Override
	public void setFoto( String url ) {
		this.foto.setSrc( url );
	}

	@Override
	public void setTitulo( String titulo ) {
		this.titulo.setInnerHTML( titulo );
	}
}
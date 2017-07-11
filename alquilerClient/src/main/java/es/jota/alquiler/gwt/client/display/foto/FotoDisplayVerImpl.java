package es.jota.alquiler.gwt.client.display.foto;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import es.jota.utils.gwt.client.utils.UtilClient;

public class FotoDisplayVerImpl extends Composite implements FotoDisplayVer {

	private static FotoDisplayVerUiBinder uiBinder = GWT.create(FotoDisplayVerUiBinder.class);
	interface FotoDisplayVerUiBinder extends UiBinder<Widget, FotoDisplayVerImpl>{}

	@UiField ImageElement foto;
	@UiField ParagraphElement nombre;
	@UiField ParagraphElement type;
	@UiField ParagraphElement size;
	@UiField ParagraphElement titulo;
	@UiField ParagraphElement tag;
	@UiField ParagraphElement vivienda;
	@UiField ParagraphElement orden;

	private FotoDisplayVer.Presenter presenter;

	@Override
	public void setPresenter( FotoDisplayVer.Presenter Presenter ) {
		this.presenter = Presenter;
	}

	public FotoDisplayVerImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setFoto( String url ) {
		this.foto.setSrc( url );
	}

	@Override
	public void setNombre( String nombre ) {
		this.nombre.setInnerHTML( nombre );
	}

	@Override
	public void setType( String type ) {
		this.type.setInnerHTML( type );
	}

	@Override
	public void setSize( Long size ) {
		this.size.setInnerHTML( UtilClient.Numeros.number2String( size, " Bytes") );
	}

	@Override
	public void setTitulo( String titulo ) {
		this.titulo.setInnerHTML( titulo );
	}

	@Override
	public void setTag( String tag ) {
		this.tag.setInnerHTML( tag );
	}

	@Override
	public void setVivienda( String vivienda ) {
		this.vivienda.setInnerHTML( vivienda );
	}
	
	@Override
	public void setOrden( Integer orden ) {
		this.orden.setInnerHTML( UtilClient.Numeros.number2String( orden ) );
	}
}
package es.jota.alquiler.gwt.client.display.fotoVivienda;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import es.jota.alquiler.gwt.client.widget.base.Dropzone;
import es.jota.utils.gwt.client.utils.UtilClient;

public class FotoViviendaDisplayCrearImpl extends Composite implements FotoViviendaDisplayCrear {

	private static FotoDisplayCrearUiBinder uiBinder = GWT.create(FotoDisplayCrearUiBinder.class);
	interface FotoDisplayCrearUiBinder extends UiBinder<Widget, FotoViviendaDisplayCrearImpl>{}

	@UiField Dropzone foto;
	@UiField InputElement titulo;
	@UiField InputElement orden;

	private FotoViviendaDisplayCrear.Presenter presenter;

	@Override
	public void setPresenter( FotoViviendaDisplayCrear.Presenter Presenter ) {
		this.presenter = Presenter;
	}

	public FotoViviendaDisplayCrearImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void cleanFoto() {
		foto.clean();
	}

	@Override
	public void setTitulo( String titulo ) {
		this.titulo.setValue( titulo );
	}

	@Override
	public void setOrden( Integer orden ) {
		this.orden.setValue( UtilClient.Numeros.number2String( orden ) );
	}

	@Override
	public String getMd5() {
		return foto.getMd5().getFirst();
	}

	@Override
	public String getTitulo() {
		return titulo.getValue();
	}

	@Override
	public Integer getOrden() {
		return UtilClient.Numeros.string2BigDecimal( orden.getValue() ).intValue();
	}

	@Override
	public boolean validate() {
		return true;
	}

	@UiHandler("create")
	void createOnClick( ClickEvent e ) {
		presenter.create( this );
	}
}
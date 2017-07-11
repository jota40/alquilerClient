package es.jota.alquiler.gwt.client.display.fotoVivienda;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import es.jota.utils.gwt.client.utils.UtilClient;

public class FotoViviendaDisplayEditarImpl extends Composite implements FotoViviendaDisplayEditar {

	private static FotoDisplayEditarUiBinder uiBinder = GWT.create(FotoDisplayEditarUiBinder.class);
	interface FotoDisplayEditarUiBinder extends UiBinder<Widget, FotoViviendaDisplayEditarImpl>{}

	@UiField ImageElement foto;
	@UiField InputElement titulo;
	@UiField InputElement orden;

	private FotoViviendaDisplayEditar.Presenter presenter;
	private Long id;
	private Long version;

	@Override
	public void setPresenter( FotoViviendaDisplayEditar.Presenter Presenter ) {
		this.presenter = Presenter;
	}

	public FotoViviendaDisplayEditarImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setVersion( Long version ) {
		this.version = version;
	}

	@Override
	public void setId( Long id ) {
		this.id = id;
	}
	
	@Override
	public void setFoto( String url ) {
		this.foto.setSrc( url );
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
	public Long getVersion() {
		return version;
	}

	@Override
	public Long getId() {
		return id;
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

	@UiHandler("update")
	void updateOnClick( ClickEvent e ) {
		presenter.update( this );
	}

	@UiHandler("delete")
	void deleteOnClick( ClickEvent e ) {
		presenter.delete( this );
	}
}
package es.jota.alquiler.gwt.client.display.foto;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import es.jota.alquiler.gwt.client.widget.SuggestTag;
import es.jota.alquiler.gwt.client.widget.SuggestVivienda;
import es.jota.alquiler.gwt.client.widget.base.Dropzone;
import es.jota.alquiler.gwt.client.widget.base.Suggest;
import es.jota.utils.gwt.client.utils.UtilClient;
import jota.server.dto.ViviendaDtoDown;
import jota.server.enumerations.TagEnum;

public class FotoDisplayCrearImpl extends Composite implements FotoDisplayCrear {

	private static FotoDisplayCrearUiBinder uiBinder = GWT.create(FotoDisplayCrearUiBinder.class);
	interface FotoDisplayCrearUiBinder extends UiBinder<Widget, FotoDisplayCrearImpl>{}

	@UiField Dropzone foto;

	@UiField Element tituloFormGroup;
	@UiField InputElement titulo;
	@UiField Element tituloError;

	@UiField Suggest tag;

	@UiField Suggest vivienda;

	@UiField Element ordenFormGroup;
	@UiField InputElement orden;
	@UiField Element ordenError;

	private SuggestTag suggestTag;
	private SuggestVivienda suggestVivienda;

	private FotoDisplayCrear.Presenter presenter;

	@Override
	public void setPresenter( FotoDisplayCrear.Presenter Presenter ) {
		this.presenter = Presenter;
	}

	public FotoDisplayCrearImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		suggestTag = new SuggestTag( tag );
		suggestVivienda = new SuggestVivienda( vivienda );
	}

	@UiHandler("create")
	void onClickActualizar( ClickEvent e ) {
		presenter.create();
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
	public void setTag( TagEnum tag ) {
		this.suggestTag.setValue( tag );
	}

	@Override
	public void setVivienda( ViviendaDtoDown vivienda ) {
		this.suggestVivienda.setValue( vivienda );
	}

	@Override
	public void setOrden( Integer capacidad ) {
		this.orden.setValue( UtilClient.Numeros.number2String( capacidad ) );
	}

	@Override
	public String getTitulo() {
		return titulo.getValue();
	}
	
	@Override
	public String getMd5() {
		return foto.getMd5().getFirst();
	}

	@Override
	public Integer getIdTag() {
		return suggestTag.getId();
	}
	
	@Override
	public Long getIdVivienda() {
		return suggestVivienda.getId();
	}

	@Override
	public Integer getOrden() {
		return UtilClient.Numeros.string2BigDecimal( orden.getValue() ).intValue();
	}

	@Override
	public boolean validate() {
		return true;
	}
}
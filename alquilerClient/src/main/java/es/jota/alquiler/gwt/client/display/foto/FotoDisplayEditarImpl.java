package es.jota.alquiler.gwt.client.display.foto;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import es.jota.alquiler.gwt.client.widget.SuggestTag;
import es.jota.alquiler.gwt.client.widget.SuggestVivienda;
import es.jota.alquiler.gwt.client.widget.base.Suggest;
import es.jota.utils.gwt.client.utils.UtilClient;
import jota.server.dto.ViviendaDtoDown;
import jota.server.enumerations.TagEnum;

public class FotoDisplayEditarImpl extends Composite implements FotoDisplayEditar {

	private static FotoDisplayEditarUiBinder uiBinder = GWT.create(FotoDisplayEditarUiBinder.class);
	interface FotoDisplayEditarUiBinder extends UiBinder<Widget, FotoDisplayEditarImpl>{}

	@UiField ImageElement foto;

	@UiField ParagraphElement nombre;
	@UiField ParagraphElement type;
	@UiField ParagraphElement size;

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

	private FotoDisplayEditar.Presenter presenter;

	private Long version;

	@Override
	public void setPresenter( FotoDisplayEditar.Presenter Presenter ) {
		this.presenter = Presenter;
	}

	public FotoDisplayEditarImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		suggestTag = new SuggestTag( tag );
		suggestVivienda = new SuggestVivienda( vivienda );
	}

	@UiHandler("update")
	void onClickActualizar( ClickEvent e ) {
		presenter.update();
	}

	@Override
	public void setVersion( Long version ) {
		this.version = version;
	}

	@Override
	public void setFoto( String foto) {
		this.foto.setSrc( foto );
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
	public Long getVersion() {
		return version;
	}

	@Override
	public String getTitulo() {
		return titulo.getValue();
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
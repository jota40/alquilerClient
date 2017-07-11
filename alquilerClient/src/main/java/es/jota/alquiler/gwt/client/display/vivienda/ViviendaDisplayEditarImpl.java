package es.jota.alquiler.gwt.client.display.vivienda;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.TextAreaElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

import es.jota.utils.gwt.client.utils.UtilClient;

public class ViviendaDisplayEditarImpl extends Composite implements ViviendaDisplayEditar {

	private static ViviendaDisplayEditarUiBinder uiBinder = GWT.create(ViviendaDisplayEditarUiBinder.class);
	interface ViviendaDisplayEditarUiBinder extends UiBinder<Widget, ViviendaDisplayEditarImpl>{}

	@UiField Element nombreFormGroup;
	@UiField InputElement nombre;
	@UiField Element nombreError;

	@UiField TextAreaElement situacion;

	@UiField TextAreaElement resumen;

	@UiField Element capacidadFormGroup;
	@UiField InputElement capacidad;
	@UiField Element capacidadError;

	@UiField SimplePanel bottom;

	private ViviendaDisplayEditar.Presenter presenter;
	private Long version;

	@Override
	public void setPresenter( ViviendaDisplayEditar.Presenter Presenter ) {
		this.presenter = Presenter;
	}

	public ViviendaDisplayEditarImpl() {
		initWidget(uiBinder.createAndBindUi(this));
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
	public void setNombre( String nombre ) {
		this.nombre.setValue( nombre );
	}

	@Override
	public void setSituacion( String situacion ) {
		this.situacion.setValue( situacion );
	}

	@Override
	public void setResumen( String resumen ) {
		this.resumen.setValue( resumen);
	}

	@Override
	public void setCapacidad( Integer capacidad ) {
		this.capacidad.setValue( UtilClient.Numeros.number2String( capacidad ) );
	}

	@Override
	public Long getVersion() {
		return version;
	}

	@Override
	public String getNombre() {
		return nombre.getValue();
	}
	
	@Override
	public String getSituacion() {
		return situacion.getValue();
	}

	@Override
	public String getResumen() {
		return resumen.getValue();
	}

	@Override
	public Integer getCapacidad() {
		return UtilClient.Numeros.string2BigDecimal( capacidad.getValue() ).intValue();
	}

	@Override
	public SimplePanel getBottom() {
		return bottom;
	}

	@Override
	public boolean validate() {
		return true;
	}
}
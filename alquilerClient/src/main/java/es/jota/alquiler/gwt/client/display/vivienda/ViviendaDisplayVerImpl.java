package es.jota.alquiler.gwt.client.display.vivienda;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

import es.jota.utils.gwt.client.utils.UtilClient;

public class ViviendaDisplayVerImpl extends Composite implements ViviendaDisplayVer {

	private static ViviendaDisplayVerUiBinder uiBinder = GWT.create(ViviendaDisplayVerUiBinder.class);
	interface ViviendaDisplayVerUiBinder extends UiBinder<Widget, ViviendaDisplayVerImpl>{}

	@UiField ParagraphElement nombre;
	@UiField ParagraphElement situacion;
	@UiField ParagraphElement resumen;
	@UiField ParagraphElement capacidad;
	@UiField SimplePanel bottom;

	private ViviendaDisplayVer.Presenter presenter;

	@Override
	public void setPresenter( ViviendaDisplayVer.Presenter Presenter ) {
		this.presenter = Presenter;
	}

	public ViviendaDisplayVerImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setNombre( String nombre ) {
		this.nombre.setInnerHTML( nombre );
	}

	@Override
	public void setSituacion( String situacion ) {
		this.situacion.setInnerHTML( situacion );
	}

	@Override
	public void setResumen( String resumen ) {
		this.resumen.setInnerHTML( resumen );
	}
	
	@Override
	public void setCapacidad( Integer capacidad ) {
		this.capacidad.setInnerHTML( UtilClient.Numeros.number2String( capacidad ) );
	}

	@Override
	public SimplePanel getBottom() {
		return bottom;
	}
}
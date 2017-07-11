package es.jota.alquiler.gwt.client.widget;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import es.jota.alquiler.gwt.client.gin.GinService;
import es.jota.utils.gwt.client.widget.presenter.SelectValuePresenter;
import es.jota.utils.gwt.client.widget.presenter.interfaz.IAcceptableValuesProvider;
import es.jota.utils.gwt.client.widget.presenter.interfaz.ISelectValue;
import jota.server.dto.ViviendaDtoDown;

public class SuggestVivienda extends SelectValuePresenter<ViviendaDtoDown> {

	public SuggestVivienda( ISelectValue.IDisplay display ) {
		super( display );
		setItemTextProvider( new IItemProvider<ViviendaDtoDown>() {
			@Override
			public String get(ViviendaDtoDown item) {
				return item.getNombre();
			}
		});

		setAcceptableValuesProvider( new IAcceptableValuesProvider() {
			@Override
			public void load( int start, int size, String query, final long timestamp ) {
				GinService.INSTANCE.getViviendaGwtServiceAsync().findMisVivendasByNombre( start, size, query, new AsyncCallback<List<ViviendaDtoDown>>() {
					@Override
					public void onFailure( Throwable caught ) {
						setAcceptableValues( new ArrayList<ViviendaDtoDown>(), timestamp );
					}
					@Override
					public void onSuccess( List<ViviendaDtoDown> result ) {
						setAcceptableValues( result, timestamp );
					}
				});
			}
		});
	}

	public Long getId() {
		return getValue() == null ? null : getValue().getId();
	}
}
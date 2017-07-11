package es.jota.alquiler.gwt.client.widget;

import java.util.Arrays;
import java.util.List;

import es.jota.utils.gwt.client.widget.presenter.SelectValuePresenter;
import es.jota.utils.gwt.client.widget.presenter.interfaz.IAcceptableValuesProvider;
import es.jota.utils.gwt.client.widget.presenter.interfaz.ISelectValue;
import jota.server.enumerations.TagEnum;

public class SuggestTag extends SelectValuePresenter<TagEnum> {

	public SuggestTag( ISelectValue.IDisplay display ) {
		super( display );
		setItemTextProvider( new IItemProvider<TagEnum>() {
			@Override
			public String get(TagEnum item) {
				return item.name();
			}
		});

		setAcceptableValuesProvider( new IAcceptableValuesProvider() {
			@Override
			public void load( int start, int size, String query, final long timestamp ) {
				List<TagEnum> list = match( Arrays.asList(TagEnum.values()), query );
				int end = Math.min( list.size(), start + size );
				setAcceptableValues( list.subList( start, end ), timestamp );
			}
		});
	}

	public Integer getId() {
		return getValue() == null ? null : getValue().ordinal();
	}
}
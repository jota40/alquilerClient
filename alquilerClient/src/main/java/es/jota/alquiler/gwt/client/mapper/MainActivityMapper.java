package es.jota.alquiler.gwt.client.mapper;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

import es.jota.alquiler.gwt.client.gin.GinActivity;
import es.jota.alquiler.gwt.client.place.foto.FotoPlace;
import es.jota.alquiler.gwt.client.place.home.HomePlace;
import es.jota.alquiler.gwt.client.place.vivienda.ViviendaPlace;

public class MainActivityMapper implements ActivityMapper {

	/**
	 * AppActivityMapper associates each Place with its corresponding
	 * {@link Activity}
	 * 
	 * @param clientFactory
	 *            Factory to be passed to activities
	 */
	public MainActivityMapper() {
		super();
	}

	/**
	 * Map each Place to its corresponding Activity. This would be a great use
	 * for GIN.
	 */
	@Override
	public Activity getActivity(Place place) {
		if ( place instanceof HomePlace ) 
			return GinActivity.INSTANCE.getHomeActivity().setPlace( (HomePlace)place );

		if ( place instanceof ViviendaPlace ) 
			return ( (ViviendaPlace)place ).getActivity( place );

		if ( place instanceof FotoPlace ) 
			return ( (FotoPlace)place ).getActivity( place );

		return null;
	}
}

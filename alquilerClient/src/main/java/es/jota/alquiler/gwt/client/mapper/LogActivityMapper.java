package es.jota.alquiler.gwt.client.mapper;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

import es.jota.alquiler.gwt.client.gin.GinActivity;
import es.jota.utils.gwt.client.my.MyPlace;

public class LogActivityMapper implements ActivityMapper {


	/**
	 * AppActivityMapper associates each Place with its corresponding
	 * {@link Activity}
	 * 
	 * @param clientFactory
	 *            Factory to be passed to activities
	 */
	public LogActivityMapper() {
		super();
	}

	/**
	 * Map each Place to its corresponding Activity. This would be a great use
	 * for GIN.
	 */
	@Override
	public Activity getActivity(Place place) {
		return GinActivity.INSTANCE.getLogsActivity().setPlace((MyPlace) place);
	}
}

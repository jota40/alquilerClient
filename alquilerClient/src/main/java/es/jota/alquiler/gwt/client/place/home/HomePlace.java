package es.jota.alquiler.gwt.client.place.home;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.gwt.safehtml.shared.SafeHtml;

import es.jota.utils.gwt.client.my.MyPlace;
import es.jota.utils.gwt.client.utils.UtilClient;

public class HomePlace extends MyPlace {
	public final static String NAME = "home";
	
	private HomePlace() {
	}

	@Prefix(NAME)
	public static class Tokenizer implements PlaceTokenizer<HomePlace> {
		@Override
		public String getToken(HomePlace place) {
			return "";
		}

		@Override
		public HomePlace getPlace(String token) {
			return instance();
		}
	}

	public static HomePlace instance() {
		return new HomePlace();
	}

	public static String getUrl() {
		return  "#" + NAME + ":";
	}

	@Override
	public SafeHtml getBreadcrumbs() {
		return UtilClient.TEMPLATES.breadcrumbs1( NAME );
	}

	@Override
	public boolean isInstanceOf( String clazz ) {
		return HomePlace.class.getSimpleName().compareTo( clazz ) == 0;
	}
}
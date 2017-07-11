package es.jota.alquiler.gwt.client.place.vivienda;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.gwt.safehtml.shared.SafeHtml;

import es.jota.alquiler.gwt.client.place.home.HomePlace;
import es.jota.utils.gwt.client.utils.UtilClient;

public class ViviendaPlaceCrear extends ViviendaPlace {
	public final static String NAME = "vivendaCrear";
	
	private ViviendaPlaceCrear() {
	}

	@Prefix(NAME)
	public static class Tokenizer implements PlaceTokenizer<ViviendaPlaceCrear> {
		@Override
		public String getToken(ViviendaPlaceCrear place) {
			return "";
		}

		@Override
		public ViviendaPlaceCrear getPlace(String token) {
			return instance();
		}
	}

	public static ViviendaPlaceCrear instance() {
		return new ViviendaPlaceCrear();
	}

	public static String getUrl() {
		return "#" + NAME + ":";
	}

	@Override
	public SafeHtml getBreadcrumbs() {
		return UtilClient.TEMPLATES.breadcrumbs2( HomePlace.getUrl(), "home", "Crear" );
	}
}
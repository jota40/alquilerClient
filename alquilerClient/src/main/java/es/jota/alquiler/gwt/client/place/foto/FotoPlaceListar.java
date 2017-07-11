package es.jota.alquiler.gwt.client.place.foto;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.gwt.safehtml.shared.SafeHtml;

import es.jota.alquiler.gwt.client.place.home.HomePlace;
import es.jota.utils.gwt.client.utils.UtilClient;

public class FotoPlaceListar extends FotoPlace {
	public final static String NAME = "fotos";
	
	private FotoPlaceListar() {
	}

	@Prefix(NAME)
	public static class Tokenizer implements PlaceTokenizer<FotoPlaceListar> {
		@Override
		public String getToken(FotoPlaceListar place) {
			return "";
		}

		@Override
		public FotoPlaceListar getPlace(String token) {
			return instance();
		}
	}

	public static FotoPlaceListar instance() {
		return new FotoPlaceListar();
	}

	public static String getUrl() {
		return  "#" + NAME + ":";
	}

	@Override
	public SafeHtml getBreadcrumbs() {
		return UtilClient.TEMPLATES.breadcrumbs2( HomePlace.getUrl(), "Home", "Mis fotos" );
	}
}
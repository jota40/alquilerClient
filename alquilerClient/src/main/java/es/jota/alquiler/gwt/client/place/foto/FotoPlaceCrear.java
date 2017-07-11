package es.jota.alquiler.gwt.client.place.foto;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.gwt.safehtml.shared.SafeHtml;

import es.jota.alquiler.gwt.client.place.home.HomePlace;
import es.jota.utils.gwt.client.utils.UtilClient;

public class FotoPlaceCrear extends FotoPlace {
	public final static String NAME = "fotoCrear";
	
	private FotoPlaceCrear() {
	}

	@Prefix(NAME)
	public static class Tokenizer implements PlaceTokenizer<FotoPlaceCrear> {
		@Override
		public String getToken(FotoPlaceCrear place) {
			return "";
		}

		@Override
		public FotoPlaceCrear getPlace(String token) {
			return instance();
		}
	}

	public static FotoPlaceCrear instance() {
		return new FotoPlaceCrear();
	}

	public static String getUrl() {
		return "#" + NAME + ":";
	}

	@Override
	public SafeHtml getBreadcrumbs() {
		return UtilClient.TEMPLATES.breadcrumbs3( HomePlace.getUrl(), "Home", FotoPlaceListar.getUrl(), "Mis fotos", "Crear" );
	}
}
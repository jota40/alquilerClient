package es.jota.alquiler.gwt.client.place.foto;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.gwt.safehtml.shared.SafeHtml;

import es.jota.alquiler.gwt.client.place.home.HomePlace;
import es.jota.utils.gwt.client.utils.UtilClient;

public class FotoPlaceEditar extends FotoPlace {
	public final static String NAME = "fotoEditar";
	private Long id;
	
	private FotoPlaceEditar( Long id ) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	@Prefix(NAME)
	public static class Tokenizer implements PlaceTokenizer<FotoPlaceEditar> {
		@Override
		public String getToken( FotoPlaceEditar place ) {
			return place.getId().toString();
		}

		@Override
		public FotoPlaceEditar getPlace( String token ) {
			try {
				return  instance( Long.parseLong( token ) );
			} catch (Exception e) {
			}
			return null;
		}
	}

	public static FotoPlaceEditar instance( Long id ) {
		return new FotoPlaceEditar( id );
	}

	public static String getUrl( Long id ) {
		return "#" + NAME + ":" + id;
	}

	@Override
	public SafeHtml getBreadcrumbs() {
		return UtilClient.TEMPLATES.breadcrumbs3( HomePlace.getUrl(), "Home", FotoPlaceListar.getUrl(), "Mis fotos", "Editar" );
	}
}
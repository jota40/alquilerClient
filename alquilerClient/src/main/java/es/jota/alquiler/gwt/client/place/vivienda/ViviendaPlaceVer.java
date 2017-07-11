package es.jota.alquiler.gwt.client.place.vivienda;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.gwt.safehtml.shared.SafeHtml;

import es.jota.alquiler.gwt.client.place.home.HomePlace;
import es.jota.utils.gwt.client.utils.UtilClient;

public class ViviendaPlaceVer extends ViviendaPlace {
	public final static String NAME = "vivendaVer";
	private Long id;
	
	private ViviendaPlaceVer( Long id ) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	@Prefix(NAME)
	public static class Tokenizer implements PlaceTokenizer<ViviendaPlaceVer> {
		@Override
		public String getToken( ViviendaPlaceVer place ) {
			return place.getId().toString();
		}

		@Override
		public ViviendaPlaceVer getPlace( String token ) {
			try {
				return instance( Long.parseLong( token ) );
			} catch (Exception e) {
			}
			return null;
		}
	}

	public static ViviendaPlaceVer instance( Long id ) {
		return new ViviendaPlaceVer( id );
	}

	public static String getUrl( Long id ) {
		return "#" + NAME + ":" + id;
	}

	@Override
	public SafeHtml getBreadcrumbs() {
		return UtilClient.TEMPLATES.breadcrumbs2( HomePlace.getUrl(), "home", "ver" );
	}
}
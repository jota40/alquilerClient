package es.jota.alquiler.gwt.client.place.vivienda;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.gwt.safehtml.shared.SafeHtml;

import es.jota.alquiler.gwt.client.place.home.HomePlace;
import es.jota.utils.gwt.client.utils.UtilClient;

public class ViviendaPlaceEditar extends ViviendaPlace {
	public final static String NAME = "vivendaEditar";
	private Long id;
	private Long idPropietario;
	
	private ViviendaPlaceEditar( Long id, Long idPropietario ) {
		this.id = id;
		this.idPropietario = idPropietario;
	}

	public Long getId() {
		return id;
	}

	public Long getIdPropietario() {
		return idPropietario;
	}

	@Prefix(NAME)
	public static class Tokenizer implements PlaceTokenizer<ViviendaPlaceEditar> {
		@Override
		public String getToken( ViviendaPlaceEditar place ) {
			return place.getId()+","+place.getIdPropietario();
		}

		@Override
		public ViviendaPlaceEditar getPlace( String token ) {
			try {
				String[] tokens = token.split(",");
				return  instance( Long.parseLong( tokens[0] ), Long.parseLong( tokens[1] ) );
			} catch (Exception e) {
			}
			return null;
		}
	}

	public static ViviendaPlaceEditar instance( Long id, Long idPropietario ) {
		return new ViviendaPlaceEditar( id, idPropietario );
	}

	public static String getUrl( Long id, Long idPropietario ) {
		return "#" + NAME + ":" + id + "," + idPropietario;
	}

	@Override
	public SafeHtml getBreadcrumbs() {
		return UtilClient.TEMPLATES.breadcrumbs2( HomePlace.getUrl(), "home", "Editar" );
	}
}
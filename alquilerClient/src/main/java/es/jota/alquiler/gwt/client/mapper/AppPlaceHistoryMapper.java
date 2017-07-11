package es.jota.alquiler.gwt.client.mapper;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

import es.jota.alquiler.gwt.client.place.foto.FotoPlaceCrear;
import es.jota.alquiler.gwt.client.place.foto.FotoPlaceEditar;
import es.jota.alquiler.gwt.client.place.foto.FotoPlaceListar;
import es.jota.alquiler.gwt.client.place.foto.FotoPlaceVer;
import es.jota.alquiler.gwt.client.place.home.HomePlace;
import es.jota.alquiler.gwt.client.place.vivienda.ViviendaPlaceCrear;
import es.jota.alquiler.gwt.client.place.vivienda.ViviendaPlaceEditar;
import es.jota.alquiler.gwt.client.place.vivienda.ViviendaPlaceVer;

/**
 * PlaceHistoryMapper interface is used to attach all places which the
 * PlaceHistoryHandler should be aware of. This is done via the @WithTokenizers
 * annotation or by extending PlaceHistoryMapperWithFactory and creating a
 * separate TokenizerFactory.
 */
@WithTokenizers( { 
			HomePlace.Tokenizer.class,
			ViviendaPlaceCrear.Tokenizer.class, ViviendaPlaceEditar.Tokenizer.class, ViviendaPlaceVer.Tokenizer.class,
			FotoPlaceCrear.Tokenizer.class, FotoPlaceEditar.Tokenizer.class, FotoPlaceListar.Tokenizer.class, FotoPlaceVer.Tokenizer.class
			} )
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {
}

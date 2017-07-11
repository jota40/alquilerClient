package es.jota.alquiler.gwt.client.place.vivienda;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.place.shared.Place;

import es.jota.alquiler.gwt.client.gin.Gin;
import es.jota.alquiler.gwt.client.gin.GinActivity;
import es.jota.alquiler.gwt.client.place.home.HomePlace;
import es.jota.utils.gwt.client.my.MyPlace;
import jota.server.enumerations.RolEnum;

public abstract class ViviendaPlace extends MyPlace {
	protected ViviendaPlace() {
	}
	/**
	 * Todos tienes acceso a ver
	 * Si es ( ADMINISTRADOR ) tiene acceso tambien a crear, editar, listar
	 * Si es ( PROPIETARIO ) tiene acceso tambien a crear y listar
	 * Si es ( PROPIETARIO y la vivienda es suya ) tambien editar	
	 * En cualquier otro caso va al home
	 * @param place
	 * @return
	 */
	public Activity getActivity( Place place ) {

		RolEnum rol = Gin.INSTANCE.getSecurityGwtUtils().getRol();
		if ( place instanceof ViviendaPlaceCrear ) {
			switch ( rol ) {
				case ADMINISTRADOR: 
				case PROPIETARIO:	return GinActivity.INSTANCE.getViviendaActivityCrear().setPlace( (ViviendaPlaceCrear)place );
			}
		}

		if ( place instanceof ViviendaPlaceEditar ) { 
			switch ( rol ) {
				case ADMINISTRADOR: return GinActivity.INSTANCE.getViviendaActivityEditar().setPlace( (ViviendaPlaceEditar)place );
				case PROPIETARIO: if ( Gin.INSTANCE.getSecurityGwtUtils().getIdUsuario() == ((ViviendaPlaceEditar)place).getIdPropietario() )
									return GinActivity.INSTANCE.getViviendaActivityEditar().setPlace( (ViviendaPlaceEditar)place );
			}
			return GinActivity.INSTANCE.getSecurityActivity().setPlace( ViviendaPlaceVer.instance( ((ViviendaPlaceEditar) place).getId()) );
		}

		if ( place instanceof ViviendaPlaceVer) { 
			return GinActivity.INSTANCE.getViviendaActivityVer().setPlace( (ViviendaPlaceVer) place );
		}

		return GinActivity.INSTANCE.getSecurityActivity().setPlace( HomePlace.instance() );
	}

	@Override
	public boolean isInstanceOf( String clazz ) {
		return ViviendaPlace.class.getSimpleName().compareTo( clazz ) == 0;
	}
}
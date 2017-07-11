package es.jota.alquiler.gwt.client.place.foto;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.place.shared.Place;

import es.jota.alquiler.gwt.client.gin.Gin;
import es.jota.alquiler.gwt.client.gin.GinActivity;
import es.jota.alquiler.gwt.client.place.home.HomePlace;
import es.jota.utils.gwt.client.my.MyPlace;

public abstract class FotoPlace extends MyPlace {
	protected FotoPlace() {
	}
	/**
	 * Solo( ADMINISTRADOR ) y ( PROPIETARIO ) tienen acceso
	 * @param place
	 * @return
	 */
	public Activity getActivity( Place place ) {

		switch ( Gin.INSTANCE.getSecurityGwtUtils().getRol() ) {
			case ADMINISTRADOR: 
			case PROPIETARIO:	
								if ( place instanceof FotoPlaceCrear ) {
									return GinActivity.INSTANCE.getFotoActivityCrear().setPlace( (FotoPlaceCrear)place );
								}
								if ( place instanceof FotoPlaceEditar ) { 
									return GinActivity.INSTANCE.getFotoActivityEditar().setPlace( (FotoPlaceEditar)place );
								}
								if ( place instanceof FotoPlaceListar ) { 
									return GinActivity.INSTANCE.getFotoActivityListar().setPlace( (FotoPlaceListar)place );
								}
								if ( place instanceof FotoPlaceVer) { 
									return GinActivity.INSTANCE.getFotoActivityVer().setPlace( (FotoPlaceVer) place );
								}

		}
		return GinActivity.INSTANCE.getSecurityActivity().setPlace( HomePlace.instance() );
	}

	@Override
	public boolean isInstanceOf( String clazz ) {
		return FotoPlace.class.getSimpleName().compareTo( clazz ) == 0;
	}
}
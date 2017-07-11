package es.jota.alquiler.gwt.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import jota.server.dto.ViviendaDtoDown;
import jota.server.dto.ViviendaDtoUp;

public interface ViviendaGwtServiceAsync {
	void findAlquilables( int start, int size, AsyncCallback<List<ViviendaDtoDown>> asyncCallback );
	void findMisVivendas( int start, int size, AsyncCallback<List<ViviendaDtoDown>> asyncCallback );
	void findMisVivendasByNombre( int start, int size, String query, AsyncCallback<List<ViviendaDtoDown>> asyncCallback );
	void read( Long id, AsyncCallback<ViviendaDtoDown> asyncCallback );
	void update( Long id, ViviendaDtoUp vivienda, AsyncCallback<Void> asyncCallback );
	void create( ViviendaDtoUp vivienda, AsyncCallback<Long> asyncCallback );
	void delete( Long id, AsyncCallback<Void> asyncCallback );
}
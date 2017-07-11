package es.jota.alquiler.gwt.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import jota.server.dto.FotoDtoDown;
import jota.server.dto.FotoDtoUp;

public interface FotoGwtServiceAsync {
	void findFotosDeMiVivienda( long idVivienda, int start, int size, AsyncCallback<List<FotoDtoDown>> asyncCallback );
	void findMisFotos( int start, int size, AsyncCallback<List<FotoDtoDown>> asyncCallback );
	void read( Long id, AsyncCallback<FotoDtoDown> asyncCallback );
	void update( Long id, FotoDtoUp foto, Integer idTag, Long idVivienda, AsyncCallback<Void> asyncCallback );
	void create( FotoDtoUp foto, Integer idTag, Long idVivienda, AsyncCallback<Long> asyncCallback );
	void delete( Long id, AsyncCallback<Void> asyncCallback );
	void deleteFromController( String md5, AsyncCallback<Void> asyncCallback );
}
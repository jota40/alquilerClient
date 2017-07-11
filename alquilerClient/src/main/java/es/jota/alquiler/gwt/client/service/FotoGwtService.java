package es.jota.alquiler.gwt.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import jota.server.dto.FotoDtoDown;
import jota.server.dto.FotoDtoUp;
import jota.server.exceptions.ServerException;

@RemoteServiceRelativePath("../fotoGwtService.gwt")
public interface FotoGwtService extends RemoteService {
	List<FotoDtoDown> findFotosDeMiVivienda( long idVivienda, int start, int size ) throws ServerException;
	List<FotoDtoDown> findMisFotos( int start, int size ) throws ServerException;
	FotoDtoDown read( Long id ) throws ServerException;
	void update( Long id, FotoDtoUp foto, Integer idTag, Long idVivienda ) throws ServerException;
	Long create( FotoDtoUp foto, Integer idTag, Long idVivienda ) throws ServerException;
	void delete( Long id ) throws ServerException;
	void deleteFromController( String md5 ) throws ServerException;

}
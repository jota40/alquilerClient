package es.jota.alquiler.gwt.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import jota.server.dto.ViviendaDtoDown;
import jota.server.dto.ViviendaDtoUp;
import jota.server.exceptions.ServerException;

@RemoteServiceRelativePath("../viviendaGwtService.gwt")
public interface ViviendaGwtService extends RemoteService {
	List<ViviendaDtoDown> findAlquilables( int start, int size ) throws ServerException;
	List<ViviendaDtoDown> findMisVivendas( int start, int size ) throws ServerException;
	List<ViviendaDtoDown> findMisVivendasByNombre( int start, int size, String nombre ) throws ServerException;
	ViviendaDtoDown read( Long id ) throws ServerException;
	void update( Long id, ViviendaDtoUp vivienda ) throws ServerException;
	Long create( ViviendaDtoUp vivienda ) throws ServerException;
	void delete( Long id ) throws ServerException;
}
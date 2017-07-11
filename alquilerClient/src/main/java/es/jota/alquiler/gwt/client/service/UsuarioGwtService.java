package es.jota.alquiler.gwt.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import es.jota.alquiler.gwt.shared.SecurityGwtUtils;
import jota.server.exceptions.ServerException;

@RemoteServiceRelativePath("../usuarioGwtService.gwt")
public interface UsuarioGwtService extends RemoteService {
	SecurityGwtUtils getSecurityGwtUtils() throws ServerException;
}
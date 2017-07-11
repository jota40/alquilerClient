package es.jota.alquiler.gwt.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

import es.jota.alquiler.gwt.shared.SecurityGwtUtils;

public interface UsuarioGwtServiceAsync {
	void getSecurityGwtUtils( AsyncCallback<SecurityGwtUtils> asyncCallback );
}
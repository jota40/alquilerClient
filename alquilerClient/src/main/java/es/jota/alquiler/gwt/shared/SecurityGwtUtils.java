package es.jota.alquiler.gwt.shared;

import java.io.Serializable;

import jota.server.enumerations.RolEnum;

public class SecurityGwtUtils implements Serializable {

	private static final long serialVersionUID = 2710321103939129797L;

	Long idUsuario;
	String nombreUsuario;
	RolEnum rol;

	public SecurityGwtUtils() {
		idUsuario = null;
		nombreUsuario = "Initado";
		rol = RolEnum.INVITADO;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public RolEnum getRol() {
		return rol;
	}
	public void setRol(RolEnum rol) {
		this.rol = rol;
	}

	public void update( SecurityGwtUtils result ) {
		idUsuario = result.getIdUsuario();
		nombreUsuario = result.getNombreUsuario();
		rol = result.getRol();
	}
}
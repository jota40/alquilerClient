package es.jota.alquiler.gwt.server.gwt_servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import es.jota.alquiler.gwt.client.my.GWTController;
import es.jota.alquiler.gwt.client.service.UsuarioGwtService;
import es.jota.alquiler.gwt.shared.SecurityGwtUtils;
import jota.server.exceptions.ServerException;
import jota.server.security.SecurityUtils;

@Controller
@RequestMapping("/usuarioGwtService.gwt")
public class UsuarioGwtServiceImpl extends GWTController implements UsuarioGwtService {

	private static final long serialVersionUID = -649450201261649641L;
	@Override
	public SecurityGwtUtils getSecurityGwtUtils() throws ServerException {
		SecurityGwtUtils securityGwtUtils = new SecurityGwtUtils();
		securityGwtUtils.setIdUsuario( SecurityUtils.getIdUsuario() );
		securityGwtUtils.setNombreUsuario( SecurityUtils.getNombreUsuario() );
		securityGwtUtils.setRol( SecurityUtils.getRol() );
		return securityGwtUtils;
	}

}

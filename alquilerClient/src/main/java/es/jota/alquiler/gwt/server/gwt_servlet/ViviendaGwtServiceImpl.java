package es.jota.alquiler.gwt.server.gwt_servlet;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import es.jota.alquiler.gwt.client.my.GWTController;
import es.jota.alquiler.gwt.client.service.ViviendaGwtService;
import jota.server.dto.Filtro;
import jota.server.dto.ViviendaDtoDown;
import jota.server.dto.ViviendaDtoUp;
import jota.server.exceptions.ServerException;
import jota.server.service.ViviendaService;

@Controller
@RequestMapping("/viviendaGwtService.gwt")
public class ViviendaGwtServiceImpl extends GWTController implements ViviendaGwtService {

	private static final long serialVersionUID = -649450201261649641L;
	static final Logger LOG = LoggerFactory.getLogger(ViviendaGwtServiceImpl.class);

	@Autowired
	ViviendaService viviendaService;

	@Override
	public List<ViviendaDtoDown> findAlquilables( int start, int size ) throws ServerException {
		try {
			return viviendaService.findAlquilables( new Filtro(start, size) );
    	} catch ( ServerException serverException ) {
			LOG.error( serverException.getUsrMessage() );
			throw serverException;
    	} catch ( Exception exception ) {
			LOG.error( exception.getLocalizedMessage() );
			throw new ServerException( exception );
		}
	}

	@Override
	public List<ViviendaDtoDown> findMisVivendas( int start, int size ) throws ServerException {
		try {
			return viviendaService.findMisVivendas( new Filtro(start, size) );
    	} catch ( ServerException serverException ) {
			LOG.error( serverException.getUsrMessage() );
			throw serverException;
    	} catch ( Exception exception ) {
			LOG.error( exception.getLocalizedMessage() );
			throw new ServerException( exception );
		}
	}

	@Override
	public List<ViviendaDtoDown> findMisVivendasByNombre( int start, int size, String nombre ) throws ServerException {
		try {
			return viviendaService.findMisVivendasByNombre( new Filtro(start, size), nombre );
    	} catch ( ServerException serverException ) {
			LOG.error( serverException.getUsrMessage() );
			throw serverException;
    	} catch ( Exception exception ) {
			LOG.error( exception.getLocalizedMessage() );
			throw new ServerException( exception );
		}
	}

	@Override
	public ViviendaDtoDown read( Long id ) throws ServerException {
		try {
			return viviendaService.read( id );
    	} catch ( ServerException serverException ) {
			LOG.error( serverException.getUsrMessage() );
			throw serverException;
    	} catch ( Exception exception ) {
			LOG.error( exception.getLocalizedMessage() );
			throw new ServerException( exception );
		}
	}

	@Override
	public void update( Long id, ViviendaDtoUp vivienda ) throws ServerException {
		try {
			viviendaService.update( id, vivienda );
    	} catch ( ServerException serverException ) {
			LOG.error( serverException.getUsrMessage() );
			throw serverException;
    	} catch ( Exception exception ) {
			LOG.error( exception.getLocalizedMessage() );
			throw new ServerException( exception );
		}
	}

	@Override
	public Long create( ViviendaDtoUp vivienda ) throws ServerException {
		try {
			return viviendaService.create( vivienda );
    	} catch ( ServerException serverException ) {
			LOG.error( serverException.getUsrMessage() );
			throw serverException;
    	} catch ( Exception exception ) {
			LOG.error( exception.getLocalizedMessage() );
			throw new ServerException( exception );
		}
}

	@Override
	public void delete( Long id) throws ServerException {
//		viviendaService.delete( id );
		throw new ServerException("Servicio no implementado"); 
	}
}
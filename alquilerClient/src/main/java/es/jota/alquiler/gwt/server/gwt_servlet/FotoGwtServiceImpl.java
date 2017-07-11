
package es.jota.alquiler.gwt.server.gwt_servlet;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import es.jota.alquiler.gwt.client.my.GWTController;
import es.jota.alquiler.gwt.client.service.FotoGwtService;
import es.jota.alquiler.gwt.server.FileUploadController;
import es.jota.alquiler.gwt.server.UploadedFile;
import jota.server.dto.Filtro;
import jota.server.dto.FotoDtoDown;
import jota.server.dto.FotoDtoUp;
import jota.server.exceptions.ServerException;
import jota.server.service.FotoService;

@Controller
@RequestMapping("/fotoGwtService.gwt")
public class FotoGwtServiceImpl extends GWTController implements FotoGwtService {

	private static final long serialVersionUID = -649450201261649641L;
	static final Logger LOG = LoggerFactory.getLogger(FotoGwtServiceImpl.class);

	@Autowired
	FileUploadController fileUploadController;
	
	@Autowired
	FotoService fotoService;

	@Override
	public List<FotoDtoDown> findFotosDeMiVivienda( long idVivienda, int start, int size ) throws ServerException {
		try {
			return fotoService.findFotosDeMiVivienda( idVivienda, new Filtro(start, size) );
    	} catch ( ServerException serverException ) {
			LOG.error( serverException.getUsrMessage() );
			throw serverException;
    	} catch ( Exception exception ) {
			LOG.error( exception.getLocalizedMessage() );
			throw new ServerException( exception );
		}
	}

	@Override
	public List<FotoDtoDown> findMisFotos( int start, int size ) throws ServerException {
		try {
			return fotoService.findMisFotos( new Filtro(start, size) );
    	} catch ( ServerException serverException ) {
			LOG.error( serverException.getUsrMessage() );
			throw serverException;
    	} catch ( Exception exception ) {
			LOG.error( exception.getLocalizedMessage() );
			throw new ServerException( exception );
		}
	}

	@Override
	public FotoDtoDown read( Long id ) throws ServerException {
		try {
			return fotoService.read( id );
    	} catch ( ServerException serverException ) {
			LOG.error( serverException.getUsrMessage() );
			throw serverException;
    	} catch ( Exception exception ) {
			LOG.error( exception.getLocalizedMessage() );
			throw new ServerException( exception );
		}
	}

	@Override
	public void update( Long id, FotoDtoUp foto, Integer idTag, Long idVivienda ) throws ServerException {
		try {
			fotoService.update( id, foto, idTag, idVivienda );
    	} catch ( ServerException serverException ) {
			LOG.error( serverException.getUsrMessage() );
			throw serverException;
    	} catch ( Exception exception ) {
			LOG.error( exception.getLocalizedMessage() );
			throw new ServerException( exception );
		}
	}

	@Override
	public Long create( FotoDtoUp foto, Integer idTag, Long idVivienda ) throws ServerException {
		try {
			Long dev = null;
			UploadedFile uploadedFile = fileUploadController.getUploadedFoto( foto.getMd5() );
			updateFoto( foto, uploadedFile );
			dev = fotoService.create( foto, idTag, idVivienda );
			fileUploadController.deleteUploadedFoto( foto.getMd5() );
			return dev;
    	} catch ( ServerException serverException ) {
			LOG.error( serverException.getUsrMessage() );
			throw serverException;
    	} catch ( Exception exception ) {
			LOG.error( exception.getLocalizedMessage() );
			throw new ServerException( exception );
		}
	}

	private void updateFoto( FotoDtoUp foto, UploadedFile uploadedFile ) {
		if ( uploadedFile != null ) {
			foto.setMd5( uploadedFile.getMd5() );
			foto.setNombreOriginal( uploadedFile.getName() );
			foto.setSize( uploadedFile.getSize() );
			foto.setType( uploadedFile.getType() );
			foto.setBytes( uploadedFile.getBytes() );
		}
	}

	@Override
	public void delete( Long id ) throws ServerException {
		try {
			fotoService.delete( id );
    	} catch ( ServerException serverException ) {
			LOG.error( serverException.getUsrMessage() );
			throw serverException;
    	} catch ( Exception exception ) {
			LOG.error( exception.getLocalizedMessage() );
			throw new ServerException( exception );
		}
	}

	@Override
	public void deleteFromController( String md5 ) throws ServerException {
		try {
			fileUploadController.deleteUploadedFoto( md5 );
    	} catch ( Exception exception ) {
			LOG.error( exception.getLocalizedMessage() );
			throw new ServerException( exception );
		}
	}
}
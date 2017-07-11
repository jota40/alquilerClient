package es.jota.alquiler.gwt.server;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import jota.server.exceptions.ServiceException;
import jota.server.service.FotoService;

@Controller
public class FileUploadController {

	static final Logger LOG = LoggerFactory.getLogger(FileUploadController.class);

	@Autowired
	FotoService fotoService;

	private static HashMap<String, UploadedFile> tmpFiles = new HashMap<String, UploadedFile>();
/*
	@RequestMapping(value = "/uploadFoto.spring", method = RequestMethod.POST)
	public @ResponseBody LinkedList<String> upload(MultipartHttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException {

		LinkedList<String> md5s = new LinkedList<String>();

		//1. build an iterator
		Iterator<String> itr =  request.getFileNames();
		MultipartFile multipartFile = null;

		//2. get each file
		while(itr.hasNext()){

			//2.1 get next MultipartFile
			multipartFile = request.getFile(itr.next());

			// create
			UploadedFile fileInfo = createUploadedFile( multipartFile ); 

			// adding info to the list
			md5s.add( fileInfo.getMd5() );

			// Save the file to hashmap
			tmpFiles.put( fileInfo.getMd5(), fileInfo  );

			// adding info to the list
			md5s.add( fileInfo.getMd5() );

			// Save the file to hashmap
			tmpFiles.put( fileInfo.getMd5(), fileInfo  );
		}

		return md5s;
	}
*/
	@RequestMapping(value = "/uploadFoto.spring", method = RequestMethod.POST)
	public @ResponseBody LinkedList<String> upload(MultipartHttpServletRequest request, HttpServletResponse response) throws IOException {

		// Maintain a list to send back the files info. to the client side
		LinkedList<String> md5s = new LinkedList<String>();

		// Getting uploaded files from the request object
		Map<String, MultipartFile> fileMap = request.getFileMap();

		// Iterate through the map
		for (MultipartFile multipartFile : fileMap.values()) {

			// create
			UploadedFile fileInfo = createUploadedFile( multipartFile ); 

			// adding info to the list
			md5s.add( fileInfo.getMd5() );

			// Save the file to hashmap
			tmpFiles.put( fileInfo.getMd5(), fileInfo  );
		}

		return md5s;
	}

	private UploadedFile createUploadedFile( MultipartFile multipartFile ) throws IOException {
		UploadedFile dev = new UploadedFile();
		dev.setBytes( multipartFile.getBytes() );
		dev.setName( multipartFile.getOriginalFilename() );
		dev.setSize( multipartFile.getSize() );
		dev.setType( multipartFile.getContentType() );
		return dev;
	}
	@RequestMapping(value = "/getUploadedFoto.spring", method = RequestMethod.GET)
	public void getUploadedFoto(HttpServletResponse response, @RequestParam long id) throws ServiceException {
		try {
			//FIXME puede que la key no exista
			FileCopyUtils.copy( fotoService.getFoto( id ), response.getOutputStream());
    	} catch ( Exception exception ) {
			LOG.error( exception.getLocalizedMessage() );
			throw new ServiceException( exception );
		}
	}
/*
	@RequestMapping(value = "/deleteUploadedFoto.spring", method = RequestMethod.POST)
	public void deleteFoto(HttpServletResponse response, @PathVariable String md5) {
		tmpFiles.remove( md5 );
		//FIXME responsebody ok o error
	}
	*/
	public UploadedFile getUploadedFoto( String md5 ) {
		return tmpFiles.get( md5 );
	}

	public void deleteUploadedFoto(String md5) {
		tmpFiles.remove( md5 );
	}
}

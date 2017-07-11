package es.jota.alquiler.gwt.client.widget.base;

import java.util.LinkedList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.FormElement;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import es.jota.alquiler.gwt.client.gin.GinService;
import es.jota.alquiler.gwt.client.my.MyAsyncCallback;

public class Dropzone extends Composite {
	
	private static DropzoneUiBinder uiBinder = GWT.create(DropzoneUiBinder.class);
	interface DropzoneUiBinder extends UiBinder<Widget, Dropzone>{}
	
	@UiField FormElement dropzoneForm;
	@UiField Element mensaje;
	JavaScriptObject myDropzone;
	private boolean autoSubmit = true;
	private boolean multiple = false;
	private int maxSize = 20;
	private int parallel = 1;
    private int maxFiles = 1;

	LinkedList<String> md5s = new LinkedList<String>();

	public Dropzone() {
		initWidget(uiBinder.createAndBindUi(this));
		final Dropzone temp = this;
		Scheduler.get().scheduleDeferred( new ScheduledCommand() {

			@Override
		    public void execute() {
		    	myDropzone = dropzone( temp, dropzoneForm, autoSubmit, multiple, maxSize, parallel, maxFiles );
			}
		});
	}

	public void setAction( String action ) {
		dropzoneForm.setAction( action );
	}

	public void setMaxSize( String maxSize ) {
		try {
			this.maxSize = Integer.parseInt( maxSize );
		} catch ( Exception e ){
		}
	}

	public void setMaxFiles( String maxFiles ) {
		try {
			this.maxFiles = Integer.parseInt( maxFiles );
		} catch ( Exception e ){
		}
	}

	public void setAutoSubmit( String autoSubmit ) {
		this.autoSubmit = autoSubmit.compareToIgnoreCase("true") == 0;
	}

	public void setMultiple( String multiple ) {
		this.multiple = multiple.compareToIgnoreCase("true") == 0;
	}

	public void setParallel( String parallel ) {
		try {
			this.parallel = Integer.parseInt( parallel );
		} catch ( Exception e ){
		}
	}

	public void setMensaje( String mensaje ) {
		this.mensaje.setInnerSafeHtml( new SafeHtmlBuilder().appendHtmlConstant( mensaje ).toSafeHtml() );
	}

	public void setClass( String className ) {
		setStyleName( className );
	}

	public void setStyle( String style ) {
		getElement().setAttribute( "style", style );
	}

	private static native JavaScriptObject dropzone( Dropzone x, Element e, boolean autoSubmit, boolean multiple, int maxSize, int parallel, int maxFiles ) /*-{
		var myDropzone;
		$wnd.jQuery(e).dropzone({ 
			autoProcessQueue : autoSubmit,
			uploadMultiple : multiple,
			maxFilesize : maxSize, // MB
			parallelUploads : parallel,
			maxFiles : maxFiles,
			addRemoveLinks : true,
	
			// The setting up of the dropzone
			init : function() {
	
				myDropzone = this;

				// customizing the default progress bar
				this.on("uploadprogress", function(file, progress) {
	
					progress = parseFloat(progress).toFixed(0);
	
					var progressBar = file.previewElement.getElementsByClassName("dz-upload")[0];
					progressBar.innerHTML = progress + "%";
				});
	
				this.on("success", function(file, response) {
					if ( !this.options.uploadMultiple ) {
						file.md5 = response;
						x.@es.jota.alquiler.gwt.client.widget.base.Dropzone::uploadComplete(*)(response);
					}
				});
				
				this.on("removedfile", function(file) {
//					alert( "remove = " + file.md5 ); 
					x.@es.jota.alquiler.gwt.client.widget.base.Dropzone::deleteFile(*)(file.md5);
//					alert( "removed" ); 
				});

				this.on("successmultiple", function(files, response) {
					if ( this.options.uploadMultiple ) {
						for ( i = 0; i < files.length; i++) {
							files[i].md5 = response[i];
							alert( "multiple = " + response[i] ); 
						}
						x.@es.jota.alquiler.gwt.client.widget.base.Dropzone::uploadComplete(*)(response);
//						alert( "multiple ok" ); 
					}
				});
			}
		});
		return myDropzone;
	}-*/;

	public void subir() { 
		Scheduler.get().scheduleDeferred( new ScheduledCommand() {
		    @Override
		    public void execute() {
				subir( myDropzone );
			}
		});
	}

	private native void subir( JavaScriptObject myDropzone ) /*-{
		myDropzone.processQueue();	
	}-*/;

	private void uploadComplete( JavaScriptObject response ) {
		JSONArray JSONResponse = new JSONArray( response );
		for ( int i = 0; i < JSONResponse.size() ; i++ ) {
			md5s.add( JSONResponse.get(i).isString().stringValue() );
		}
	}

	private void deleteFile( final Object md5 ) {
		if ( md5 != null ) {
			GinService.INSTANCE.getFotoGwtServiceAsync().deleteFromController( md5.toString(), new MyAsyncCallback<Void>("","") {
				@Override
				public void handleOnSuccess( Void result ) {
				}
			});
			md5s.remove( md5.toString() );
		}
	}

	public LinkedList<String> getMd5() {
		return md5s;
	}

	public void clean() {
		Scheduler.get().scheduleDeferred( new ScheduledCommand() {
		    @Override
		    public void execute() {
				clean( myDropzone );
				dropzoneForm.removeClassName("dz-started");
				// para evitar que quede algun colgado
				for (String md5 : md5s) {
					deleteFile(md5);
				}
				md5s.clear();
			}
		});
	}

	private native void clean( JavaScriptObject myDropzone ) /*-{
		myDropzone.removeAllFiles(true);	
	}-*/;
}
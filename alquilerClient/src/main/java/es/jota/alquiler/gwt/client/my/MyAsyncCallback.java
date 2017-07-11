package es.jota.alquiler.gwt.client.my;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.user.client.rpc.AsyncCallback;

import es.jota.alquiler.gwt.client.display.logs.AlertDisplay;
import es.jota.alquiler.gwt.client.gin.GinActivity;
import jota.server.exceptions.ServerException;

public abstract class MyAsyncCallback<T> implements AsyncCallback<T> {
	static final Logger LOG = Logger.getLogger(MyAsyncCallback.class.getName());

	private AlertDisplay alert;

	private String error;
	private String ok;

	public MyAsyncCallback( String titulo, String info ) {
		this( titulo, info, null, null );
	}

	public MyAsyncCallback( String titulo, String info, String error ) {
		this( titulo, info, error, null );
	}

	public MyAsyncCallback( String titulo, String info, String error, String ok ) {
		alert = GinActivity.INSTANCE.getLogsActivity().crearAlert( titulo, info );
		this.error = error;
		this.ok = ok;
	}

	@Override
	public void onFailure(Throwable caught){
		if ( caught instanceof ServerException ) {
			ServerException serviceException = ((ServerException)caught);
			error = serviceException.getUsrMessage();
		}
		alert.turnToFail( error );
		handleOnFailure( caught );
	}

	public void handleOnFailure(Throwable caught) {
		LOG.log( Level.SEVERE, "MyAsyncCallBack", caught );
	}

	@Override
	public void onSuccess(T result){
		if ( ok == null ) {
			alert.close();
		} else {
			alert.turnToSuccess( ok );
		}
		handleOnSuccess( result );
	}

	public abstract void handleOnSuccess(T result);
}
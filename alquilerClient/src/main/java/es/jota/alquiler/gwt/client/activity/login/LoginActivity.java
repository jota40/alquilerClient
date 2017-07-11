package es.jota.alquiler.gwt.client.activity.login;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;

import es.jota.alquiler.gwt.client.display.login.LoginDisplay;
import es.jota.alquiler.gwt.client.display.login.LogoutDisplay;
import es.jota.alquiler.gwt.client.event.SecurityGwtUtilsUpdatedEvent;
import es.jota.alquiler.gwt.client.gin.Gin;
import es.jota.alquiler.gwt.client.gin.GinDisplay;
import es.jota.alquiler.gwt.client.gin.GinService;
import es.jota.alquiler.gwt.client.my.MyAbstractActivity;
import es.jota.alquiler.gwt.client.my.MyAsyncCallback;
import es.jota.alquiler.gwt.shared.SecurityGwtUtils;
import es.jota.utils.gwt.client.my.MyPlace;

public class LoginActivity extends MyAbstractActivity<MyPlace> implements LoginDisplay.Presenter, LogoutDisplay.Presenter {
	
	private LoginDisplay displayNoUser = GinDisplay.INSTANCE.getLoginDisplay();
	private LogoutDisplay displayUser = GinDisplay.INSTANCE.getLogoutDisplay();
	
	public LoginActivity() {
		displayNoUser.setPresenter( this );
		displayUser.setPresenter( this );
	}

	@Override
	public void start() {
		if ( Gin.INSTANCE.getSecurityGwtUtils().getIdUsuario() != null ) {
			containerWidget.setWidget( displayUser.asWidget() );
		} else {
			containerWidget.setWidget( displayNoUser.asWidget() );
		}
		fillForm();
	}

	private void fillForm() {
		displayUser.setUsuario( Gin.INSTANCE.getSecurityGwtUtils().getNombreUsuario() );
		displayUser.setRol( Gin.INSTANCE.getSecurityGwtUtils().getRol() );
		displayUser.hideError();
		displayNoUser.setLogin( "" );
		displayNoUser.setPass( "" );
		displayNoUser.hideError();
	}

	@Override
	public void login() {
		RequestBuilder rb = new RequestBuilder(RequestBuilder.POST, "/login");
		rb.setHeader("Content-Type", "application/x-www-form-urlencoded");
		rb.setRequestData("username=" + URL.encode( displayNoUser.getLogin() + "&password=" + URL.encode( displayNoUser.getPass() ) ) );
		rb.setCallback(new RequestCallback() {
			@Override
			public void onResponseReceived( Request request, final Response response ) {
				if (response.getStatusCode() == 200) {
					loginSuccess();
				} else {
					loginFail();
				}
			}

			@Override
			public void onError(Request request, Throwable exception) {
				loginFail();
			}
		});

		try {
			rb.send();
		} catch (RequestException re) {
			re.printStackTrace();
		}
	}

	@Override
	public void logout() {
		RequestBuilder rb = new RequestBuilder(RequestBuilder.POST, "/logout");
		try {
			rb.sendRequest(null, new RequestCallback() {
				public void onResponseReceived(Request request, Response response) {
					if (response.getStatusCode() == 200) {
						logoutSuccess();
					} else {
						logoutFail();
					}
				}
				public void onError(Request request, Throwable caught) {
					logoutFail();
				}
			});
		} catch (RequestException re) {
		}
	}


	private void loginSuccess() {
		containerWidget.setWidget(displayUser.asWidget());
		GinService.INSTANCE.getUsuarioGwtServiceAsync().getSecurityGwtUtils( new MyAsyncCallback<SecurityGwtUtils>( "Login", "Correcto" ) {
			@Override
			public void handleOnSuccess( SecurityGwtUtils result ) {
				Gin.INSTANCE.getSecurityGwtUtils().update( result );
				SecurityGwtUtilsUpdatedEvent.throwSecurityGwtUtilsUpdated( globalEventBus, SecurityGwtUtilsUpdatedEvent.LOGIN );
			}
		});
	}
	private void loginFail() {
		displayNoUser.showError();
		displayNoUser.focus();
	}
	private void logoutSuccess() {
		containerWidget.setWidget(displayNoUser.asWidget());
		GinService.INSTANCE.getUsuarioGwtServiceAsync().getSecurityGwtUtils( new MyAsyncCallback<SecurityGwtUtils>( "Logout", "Correcto" ) {
			@Override
			public void handleOnSuccess( SecurityGwtUtils result ) {
				Gin.INSTANCE.getSecurityGwtUtils().update( result );
				SecurityGwtUtilsUpdatedEvent.throwSecurityGwtUtilsUpdated( globalEventBus, SecurityGwtUtilsUpdatedEvent.LOGOUT );
			}
		});
	}
	private void logoutFail() {
		displayUser.showError();
	}
}
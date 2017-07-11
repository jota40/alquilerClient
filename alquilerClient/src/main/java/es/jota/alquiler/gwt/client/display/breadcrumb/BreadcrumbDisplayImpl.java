package es.jota.alquiler.gwt.client.display.breadcrumb;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

public class BreadcrumbDisplayImpl extends Composite implements BreadcrumbDisplay {

	private static BreadcrumbDisplayUiBinder uiBinder = GWT.create(BreadcrumbDisplayUiBinder.class);
	interface BreadcrumbDisplayUiBinder extends UiBinder<Widget, BreadcrumbDisplayImpl>{}

	@UiField HTMLPanel breadcrumb;

	private BreadcrumbDisplay.Presenter presenter;

	@Override
	public void setPresenter( BreadcrumbDisplay.Presenter Presenter ) {
		this.presenter = Presenter;
	}

	public BreadcrumbDisplayImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setBreadcrumbs( SafeHtml breadcrumbs ) {
		breadcrumb.getElement().setInnerSafeHtml( breadcrumbs );
	}

	@Override
	public void cleanBreadcrumbs() {
		breadcrumb.getElement().setInnerHTML("");
	}
}
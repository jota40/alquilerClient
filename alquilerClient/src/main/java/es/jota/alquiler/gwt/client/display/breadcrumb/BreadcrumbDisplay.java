package es.jota.alquiler.gwt.client.display.breadcrumb;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.IsWidget;

public interface BreadcrumbDisplay extends IsWidget {
	void setPresenter( BreadcrumbDisplay.Presenter Presenter );
	public interface Presenter {
	}

	void setBreadcrumbs( SafeHtml breadcrumbs );
	void cleanBreadcrumbs();
}
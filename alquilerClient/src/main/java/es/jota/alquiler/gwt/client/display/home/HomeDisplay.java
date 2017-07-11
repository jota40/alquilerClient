package es.jota.alquiler.gwt.client.display.home;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.IsWidget;

import es.jota.utils.gwt.client.table.paginator.MyPager.MyPagerView;
import jota.server.dto.ViviendaDtoDown;

public interface HomeDisplay extends IsWidget {
	void setPresenter( HomeDisplay.Presenter Presenter );
	public interface Presenter {
		void reset();
		void refresh();
		void borrar( Long idVivienda );
	}
	CellTable<ViviendaDtoDown> getTabla();
	MyPagerView getPagerDisplay();

	void configure();
	boolean isMisViviendas();
}
package es.jota.alquiler.gwt.client.display.foto;

import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.IsWidget;

import es.jota.utils.gwt.client.table.paginator.MyPager.MyPagerView;
import jota.server.dto.FotoDtoDown;

public interface FotoDisplayListar extends IsWidget {
	void setPresenter( FotoDisplayListar.Presenter Presenter );
	public interface Presenter {
		void reset();
		void refresh();
		void borrar( Long id );
	}
	CellTable<FotoDtoDown> getTabla();
	MyPagerView getPagerDisplay();
}
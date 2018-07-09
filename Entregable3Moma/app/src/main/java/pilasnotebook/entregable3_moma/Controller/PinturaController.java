package pilasnotebook.entregable3_moma.Controller;

import java.util.List;

import pilasnotebook.entregable3_moma.Model.DAO.PinturaRetrofitDAO;
import pilasnotebook.entregable3_moma.Model.Pintura;
import pilasnotebook.entregable3_moma.Utils.ResultListener;

public class PinturaController {


    public void obtenerPinturasController(final ResultListener<List<Pintura>> resultListenerDeLaVista) {
        if (hayInternet()) {
            PinturaRetrofitDAO pinturaRetrofitDAO = new PinturaRetrofitDAO();
            pinturaRetrofitDAO.obtenerPinturasDAO(new ResultListener<List<Pintura>>() {


                @Override
                public void finish(List<Pintura> pinturas) {
                    resultListenerDeLaVista.finish(pinturas);
                }
            });
        }
    }

    private boolean hayInternet() {
        return true;
    }
}



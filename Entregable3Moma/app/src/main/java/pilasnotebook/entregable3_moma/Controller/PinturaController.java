package pilasnotebook.entregable3_moma.Controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.ArrayList;
import java.util.List;

import pilasnotebook.entregable3_moma.Model.Artista;
import pilasnotebook.entregable3_moma.Model.DAO.ArtistaDAO;
import pilasnotebook.entregable3_moma.Model.DAO.PinturaRetrofitDAO;
import pilasnotebook.entregable3_moma.Model.Pintura;
import pilasnotebook.entregable3_moma.Utils.ResultListener;

public class PinturaController {

    private Context context;



    public PinturaController(Context context) {
        this.context = context;

    }

//este metodo lo uso para pedirle al dao retrofit
    public void obtenerPinturasController(final ResultListener<List<Pintura>> resultListenerDeLaVista) {
        if (hayInternet()) {
            PinturaRetrofitDAO pinturaRetrofitDAO = new PinturaRetrofitDAO();
            pinturaRetrofitDAO.obtenerPinturasDAO(new ResultListener<List<Pintura>>() {


                @Override
                public void finish(List<Pintura> pinturas) {
                    resultListenerDeLaVista.finish(pinturas);
                }
            });
        } else {
            resultListenerDeLaVista.finish(new ArrayList<Pintura>());
        }
    }

// este metodo lo uso para pedirle al dao firebase
    public void obtenerArtistaController(String idArtista, final ResultListener<Artista> ResultListenerDeDetallePintura) {
        if (hayInternet()) {
            ArtistaDAO artistaDAO = new ArtistaDAO();
            artistaDAO.obtenerArtistaDeDAO(idArtista, new ResultListener<Artista>() {
                @Override
                public void finish(Artista artista) {
                    ResultListenerDeDetallePintura.finish(artista);


                }
            });
        }else {
            ResultListenerDeDetallePintura.finish(new Artista());
        }
    }



        public boolean hayInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
    }
}




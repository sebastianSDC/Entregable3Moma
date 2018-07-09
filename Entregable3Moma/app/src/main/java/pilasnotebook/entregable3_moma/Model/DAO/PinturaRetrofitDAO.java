package pilasnotebook.entregable3_moma.Model.DAO;

import java.util.ArrayList;
import java.util.List;

import pilasnotebook.entregable3_moma.Model.Pintura;
import pilasnotebook.entregable3_moma.Model.PinturaContainer;
import pilasnotebook.entregable3_moma.Utils.ResultListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PinturaRetrofitDAO {

    private static final String BASE_URL = "https://api.myjson.com/bins/";
    private Retrofit retrofit;
    private ServicePintura servicePintura;

    public PinturaRetrofitDAO() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        retrofit = builder.build();
        //tengo que pasarle el tipo de dato de la interfaz, en este caso ServiceNoticias.class
        servicePintura = retrofit.create(ServicePintura.class);
    }


    public void obtenerPinturasDAO(final ResultListener<List<Pintura>> resultListenerDelController) {
        servicePintura.obtenerPinturas().enqueue(new Callback<PinturaContainer>() {
            @Override
            public void onResponse(Call<PinturaContainer> call, Response<PinturaContainer> response) {
                PinturaContainer pinturaContainerobtenido = response.body();
                if (pinturaContainerobtenido != null && pinturaContainerobtenido.getPaints() != null) {
                    List<Pintura> listaPinturas = pinturaContainerobtenido.getPaints();
                    resultListenerDelController.finish(listaPinturas);
                } else {
                    resultListenerDelController.finish(new ArrayList<Pintura>());
                }
            }

            @Override
            public void onFailure(Call<PinturaContainer> call, Throwable t) {
                resultListenerDelController.finish(new ArrayList<Pintura>());
            }

        });
    }


}

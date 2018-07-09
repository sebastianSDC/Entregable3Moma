package pilasnotebook.entregable3_moma.Model.DAO;


import pilasnotebook.entregable3_moma.Model.PinturaContainer;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ServicePintura {

    @GET("x858r")
    Call<PinturaContainer> obtenerPinturas();
}

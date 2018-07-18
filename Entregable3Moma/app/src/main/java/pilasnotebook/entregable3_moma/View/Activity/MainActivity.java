package pilasnotebook.entregable3_moma.View.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import pilasnotebook.entregable3_moma.Controller.PinturaController;
import pilasnotebook.entregable3_moma.Model.Pintura;
import pilasnotebook.entregable3_moma.R;
import pilasnotebook.entregable3_moma.Utils.ResultListener;
import pilasnotebook.entregable3_moma.View.Adapter.PinturaAdapter;

public class MainActivity extends AppCompatActivity{

    private TextView textViewMarquee;
    private RecyclerView recyclerView;
    private PinturaAdapter pinturaAdapter;
    private PinturaController pinturaController;

    private Button botonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewMarquee = findViewById(R.id.marquee);
        recyclerView = findViewById(R.id.recycler_id);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2, 0, false));
        recyclerView.setHasFixedSize(true);
        marquee("En el MoMA. Modern Museum of Arts, usted puede encontrar la mas variada coleccion de arte, como las expuestas en esta galeria de imagenes.");

        pinturaAdapter = new PinturaAdapter();
        recyclerView.setAdapter(pinturaAdapter);

        //implemento la interface creando una clase anonima a la cual le debo implementar el metodo de dicha interface.
        pinturaAdapter.setNotificadorPinturaCelda(new PinturaAdapter.NotificadorPinturaCelda() {
            @Override
            public void notificarPinturaClickeada(Pintura pintura) {
                
            }
        });
        pinturaController = new PinturaController(this);
        obtenerPinturasView();

        botonLogout = findViewById(R.id.boton_logout);

        botonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                    if (AccessToken.getCurrentAccessToken() != null) {
                        //si es que esta logueado con facebook, tengo que desloguearlo
                        LoginManager.getInstance().logOut();
                    }
                    //esto es para desloguearlo de firebase, ya se que entro con facebok o nativo
                    FirebaseAuth.getInstance().signOut();
                    onBackPressed();
                }
            }
        });

    }


    public void marquee(String texto) {
        textViewMarquee.setText(texto);
        textViewMarquee.setSelected(true);
        //Toast.makeText(HomeActivity.this,texto,Toast.LENGTH_LONG).show();
    }

    private void obtenerPinturasView() {
        pinturaController.obtenerPinturasController(new ResultListener<List<Pintura>>() {
            @Override
            public void finish(List<Pintura> pinturas) {
                pinturaAdapter.agregarPinturas(pinturas);
            }
        });
    }
}

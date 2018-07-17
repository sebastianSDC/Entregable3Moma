package pilasnotebook.entregable3_moma.View.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;


import java.util.List;

import pilasnotebook.entregable3_moma.Controller.PinturaController;
import pilasnotebook.entregable3_moma.Model.Pintura;
import pilasnotebook.entregable3_moma.R;
import pilasnotebook.entregable3_moma.Utils.ResultListener;
import pilasnotebook.entregable3_moma.View.Adapter.PinturaAdapter;

public class MainActivity extends AppCompatActivity {

    private TextView textViewMarquee;
    private RecyclerView recyclerView;
    private PinturaAdapter pinturaAdapter;
    private PinturaController pinturaController;


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
        pinturaController = new PinturaController();
        obtenerPinturasView();





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

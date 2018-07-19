package pilasnotebook.entregable3_moma.View.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import pilasnotebook.entregable3_moma.Controller.PinturaController;
import pilasnotebook.entregable3_moma.Model.Artista;
import pilasnotebook.entregable3_moma.Model.Pintura;
import pilasnotebook.entregable3_moma.R;
import pilasnotebook.entregable3_moma.Utils.ResultListener;

public class DetallePinturaActivity extends AppCompatActivity {

    private PinturaController pinturaController;
    private TextView nombreArtista;
    private TextView nacionalidadArtista;
    private TextView influenciaArtista;
    private ImageView fotoPintura;
    private TextView tituloPintura;
    public static final String KEY_PINTURA = "pintura";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pintura);
        fotoPintura = findViewById(R.id.foto_pinturaBig);
        tituloPintura = findViewById(R.id.titulo_pinturaBig);
        nombreArtista = findViewById(R.id.nombre_artista);
        nacionalidadArtista = findViewById(R.id.nacionalidad_artista);
        influenciaArtista = findViewById(R.id.influencia_artista);

        Bundle bundle = getIntent().getExtras();
        final Pintura pintura = (Pintura) bundle.getSerializable(KEY_PINTURA);
        tituloPintura.setText(pintura.getName());


        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference storageReference = firebaseStorage.getReference();
        // le indico la ruta de donde buscar la imagen
        storageReference = storageReference.child(pintura.getImage());

        Glide.with(DetallePinturaActivity.this)
                .using(new FirebaseImageLoader())
                .load(storageReference)
                .into(fotoPintura);

        pinturaController = new PinturaController(this);

        pinturaController.obtenerArtistaController(pintura.getArtistId(), new ResultListener<Artista>() {
            @Override
            public void finish(Artista artista) {
                pintura.setArtista(artista);
                nombreArtista.setText(pintura.getArtista().getName());
                nacionalidadArtista.setText(pintura.getArtista().getNationality());
                influenciaArtista.setText(pintura.getArtista().getInfluenced_by());
            }
        });


    }
}


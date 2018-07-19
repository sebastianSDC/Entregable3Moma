package pilasnotebook.entregable3_moma.Model.DAO;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import pilasnotebook.entregable3_moma.Model.Artista;
import pilasnotebook.entregable3_moma.Utils.ResultListener;

public class ArtistaDAO {

    private static final String ARTISTS = "artists";

    public void obtenerArtistaDeDAO(String id, final ResultListener<Artista> ResultListenerDelController) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference().child(ARTISTS).child(id);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
// lo que te pase convertilo a objeto Artista
                if (dataSnapshot.exists()) {
                    Artista artista = dataSnapshot.getValue(Artista.class);
                    ResultListenerDelController.finish(artista);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("error", databaseError.toString());
            }
        });
    }

    public void obtenerArtistas(final ResultListener<List<Artista>> resultListener){
        FirebaseDatabase artistasDatabase = FirebaseDatabase.getInstance();
        DatabaseReference reference = artistasDatabase.getReference().child(ARTISTS);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    List<Artista> artistas = new ArrayList<>();
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                        Artista artista = snapshot.getValue(Artista.class);
                        artistas.add(artista);
                    }
                    resultListener.finish(artistas);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }




}



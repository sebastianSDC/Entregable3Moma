package pilasnotebook.entregable3_moma.Model.DAO;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import pilasnotebook.entregable3_moma.Model.Artista;
import pilasnotebook.entregable3_moma.Utils.ResultListener;

public class ArtistaDAO {

    private static final String Artists = "artists";

    public void obtenerArtistaDeFirebase(String idArtista, final ResultListener<Artista> ResultListenerDelController) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference().child(Artists).child(idArtista);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    Artista artista = dataSnapshot.getValue(Artista.class);
                    ResultListenerDelController.finish(artista);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}



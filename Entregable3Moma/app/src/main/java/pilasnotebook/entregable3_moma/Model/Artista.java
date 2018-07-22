package pilasnotebook.entregable3_moma.Model;

import com.google.firebase.database.PropertyName;
import com.google.gson.annotations.SerializedName;

public class Artista {

    private String artistId;
    private String name;
    private String nationality;
   // @PropertyName("Influenced_by")
    private String Influenced_by;


    public Artista(String artistId, String name, String nationality, String Influenced_by) {
        this.artistId = artistId;
        this.name = name;
        this.nationality = nationality;
        this.Influenced_by = Influenced_by;
    }

    public Artista(){
    }

    public String getArtistId() {
        return artistId;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public String getInfluenced_by() {
        return Influenced_by;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Artista)) {
            return false;
        }
        Artista artistaAComparar = (Artista) obj;
        return (artistaAComparar.getArtistId().equals(this.artistId));

    }
}

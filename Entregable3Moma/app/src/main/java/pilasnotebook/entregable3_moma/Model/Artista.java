package pilasnotebook.entregable3_moma.Model;

import com.google.gson.annotations.SerializedName;

public class Artista {

    private String artistId;
    private String name;
    private String nationality;
    @SerializedName("Influenced_by")
    private String influencia;


    public Artista(String artistId, String name, String nationality, String influencia) {
        this.artistId = artistId;
        this.name = name;
        this.nationality = nationality;
        this.influencia = influencia;
    }

    public Artista(){
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
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

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getInfluencia() {
        return influencia;
    }

    public void setInfluencia(String influencia) {
        this.influencia = influencia;
    }

    @Override
    public String toString() {
        return "Autor: " + name + " / Nacionalidad: " + nationality + " / Influencia: " + influencia;
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

package pilasnotebook.entregable3_moma.Model;

import java.io.Serializable;

public class Pintura implements Serializable {

    private String name;
    private String artistId;
    private String image;

    // al ir haciendo el ejercicio me di cuenta que necesitaba ponerle como atributo
    // un Artista para saber que ID esta clickeando en el recycler y poder sacarle los datos correctos.
    private Artista artista;

    public Pintura(String name, String artistId, String image, Artista artista) {
        this.name = name;
        this.artistId = artistId;
        this.image = image;
        this.artista = artista;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Nombre de la obra: " + name;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Pintura)) {
            return false;
        }
        Pintura pinturaAComparar = (Pintura) obj;
        return (pinturaAComparar.getName().equals(this.name));
    }
}

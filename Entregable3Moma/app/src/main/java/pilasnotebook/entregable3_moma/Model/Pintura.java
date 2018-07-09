package pilasnotebook.entregable3_moma.Model;

public class Pintura {

    private String name;
    private String artistId;
    private String image;


    public Pintura(String name, String artistId, String image) {
        this.name = name;
        this.artistId = artistId;
        this.image = image;
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

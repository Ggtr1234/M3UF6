package Artist.artistBasicJDBC;

import Album.albumBasicJDBC.Connexio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Artist {
    private int idArtista;
    private String nom;
    private static Connection con = Connexio.getConnection();

    public Artist() {
        super();
    }

    public Artist(int idArtista, String nom) {
        this.idArtista = idArtista;
        this.nom = nom;
    }

    public int getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(int idArtista) {
        this.idArtista = idArtista;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean verificarExistenciaArtista(int idArtista){
            Statement stmt = null;
            Artist artista = null;
            try {
                String query = "SELECT * FROM Artist WHERE ArtistId = ?";
                PreparedStatement ps = con.prepareStatement(query);
                ps.setInt(1,idArtista);

                ResultSet rs = ps.executeQuery();
                while ( rs.next() ) {
                    int albumId = rs.getInt("ArtistId");
                    String  nom = rs.getString("Name");
                    artista = new Artist(idArtista,nom);
                }
                rs.close();
                ps.close();
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            }

            if (artista != null){
                return true;
            }else {
                return false;
            }

    }
    @Override
    public String toString() {
        return "Artist{" +
                "idArtista=" + idArtista +
                ", nom='" + nom + '\'' +
                '}';
    }
}

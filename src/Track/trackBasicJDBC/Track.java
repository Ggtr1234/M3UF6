package Track.trackBasicJDBC;

import Album.albumDao.Album;
import Artist.artistBasicJDBC.Artist;
import genre.Genre;
import mediaType.MediaType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Track {
    private static Connection con = Connexio.getConnection();
    private int trackID;
    private String nom;
    private Album album;
    private MediaType mediaType;
    private Genre genre;
    private String composer;
    private int milliseconds;
    private int bytes;
    private int unitPrice;

    public int getTrackID() {
        return trackID;
    }

    public void setTrackID(int trackID) {
        this.trackID = trackID;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public int getMilliseconds() {
        return milliseconds;
    }

    public void setMilliseconds(int milliseconds) {
        this.milliseconds = milliseconds;
    }

    public int getBytes() {
        return bytes;
    }

    public void setBytes(int bytes) {
        this.bytes = bytes;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Track(int trackID, String nom, Album album, MediaType mediaType, Genre genre, String composer, int milliseconds, int bytes, int unitPrice) {
        this.trackID = trackID;
        this.nom = nom;
        this.album = album;
        this.mediaType = mediaType;
        this.genre = genre;
        this.composer = composer;
        this.milliseconds = milliseconds;
        this.bytes = bytes;
        this.unitPrice = unitPrice;
    }

    public Track() {
    }

    public void modificaTrack(int trackId, String name, int albumId, int mediaTypeId, int genreId, String composer, int milliseconds, int bytes, int unitPrice) {
        try {
            con.setAutoCommit(false);
            String query = "UPDATE Track set TrackId = ?, Name = ?, AlbumId = ?, MediaTypeId = ?, GenreId = ?, Composer = ?, Milliseconds = ?, Bytes = ?, UnitPrice = ? WHERE TrackId = ?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, trackId);
            ps.setString(2, name);
            ps.setInt(3, albumId);
            ps.setInt(4,mediaTypeId);
            ps.setInt(5,genreId);
            ps.setString(6,composer);
            ps.setInt(7,milliseconds);
            ps.setInt(8,bytes);
            ps.setInt(9,unitPrice);
            ps.setInt(10,trackId);
            ps.executeUpdate();
            con.commit();
            ps.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully");
    }

    public boolean verificarExistenciaTrack(int trackId) {
        Track track = null;
        try {
            String query = "SELECT t.TrackId as TrackId, t.Name as Name, t.AlbumId as AlbumId, a.Title as Title, a.ArtistId as ArtistId," +
                    "m.MediaTypeId as MediaTypeId, m.Name as MediaTypeName, g.GenreId as GenreId, g.Name as GenreName,t.Composer as Composer," +
                    "t.Milliseconds as Milliseconds, t.Bytes as Bytes, t.UnitPrice as UnitPrice " +
                    "FROM Track t " +
                    "JOIN Album a ON a.AlbumId = t.AlbumId " +
                    "JOIN MediaType m ON m.MediaTypeId = t.MediaTypeId " +
                    "JOIN Genre g ON g.GenreId = t.GenreId " +
                    "WHERE t.TrackId = ?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, trackId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int atrackId = rs.getInt("TrackId");
                String name = rs.getString("Name");
                int albumId = rs.getInt("AlbumId");
                String title = rs.getString("Title");
                int artistId = rs.getInt("ArtistId");
                int mediaTypeId = rs.getInt("MediaTypeId");
                String mediaTypeName = rs.getString("MediaTypeName");
                int genreId = rs.getInt("GenreId");
                String genreName = rs.getString("GenreName");
                String composer = rs.getString("Composer");
                int miliSec = rs.getInt("Milliseconds");
                int bytes = rs.getInt("Bytes");
                int unitPrice = rs.getInt("UnitPrice");
                track = new Track(atrackId, name, new Album(albumId, title, artistId), new MediaType(mediaTypeId, mediaTypeName), new Genre(genreId, genreName), composer, miliSec, bytes, unitPrice);

            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        if (track != null) {
            return true;
        } else {
            return false;
        }
    }

    public int creaTrack(String nom, int albumId, int mediaTypeId, int genreId, String composer, int milliseconds, int bytes, int unitPrice) {
        Statement stmt = null;
        int idTrackNew = -1;
        try {

//            int albumId = album.getIdAlbum();
//            int mediaTypeId = mediaType.getMediaTypeId();
//            int genreId = genre.getGenreId();
            //Creem la consulta de la PreparedStatement
            String query = "INSERT INTO Track (Name,AlbumId,MediaTypeId,GenreId,Composer,Milliseconds,Bytes,UnitPrice) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            //Modifiquem i executem la PreparedStatement
            ps.setString(1, nom);
            ps.setInt(2, albumId);
            ps.setInt(3, mediaTypeId);
            ps.setInt(4, genreId);
            ps.setString(5, composer);
            ps.setInt(6, milliseconds);
            ps.setInt(7, bytes);
            ps.setInt(8, unitPrice);
            ps.executeUpdate();

            // Obtenim claus autogenerades
            ResultSet rs = ps.getGeneratedKeys();
            rs.next(); // Sabem que només n'hi ha una
            idTrackNew = rs.getInt(1);

            ps.close();
            System.out.println("Records created successfully");
            return idTrackNew;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return idTrackNew;
    }

    public List<Track> seleccionaTracks() {
        Statement stmt = null;
        List<Track> tracks = new ArrayList<>();
        try {
            //Crear una consulta / query amb un object Statement
            stmt = con.createStatement();
            //Executar la consulta
            ResultSet rs = stmt.executeQuery("" +
                    "SELECT t.TrackId as TrackId, t.Name as Name, t.AlbumId as AlbumId, a.Title as Title, a.ArtistId as ArtistId," +
                    "m.MediaTypeId as MediaTypeId, m.Name as MediaTypeName, g.GenreId as GenreId, g.Name as GenreName,t.Composer as Composer," +
                    "t.Milliseconds as Milliseconds, t.Bytes as Bytes, t.UnitPrice as 'UnitPrice'" +
                    "FROM Track t " +
                    "JOIN Album a ON a.AlbumId = t.AlbumId " +
                    "JOIN MediaType m ON m.MediaTypeId = t.MediaTypeId " +
                    "JOIN Genre g ON g.GenreId = t.GenreId;");

            //Procesar el resultat amb l’objecte ResultSet
            while (rs.next()) {
                int trackId = rs.getInt("TrackId");
                String name = rs.getString("Name");
                int albumId = rs.getInt("AlbumId");
                String title = rs.getString("Title");
                int artistId = rs.getInt("ArtistId");
                int mediaTypeId = rs.getInt("MediaTypeId");
                String mediaTypeName = rs.getString("MediaTypeName");
                int genreId = rs.getInt("GenreId");
                String genreName = rs.getString("GenreName");
                String composer = rs.getString("Composer");
                int miliSec = rs.getInt("Milliseconds");
                int bytes = rs.getInt("Bytes");
                int unitPrice = rs.getInt("UnitPrice");
                tracks.add(new Track(trackId, name, new Album(albumId, title, artistId), new MediaType(mediaTypeId, mediaTypeName), new Genre(genreId, genreName), composer, miliSec, bytes, unitPrice));
            }
            rs.close();
            stmt.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully");
        return tracks;
    }

    public Track llegeixTrack(int trackId) {
        Track track = null;
        try {
            String query = "SELECT t.TrackId as TrackId, t.Name as Name, t.AlbumId as AlbumId, a.Title as Title, a.ArtistId as ArtistId," +
                    "m.MediaTypeId as MediaTypeId, m.Name as MediaTypeName, g.GenreId as GenreId, g.Name as GenreName,t.Composer as Composer," +
                    "t.Milliseconds as Milliseconds, t.Bytes as Bytes, t.UnitPrice as UnitPrice " +
                    "FROM Track t " +
                    "JOIN Album a ON a.AlbumId = t.AlbumId " +
                    "JOIN MediaType m ON m.MediaTypeId = t.MediaTypeId " +
                    "JOIN Genre g ON g.GenreId = t.GenreId " +
                    "WHERE t.TrackId = ?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, trackId);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int atrackId = rs.getInt("TrackId");
                String name = rs.getString("Name");
                int albumId = rs.getInt("AlbumId");
                String title = rs.getString("Title");
                int artistId = rs.getInt("ArtistId");
                int mediaTypeId = rs.getInt("MediaTypeId");
                String mediaTypeName = rs.getString("MediaTypeName");
                int genreId = rs.getInt("GenreId");
                String genreName = rs.getString("GenreName");
                String composer = rs.getString("Composer");
                int miliSec = rs.getInt("Milliseconds");
                int bytes = rs.getInt("Bytes");
                int unitPrice = rs.getInt("UnitPrice");
                track = new Track(atrackId, name, new Album(albumId, title, artistId), new MediaType(mediaTypeId, mediaTypeName), new Genre(genreId, genreName), composer, miliSec, bytes, unitPrice);

            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully");
        return track;
    }
    public void eliminaTrack(int trackId) {
        try {
            con.setAutoCommit(false);
            String query = "DELETE from Track where TrackId=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, trackId);
            ps.executeUpdate();
            con.commit();
            ps.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Operation done successfully");
    }

    @Override
    public String toString() {
        return "\nTrack{" +
                "trackID=" + trackID +
                ", nom='" + nom + '\'' +
                ", album=" + album +
                ", mediaType=" + mediaType +
                ", genre=" + genre +
                ", composer='" + composer + '\'' +
                ", milliseconds=" + milliseconds +
                ", bytes=" + bytes +
                ", unitPrice=" + unitPrice +
                "}";
    }



}

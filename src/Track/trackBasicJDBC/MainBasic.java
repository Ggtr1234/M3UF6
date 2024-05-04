package Track.trackBasicJDBC;

import Album.albumBasicJDBC.Album;
import Artist.artistBasicJDBC.Artist;
import genre.Genre;
import mediaType.MediaType;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainBasic {
    public void menu(){
        System.out.println("Diguis quina opció vols executar:\n"
                        + "1) Llista de Tracks\n"
                        + "2) Selecciona un sol Track\n"
                        + "3) Introdueix un Track\n"
                        + "4) Modifica un Track\n"
                        + "5) Elimina un Track\n"
                        + "0) Sortir\n"
                );
    }

    public static void main( String args[] ) throws SQLException {
        Track track = new Track();
        Genre genre = new Genre();
        MediaType mediaType = new MediaType();
        Scanner sc = new Scanner(System.in);
        MainBasic main = new MainBasic();
        main.menu();
        int opcio = sc.nextInt();sc.nextLine();

        while (opcio!=0){
            switch(opcio){
                case 1: {
                    System.out.println(track.seleccionaTracks());
                    break;
                }
                case 2:{
                    System.out.println("Introdueix quin track vols veure");
                    int trackId = sc.nextInt();sc.nextLine();
                    Track trackLlegit = track.llegeixTrack(trackId);
                    System.out.println(trackLlegit);
                    System.out.println();
                    break;
                }
//                case 3:{
//                    System.out.println("Introdueix el titol nou");
//                    String titol = sc.nextLine();
//                    System.out.println("Introdueix idArtista nou");
//                    try{
//                        int idArtista = sc.nextInt();
//                        System.out.println(album.creaAlbum(titol, idArtista));
//                    }catch (InputMismatchException e){
//                        sc.nextLine();
//                        System.out.println("Error introdueix nombre enter\n Error: " + e.toString());
//                    }
//
//                    break;
//                }
//                case 4:{
//                    System.out.println("Introdueix quin album vols modificar");
//                    try{
//                        int idAlbum = sc.nextInt();
//                        System.out.println("Introdueix el nou títol");
//                        String titol = sc.nextLine();
//                        System.out.println("Introdueix el idArtista nou");
//                        int idArtista = sc.nextInt();sc.nextLine();
//                        if (artist.verificarExistenciaArtista(idArtista)){
//                            album.modificaAlbum(idAlbum, titol, idArtista);
//                        }else {
//                            System.out.println("Este artista no existe");
//                        }
//
//                    }catch (InputMismatchException e){
//                        sc.nextLine();
//                        System.out.println("Error introdueix nombre enter\n Error: " + e.toString());
//                    }
//
//                    break;
//                }
//                case 5:{
//                    System.out.println("Introdueix quin album vols eliminar");
//                    int idAlbum = sc.nextInt();sc.nextLine();
//                    album.eliminaAlbum(idAlbum);
//                    break;
//                }
                case 0:{

                    break;
                }
                default:{
                    System.out.println("Introdueix un nombre vàlid del 0 al 6");
                    break;
                }
            }

            main.menu();
            opcio = sc.nextInt();sc.nextLine();
        }

    }
}
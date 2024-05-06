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
                case 3:{

                    try{
                        System.out.println("Introdueix el nom de Track nou");
                        String nom = sc.nextLine();
                        System.out.println("introdueix id de album");
                        int albumId = sc.nextInt();
                        System.out.println("Introdueix id de MedyaType");
                        int mediaTypeId = sc.nextInt();
                        System.out.println("Introdueix genreId");
                        int genreId = sc.nextInt();
                        System.out.println("Introdueix el autor nou");
                        String autor = sc.next();
                        System.out.println("Introduiex milliseconds que dura la Track");
                        int milliseconds = sc.nextInt();
                        System.out.println("Introdueix quants bytes ocupa");
                        int bytes = sc.nextInt();
                        System.out.println("Introdueix Preu: ");
                        int unitPrice = sc.nextInt();

                        System.out.println(track.creaTrack(nom,albumId,mediaTypeId,genreId,autor,milliseconds,bytes,unitPrice));
                    }catch (InputMismatchException e){
                        sc.nextLine();
                        System.out.println("Error\n Error: " + e.toString());
                    }

                    break;
                }
                case 4:{
                    System.out.println("Introdueix quin Track vols modificar");
                    try{
                        int trackId = sc.nextInt();
                        System.out.println("Introdueix el nom de Track nou");
                        String nom = sc.next();
                        System.out.println("introdueix id de album");
                        int albumId = sc.nextInt();sc.nextLine();
                        System.out.println("Introdueix id de MedyaType");
                        int mediaTypeId = sc.nextInt();
                        System.out.println("Introdueix genreId");
                        int genreId = sc.nextInt();
                        System.out.println("Introdueix el autor nou");
                        String autor = sc.next();
                        System.out.println("Introduiex milliseconds que dura la Track");
                        int milliseconds = sc.nextInt();
                        System.out.println("Introdueix quants bytes ocupa");
                        int bytes = sc.nextInt();
                        System.out.println("Introdueix Preu: ");
                        int unitPrice = sc.nextInt();
                        if (track.verificarExistenciaTrack(trackId)){
                            track.modificaTrack(trackId,nom,albumId,mediaTypeId,genreId,autor,milliseconds,bytes,unitPrice);
                        }else {
                            System.out.println("Este artista no existe");
                        }

                    }catch (InputMismatchException e){
                        sc.nextLine();
                        System.out.println("Error introdueix nombre enter\n Error: " + e.toString());
                    }

                    break;
                }
                case 5:{
                    System.out.println("Introdueix quin Track vols eliminar");
                    int trackId = sc.nextInt();sc.nextLine();
                    track.eliminaTrack(trackId);
                    break;
                }
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
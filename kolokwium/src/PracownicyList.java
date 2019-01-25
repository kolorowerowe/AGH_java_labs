import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import java.util.function.Predicate;


public class PracownicyList {
    List<Pracownik> pracownicy = new ArrayList<>();
    int wszyscy=0;


    public void read(String filename) throws IOException {
        CSVReader reader = new CSVReader(filename, ";", true);

        while (reader.next()) {

            Pracownik pr = new Pracownik();
            pr.set(reader.getInt(0),
                    reader.get(1),
                    reader.get(2),
                    reader.get(3),
                    reader.get(4),
                    reader.get(5),
                    reader.get(6),
                    reader.get(7)
                 );
            pracownicy.add(pr);
            wszyscy+=1;
        }

    }


    void list(PrintStream out) {
        for (Pracownik un : this.pracownicy ){
            out.print(un.toString());
        }
    }


    void ile(){
        int sumaKobiet=0;
        for (Pracownik un : this.pracownicy ){
            if (un.imie.endsWith("a") || un.imie.endsWith("a ")){
                sumaKobiet+=1;
            }
        }
        System.out.print("\nTyle kobiet:");
        System.out.print(sumaKobiet);

        System.out.print("\nTyle facetów: ");
        System.out.println(wszyscy-sumaKobiet);
        System.out.println();
    }


    PracownicyList zC4 (){
        PracownicyList wynik = new PracownicyList();
        for (Pracownik pr:this.pracownicy){
            if (pr.kobieta && pr.pawilon.matches("C4")){
                wynik.pracownicy.add(pr);
            }
        }
        return wynik;
    }

    PracownicyList wiecejTel (){
        PracownicyList wynik = new PracownicyList();
        for (Pracownik pr:this.pracownicy){
            if (pr.telefon.contains(",")){
                wynik.pracownicy.add(pr);
            }
        }
        return wynik;
    }

    public static void main(String args[]) throws IOException {

        PracownicyList lista = new PracownicyList();
        lista.read("pracownicy-geo-agh.csv");
        lista.ile();

        PracownicyList listaKobietzC4 = lista.zC4();
        System.out.println("Kobiety z C4");
        listaKobietzC4.list(System.out);

        System.out.println("\nWięcej niż jeden tr. tel: ");
        PracownicyList wiecejnizjedentel= lista.wiecejTel();
        wiecejnizjedentel.list(System.out);








    }



}


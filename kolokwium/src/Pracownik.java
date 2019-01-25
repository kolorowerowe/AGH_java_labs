import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pracownik {

    int id;
    String tytul,imie,nazwisko,pawilon,pokoj,telefon,mail;
    Boolean kobieta;



    void set(int id, String tytul, String imie, String nazwisko, String pawilon, String pokoj, String telefon, String mail) {
            this.id=id;
            this.tytul=tytul;
            this.imie=imie;
            this.nazwisko=nazwisko;
            this.pawilon=pawilon;
            this.pokoj=pokoj;
            this.telefon=telefon;
            this.mail=mail;

        if (imie.endsWith("a") || imie.endsWith("a ")){
            kobieta=true;
        }
        else{
            kobieta=false;
        }
    }


    public String toString(){
        StringBuilder b =  new StringBuilder();
        b.append(this.id+": " + imie + " " + nazwisko +" "+ ", tel: "+telefon + ", mail: " + mail+ ", pawilon: " + pawilon + "\n" );
        return b.toString();
    }






}
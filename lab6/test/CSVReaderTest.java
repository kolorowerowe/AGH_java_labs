import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.Locale;

public class CSVReaderTest {



    @Test
    public void read() throws IOException {
        System.out.print("\n Odczyt \n");

        CSVReader reader = new CSVReader("with-header.csv");
        for(int i =0;i<reader.columnLabels.size();i++){
            System.out.print(reader.columnLabels.get(i)+" ");
        }
        int cols=reader.columnLabels.size();
        while(reader.next()){
            for(int i=0; i<cols; i++){
                System.out.print(reader.get(i) + " " );
            }
            System.out.println();
        }

    }

    @Test
    public void typy() throws IOException {
        System.out.print("\nTypy \n");

        CSVReader reader = new CSVReader("with-header.csv");
        for(int i =0;i<reader.columnLabels.size();i++){
            System.out.print(reader.columnLabels.get(i)+" ");
        }
        System.out.println();
        while (reader.next()){
            int id=reader.getInt(0);
            String imie=reader.get(1);
            String nazwisko=reader.get(2);

            System.out.printf("%d,  %s\n", id, imie);

        }
    }

    @Test
    public void missing_values() throws IOException {
        System.out.print("\nMissing values \n");

        CSVReader reader = new CSVReader("missing-values.csv");
        System.out.println();
        for(int i =0;i<reader.columnLabels.size();i++){
            System.out.print(reader.columnLabels.get(i)+" ");
        }
        System.out.println();
        int cols=reader.columnLabels.size();
        while(reader.next()){
            for(int i=0; i<cols; i++){
                System.out.print(reader.get(i) + " " );
            }
            System.out.println();
        }

    }


    @Test
    public void missing_columns() throws IOException {
        System.out.print("\nMissing columns \n");

        CSVReader reader = new CSVReader("missing-values.csv");
        System.out.println();
        int cols=reader.columnLabels.size();

        reader.next();
        System.out.print(reader.get(cols+1));
        System.out.print(reader.get(-1));
        System.out.println();


    }

    /*
    @Test
    public void main() throws IOException {
        String text = "a,b,c\n123.4,567.8,91011.12";
        CSVReader reader = new CSVReader(new StringReader(text) ,",",true);
    }
    */
}
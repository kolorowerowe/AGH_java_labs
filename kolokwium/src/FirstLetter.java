import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.*;

public class FirstLetter {

    String tekst;
    List<String> wyrazy = new ArrayList<>();
    Map<Character, Integer> firstLettertoNumber = new HashMap<>();

    static String readFile(String name, Charset charset){
        StringBuilder s = new StringBuilder();
        try( BufferedReader file = new BufferedReader(new InputStreamReader( new FileInputStream(name), charset))){
            for(;;){
                int c=file.read();
                if(c<0)break;
                s.append((char)c);
            }
            return s.toString();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return "";
    }

    void podziel(){
        wyrazy= Arrays.asList(tekst.split("[\\s|\\r|\\,|\\.|\\-|\\!|\\—|\\?]+"));
        for (String w:wyrazy){
            w=w.toLowerCase();
            addtoMap(w.charAt(0));
        }
    }

    void addtoMap(Character ch){
        if (this.firstLettertoNumber.get(ch) == null){
            this.firstLettertoNumber.put(ch,1);
        }
        else{
            this.firstLettertoNumber.put(ch,this.firstLettertoNumber.get(ch)+1);
        }
    }

    void mostFrequent() {
        String max_c = "a";
        int max_i = 0;
        Iterator it = firstLettertoNumber.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            //System.out.println(pair.getKey() + " = " + pair.getValue());
            if (Integer.parseInt(pair.getValue().toString()) > max_i) {
                max_i = Integer.parseInt(pair.getValue().toString());
                max_c = pair.getKey().toString();
                it.remove();
            }
        }

        System.out.print("\nNajwięcej wystapien: \'"+max_c+"\', razy: ");
        System.out.println(max_i);
    }

    public static void main(String args[]){

        FirstLetter pustynia= new FirstLetter();
        pustynia.tekst=readFile("w-pustyni.txt",  Charset.forName("cp1250"));
        pustynia.podziel();

        pustynia.mostFrequent();


    }



}

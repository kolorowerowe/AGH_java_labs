import java.io.PrintStream;


public class Photo {
    Photo(String url){
        this.url =url;
    }
    String url;
    void writeHTML(PrintStream out){
        out.printf("<img src=\"%s\" alt=\"Person photo\" height=\"400\" align=\"right\"/>\n",url);
    }
}
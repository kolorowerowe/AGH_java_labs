import java.util.ArrayList;
import java.io.PrintStream;
import java.util.List;


public class Section {
    String title;
    List<Paragraph> paragraphs = new ArrayList<>() ;

    Section setTitle(String title){
        this.title = title;
        return this;
    }


    Section addParagraph(String paragraphText){
        Paragraph p = new Paragraph();
        p.setContent(paragraphText);
        paragraphs.add(p);
        return this;
    }

    Section addParagraph(Paragraph p){
        paragraphs.add(p);
        return this;

    }


    void writeHTML(PrintStream out){
        out.printf("<h3>"+title + "</h3>");
        for (Paragraph p:paragraphs){
            p.writeHTML(out);
        }
    }

}

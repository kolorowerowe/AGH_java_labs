import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class Document {
    String title;
    Photo photo;
    List<Section> sections = new ArrayList<>() ;



    Document setTitle(String title){
        this.title = title;
        return this;
    }

    Document setPhoto(String photoUrl){
        photo = new Photo(photoUrl);
        return this;
    }

    Section addSection(String sectionTitle){
        Section a = new Section();
        a.setTitle(sectionTitle);
        sections.add(a);
        return a;

    }
    Document addSection(Section s){
        sections.add(s);
        return this;
    }


    void writeHTML(PrintStream out){
        out.printf(" <!DOCTYPE html>");
        out.printf("<html>\n<head>\n<title>"+title+"</title>\n</head>\n<body>\n");
        out.printf("<h1>"+title+"</h1>");
        photo.writeHTML(out);
        for (Section sec:sections){
            sec.writeHTML(out);
        }
        out.printf("</body>\n</html>\n");

    }

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

        Document doc = new Document();

        doc.setTitle("Dominik Kołodziej");
        doc.setPhoto("src/jaa.jpg");
        doc.addSection("O mnie")
                .addParagraph("Jestem studentem 2 roku Informatyki na Wydziale EAIiIB na Akademii Górniczo-Hutniczej w Krakowie");
        doc.addSection("Wyształcenie").addParagraph(new ParagraphWithList().setContent("")
                .addListItem("Szkoła Podstwowa w Brzezówce")
                .addListItem("Gimnazjum w Lubzinie")
                .addListItem("LO w Ropczycach")
                .addListItem("AGH w Krakowie"));
        doc.addSection("Znane języki programowania").addParagraph(new ParagraphWithList().setContent("")
                .addListItem("C")
                .addListItem("C++")
                .addListItem("Python")
                .addListItem("Java"));

        doc.writeHTML(new PrintStream("p/cv2.html","ISO-8859-2"));
    }

}

import java.io.PrintStream;

public class ParagraphWithList extends Paragraph {

    UnorderedList lista = new UnorderedList();

    ParagraphWithList setContent(String content){
        this.content = content;
        return this;
    }

    ParagraphWithList addListItem(String s){
        lista.addItem(s);
        return this;
    }

    void writeHTML(PrintStream out){
        out.printf("<p>"+content + "</p>");
        lista.writeHTML(out);
    }
}

import java.io.PrintStream;

public class ListItem {
    String content;

    ListItem setContent(String content){
        this.content=content;
        return this;
    }
    void writeHTML(PrintStream out){
        out.printf("<li>" + content + "</li>");
    }

}

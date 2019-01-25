import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;


public class UnorderedList {
    List<ListItem> lis = new ArrayList<>() ;

    void writeHTML(PrintStream out){
        out.printf("<ul>");
        for (ListItem li : lis){
            li.writeHTML(out);
        }
        out.printf("</ul>");
    }

    UnorderedList addItem(String content){
        ListItem li = new ListItem();
        li.setContent(content);
        lis.add(li);
        return this;
    }


}

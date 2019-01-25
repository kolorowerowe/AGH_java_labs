import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminUnit {
    String name;
    int adminLevel;
    double population;
    double area;
    double density;
    AdminUnit parent;
    BoundingBox bbox;
    List<AdminUnit> children = new ArrayList<>();
    Map<Long,List<AdminUnit>> parentid2child = new HashMap<>();



    void set(String name, int adminLevel, double population, double area, double density, double x1, double y1, double x3, double y3){
        this.name=name;
        this.adminLevel=adminLevel;
        this.population=population;
        this.area = area;
        this.density=density;
        bbox = new BoundingBox(x1,y1,x3,y3);

    }

    public void saveAsHtmlFile()  {
        PrintStream out = null;
        try {
            out = new PrintStream("pliki/"+this.name+".html", "ISO-8859-2");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        out.print("<?xml version=\"1.0\"?>");
        out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\"\n" +
                "\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
        out.println("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "<head>\n" +
                "<title>"+this.name+"</title>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"application/xhtml+xml;\n" +
                "charset=UTF-8\" />\n" +
                "</head>");
        out.println("<body>");
        out.println("<h1>"+this.name+"</h1>");
        out.println("<p>"+this.area+"</p>");
        out.println("<p>"+this.population+"</p>");
        out.println("<p>"+this.density+"</p>");

//        out.println("<p>Parent: <a href=\""+"pliki\\"+this.parent.name+".html\">"+this.parent.name+"</a></p>");
        out.println("</body>");
        out.println("</html>");

    }

    public String toString(){
        StringBuilder b =  new StringBuilder();
        b.append(name + " " + adminLevel + " " + population + " " + area + " " + density + " " + bbox.toString() +"\n");
        return b.toString();
    }






}
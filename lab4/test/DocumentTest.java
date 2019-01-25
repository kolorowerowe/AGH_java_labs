import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

public class DocumentTest {

    @Test
    public void writeHTML() throws Exception {


        String title = "Doc title";
        String photo_url="ja.png";
        // Utwórz strumień zapisujący w pamięci
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);

        // Utwórz obiekt i zapisz do strumienia
        new Document().setTitle(title).setPhoto(photo_url).writeHTML(ps);
        String result = null;

        // Pobierz jako String
        try {
            result = os.toString("ISO-8859-2");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // System.out.println(result);

        // Sprawdź, czy result zawiera wybrane elementy

        assertTrue(result.contains(title));
        assertTrue(result.contains("<head>"));
        assertTrue(result.contains("</head>"));
        assertTrue(result.contains("<body>"));
        assertTrue(result.contains("</body>"));
        assertTrue(result.contains(title));
        assertTrue(result.contains("<html>"));
        assertTrue(result.contains("</html>"));


    }


}
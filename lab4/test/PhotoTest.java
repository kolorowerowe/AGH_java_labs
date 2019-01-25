import org.junit.Test;
import java.io.UnsupportedEncodingException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class PhotoTest {

    @Test
    public void writeHTML() throws Exception {
        String imageUrl = "jan-kowalski.png";

        // Utwórz strumień zapisujący w pamięci
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);

        // Utwórz obiekt i zapisz do strumienia
        new Photo(imageUrl).writeHTML(ps);
        String result = null;

        // Pobierz jako String
        try {
            result = os.toString("ISO-8859-2");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

       // System.out.println(result);

        // Sprawdź, czy result zawiera wybrane elementy
        assertTrue(result.contains("<img"));
        assertTrue(result.contains("/>"));
        assertTrue(result.contains("src="));
        assertTrue(result.contains(imageUrl));

    }
}
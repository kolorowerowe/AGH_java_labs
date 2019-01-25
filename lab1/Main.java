import java.util.Scanner;
import java.util.Locale;
 
public class Main {
 
    public static void main(String[] args) {
    	Scanner scan = new Scanner(System.in);
        String s = scan.next();
        int i = scan.nextInt();
        double d = scan.nextDouble();
        System.out.printf("Wczytano %s , %d, %f\n",s,i,d);
    }
}

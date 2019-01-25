import java.util.Scanner;

public class Problem610A{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        if (n%2==1) {
            System.out.println(0);
            return;
        }
        int dwa_boki=n/2;
        int licznik=1;
        while(licznik<dwa_boki/2) {
            licznik++;
        }
        if (dwa_boki%2==0){
            licznik--;
        }
        System.out.println(licznik);
    }
}

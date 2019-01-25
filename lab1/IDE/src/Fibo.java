import java.util.Scanner;

public class Fibo {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int[] tab = new int[n];

        int i=2;
        tab[0]=1;
        tab[1]=1;
        while(i<n){
            tab[i]=tab[i-1]+tab[i-2];
            i++;
        }

        for (i=0;i<n;i++){
            System.out.print(tab[i] + ", ");
        }
    }
}

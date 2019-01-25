import java.util.Locale;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Mean {
    static double[] array;


    static class MeanCalc extends Thread{
        private final int start;
        private final int end;
        double mean = 0;

        MeanCalc(int start, int end){
            this.start = start;
            this.end=end;
        }
        public void run(){
            for(int i = start;i<=end;i++){
                mean+=array[i];
            }
            mean=mean/(end-start);
           // System.out.printf(Locale.US,"%d-%d mean=%f\n",start,end,mean);


            try {
                results.put(mean);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    static void initArray(int size){
        array = new double[size];
        for(int i=0;i<size;i++){
            array[i]= Math.random()*size/(i+1);
        }
    }

    static void parallelMean(int cnt) throws InterruptedException {
        // utwórz tablicę wątków
        MeanCalc threads[] = new MeanCalc[cnt];
        // utwórz wątki, podziel tablice na równe bloki i przekaż indeksy do wątków
        int dlugosc = array.length / cnt;
        int i = 0;
        for (int poczatek = 0; poczatek < array.length; poczatek += dlugosc) {
            MeanCalc mc = new MeanCalc(poczatek, poczatek + dlugosc - 1);
            threads[i] = mc;
            i += 1;
        }

        // załóż, że array.length dzieli się przez cnt)
        double t1 = System.nanoTime() / 1e6;
        for (MeanCalc mc : threads) {
            mc.start();
        }

        double mean = 0;

        for (MeanCalc mc : threads) {
            mean+=results.take();
        }

        double t2 = System.nanoTime() / 1e6;



/*
        // czekaj na ich zakończenie używając metody ''join''
        for (MeanCalc mc : threads) {
            mc.join();
        }
        // oblicz średnią ze średnich
        for (MeanCalc mc : threads) {
            mean += mc.mean;
        }

        */

        mean = mean / cnt;


        double t3 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,"size = %d cnt=%d >  t2-t1=%f t3-t1=%f mean=%f\n",
                array.length,
                cnt,
                t2-t1,
                t3-t1,
                mean);
    }

    static BlockingQueue<Double> results = new ArrayBlockingQueue<Double>(100);

    public static void main(String[] args) throws InterruptedException {
        initArray(134217728);
        for(int cnt:new int[]{1,2,4,8,16,32,64,128}){
            parallelMean(cnt);
        }
    }
}

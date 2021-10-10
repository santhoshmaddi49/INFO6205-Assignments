package edu.neu.coe.info6205.union_find;

import java.util.Random;


public class HWQUPC_Solution {



    public static int count(int n){

        UF h = new UF_HWQUPC(n);
        int count = 0;
        Random rand = new Random();

        while (h.components() > 1) {
            count++;
            int p = rand.nextInt(n);
            int q = rand.nextInt(n);
            h.connect(p, q);
        }

        return count;
    }
    public static void main(String[] args) {

        int n = 100;
        if (args.length > 0) n = Integer.parseInt(args[0]);

        System.out.println(String.format("Running 5 times for n = %d",n));
        int averageValue = 0;
        for (int i =0; i<5; i++){
            int pairs = count(n);
            averageValue += pairs;
            System.out.println(String.format("%d.Total number of pairs generated for %d objects are: %d ", i+1, n, pairs));
        }

        System.out.println(String.format("\nAverage Value of number of pairs for n = %s is: %s",n, averageValue/5));

    }

}

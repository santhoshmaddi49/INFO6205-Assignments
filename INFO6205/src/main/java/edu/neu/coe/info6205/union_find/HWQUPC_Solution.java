package edu.neu.coe.info6205.union_find;

import java.util.Random;


public class HWQUPC_Solution {


    public static boolean check (UF h){

        int n = h.size();

        for(int i = 1; i<n;i++){
            if ( !h.isConnected(i,i-1))
                return false;
        }
        return true;
    }
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
        int count = count( n);
        System.out.println(count);

    }

}

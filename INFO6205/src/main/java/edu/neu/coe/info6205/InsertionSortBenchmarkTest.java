package edu.neu.coe.info6205;

import edu.neu.coe.info6205.sort.BaseHelper;
import edu.neu.coe.info6205.sort.GenericSort;
import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.elementary.InsertionSort;
import edu.neu.coe.info6205.util.Benchmark;
import edu.neu.coe.info6205.util.Benchmark_Timer;
import edu.neu.coe.info6205.util.Config;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Supplier;

public class InsertionSortBenchmarkTest {


    private static Config config;
    public static Integer[] createSortedArray(int N){

        Integer[] arr = new Integer[N];
        for(int i = 1; i<=N;i++){
            arr[i-1] = i;
        }
        return arr;
    }

    public static Integer[] createReverseSortedArray(int N){

        Integer[] arr = new Integer[N];
        for(int i = 0; i<N;i++){
            arr[i] = N-i;
        }
        return arr;
    }
    public static Integer[] createRandomArray(int N){


        Random rand = new Random();
        Integer[] arr = new Integer[N];
        for(int i = 0; i<N;i++){
            arr[i] = rand.nextInt(N);
        }
        return arr;
    }
    public static Integer[] createPartiallySortedArray(int N){


        Random rand = new Random();
        Integer[] arr = new Integer[N];
        for (int i = 0; i<N/2; i++){
            arr[i] = i;
        }

        for (int i = N/2; i<N; i++){

            arr[i] =  rand.nextInt(N) + N/2;
        }

        return arr;
    }

    public static void runBenchmarkTest(int N, String description, Supplier<Integer[]> supplier){

        Helper<Integer> helper = new BaseHelper<>(description, N, config);
        final GenericSort<Integer> sort = new InsertionSort<>(helper);
        final Benchmark<Integer[]> benchmark = new Benchmark_Timer<>(
                description ,
                (xs) -> Arrays.copyOf(xs, xs.length),
                sort::sort,
                null

        );
        System.out.println(benchmark.runFromSupplier(supplier, 5)+  " ms");
    }


    public static void main(String[] args) {

        for(int N =1000; N<=16000; N = N*2) {

            int finalN = N;
            String description = "Insertion sort for sorted array of size: " + N;
            Supplier<Integer[]> supplier = () -> createSortedArray(finalN);
            runBenchmarkTest(N, description, supplier);

            description = "Insertion sort for partially sorted array of size: " + N;
            supplier = () -> createPartiallySortedArray(finalN);
            runBenchmarkTest(N, description, supplier);

            description = "Insertion sort for random numbers in a array of size: " + N;
            supplier = () -> createRandomArray(finalN);
            runBenchmarkTest(N, description, supplier);

            description = "Insertion sort for reversely sorted array of size: " + N;
            supplier = () -> createReverseSortedArray(finalN);
            runBenchmarkTest(N, description, supplier);

        }

    }

}
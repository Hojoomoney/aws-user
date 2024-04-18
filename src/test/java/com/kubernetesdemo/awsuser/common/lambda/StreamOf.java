package com.kubernetesdemo.awsuser.common.lambda;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamOf {
    @Test
    public void testSave(){

        int arr[] = { 1, 2, 3, 4, 5 };

        IntStream intStream = Arrays.stream(arr);

        intStream.forEach(str -> System.out.print(str + " "));

        Stream<int[]> stream = Stream.of(arr);

        stream.forEach(str -> System.out.print(str + " "));

        /** 결과 1 2 3 4 5 [I@43ee72e6
         **/
    }

    @Test
    public void testDifferent(){

        int arr[] = { 1, 2, 3, 4, 5 };

        IntStream intStream = Arrays.stream(arr);

        intStream.forEach(str -> System.out.print(str + " "));

        Stream<int[]> stream = Stream.of(arr);

        IntStream intStreamNew = stream.flatMapToInt(Arrays::stream);

        intStreamNew.forEach(str -> System.out.print(str + " "));

        /**  결과 : 1 2 3 4 5 1 2 3 4 5
         **/
    }
}

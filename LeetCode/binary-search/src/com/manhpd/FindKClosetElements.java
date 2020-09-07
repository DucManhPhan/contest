package com.manhpd;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class FindKClosetElements {

    public static void main(String[] args) throws IOException {
        test1();
    }

    private static int test() throws IOException {
        System.out.println("It's ok");
//        throw new Exception();
        throw new IllegalArgumentException();
    }

    public static void test1() {
        System.out.println("a");
        try {
            System.out.println("b");
            throw new IllegalArgumentException();
        } catch (IllegalArgumentException ex) {
            System.out.println("c");
            throw new RuntimeException("1");
        } catch (RuntimeException ex) {
            System.out.println("d");
            throw new RuntimeException("2");
        } finally {
            System.out.println("e");
            throw new RuntimeException("3");
        }
    }

    public static List<Integer> findClosetElements(int[] arr, int k, int x) {
        return Collections.emptyList();
    }

}

class HasShoreThroatException extends Exception {}

class TiredException extends RuntimeException {}

interface Roar {
    void roar() throws HasShoreThroatException;
}

class Lion implements Roar {

    @Override
    public void roar() throws IllegalArgumentException {

    }
}
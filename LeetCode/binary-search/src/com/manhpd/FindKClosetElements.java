package com.manhpd;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class FindKClosetElements {

    public static void main(String[] args) throws IOException {
        Test t1 = new Test();
        t1.var = 300;
        System.out.println(t1);

        Test t2 = new Test();
        t2.stVar = 300;
        System.out.println(t2);
    }

    public static List<Integer> findClosetElements(int[] arr, int k, int x) {
        return Collections.emptyList();
    }

}

class Test {
    public static int stVar = 100;
    public int var = 200;

    @Override
    public String toString() {
        return var + ":" + stVar;
    }
}

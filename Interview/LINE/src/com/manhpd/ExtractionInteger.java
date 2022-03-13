package com.manhpd;


import java.io.*;
import java.util.Iterator;

/**
 * Given a sample file with each line will contain an integer.
 * Using Iterator to display each integer.
 *
 * Constraints:
 * - the value in a range [-1,000,000,000 ; 1,000,000,000]
 * - can be contain addition/subtraction operator.
 *
 * Example 1:
 * Input: Data in the sample.txt file
 * Output: 137, -104, 0, 1, 0, -1
 *
 */
public class ExtractionInteger implements Iterable<Integer> {

    private static final int INT_UNDER_RANGE = -1000000000;

    private static final int INT_UPPER_RANGE = 1000000000;

    private static final String regex = "[+-]?[0-9]{1,10}";

    private BufferedReader bufferedReader;

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("sample");
        FileReader fileReader = new FileReader(file);

        for (Integer x : new ExtractionInteger(fileReader)) {
            System.out.println(x);
        }
    }

    public ExtractionInteger(Reader inp) {
        this.bufferedReader = new BufferedReader(inp);
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {

            @Override
            public boolean hasNext() {
                try {
                    if (bufferedReader.ready()) {
                        return true;
                    } else {
                        bufferedReader.close();
                        return false;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return false;
            }

            @Override
            public Integer next() {
                String currentLine = "";
                try {
                    while ((currentLine = bufferedReader.readLine()) != null) {
                        if (currentLine.matches(regex)) {
                            int value = Integer.valueOf(currentLine);
                            if (value >= INT_UNDER_RANGE && value <= INT_UPPER_RANGE) {
                                return value;
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }
        };
    }

}


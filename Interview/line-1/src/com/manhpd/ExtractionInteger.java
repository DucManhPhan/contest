package com.manhpd;


import java.io.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Refer: http://www.java2s.com/Code/Java/File-Input-Output/AnIteratoroverthelinesinaReader.htm
 *
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

    private static final int INT_LOWER_RANGE = -1000000000;

    private static final int INT_UPPER_RANGE = 1000000000;

    private static final String regex = "[-+]?[0-9]{1,10}$";

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

            private String currentLine;

            private boolean isFinished = false;

            @Override
            public boolean hasNext() {
                if (this.currentLine != null) {
                    return true;
                } else if (this.isFinished) {
                    return false;
                } else {
                    try {
                        while (true) {
                            String line = bufferedReader.readLine();
                            if (line == null) {
                                this.isFinished = true;
                                return false;
                            } else if (this.isValidLine(line)) {
                                this.currentLine = line.trim();
                                return true;
                            }
                        }
                    } catch (IOException ex) {
                        this.close();
                        throw new IllegalStateException(ex.toString());
                    }
                }
            }

            @Override
            public Integer next() {
                return this.nextLine();
            }

            public void close() {
                this.isFinished = true;
                this.currentLine = null;
            }

            protected boolean isValidLine(String line) {
                String trimedLine = line.trim();
                if (trimedLine.matches(regex)) {
                    int value = Integer.valueOf(trimedLine);
                    if (value >= INT_LOWER_RANGE && value <= INT_UPPER_RANGE) {
                        return true;
                    }
                }

                return false;
            }

            public Integer nextLine() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException("No more lines");
                }

                String currentLine = this.currentLine;
                this.currentLine = null;

                return Integer.valueOf(currentLine);
            }

        };
    }

}


package com.manhpd;


import java.util.Optional;

public class App {
    public static void main( String[] args ) {
        String value = null; //"How we do that";
        Optional<String> optValue = Optional.ofNullable(value);
        optValue.ifPresentOrElse(content -> System.out.println("The value of core is: " + content),
                                 () -> System.out.println("Do not contain value."));
    }
}

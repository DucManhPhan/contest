package com.manhpd;

public class DefangIpAddress {

    public static void main(String[] args) {
//        String address = "1.1.1.1";
        String address = "255.100.50.0";

        String result = defangIPaddr(address);
        System.out.println("Result = " + result);
    }

    public static String defangIPaddr(String address) {
        int size = address.length();
        for (int i = 0; i < size; ++i) {

        }
        return "";
    }

}

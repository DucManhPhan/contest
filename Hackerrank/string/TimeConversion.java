package com.manhpd;

public class TimeConversion {

    public static void main(String[] args) {
//        String s = "7:05:45PM";
//        String s = "12:05:45AM";
        String s = "06:40:03AM";
        String result24format = timeConversion(s);
        System.out.println(result24format);
    }

    private static String timeConversion(String s) {
        String pmOrAm = s.substring(s.length() - 2, s.length());
        String[] hhmmss = s.split(":");
        String ss = hhmmss[2].substring(0, 2);

        StringBuilder result = new StringBuilder();

        if (pmOrAm.equals("AM"))  {
            if (hhmmss[0].equals("12")) {
                result.append("00");
            } else {
                result.append(hhmmss[0]);
            }
        } else if (pmOrAm.equals("PM")) {
            if (hhmmss[0].equals("12")) {
                result.append("12");
            } else {
                int hh = Integer.parseInt(hhmmss[0]) + 12;
                result.append(String.valueOf(hh));
            }
        }

        result.append(":" + hhmmss[1]);
        result.append(":" + ss);

        return result.toString();
    }

}

package com.manhpd;

/**
 * https://leetcode.com/problems/long-pressed-name/
 */
public class LongPressedName {

    public static void main(String[] args) {
        String name = "alex";
        String typed = "aaleex";

//        String name = "saeed";
//        String typed = "ssaaedd";

        LongPressedName solution = new LongPressedName();
        System.out.println(solution.isLongPressedName(name, typed));
    }

    /**
     * Using two pointers technique
     *
     * @param name
     * @param typed
     * @return
     */
    public boolean isLongPressedName(String name, String typed) {
        int pName = 0;
        int pTyped = 0;

        char[] names = name.toCharArray();
        char[] typeds = typed.toCharArray();

        while (pName < names.length || pTyped < typeds.length) {
            char cName = names[pName];
            char cTyped = typeds[pTyped];

            if (cName != cTyped) {
                return false;
            }

            while (cName == cTyped) {
                ++pTyped;
                if (pTyped < typeds.length) {
                    cTyped = typeds[pTyped];
                } else {
                    break;
                }
            }

            ++pName;
        }

        if (pName == names.length && pTyped == typeds.length) {
            return true;
        }

        return false;
    }

}

package com.manhpd;

/**
 * https://leetcode.com/problems/long-pressed-name/
 */
public class LongPressedName {

    public static void main(String[] args) {
//        String name = "alex";
//        String typed = "aaleex";

        String name = "alex";
        String typed = "aaleexa";

//        String name = "saeed";
//        String typed = "ssaaedd";

//        String name = "leelee";
//        String typed = "lleeelee";

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

        while (pName < names.length && pTyped < typeds.length) {
            int nDuplicatedName = 1;
            while (pName + 1 < names.length && names[pName] == names[pName + 1]) {
                pName = pName + 1;
                ++nDuplicatedName;
            }

            char cName = names[pName];
            char cTyped = typeds[pTyped];
            if (cName != cTyped) {
                return false;
            }

            int nDuplicatedTyped = 0;
            while (cName == cTyped) {
                ++pTyped;
                ++nDuplicatedTyped;
                if (pTyped < typeds.length) {
                    cTyped = typeds[pTyped];
                } else {
                    break;
                }
            }

            if (nDuplicatedName > nDuplicatedTyped) {
                return false;
            }

            ++pName;
        }

        if (pName == names.length && pTyped == typeds.length) {
            return true;
        }

        return false;
    }

}

import java.util.HashMap;
import java.util.Map;

/**
 * Delete duplicate words, keep the first occurrence and keep the case.
 *
 * Sample Input:
 * Jim went Went To to to school went to
 *
 * Sample Output:
 * Jim went To school
 */
public class Application {

    public static void main(String[] args) {
        String s = "Jim went Went To to to school went to";
        String result = removeDuplicateWords(s);

        System.out.println(result);
    }

    private static String removeDuplicateWords(String input) {
        if ("".equals(input)) {
            return "";
        }

        String[] strs = input.split(" ");
        Map<String, Integer> map = new HashMap<>();     // set
        StringBuilder result = new StringBuilder();

        for (String str : strs) {
            String lowerCaseStr = str.toLowerCase();
            if (!map.containsKey(lowerCaseStr)) {
                result.append(str);
                result.append(" ");

                map.put(lowerCaseStr, 1);
            }
        }

        return result.toString();
    }

}

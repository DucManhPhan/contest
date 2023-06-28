package com.manhpd;

public class Applications {

    public static void main(String[] args) {
        List<String> commands = new ArrayList<>() {{
            add("open firefox");
            add("open curl");
            add("open terminal");
            add("close 2");
            add("open ps");
        }};

        getOpenApplications(commands);
    }

    public static List<String> getOpenApplications(List<String> commands) {
        // Write your code here
        if (commands == null || commands.size() == 0) {
            return new LinkedList<>();
        }

        List<String> openApplications = new LinkedList<>();
        for (String command : commands) {
            System.out.println(command);

            String name = extractApplicationName(command);

            if (command.contains("open")) {
                openApplications.add(name);
            } else if (command.contains("close")) {
                int k = Integer.valueOf(name);
                System.out.println("Close number of app: " + k);

                if (k >= openApplications.size()) {
                    openApplications.clear();
                    continue;
                }

                int length = openApplications.size();
                for (int i = length - 1;; --i) {
                    if (length - i == k + 1) {
                        break;
                    }

                    openApplications.remove(i);
                }
            } else if (command.contains("clear")) {
                openApplications.clear();
            }
        }

        System.out.println(openApplications.toArray());
        return openApplications;
    }

    private static String extractApplicationName(String command) {
        int idxOfSpace = command.indexOf(' ');
        return command.substring(idxOfSpace + 1, command.length());
    }

}
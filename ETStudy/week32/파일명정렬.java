package week32;

import java.util.*;

class Solution3 {
    class File {
        String head, number, tail;

        public File(String head, String number, String tail) {
            this.head = head;
            this.number = number;
            this.tail = tail;
        }

        public String getHead() {
            return head.toLowerCase();
        }

        public long getNumber() {
            return Long.parseLong(number);
        }

        @Override
        public String toString() {
            return head + number + tail;
        }
    }

    List<File> splitFile = new ArrayList<>();

    public String[] solution(String[] files) {
        for (String file : files) {
            splitFile(file);
        }

        return splitFile.stream()
                .sorted(Comparator.comparing(File::getHead).thenComparing(File::getNumber))
                .map(File::toString).toArray(String[]::new);
    }

    void splitFile(String file) {
        String head = "";
        String number = "";
        String tail = "";
        int idx = 0;
        while (idx < file.length() && !Character.isDigit(file.charAt(idx))) {
            head += file.substring(idx, idx + 1);
            idx++;
        }
        while (idx < file.length() && Character.isDigit(file.charAt(idx))) {
            number += file.substring(idx, idx + 1);
            idx++;
        }
        if (idx < file.length()) {
            tail = file.substring(idx);
        }

        splitFile.add(new File(head, number, tail));
    }
}
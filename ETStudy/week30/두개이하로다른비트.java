package week30;

import java.util.*;

class Solution3 {
    List<Long> answer = new ArrayList<>();

//    public long[] solution(long[] numbers) {
//        for (long number : numbers) {
//            checkBitNumber(number);
//        }
//        return answer.stream().mapToLong(a -> a).toArray();
//    }
//
//    void checkBitNumber(long number) {
//        for (long i = number; i < MAX_NUMBER; i++) {
//            int count = Long.bitCount(i ^ number);
//            if (count == 1 || count == 2) {
//                answer.add(i);
//                return;
//            }
//        }
//    }

    public long[] solution(long[] numbers) {
        for (long number : numbers) {
            checkBitNumber(number);
        }
        return answer.stream().mapToLong(a -> a).toArray();
    }

    void checkBitNumber(long number) {

        // 짝수 -> 그냥 1 더해주면 됨.
        if (number % 2 == 0) {
            answer.add(number + 1);

        } else {
            String binaryString = Long.toBinaryString(number);

            // 0이 없는 경우
            if (!binaryString.contains("0")) {
                //맨앞이 10, 나머지는 전부 1
                binaryString = Long.toBinaryString(number + 1);
                binaryString = "10" + "1".repeat(binaryString.length() - 2);
            } else {
                int lastIndex = binaryString.lastIndexOf("0");
                int firstIndex = binaryString.indexOf("1", lastIndex);

                // 마지막 0을 1로 수정, 마지막 다음의 1을 0으로 수정
                binaryString = binaryString.substring(0, lastIndex) + "1"
                        + binaryString.substring(lastIndex + 1, firstIndex) + "0"
                        + binaryString.substring(firstIndex + 1, binaryString.length());
            }
            answer.add(Long.parseLong(binaryString, 2));
        }
    }
}
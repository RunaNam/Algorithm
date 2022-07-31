package week41;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution3 {
    public static void main(String[] args) {
        String result = solution(2, 1, 2, new String[] {"09:00", "09:00", "09:00", "09:00"});
        System.out.println(result);

    }

    public static String solution(int n, int t, int m, String[] timetable) {
        int answer = 0;
        int timeLimit = convertToTime("09:00");

        List<Integer> crews = Arrays.stream(timetable)
            .map(time -> convertToTime(time))
            .sorted()
            .collect(Collectors.toList());

        int idx = 0; // 현재 가장 앞의 크루

        for (int i = 0; i < n; i++) {
            int rideCrew = 0;

            //현재 가장 앞의 크루가 일찍 왔고, 사람 수가 남을 때
            while (idx < crews.size() && crews.get(idx) <= timeLimit && rideCrew < m) {
                rideCrew++;
                idx++;
            }

            // 버스가 가득 찬 경우 마지막 사람 -1
            if (rideCrew == m) {
                answer = crews.get(idx - 1) - 1;
            } else {
                //아닌 경우
                answer = timeLimit;
            }

            timeLimit += t;
        }

        return convertToString(answer);
    }

    static int convertToTime(String input) {
        String[] timeInfo = input.split(":");
        return Integer.parseInt(timeInfo[0]) * 60 + Integer.parseInt(timeInfo[1]);
    }

    static String convertToString(int input) {
        StringBuilder sb = new StringBuilder();
        int hour = input / 60;
        int min = input % 60;

        //일의 자리수일 경우 추가해서 string으로 변환.
        if (hour < 10) {
            sb.append("0");
        }
        sb.append(hour).append(":");
        if (min < 10) {
            sb.append("0");
        }
        sb.append(min);

        return sb.toString();
    }
}
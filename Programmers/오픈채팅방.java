import java.util.*;

class Solution {

    static class User {

        String id;
        boolean status;

        public User(String id, boolean status) {
            this.id = id;
            this.status = status;
        }
    }

    public String[] solution(String[] records) {
        Map<String, String> nameList = new HashMap<>(); // id : 이름
        List<User> log = new ArrayList<>();

        List<String> tmpAnswer = new ArrayList<>();

        for (String record : records) {
            String[] info = record.split(" ");

            String mode = info[0];
            String id = info[1];

            if (mode.equals("Enter")) {
                nameList.put(id, info[2]);
                log.add(new User(id, true));
            } else if (mode.equals("Leave")) {
                log.add(new User(id, false));
            } else {
                nameList.put(id, info[2]);
            }
        }

        for (User user : log) {
            if (user.status) {
                tmpAnswer.add(nameList.get(user.id) + "님이 들어왔습니다.");
            } else {
                tmpAnswer.add(nameList.get(user.id) + "님이 나갔습니다.");
            }
        }

        String[] answer = tmpAnswer.stream().toArray(String[]::new);

        return answer;
    }
}
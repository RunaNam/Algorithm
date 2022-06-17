package week35;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Stack;

class Solution3 {
    Deque<String> dq = new ArrayDeque<>();
    Map<String, String> BracketMap = Map.of("]", "[", "}", "{", ")", "(");

    public int solution(String s) {
        int answer = 0;

        String[] splitString = s.split("");
        for (String splitStr : splitString) {
            dq.addLast(splitStr);
        }

        for (int i = 0; i < dq.size(); i++) {
            if (checkBracket()) {
                answer++;
            }
            String first = dq.pollFirst();
            dq.addLast(first);
        }

        return answer;
    }

    private boolean checkBracket() {
        Stack<String> stack = new Stack<>();
        for (String str : dq) {
            if (stack.isEmpty()) { //비어있는 경우
                stack.push(str);
            } else {
                if (stack.peek().equals(BracketMap.get(str))) { // 닫는 괄호와 여는 괄호가 겹치는 경우
                    stack.pop();
                } else {
                    stack.push(str);
                }
            }
        }
        return stack.isEmpty();
    }
}
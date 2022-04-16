package week29;

import java.util.*;

class Solution2 {
    int DEFAULT = -1;
    Map<Character, Integer> skillIdx = new HashMap<>();

    public int solution(String skill, String[] skill_trees) {
        int answer = 0;


        for(int i = 0; i<skill.length(); i++){
            skillIdx.put(skill.charAt(i), i);
        }

        for(String st : skill_trees){
            if(checkSkill(st)){
                answer ++;
            }
        }


        return answer;
    }

    public boolean checkSkill(String st){
        int skillnum = -1;

        for(int i = 0; i<st.length(); i++){
            int num = skillIdx.getOrDefault(st.charAt(i), DEFAULT);
            if(num != DEFAULT){
                if(num == skillnum + 1){
                    skillnum = num;
                }
                else{
                    return false;
                }
            }
        }
        return true;
    }
}
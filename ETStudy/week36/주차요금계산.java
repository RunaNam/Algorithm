package week36;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class Solution2 {
    class ParkingFee {
        int enterTime, parkingFullTime;

        public ParkingFee(int enterTime, int parkingFullTime) {
            this.enterTime = enterTime;
            this.parkingFullTime = parkingFullTime;
        }

        public int getParkingFullTime() {
            return parkingFullTime;
        }

        public void addParkingTime(int time) {
            parkingFullTime += time - enterTime;
        }

        public void resetEnterTime(){
            enterTime = -1;
        }

        public boolean isExit(){
            return enterTime == -1;
        }
    }

    public int[] solution(int[] fees, String[] records) {
        Map<String, ParkingFee> parkCar = new HashMap<>(); // 자동차번호, 시간

        for (String record : records) {
            String[] recordInfo = record.split(" ");
            String carNum = recordInfo[1];
            int time = getTime(recordInfo[0]);

            if (recordInfo[2].equals("IN")) {
                if (parkCar.containsKey(carNum)) {
                    ParkingFee parkingFee = parkCar.get(carNum);
                    parkCar.put(carNum, new ParkingFee(time, parkingFee.getParkingFullTime()));
                } else {
                    parkCar.put(carNum, new ParkingFee(time, 0));
                }

            } else {
                ParkingFee parkingFee = parkCar.get(carNum);
                parkingFee.addParkingTime(time);
                parkingFee.resetEnterTime();
            }
        }

        for (Entry<String, ParkingFee> entry : parkCar.entrySet()) {
            if(!entry.getValue().isExit()){
                entry.getValue().addParkingTime(getTime("23:59"));
            }
        }

        return parkCar.entrySet().stream()
                .sorted(Entry.comparingByKey())
                .mapToInt(entry -> calculateFee(entry.getValue().getParkingFullTime(), fees))
                .toArray();
    }

    private int calculateFee(int parkingTime, int[] fees) {
        if (parkingTime <= fees[0]) {
            return fees[1];
        } else {
            parkingTime -= fees[0];
            int parkingAdditionalFeeCnt = parkingTime / fees[2];
            if (parkingTime % fees[2] != 0) {
                parkingAdditionalFeeCnt++;
            }
            return fees[1] + parkingAdditionalFeeCnt * fees[3];
        }
    }

    private int getTime(String input) {
        String[] timeInfo = input.split(":");

        return Integer.parseInt(timeInfo[0]) * 60 + Integer.parseInt(timeInfo[1]);
    }
}
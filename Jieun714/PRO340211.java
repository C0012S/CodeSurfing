package Jieun714;
/**
 * 문제: 어떤 물류 센터는 로봇을 이용한 자동 운송 시스템을 운영합니다. 운송 시스템이 작동하는 규칙은 다음과 같습니다.
 *      1. 물류 센터에는 (r, c)와 같이 2차원 좌표로 나타낼 수 있는 n개의 포인트가 존재합니다. 각 포인트는 1~n까지의 서로 다른 번호를 가집니다.
 *      2. 로봇마다 정해진 운송 경로가 존재합니다. 운송 경로는 m개의 포인트로 구성되고 로봇은 첫 포인트에서 시작해 할당된 포인트를 순서대로 방문합니다.
 *      3. 운송 시스템에 사용되는 로봇은 x대이고, 모든 로봇은 0초에 동시에 출발합니다. 로봇은 1초마다 r 좌표와 c 좌표 중 하나가 1만큼 감소하거나 증가한 좌표로 이동할 수 있습니다.
 *      4. 다음 포인트로 이동할 때는 항상 최단 경로로 이동하며 최단 경로가 여러 가지일 경우, r 좌표가 변하는 이동을 c 좌표가 변하는 이동보다 먼저 합니다.
 *      5. 마지막 포인트에 도착한 로봇은 운송을 마치고 물류 센터를 벗어납니다. 로봇이 물류 센터를 벗어나는 경로는 고려하지 않습니다.
 *
 *      이동 중 같은 좌표에 로봇이 2대 이상 모인다면 충돌할 가능성이 있는 위험 상황으로 판단합니다. 관리자인 당신은 현재 설정대로 로봇이 움직일 때 위험한 상황이 총 몇 번 일어나는지 알고 싶습니다. 만약 어떤 시간에 여러 좌표에서 위험 상황이 발생한다면 그 횟수를 모두 더합니다.
 *      운송 포인트 n개의 좌표를 담은 2차원 정수 배열 points와 로봇 x대의 운송 경로를 담은 2차원 정수 배열 routes가 매개변수로 주어집니다. 이때 모든 로봇이 운송을 마칠 때까지 발생하는 위험한 상황의 횟수를 return 하도록 solution 함수를 완성해 주세요.
 * 입력: points = [[3, 2], [6, 4], [4, 7], [1, 4]], routes = [[4, 2], [1, 3], [2, 4]]
 * 출력: 1
 * 해결: 이중 Map
 * */
import java.util.*;
public class PRO340211 {
    class Solution {
        public int answer; //충돌 횟수를 저장하는 변수
        public Map<Integer, Map<String, Integer>> map; //시간별 좌표와 충돌 로봇 수 저장
        public StringBuilder sb = new StringBuilder();

        //특정 시간에 좌표(r, c)에 로봇의 수를 기록
        public void add(int r, int c, int t) {
            sb.setLength(0);
            sb.append(r).append(",").append(c); //좌표를 r,c 형태로 저장

            Map<String, Integer> timeMap = map.computeIfAbsent(t, k -> new HashMap<>()); //현재 시간 t가 없으면 새로운 Map 생성, 있으면 기존 Map 반환
            int count = timeMap.getOrDefault(sb.toString(), 0) + 1; //카운트 증가
            timeMap.put(sb.toString(), count);

            if (count == 2) answer++; //2개 이상이 되는 순간만 카운트
        }

        public int solution(int[][] points, int[][] routes) {
            answer = 0;
            map = new HashMap<>();

            for (int[] route : routes) {
                int time = 0;

                int startIdx = route[0] - 1;
                int curR = points[startIdx][0];
                int curC = points[startIdx][1];

                add(curR, curC, time); //출발 위치 기록

                for (int i = 1; i < route.length; i++) {
                    int endIdx = route[i] - 1;
                    int endR = points[endIdx][0];
                    int endC = points[endIdx][1];

                    //R 먼저 이동 후 C 이동
                    while (curR != endR) {
                        time++;
                        curR += (endR > curR) ? 1 : -1; //이동 좌표(R) 설정
                        add(curR, curC, time);
                    }

                    while (curC != endC) {
                        time++;
                        curC += (endC > curC) ? 1 : -1; //이동 좌표(L) 설정
                        add(curR, curC, time);
                    }
                }
            }

            return answer; //위험한 상황의 횟수 출력
        }
    }
}

package Jieun714;
/**
 * 문제: 도시에서 태양이 질 때에 보이는 건물들의 윤곽을 스카이라인이라고 한다. 스카이라인만을 보고서 도시에 세워진 건물이 몇 채인지 알아 낼 수 있을까? 건물은 모두 직사각형 모양으로 밋밋하게 생겼다고 가정한다.
 *      정확히 건물이 몇 개 있는지 알아내는 것은 대부분의 경우에 불가능하고, 건물이 최소한 몇 채 인지 알아내는 것은 가능해 보인다. 이를 알아내는 프로그램을 작성해 보자.
 * 입력: 첫째 줄에 n이 주어진다. (1 ≤ n ≤ 50,000) 다음 n개의 줄에는 왼쪽부터 스카이라인을 보아 갈 때 스카이라인의 고도가 바뀌는 지점의 좌표 x와 y가 주어진다. (1 ≤ x ≤ 1,000,000. 0 ≤ y ≤ 500,000)
 *      첫 번째 지점의 x좌표는 항상 1이다.
 * 출력: 첫 줄에 최소 건물 개수를 출력한다.
 * 해결: Stack
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ1863 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); //입력 좌표 수

        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()); //x
            int y = Integer.parseInt(st.nextToken()); //y

            //현재 높이보다 큰 건물 제거
            while (!stack.isEmpty() && stack.peek() > y) {
                stack.pop();
                answer++;
            }

            //같은 높이는 스킵
            if (!stack.isEmpty() && stack.peek() == y) continue;

            //0이 아니면 새 건물 시작
            if (y != 0) stack.push(y);
        }

        answer += stack.size(); //남아있는 건물 수 추가
        System.out.println(answer); //결과 출력
    }
}

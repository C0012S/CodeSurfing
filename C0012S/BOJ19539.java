/*
19539. Gold 5 - 사과나무

    시간 제한	                    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초 (추가 시간 없음)	        1024 MB          5568	    2294      1944	         43.056%


    문제
        이하는 최근 사과나무 씨앗을 구매하여 농장 뒷뜰에 일렬로 1번부터 N번까지 심었다. 이 나무들의 초기 높이는 모두 0이다.
        사과나무를 무럭무럭 키우기 위해 이하는 물뿌리개 2개를 준비했다. 한 물뿌리개는 나무 하나를 1만큼 성장시키고, 다른 물뿌리개는 나무 하나를 2만큼 성장시킨다. 이 물뿌리개들은 동시에 사용해야 하며, 물뿌리개를 나무가 없는 토양에 사용할 수는 없다. 두 물뿌리개를 한 나무에 사용하여 3만큼 키울 수도 있다.
        물뿌리개 관리 시스템을 전부 프로그래밍한 이하는 이제 사과나무를 키워보려고 했다. 그 순간, 갊자가 놀러와서 각 사과나무의 높이가 이런 배치가 되었으면 좋겠다고 말했다. 이제 이하는 약간 걱정이 되기 시작했는데, 갊자가 알려준 사과나무의 배치를 이 프로그램 상으로 만들어내지 못할 수도 있기 때문이다.
        이하는 이제 프로그램을 다시 수정하느라 바쁘기 때문에, 두 물뿌리개를 이용해 갊자가 알려준 사과나무의 배치를 만들 수 있는지의 여부를 판단하는 과정은 여러분의 몫이 되었다.


    입력
        첫 번째 줄에는 자연수 N이 주어진다. (1 ≤ N ≤ 100000) 이는 이하가 뒷뜰에 심은 사과나무의 개수를 뜻한다.
        두 번째 줄에는 N개의 정수 h_1, h_2, ..., h_N이 공백으로 구분되어 주어진다. (0 ≤ h_i ≤ 10000) h_i는 갊자가 바라는 i번째 나무의 높이이다.


    출력
        첫 번째 줄에 모든 나무가 갊자가 바라는 높이가 되도록 물뿌리개를 통해 만들 수 있으면 “YES”를, 아니면 “NO”를 따옴표를 제외하고 출력한다.


    예제 입력 1
        1
        0
    예제 출력 1
        YES

    예제 입력 2
        2
        4 3
    예제 출력 2
        NO

    예제 입력 3
        3
        10000 1000 100
    예제 출력 3
        YES

    예제 입력 4
        5
        1 3 1 3 1
    예제 출력 4
        NO


    알고리즘 분류
        수학
        그리디 알고리즘
*/


// 메모리 : 24020KB
// 시간 : 248ms
// 코드 길이 : 1048B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ19539 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine()); // 이하가 뒷뜰에 심은 사과나무의 개수
        int wateringCan2 = 0; // 나무 하나를 2만큼 성장시킬 수 있는 물뿌리개를 사용한 횟수
        int sum = 0; // 갊자가 바라는 나무의 높이 총합

        StringTokenizer token = new StringTokenizer(bf.readLine());
        for (int n = 0; n < N; n++) {
            int height = Integer.parseInt(token.nextToken()); // 갊자가 바라는 나무의 높이

            wateringCan2 += height / 2;
            sum += height;
        }

        if ((sum % 3 != 0) || (wateringCan2 < (sum / 3))) {
            System.out.println("NO");
        }
        else {
            System.out.println("YES");
        }
    }
}

/*
15903. Silver 1 - 카드 합체 놀이

    시간 제한	                메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초 (추가 시간 없음)	    512 MB           13961	    6086      5077	         43.375%


    문제
        석환이는 아기다. 아기 석환이는 자연수가 쓰여져있는 카드를 갖고 다양한 놀이를 하며 노는 것을 좋아한다. 오늘 아기 석환이는 무슨 놀이를 하고 있을까? 바로 카드 합체 놀이이다!
        아기 석환이는 자연수가 쓰여진 카드를 n장 갖고 있다. 처음에 i번 카드엔 ai가 쓰여있다. 카드 합체 놀이는 이 카드들을 합체하며 노는 놀이이다. 카드 합체는 다음과 같은 과정으로 이루어진다.
            1. x번 카드와 y번 카드를 골라 그 두 장에 쓰여진 수를 더한 값을 계산한다. (x ≠ y)
            2. 계산한 값을 x번 카드와 y번 카드 두 장 모두에 덮어 쓴다.

        이 카드 합체를 총 m번 하면 놀이가 끝난다. m번의 합체를 모두 끝낸 뒤, n장의 카드에 쓰여있는 수를 모두 더한 값이 이 놀이의 점수가 된다. 이 점수를 가장 작게 만드는 것이 놀이의 목표이다.
        아기 석환이는 수학을 좋아하긴 하지만, 아직 아기이기 때문에 점수를 얼마나 작게 만들 수 있는지를 알 수는 없었다(어른 석환이는 당연히 쉽게 알 수 있다). 그래서 문제 해결 능력이 뛰어난 여러분에게 도움을 요청했다. 만들 수 있는 가장 작은 점수를 계산하는 프로그램을 만들어보자.


    입력
        첫 번째 줄에 카드의 개수를 나타내는 수 n(2 ≤ n ≤ 1,000)과 카드 합체를 몇 번 하는지를 나타내는 수 m(0 ≤ m ≤ 15×n)이 주어진다.
        두 번째 줄에 맨 처음 카드의 상태를 나타내는 n개의 자연수 a1, a2, …, an이 공백으로 구분되어 주어진다. (1 ≤ ai ≤ 1,000,000)


    출력
        첫 번째 줄에 만들 수 있는 가장 작은 점수를 출력한다.


    예제 입력 1
        3 1
        3 2 6
    예제 출력 1
        16

    예제 입력 2
        4 2
        4 2 3 1
    예제 출력 2
        19


    알고리즘 분류
        자료 구조
        그리디 알고리즘
        우선순위 큐
*/


// 메모리 : 27548KB
// 시간 : 708ms
// 코드 길이 : 1765B
// 정렬 이용
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ15903_1 {
    static int n; // 카드의 개수 n (2 ≤ n ≤ 1,000)
    static int m; // 카드 합체 횟수 m (0 ≤ m ≤ 15 × n)
    static long a[]; // 처음 카드의 상태(카드에 쓰여진 수)를 저장하는 배열 (1 ≤ a_i ≤ 1,000,000)

    public static void cardUnionGame() { // 카드 합체 놀이 후, 만들 수 있는 가장 작은 점수를 계산하는 함수
        long sum;
        for (int u = 0; u < m; u++) { // 카드 합체 과정
            Arrays.sort(a);
            sum = a[0] + a[1]; // 고른 2 장의 카드에 쓰여진 수의 합
            a[0] = sum; // 2 장의 카드에 쓰여진 수의 합을 첫 번째 카드에 덮어 쓰기
            a[1] = sum; // 2 장의 카드에 쓰여진 수의 합을 두 번째 카드에 덮어 쓰기
        }

        long minScore = 0; // 카드 합체 놀이 후, 만들 수 있는 가장 작은 점수
        for (int r = 0; r < n; r++) { // 카드 합체가 끝난 후, 놀이의 점수 계산
            minScore += a[r];
        }

        System.out.println(minScore);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(token.nextToken());
        m = Integer.parseInt(token.nextToken());

        token = new StringTokenizer(bf.readLine());
        a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = Long.parseLong(token.nextToken());
        }

        cardUnionGame();
    }
}

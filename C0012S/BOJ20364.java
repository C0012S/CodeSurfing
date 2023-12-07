/*
20364. Silver 1 - 부동산 다툼

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    2 초	    1024 MB          3579	    1224      893	         31.544%


    문제
        이진 트리 모양의 땅으로 이루어진 꽉꽉마을에는 오리들이 살고 있다. 땅 번호는 다음과 같이 매겨진다.
            1. 루트 땅의 번호는 1이다.
            2. 어떤 땅의 번호가 K라면, 왼쪽 자식 땅의 번호는 2 × K, 오른쪽 자식 땅의 번호는 2 × K + 1이다.

        어느날 오리들끼리 부동산 다툼이 일어나서 꽉꽉마을 촌장 경완이가 해결책을 가져왔고, 그 내용은 다음과 같다.
            1. 오리들을 한 줄로 대기시킨다. 맨 처음 오리들은 1번 땅에 위치해 있다.
            2. 오리들이 서있는 순서대로 원하는 땅을 가지도록 한다.

                           [땅]            [오리] 3  5  6  2
                            1
                    2               3
                4       5       6

        만약, 한 오리가 원하는 땅까지 가는 길에 이미 다른 오리가 점유한 땅이 있다면 막대한 세금을 내야 하는 이유로 해당 땅을 지나가지 못해 그 오리는 땅을 가지지 못한다. 오리가 원하는 땅까지 가는 길에는 오리가 원하는 땅도 포함된다.
        경완이의 해결책대로 땅 분배를 했을 때 각 오리별로 원하는 땅을 가질 수 있는지, 가질 수 없다면 처음 마주치는 점유된 땅의 번호를 구해보자.


    입력
        첫 번째 줄에 땅 개수 N과 꽉꽉나라에 사는 오리 수 Q가 공백으로 구분되어 주어진다. (2 ≤ N < 2^20, 1 ≤ Q ≤ 200,000)
        두 번째 줄부터 차례로 Q개의 줄에 걸쳐 i+1번째 줄에는 i번째 오리가 원하는 땅 번호 xi가 주어진다. (2 ≤ xi ≤ N)


    출력
        Q개의 줄에 원하는 땅에 갈 수 있다면 0을, 갈 수 없다면 처음 마주치는 점유된 땅의 번호를 출력한다.


    예제 입력 1
        6 4
        3
        5
        6
        2
    예제 출력 1
        0
        0
        3
        0


    알고리즘 분류
        트리
*/


// 메모리 : 35820KB
// 시간 : 460ms
// 코드 길이 : 1876B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20364 {
    static boolean isOccupied[]; // 해당 인덱스의 번호의 땅의 점유 여부
    static StringBuilder sb;

    public static void occupyLand(int landNum) { // 오리가 원하는 땅을 점유하는 메서드  // 만약 오리가 원하는 땅까지 가는 길에 이미 다른 오리가 점유한 땅이 있을 경우, 땅을 가지지 못한다.  // 오리가 원하는 땅까지 가는 길에는 오리가 원하는 땅도 포함된다.
        int parentNum = landNum;
        int answerLandNum = 0;

        while (parentNum > 0) {
            if (isOccupied[parentNum]) { // 해당 땅이 이미 다른 오리가 점유한 땅일 경우
                answerLandNum = parentNum;
            }

            parentNum /= 2;
        }

        if (answerLandNum == 0) { // 오리가 원하는 땅을 점유할 수 있을 경우  // 오리가 원하는 땅까지 가는 길에 이미 다른 오리가 점유한 땅이 없을 경우
            isOccupied[landNum] = true; // 오리가 원하는 땅 점유
        }

        sb.append(answerLandNum);
        sb.append("\n");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(token.nextToken()); // 땅 개수 (2 ≤ N < 2^20)
        int Q = Integer.parseInt(token.nextToken()); // 꽉꽉나라에 사는 오리 수 (1 ≤ Q ≤ 200,000)

        isOccupied = new boolean[N + 1];
        sb = new StringBuilder();
        for (int q = 0; q < Q; q++) {
            occupyLand(Integer.parseInt(bf.readLine()));
        }

        System.out.println(sb);
    }
}

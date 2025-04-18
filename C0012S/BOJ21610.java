/*
21610. Gold 5 - 마법사 상어와 비바라기

    시간 제한	                    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    1 초 (추가 시간 없음)	        1024 MB          18363	    9521      6499	         49.649%


    문제
        마법사 상어는 파이어볼, 토네이도, 파이어스톰, 물복사버그 마법을 할 수 있다. 오늘 새로 배운 마법은 비바라기이다. 비바라기를 시전하면 하늘에 비구름을 만들 수 있다. 오늘은 비바라기를 크기가 N×N인 격자에서 연습하려고 한다. 격자의 각 칸에는 바구니가 하나 있고, 바구니는 칸 전체를 차지한다. 바구니에 저장할 수 있는 물의 양에는 제한이 없다. (r, c)는 격자의 r행 c열에 있는 바구니를 의미하고, A[r][c]는 (r, c)에 있는 바구니에 저장되어 있는 물의 양을 의미한다.
        격자의 가장 왼쪽 윗 칸은 (1, 1)이고, 가장 오른쪽 아랫 칸은 (N, N)이다. 마법사 상어는 연습을 위해 1번 행과 N번 행을 연결했고, 1번 열과 N번 열도 연결했다. 즉, N번 행의 아래에는 1번 행이, 1번 행의 위에는 N번 행이 있고, 1번 열의 왼쪽에는 N번 열이, N번 열의 오른쪽에는 1번 열이 있다.

        비바라기를 시전하면 (N, 1), (N, 2), (N-1, 1), (N-1, 2)에 비구름이 생긴다. 구름은 칸 전체를 차지한다. 이제 구름에 이동을 M번 명령하려고 한다. i번째 이동 명령은 방향 di과 거리 si로 이루어져 있다. 방향은 총 8개의 방향이 있으며, 8개의 정수로 표현한다. 1부터 순서대로 ←, ↖, ↑, ↗, →, ↘, ↓, ↙ 이다. 이동을 명령하면 다음이 순서대로 진행된다.
            1. 모든 구름이 di 방향으로 si칸 이동한다.
            2. 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가한다.
            3. 구름이 모두 사라진다.
            4. 2에서 물이 증가한 칸 (r, c)에 물복사버그 마법을 시전한다. 물복사버그 마법을 사용하면, 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수만큼 (r, c)에 있는 바구니의 물이 양이 증가한다.
                · 이때는 이동과 다르게 경계를 넘어가는 칸은 대각선 방향으로 거리가 1인 칸이 아니다.
                · 예를 들어, (N, 2)에서 인접한 대각선 칸은 (N-1, 1), (N-1, 3)이고, (N, N)에서 인접한 대각선 칸은 (N-1, N-1)뿐이다.
            5. 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다. 이때 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 한다.

        M번의 이동이 모두 끝난 후 바구니에 들어있는 물의 양의 합을 구해보자.


    입력
        첫째 줄에 N, M이 주어진다.
        둘째 줄부터 N개의 줄에는 N개의 정수가 주어진다. r번째 행의 c번째 정수는 A[r][c]를 의미한다.
        다음 M개의 줄에는 이동의 정보 di, si가 순서대로 한 줄에 하나씩 주어진다.


    출력
        첫째 줄에 M번의 이동이 모두 끝난 후 바구니에 들어있는 물의 양의 합을 출력한다.


    제한
        · 2 ≤ N ≤ 50
        · 1 ≤ M ≤ 100
        · 0 ≤ A[r][c] ≤ 100
        · 1 ≤ d_i ≤ 8
        · 1 ≤ s_i ≤ 50


    예제 입력 1
        5 4
        0 0 1 0 2
        2 3 2 1 0
        4 3 2 9 0
        1 0 2 9 0
        8 8 2 1 0
        1 3
        3 4
        8 1
        4 8
    예제 출력 1
        77

        구름이 있는 칸은 빨간색으로 표시했고, 물이 증가한 칸은 초록색으로 표시했다.
            0	0	1	0	2
            2	3	2	1	0
            4	3	2	9	0
            [1]	[0]	2	9	0
            [8]	[8]	2	1	0

        첫 번째 이동은 구름이 1번 방향(←)으로 3칸 이동해야 한다. 구름이 이동한 후는 다음과 같다.
            0	0	1	0	2
            2	3	2	1	0
            4	3	2	9	0
            1	0	[2]	[9]	0
            8	8	[2]	[1]	0

        구름이 있는 칸에 비가 1씩 내리고, 구름은 사라진다.
            0	0	1	0	    2
            2	3	2	1	    0
            4	3	2	9	    0
            1	0	[3]	[10]	0
            8	8	[3]	[2]	    0

        (4, 3)은 대각선 4개의 방향 모두에 물이 있다. 따라서, 물의 양이 4 증가해 7이 된다. (4, 4)는 대각선 2개의 방향(↖, ↙)에 물이 있다. 물의 양은 2 증가하고, 12가 된다. (5, 3)은 대각선으로 거리가 1인 칸이 2개(↖, ↗)있고, 이 중에서 1개(↗)만 물이 있다. 따라서, 물의 양은 3에서 4로 변한다. (5, 4)도 방향 1개(↖)만 물이 있기 때문에, 물의 양이 3이 된다.
            0	0	1	0	    2
            2	3	2	1	    0
            4	3	2	9	    0
            1	0	[7]	[12]	0
            8	8	[4]	[3]	    0

        이제 구름이 있었던 칸을 제외한 나머지 칸 중에서 물의 양이 2 이상인 칸에 구름이 생긴다. 구름이 생기면 물의 양이 2만큼 줄어든다.
            0	0	1	0	[0]
            [0]	[1]	[0]	1	0
            [2]	[1]	[0]	[7]	0
            1	0	7	12	0
            [6]	[6]	4	3	0

        두 번째 이동이 끝난 후의 상태는 다음과 같다.
            2	1	1	0	0
            0	1	0	1	2
            5	4	5	[5]	0
            4	5	12	15	0
            [4]	[4]	[2]	[1]	0

        다음은 세 번째 이동이 끝난 후의 상태이다.
            4	2	4	0	    2
            0	1	0	1	    [0]
            [3]	[2]	[3]	[3]	    0
            [2]	[3]	17	[13]	0
            [2]	[2]	[0]	1	    0

        모든 이동이 끝난 최종 상태는 다음과 같다.
            [2]	4	[2]	2	    4
            3	1	0	5	    3
            [1]	[0]	[1]	[1]	    0
            [0]	[1]	22	[11]	0
            4	5	0	3	    2

    예제 입력 2
        5 8
        0 0 1 0 2
        2 3 2 1 0
        0 0 2 0 0
        1 0 2 0 0
        0 0 2 1 0
        1 9
        2 8
        3 7
        4 6
        5 5
        6 4
        7 3
        8 2
    예제 출력 2
        41

        2	[1]	1	0	0
        [1]	[0]	[3]	[7]	1
        1	[1]	[9]	0	[0]
        0	1	[4]	0	2
        2	[1]	2	1	1

    예제 입력 3
        5 8
        100 100 100 100 100
        100 100 100 100 100
        100 100 100 100 100
        100 100 100 100 100
        100 100 100 100 100
        8 1
        7 1
        6 1
        5 1
        4 1
        3 1
        2 1
        1 1
    예제 출력 3
        2657

        100	    104	    104	    104	    100
        104	    112	    112	    119	    99
        109	    112	    105	    112	    104
        [99]	[112]	119	    112	    104
        [100]	[104]	104	    99	    104


    알고리즘 분류
        구현
        시뮬레이션
*/


// 메모리 : 21180KB
// 시간 : 200ms
// 코드 길이 : 5216B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ21610 {
    static int N; // 격자의 크기 (2 ≤ N ≤ 50)
    static int M; // 이동의 횟수 (1 ≤ M ≤ 100)
    static int[][] map; // 격자의 각 칸에 있는 바구니에 저장할 수 있는 물의 양을 저장하는 배열 (0 ≤ map[r][c] ≤ 100)
    static Queue<int[]> cloud; // 구름의 좌표를 저장하는 큐
    static boolean[][] isRained; // 각 좌표의 구름에서 비가 내린 여부를 저장하는 배열
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1}; // 좌, 좌상, 상, 우상, 우, 우하, 하, 좌하
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1}; // 좌, 좌상, 상, 우상, 우, 우하, 하, 좌하

    public static boolean check(int x, int y) { // 해당 좌표가 격자 범위 내의 좌표인지 검사하는 메서드
        if (x >= 0 && x < N && y >= 0 && y < N) {
            return true;
        }

        return false;
    }

    public static void move(int direction, int distance) { // 구름이 이동하고, 구름이 이동한 칸에 비를 내리는 메서드
        int cloudNum = cloud.size(); // 존재하는 구름의 개수
        for (int c = 0; c < cloudNum; c++) {
            int[] nowLocation = cloud.poll(); // 이동시키려는 구름의 좌표
            int nx = (nowLocation[0] + distance * dx[direction]) % N; // 이동시키려는 구름이 이동할 행
            int ny = (nowLocation[1] + distance * dy[direction]) % N; // 이동시키려는 구름이 이동할 열
            nx = nx < 0 ? N - Math.abs(nx) : nx;
            ny = ny < 0 ? N - Math.abs(ny) : ny;

            cloud.offer(new int[] {nx, ny}); // 구름이 direction 방향으로 distance만큼 칸 이동
            map[nx][ny] += 1; // 구름이 이동한 칸에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가
            isRained[nx][ny] = true; // 구름이 이동한 칸의 비가 내린 여부 표시
        }
    }

    public static void copy() { // 구름에서 비가 내려 물이 증가한 칸에 물복사버그마법을 시전하는 메서드
        while (!cloud.isEmpty()) {
            int[] nowLocation = cloud.poll();

            int count = 0; // 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니의 수
            for (int d = 1; d < 8; d += 2) {
                int nx = nowLocation[0] + dx[d];
                int ny = nowLocation[1] + dy[d];

                if (check(nx, ny) && map[nx][ny] > 0) { // 경계를 넘어가는 칸이 아니고, 해당 칸의 바구니에 물이 들어 있을 경우
                    count += 1;
                }
            }

            map[nowLocation[0]][nowLocation[1]] += count;
        }
    }

    public static void make() { // 구름을 생성하는 5 번 과정을 수행하는 메서드
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (isRained[r][c]) { // 비가 내려서 구름이 사라진 칸일 경우
                    isRained[r][c] = false;
                }
                else { // 비가 내려서 구름이 사라진 칸이 아닐 경우
                    if (map[r][c] >= 2) { // 해당 칸의 바구니에 저장된 물의 양이 2 이상일 경우
                        cloud.offer(new int[] {r, c}); // 해당 칸에 구름 생성
                        map[r][c] -= 2; // 해당 칸의 물의 양 2 감소
                    }
                }
            }
        }
    }

    public static void doMagic(int direction, int distance) { // 비바라기를 시전하는 메서드
        move(direction, distance);
        copy();
        make();
    }

    public static void find() { // 바구니에 들어있는 물의 양의 합을 구하고 출력하는 메서드
        int sum = 0;
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                sum += map[x][y];
            }
        }

        System.out.println(sum);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            token = new StringTokenizer(bf.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(token.nextToken());
            }
        }

        cloud = new ArrayDeque<>(); // 구름의 좌표 저장
        cloud.offer(new int[] {N - 1, 0});
        cloud.offer(new int[] {N - 1, 1});
        cloud.offer(new int[] {N - 2, 0});
        cloud.offer(new int[] {N - 2, 1});

        isRained = new boolean[N][N];
        for (int k = 0; k < M; k++) {
            token = new StringTokenizer(bf.readLine());
            doMagic(Integer.parseInt(token.nextToken()) - 1, Integer.parseInt(token.nextToken()));
        }

        find();
    }
}

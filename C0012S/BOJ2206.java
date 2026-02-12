/*
2206. Gold 3 - 벽 부수고 이동하기

    시간 제한	    메모리 제한        제출        정답	      맞힌 사람	    정답 비율
    2 초	    192 MB           181891	    50895     31946	         24.829%


    문제
        N×M의 행렬로 표현되는 맵이 있다. 맵에서 0은 이동할 수 있는 곳을 나타내고, 1은 이동할 수 없는 벽이 있는 곳을 나타낸다. 당신은 (1, 1)에서 (N, M)의 위치까지 이동하려 하는데, 이때 최단 경로로 이동하려 한다. 최단경로는 맵에서 가장 적은 개수의 칸을 지나는 경로를 말하는데, 이때 시작하는 칸과 끝나는 칸도 포함해서 센다.
        만약에 이동하는 도중에 한 개의 벽을 부수고 이동하는 것이 좀 더 경로가 짧아진다면, 벽을 한 개 까지 부수고 이동하여도 된다.
        한 칸에서 이동할 수 있는 칸은 상하좌우로 인접한 칸이다.
        맵이 주어졌을 때, 최단 경로를 구해 내는 프로그램을 작성하시오.


    입력
        첫째 줄에 N(1 ≤ N ≤ 1,000), M(1 ≤ M ≤ 1,000)이 주어진다. 다음 N개의 줄에 M개의 숫자로 맵이 주어진다. (1, 1)과 (N, M)은 항상 0이라고 가정하자.


    출력
        첫째 줄에 최단 거리를 출력한다. 불가능할 때는 -1을 출력한다.


    예제 입력 1
        6 4
        0100
        1110
        1000
        0000
        0111
        0000
    예제 출력 1
        15

    예제 입력 2
        4 4
        0111
        1111
        1111
        1110
    예제 출력 2
        -1


    알고리즘 분류
        그래프 이론
        그래프 탐색
        너비 우선 탐색
        격자 그래프
*/


// 메모리 : 107844KB
// 시간 : 696ms
// 코드 길이 : 4619B
// 정답

package C0012S;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2206 {
    static int N; // 맵의 행의 개수
    static int M; // 맵의 열의 개수
    static int[][] map; // 맵의 정보를 저장하는 배열
    static int[][][] distanceMap; // 맵의 각 좌표로 이동 시 지나는 칸의 개수의 최솟값을 저장하는 배열  // distance[x][y][0] : 벽을 부수지 않고 이동했을 때의 값을 저장, distance[x][y][1] : 벽 1 개를 부수고 이동했을 때의 값을 저장
    static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dy = {0, 0, -1, 1}; // 상, 하, 좌, 우

    static class Information { // 맵 이동 시 정보를 저장하는 클래스
        int x; // 맵의 행의 좌표
        int y; // 맵의 열의 좌표
        int crush; // 벽을 부술 수 있는 횟수
        int distance; // 현재 맵의 좌표까지 이동한 거리

        public Information(int x, int y, int crush, int distance) {
            this.x = x;
            this.y = y;
            this.crush = crush;
            this.distance = distance;
        }
    }

    public static boolean check(int x, int y) { // 해당 좌표가 맵 내 좌표인지 검사하는 메서드  // 맵 내 좌표일 경우 true 반환, 맵 내 좌표가 아닐 경우 false 반환
        if (x >= 0 && x < N && y >= 0 && y < M) {
            return true;
        }

        return false;
    }

    public static void move() { // 좌표 (1, 1)부터 좌표 (N, M)까지 이동 시, 최단 거리를 구하는 메서드
        distanceMap = new int[N][M][2];
        Queue<Information> queue = new ArrayDeque<>();
        int minCount = Integer.MAX_VALUE; // 좌표 (1, 1)부터 좌표 (N, M)까지 이동 시 지나는 칸의 개수의 최솟값

        Arrays.stream(distanceMap)
                .flatMap(Arrays::stream)
                .forEach(row -> Arrays.fill(row, Integer.MAX_VALUE));
        queue.offer(new Information(0, 0, 1, 1));

        while (!queue.isEmpty()) {
            Information now = queue.poll(); // 현재 칸까지 이동 시 구한 정보

            if (now.x == (N - 1) && now.y == (M - 1)) { // 좌표 (N, M)에 도착했을 경우
                minCount = Math.min(minCount, now.distance); // 최단 거리 갱신
            }

            int nextCount = now.distance + 1; // 다음 칸으로 이동할 때 걸리는 거리
            for (int d = 0; d < 4; d++) {
                int nx = now.x + dx[d]; // 다음 칸으로 이동할 때의 행의 좌표
                int ny = now.y + dy[d]; // 다음 칸으로 이동할 때의 열의 좌표

                if (check(nx, ny)) { // 이동할 칸의 좌표가 맵 내의 좌표일 경우
                    if (map[nx][ny] == 1) { // 이동할 칸에 벽이 있을 경우
                        if ((now.crush > 0) && (distanceMap[nx][ny][now.crush] > nextCount)) { // 벽을 부술 수 있는 횟수가 남아 있고, 이전에 구했던 거리의 값이 이동할 때 걸리는 거리보다 클 경우
                            distanceMap[nx][ny][now.crush] = nextCount;
                            queue.offer(new Information(nx, ny, now.crush - 1, nextCount));
                        }
                    }
                    else if (distanceMap[nx][ny][now.crush] > nextCount) { // 이동할 칸에 벽이 있지 않고, 이전에 구했던 거리의 값이 이동할 때 걸리는 거리보다 클 경우
                        distanceMap[nx][ny][now.crush] = nextCount;
                        queue.offer(new Information(nx, ny, now.crush, nextCount));
                    }
                }
            }
        }

        if (minCount == Integer.MAX_VALUE) { // 좌표 (1, 1)부터 좌표 (N, M)까지 이동이 불가능할 경우
            System.out.println(-1);
        }
        else {
            System.out.println(minCount);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String str = bf.readLine();

            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        move();
    }
}

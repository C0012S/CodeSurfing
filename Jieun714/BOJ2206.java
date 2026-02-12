package Jieun714;
/**
 * 문제: N×M의 행렬로 표현되는 맵이 있다. 맵에서 0은 이동할 수 있는 곳을 나타내고, 1은 이동할 수 없는 벽이 있는 곳을 나타낸다. 당신은 (1, 1)에서 (N, M)의 위치까지 이동하려 하는데, 이때 최단 경로로 이동하려 한다. 최단경로는 맵에서 가장 적은 개수의 칸을 지나는 경로를 말하는데, 이때 시작하는 칸과 끝나는 칸도 포함해서 센다.
 *      만약에 이동하는 도중에 한 개의 벽을 부수고 이동하는 것이 좀 더 경로가 짧아진다면, 벽을 한 개 까지 부수고 이동하여도 된다.
 *      한 칸에서 이동할 수 있는 칸은 상하좌우로 인접한 칸이다.
 *      맵이 주어졌을 때, 최단 경로를 구해 내는 프로그램을 작성하시오.
 * 입력: 첫째 줄에 N(1 ≤ N ≤ 1,000), M(1 ≤ M ≤ 1,000)이 주어진다. 다음 N개의 줄에 M개의 숫자로 맵이 주어진다. (1, 1)과 (N, M)은 항상 0이라고 가정하자.
 * 출력: 첫째 줄에 최단 거리를 출력한다. 불가능할 때는 -1을 출력한다.
 * 해결: BFS
 * */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2206 {
    public static int N, M;
    public static int [][] map;
    public static boolean [][][] isVisited;
    public static int [] dx = {0, 0, 1, -1};
    public static int [] dy = {1, -1, 0, 0};

    public static int bfs() {
        Queue<int []> que = new ArrayDeque<>();
        que.add(new int[] {0, 0, 1, 1});

        while(!que.isEmpty()) {
            int [] now = que.poll();
            if(now[0] == N-1 && now[1] == M-1) return now[3];

            for(int i=0; i<4; i++) {
                int nx = now[1] + dx[i];
                int ny = now[0] + dy[i];
                int isBroken = now[2];

                if(ny < 0 || ny >= N || nx < 0 || nx >= M) continue;

                //벽일 경우
                if (map[ny][nx] == 1) {
                    if (now[2] == 0) continue; //더 이상 못 부숨
                    isBroken = 0; //벽 부수기
                }

                if (!isVisited[ny][nx][isBroken]) {
                    isVisited[ny][nx][isBroken] = true; //발문 처리
                    que.add(new int[]{ny, nx, isBroken, now[3] + 1});
                }
            }
        }
        return -1; //최단 거리 계산 불가능할 경우
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //행
        M = Integer.parseInt(st.nextToken()); //열
        map = new int[N][M]; //입력 받은 맵의 형태를 담는 배열
        isVisited = new boolean[N][M][2]; //벽의 상태를 담는 배열(부순 상태 여부)

        for(int i=0; i<N; i++) {
            String str = br.readLine();
            for(int j=0; j<M; j++) map[i][j] = Character.getNumericValue(str.charAt(j));
        }

        if(N == 1 && M == 1) System.out.println(1);
        else System.out.println(bfs());
    }
}

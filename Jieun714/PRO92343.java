package Jieun714;
/**
 * 문제: 2진 트리 모양 초원의 각 노드에 늑대와 양이 한 마리씩 놓여 있습니다. 이 초원의 루트 노드에서 출발하여 각 노드를 돌아다니며 양을 모으려 합니다. 각 노드를 방문할 때 마다 해당 노드에 있던 양과 늑대가 당신을 따라오게 됩니다.
 *      이때, 늑대는 양을 잡아먹을 기회를 노리고 있으며, 당신이 모은 양의 수보다 늑대의 수가 같거나 더 많아지면 바로 모든 양을 잡아먹어 버립니다. 당신은 중간에 양이 늑대에게 잡아먹히지 않도록 하면서 최대한 많은 수의 양을 모아서 다시 루트 노드로 돌아오려 합니다.
 *      각 노드에 있는 양 또는 늑대에 대한 정보가 담긴 배열 info, 2진 트리의 각 노드들의 연결 관계를 담은 2차원 배열 edges가 매개변수로 주어질 때,
 *      문제에 제시된 조건에 따라 각 노드를 방문하면서 모을 수 있는 양은 최대 몇 마리인지 return 하도록 solution 함수를 완성해주세요.
 *
 * 입력: info=[0,0,1,1,1,0,1,0,1,0,1,1], edges=[[0,1],[1,2],[1,4],[0,8],[8,7],[9,10],[9,11],[4,3],[6,5],[4,6],[8,9]]
 * 출력: 5
 * 해결: DFS
 * */
import java.util.*;
public class PRO92343 {
    class Solution {
        public int answer;
        public ArrayList<Integer> [] graph;

        public void dfs(int sheep, int wolf, int node, int [] info, boolean [] isVisited) {
            //현재 노드가 양인지 늑대인지 체크
            if(info[node] == 0){
                sheep++;
                answer = Math.max(answer, sheep); //최대 양 마리 수 갱신
            } else{
                wolf++;
            }

            if(sheep <= wolf) return; //늑대가 양보다 많거나 같으면 종료

            isVisited[node] = true; //현재 노드 방문 처리
            //현재까지 방문한 노드 중에서 갈 수 있는 다음 후보 탐색
            for(int i = 0; i<info.length; i++) {
                if(isVisited[i]) {
                    for(int next : graph[i]) { //방문한 노드와 연결된 노드 탐색
                        if(!isVisited[next]) dfs(sheep, wolf, next, info, isVisited);
                    }
                }
            }
            isVisited[node] = false; //방문 처리 해제(백트래킹을 위함)
        }

        public int solution(int[] info, int[][] edges) {
            answer = 0;
            graph = new ArrayList[info.length];
            for(int i=0; i<info.length; i++) graph[i] = new ArrayList<>();

            for(int i=0; i<edges.length; i++) {
                int a = edges[i][0];
                int b = edges[i][1];

                graph[a].add(b);
                graph[b].add(a);
            }

            dfs(0, 0, 0, info, new boolean[info.length]);

            return answer;
        }
    }
}

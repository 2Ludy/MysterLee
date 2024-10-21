import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class back16236 {
    static int N,S,result,size,eat;
    static int[][] data;
    static int[] dr = {-1,0,0,1};
    static int[] dc = {0,-1,1,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        data = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                data[i][j] = Integer.parseInt(st.nextToken());
                if(data[i][j]==9) {
                    data[i][j]=0; // 상어 위치 값 제거
                    S = i*N+j; // 상어 처음 위치 저장
                }
            }
        }
        // 로직
        eat = 0; // 먹는 물고기 개수
        result = 0; // 총 이동 거리
        size = 2; // 상어 크기
        while(true) {
            int a = bfs(S); // 이동 거리 반환
            if(a==0) break; // 이동 거리 없으면 break
            result+=a; // 총 이동거리 갱신
            // 상어 크기 업데이트 조건
            if(eat==size) {
                eat = 0;
                size++;
            }
        }
        // 출력
        System.out.println(result);
    }

    private static int bfs(int s) {
        // bfs 를 위한 설정
        Queue<Integer> q = new LinkedList<>();
        ArrayList<int []> arr = new ArrayList<>();
        boolean[] visited = new boolean[N*N];
        // 큐에 시작 위치 추가
        q.add(s);
        visited[s]=true;

        // 이동 거리 계산을 위한 변수
        int dis = 0;
        int temp = 0;
        int temp2 = 1;
        // 가장 가까운 물고기가 있는 거리를 저정하기 위한 변수
        int flag = Integer.MAX_VALUE;
        // 먹을 물고기 없을 때, 이동거리 0 반환
        boolean check=false;


        while(!q.isEmpty()) {
            int a = q.remove();
            temp2--;

            for (int i = 0; i < 4; i++) {
                int r = a/N + dr[i];
                int c = a%N + dc[i];

                if(r>=0 && r<N && c>=0 && c<N) {
                    if(visited[r*N+c]) continue;
                    if(data[r][c]>=0 && data[r][c]<=size) {
                        q.add(r*N+c);
                        temp++;
                        visited[r*N+c]=true;
                        if(data[r][c] == size || data[r][c] == 0) continue;
                        // 먹을 수 있는 물고기를 arr 리스트에 추가한다..
                        flag = dis;
                        check = true;
                        arr.add(new int[]{r,c});
                    }
                }
            }
            // 이동 거리 계산
            if(temp2==0) {
                temp2=temp;
                temp=0;
                dis++;
            }
            // 최단 거리에 있는 물고기 탐색 이후 bfs 탐색하는 것을 막기 위해..
            if(flag < dis) break;
        }
        select(arr);
        if(!check) dis=0;
        return dis;
    }
    // 같은 거리에서 위, 왼쪽 우선순위를 설정하기 위한 메서드
    private static void select(ArrayList<int[]> arr) {
        int r_min=N-1;
        int c_min=N-1;

        for(int[] tmp:arr) {
            r_min = Math.min(r_min,tmp[0]);
        }
        for(int[] tmp:arr) {
            if(tmp[0]==r_min) c_min=Math.min(c_min, tmp[1]);
        }
        eat++;
        data[r_min][c_min]=0; // 지정된 물고기를 먹고 해당 위치를 0으로 초기화한다.
        S = r_min*N+c_min; // 현재 위치를 먹은 물고기 위치로 변경
    }
}

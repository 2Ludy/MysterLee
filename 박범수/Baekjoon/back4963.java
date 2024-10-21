import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class back4963 {
    static int N,M;
    static int[][] data;
    static int[] dr = {-1,0,0,1,1,-1,-1,1};
    static int[] dc = {0,-1,1,0,1,1,-1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        String end = "0 0"; // 종료 문자열
        String input; // 입력값

        // 종료 문자열이 나올 때까지 반복
        while(!(input = br.readLine()).equals(end)) {
            st = new StringTokenizer(input);
            M = Integer.parseInt(st.nextToken()); // 열
            N = Integer.parseInt(st.nextToken()); // 행
            int result=0; // 섬의 개수
            data = new int[N][M]; // 입력값

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    data[i][j]=Integer.parseInt(st.nextToken());
                }
            }

            // 로직
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(data[i][j]==1) {
                        bfs(i,j); // 연결된 섬을 찾는다
                        result++;
                    }
                }
            }
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }

    // bfs 로직
    private static void bfs(int x, int y) {
        Queue<int []> q = new LinkedList<>();
        // 큐에 시작 위치 추가
        q.add(new int[] {x,y});

        while(!q.isEmpty()) {
            int[] arr = q.remove();

            for (int i = 0; i < 8; i++) {
                int r = arr[0] + dr[i];
                int c = arr[1] + dc[i];

                if(r>=0 && r<N && c>=0 && c<M) {
                    if(data[r][c]==0) continue;
                    data[r][c]=0;
                    q.add(new int[] {r,c});
                }
            }
        }
    }
}

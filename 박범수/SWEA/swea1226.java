import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class  swea1226{
    static int T,N;
    static int[][] data;
    static int[] dr = {1,0,-1,0};
    static int[] dc = {0,1,0,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = 10; // TC
        N = 16; // 행과 열
        for (int i = 1; i <= T; i++) {
            int g = Integer.parseInt(br.readLine());
            data = new int[N][N];
            for (int j = 0; j < N; j++) {
                char[] temp = br.readLine().toCharArray();
                for (int k = 0; k < N; k++) {
                    data[j][k]=temp[k]-'0'; // char to int
                }
            }
            // 로직
            int result = bfs();  // bfs
            System.out.println("#"+i+" "+result);
        }

    }

    private static int bfs() {
        Queue<int[]> q = new LinkedList<>();
        int[] arr = {1,1};
        q.add(arr);
        data[1][1]=1;
        int tmp = 0; // 도착하면 1, 도착하지 못하면 0

        out:
        // bfs 로직
        while(!q.isEmpty()) {
            int[] arr2 = q.remove();
            for (int i = 0; i < 4; i++) {
                int r = dr[i]+ arr2[0];
                int c = dc[i]+ arr2[1];

                if(r>=0 && r<N && c>=0 && c<N) {
                    if(data[r][c]==1) continue;
                    if(data[r][c]==3) {
                        tmp=1; // 도착
                        break out; // while 문 종료
                    }
                    data[r][c]=1; // 방문처리
                    int[] arr3 = {r,c};
                    q.add(arr3);
                }
            }
        }
        return tmp;
    }

}

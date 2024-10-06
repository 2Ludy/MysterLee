import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea22683 {
    static int T,N,K,S,min;
    static char[][] data;
    static int[] dr = {1,0,-1,0};
    static int[] dc = {0,1,0,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine()); // TC
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 행, 열
            K = Integer.parseInt(st.nextToken()); // 최대 나무 제거 횟수
            data = new char[N][N];
            for (int i = 0; i < N; i++) {
                data[i] = br.readLine().toCharArray();
                for (int j = 0; j < N; j++) {
                    if (data[i][j] == 'X') S = i * N + j; // 시작위치
                }
            }
            // 로직
            min = Integer.MAX_VALUE; // 정답
            boolean[][] visited = new boolean[N][N]; // 방문위치
            search(visited,S/N,S%N,0,1,0); // 백트래킹
            if(min==Integer.MAX_VALUE) min=-1; // 예외처리
            System.out.println("#"+t+" "+min); // 갑 출력
        }
    }

    private static void search(boolean[][] visited, int r, int c, int sum, int dir, int count) {
        if(data[r][c]=='T') count++; // 나무 위치에 있을 때, 횟수를 셈
        if(count>K) return; // 최대 제거 횟수보다 클때, 탐색 종료

        // 종료 지점에 도착했을 때, 최소값을 갱신
        if(data[r][c]=='Y') {
            min = Math.min(min,sum);
            return;
        }
        // 이동
        for (int i = 0; i < 4; i++) {
            int x = r+dr[i];
            int y = c+dc[i];
            if(x>=0 && x<N && y>=0 && y<N){
                if(visited[x][y]) continue; // 방문한곳은 가지 않음
                int tmp = direction(r,c,x,y); // 방향 설정
                visited[x][y]=true;
                if(tmp==dir) search(visited,x,y,sum+1,tmp,count); // 방형이 0도 일때
                else if(Math.abs(tmp-dir)==2) search(visited,x,y,sum+3,tmp, count); // 방향이 180도 일때
                else if(Math.abs(tmp-dir)!=2) search(visited,x,y,sum+2,tmp, count); // 방향이 90도 일때
                visited[x][y]=false; // 재방문을 위해
            }
        }
    }
    private static int direction(int r, int c, int x, int y){
        if(c==y && x-r==-1) return 1; // 북
        else if(r==x && y-c==1) return 2; // 동
        else if(c==y && x-r==1) return 3; // 남
        else if(r==x && y-c==-1) return 4; // 서
        else return 0; // 그냥
    }
}

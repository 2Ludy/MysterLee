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
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            data = new char[N][N];
            for (int i = 0; i < N; i++) {
                data[i] = br.readLine().toCharArray();
                for (int j = 0; j < N; j++) {
                    if (data[i][j] == 'X') S = i * N + j;
                }
            }
            min = Integer.MAX_VALUE;
            boolean[][] visited = new boolean[N][N];
            search(visited,S/N,S%N,0,1,0);
            if(min==Integer.MAX_VALUE) min=-1;
            System.out.println("#"+t+" "+min);
        }
    }

    private static void search(boolean[][] visited, int r, int c, int sum, int dir, int count) {
        if(data[r][c]=='T') count++;
        if(count>K) return;

        if(data[r][c]=='Y') {
            min = Math.min(min,sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int x = r+dr[i];
            int y = c+dc[i];
            if(x>=0 && x<N && y>=0 && y<N){
                if(visited[x][y]) continue;
                int tmp = direction(r,c,x,y);
                visited[x][y]=true;
                if(tmp==dir) search(visited,x,y,sum+1,tmp,count);
                else if(Math.abs(tmp-dir)==2) search(visited,x,y,sum+3,tmp, count);
                else if(Math.abs(tmp-dir)!=2) search(visited,x,y,sum+2,tmp, count);
                visited[x][y]=false;
            }
        }
    }
    private static int direction(int r, int c, int x, int y){
        if(c==y && x-r==-1) return 1;
        else if(r==x && y-c==1) return 2;
        else if(c==y && x-r==1) return 3;
        else if(r==x && y-c==-1) return 4;
        else return 0;
    }
}

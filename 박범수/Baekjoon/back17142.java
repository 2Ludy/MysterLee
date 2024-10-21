import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class back17142 {
    static int N,M,zero,min, arr_len;
    static int[][] data;
    static ArrayList<Integer> arr;
    static int[] nums;
    static int[] dr = {0,1,0,-1};
    static int[] dc = {1,0,-1,0};
    static boolean check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        data = new int[N][N];
        zero = 0; // 빈칸 개수를 세기 위한 값
        arr = new ArrayList<>(); // 바이러스 위치
        nums = new int[M]; // 조합 경우의 수를 저장하기 위한 배열

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                data[i][j]=Integer.parseInt(st.nextToken());
                if(data[i][j]==0) zero++;
                if(data[i][j]==2) arr.add(i*N+j);
            }
        }
        // 로직
        arr_len = arr.size();
        min = Integer.MAX_VALUE;
        check = false;
        nCr(0,0); // 백트래킹 && bfs
        if(!check) min=-1; // 예외처리
        System.out.println(min);
    }
    static void nCr(int cnt, int start){
        if(cnt==M){
            int b = bfs();
            // 한번이라도 -1 이외에 값이 나온다면 그 값으로 최소시간을 갱신
            if(b!=-1) {
                check = true;
                min = Math.min(--b,min); // bfs 구한 시간에서 1을 한번 빼준다
            }
            return;
        }
        for (int i = start; i < arr_len; i++) {
            nums[cnt]=arr.get(i);
            nCr(cnt+1,i+1);
            nums[cnt]=0;
        }
    }
    static int bfs(){
        int[][] data2 = new int[N][N];
        for (int i = 0; i < N; i++) {
            data2[i]=data[i].clone();
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            q.add(nums[i]);
            data2[nums[i]/N][nums[i]%N]=3;
        }
        int sec=0; // 시간
        int temp=M;
        int temp2=0;
        int check=0; // 빈칸에 바이러스를 퍼트린 개수

        while(!q.isEmpty()){
            int a = q.remove();
            temp--;

            for (int i = 0; i < 4; i++) {
                int r = a/N+dr[i];
                int c = a%N+dc[i];

                if(r>=0 && r<N && c>=0 && c<N){
                    if(data2[r][c]==1 || data2[r][c]==3) continue;
                    if(check==zero) continue;
                    if(data2[r][c]==0) check++;
                    data2[r][c]=3;
                    q.add(r*N+c);
                    temp2++;
                }
            }
            // q에 들어간 값과 나온 값을 계산해서 시간을 정한다
            if(temp==0){
                temp=temp2;
                temp2=0;
                sec++;
            }
        }
        if(check< zero) sec=-1;

        return sec;
    }
}

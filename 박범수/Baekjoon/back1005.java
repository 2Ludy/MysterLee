import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class back1005 {
    static int T,N,M,W;
    static List<Integer>[] abjlist;
    static int[] indegree;
    static int[] result;
    static int[] cost;
    static int[] answer;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            abjlist = new ArrayList[N+1];
            indegree = new int[N+1];
            result = new int[N+1];
            cost = new int[N+1];
            answer = new int[N+1];

            for (int i = 0; i <= N; i++) {
                abjlist[i]= new ArrayList<>();
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                cost[i]=Integer.parseInt(st.nextToken());
            }
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                abjlist[a].add(b);
                indegree[b]++;
            }
            W = Integer.parseInt(br.readLine());
            // 로직
            int count=0;
            int temp=0;
            int time=1;
            Queue<Integer> q = new LinkedList<>();
            for (int i = 1; i < indegree.length; i++) {
                if(indegree[i]==0) {
                    q.add(i);
                    count++;
                    indegree[i]=-1;
                }
            }
            // 위상정렬 알고리즘
            while(!q.isEmpty()) {
                int a=q.poll();
                count--;
                for(int i:abjlist[a]) {
                    indegree[i]--;
                    if(indegree[i]==0) {
                        temp++;
                        indegree[i]=-1;
                        q.add(i);
                    }
                }
                result[a]=time;
                if(count==0) {
                    count=temp;
                    temp=0;
                    time++;
                }
            }
            // 1단계 부터 시작
            int con = 1;
            // 1단계 포함된 노드들의 비용 값을 초기화
            for (int i = 1; i <= N; i++) {
                if(result[i]==1) answer[i]=cost[i];
            }
            // 메모이제이션
            // 이전 값에서 현재 값을 더해 노드를 인덱스로 취급하여 저장
            while(result[W]>con) {
                for (int i = 1; i < result.length; i++) {
                    if(result[i]==con) {
                        for (int j: abjlist[i]) {
                            if (answer[j]!=0) { // 값이 있다면 이미 한번 값이 갱신된 것인데, 이떄 값이 큰것으로 교체
                                if(answer[j]<cost[j]+answer[i]) {
                                    answer[j]=cost[j]+answer[i];
                                }
                            }else {
                                answer[j]+=(cost[j]+answer[i]); // 값이 없으면 현재값과 이전값을 더한 값을 저장
                            }
                        }
                    }
                }
                con++;
            }
            System.out.println(answer[W]);
        }
    }
}
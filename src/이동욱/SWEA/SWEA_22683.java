package 이동욱.SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA_22683 {
	
	static int TC, N, T, sr, sc;
	static char[][] map;
	static boolean[][][] visited;
	static int[] dr = {-1, 0, 1, 0}; // 위 오른쪽 아래 왼쪽
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		TC = Integer.parseInt(br.readLine());
		a : for(int t=1; t<=TC; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			T = Integer.parseInt(st.nextToken());
			map = new char[N][N];
			visited = new boolean[N][N][T+1];
			
			for(int i=0; i<N; i++) {
				char[] cs = br.readLine().toCharArray();
				for(int j=0; j<N; j++) {
					map[i][j] = cs[j];
					if(map[i][j] == 'X') {
						sr = i;
						sc = j;
					}
				}
			} // 완
			
			PriorityQueue<int[]> que = new PriorityQueue<>(new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					return o1[2] - o2[2];
				}
			});
			
			que.offer(new int[] {sr, sc, 0, T, 0}); // 인덱스 순서대로 r, c, count, T, 방향(0:위, 1: 오른쪽, 2:아래, 3:왼쪽)
			visited[sr][sc][T] = true;
			while(!que.isEmpty() ) {
				int[] nums = que.poll();
				int r = nums[0];
				int c = nums[1];
				int count = nums[2];
				int tree = nums[3];
				int direction = nums[4];
				
				if(map[r][c] == 'Y') {
					System.out.println("#" + t + " " + count);
					continue a;
				}
				
				for(int d=0; d<4; d++) {
					int nr = r+dr[d];
					int nc = c+dc[d];
					if(!check(nr,nc)) continue;
					if(visited[nr][nc][tree]) continue;
					int tmp = Math.abs(direction-d);
					if(tmp == 3) tmp = 1;
					int treetmp=tree;
					if(map[nr][nc] == 'T') {
						if(tree == 0) {
							continue;
						}else {
							treetmp--;
						}
					}
					visited[nr][nc][treetmp] = true;
					que.offer(new int[] {nr, nc, count+tmp+1, treetmp, d});
					
				}
			}
			System.out.println("#" + t + " " + -1);
		}
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}
}

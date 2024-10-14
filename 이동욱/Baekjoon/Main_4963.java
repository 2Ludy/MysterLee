package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_4963 { 
	
	static int N, M;
	static int[][] map;
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
	static int count;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			
			if(N == 0 && M == 0) return;
			
			map = new int[N][M];
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			} // 맵 생성
			
			count = 1;
			
			Queue<int[]> que = new LinkedList<>();
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j] == 1) {
						count++;
						map[i][j] = count;
						que.offer(new int[] {i, j});
						while(!que.isEmpty()) {
							int[] nums = que.poll();
							int r = nums[0];
							int c = nums[1];
							for(int d=0; d<8; d++) {
								int nr = r+dr[d];
								int nc = c+dc[d];
								if(!check(nr,nc)) continue;
								if(map[nr][nc] != 1) continue;
								map[nr][nc] = count;
								que.offer(new int[] {nr,nc});
							}
						}
					}
				}
			}
			System.out.println(count-1);
		}
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}
}

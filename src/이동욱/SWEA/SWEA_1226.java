package 이동욱.SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA_1226 {
	
	static int T = 10, M = 16;
	static int N, sr, sc;
	static int[] dr = {1, -1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	static char[][] map;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		a : for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new char[16][16];
			for(int i=0; i<M; i++) {
				char[] cs = br.readLine().toCharArray();
				for(int j=0; j<M; j++) {
					map[i][j] = cs[j];
					if(map[i][j] == '2') {
						sr = i;
						sc = j;
					}
				}
			} // 맵 생성
			
			Queue<int []> que = new LinkedList<>();
			que.offer(new int[] {sr, sc});
			while(!que.isEmpty()) {
				int[] nums = que.poll();
				int r = nums[0];
				int c = nums[1];
				for(int d=0; d<4; d++) {
					int nr = r+dr[d];
					int nc = c+dc[d];
					if(map[nr][nc] == '3') {
						System.out.println("#" + N + " " + 1);
						continue a;
					}
					if(map[nr][nc] != '0') continue;
					map[nr][nc] = '1';
					que.offer(new int[] {nr,nc});
				}
			}
			System.out.println("#" + N + " " + 0);
		}
	}
}

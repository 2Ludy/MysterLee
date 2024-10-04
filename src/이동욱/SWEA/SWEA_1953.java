package 이동욱.SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1953 {
	
	static int T, N, M, sr, sc, L, count;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 0, 1, 0}; // 위 오른쪽 아래 왼쪽
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			sr = Integer.parseInt(st.nextToken());
			sc = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			visited = new boolean[N][M];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			if(L == 1) {
				System.out.println("#" + t + " " + 1);
				continue;
			}
			
			Queue<int[]> que = new LinkedList<>(); // 인덱스 순서대로 r, c, time
			que.offer(new int[] {sr, sc, 1});
			visited[sr][sc] = true;
			count = 1;
			while(!que.isEmpty()) {
				int[] nums = que.poll();
				int r = nums[0];
				int c = nums[1];
				int time = nums[2];
				int type = map[r][c]; // 지하 터널 타입
				
				for(int d=0; d<4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if(!check(nr,nc)) continue;
					if(visited[nr][nc]) continue;
					if(map[nr][nc] == 0) continue;
					if(!checkType(type,nr,nc,d)) continue;
					visited[nr][nc] = true;
					count++;
					if(time+1 == L) {
						continue;
					}else {
						que.offer(new int[] {nr, nc, time+1});
					}
				}
			}
			System.out.println("#" + t + " " + count);
		}
	}

	private static boolean checkType(int type, int r, int c, int direction) {
		int nextType = map[r][c];
		if(direction == 0) {
			if(type == 1 || type == 2 || type == 4 || type == 7) {
				if(nextType == 1 || nextType == 2 || nextType == 5 || nextType == 6) return true;
			}
		}else if(direction == 1) {
			if(type == 1 || type == 3 || type == 4 || type == 5) {
				if(nextType == 1 || nextType == 3 || nextType == 6 || nextType == 7) return true;
			}
		}else if(direction == 2) {
			if(type == 1 || type == 2 || type == 5 || type == 6) {
				if(nextType == 1 || nextType == 2 || nextType == 4 || nextType == 7) return true;
			}
		}else if(direction == 3) {
			if(type == 1 || type == 3 || type == 6 || type == 7) {
				if(nextType == 1 || nextType == 3 || nextType == 4 || nextType == 5) return true;
			}
		}

		return false;
	}

	static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}
}

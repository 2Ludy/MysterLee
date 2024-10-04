package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
	* 구조물 타입
	1 : 상하좌우
	2 : 상하
	3 : 좌우
	4 : 상우
	5 : 하우
	6 : 하좌
	7 : 상좌
 */


public class Solution_1953 { // 탈주범 검거 (경로 탐색 > bfs)
	static int T,N,M,R,C,L,loc;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0}; // 상하좌우
	static int[] dc = {0, 0, -1, 1};
	
	static int[][] pipeType = {
			{},
			{0,1,2,3},
			{0,1},
			{2,3},
			{0,3},
			{1,3},
			{1,2},
			{0,2}
	};
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 행
			M = Integer.parseInt(st.nextToken()); // 열
			R = Integer.parseInt(st.nextToken()); // 맨홀 행 
			C = Integer.parseInt(st.nextToken()); // 맨홀 열
			L = Integer.parseInt(st.nextToken()); // 탈출 시간
			
			map = new int[N][M];
			visited = new boolean[N][M];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			loc = 0; // 탈주범 위치 개수
			bfs(R,C);
			
			sb.append("#"+t+" "+loc+"\n");
			
		} // tc
		System.out.print(sb);
	}


	static void bfs(int r, int c) {
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] {r,c,1});
		visited[r][c] = true;
		loc = 1;
		
		while(!que.isEmpty()) {
			int cur[] = que.poll();
			int cr = cur[0];
			int cc = cur[1];
			int time = cur[2];

			if(time == L) continue;
			
			int type = map[cr][cc];
			for(int d : pipeType[type]) {
				int nr = cr + dr[d];
				int nc = cc + dc[d];
				
				if(!check(nr,nc)) continue;
				if(connected(cr,cc,nr,nc)) {
					que.offer(new int[] {nr,nc,time+1});
					visited[nr][nc] = true;
					loc++;
				}
			}
		}
	}

	static boolean connected(int cx, int cy, int nx, int ny) {
		int cur = map[cx][cy];
		int nex = map[nx][ny];
		
		int dir;
		if(cx > nx) dir = 0; 	  // 상
		else if(cx < nx) dir = 1; // 하
		else if(cy > ny) dir = 2; // 좌
		else dir = 3; 			  // 우
		
		boolean check = false;
		for(int d : pipeType[cur]) {
			if(d == dir) {
				check = true;
				break;
			}
		}
		
		if(!check) return false;
		
		int backdir = getBack(dir);
		for(int d : pipeType[nex]) {
			if(d == backdir) return true;
		}
		
		return false;
	}


	private static int getBack(int dir) {
	    if (dir == 0) return 1;      // 상 -> 하
	    else if (dir == 1) return 0; // 하 -> 상
	    else if (dir == 2) return 3; // 좌 -> 우
	    else return 2;               // 우 -> 좌	
	}


	static boolean check(int rr, int cc) {
		return rr >= 0 && rr < N && cc >= 0 && cc < M && !visited[rr][cc] && map[rr][cc] != 0;
	}
}

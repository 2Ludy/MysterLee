package 이동욱.SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_22654 {
	
	static int T, N, Q, sr, sc;
	static char[][] map;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static List<Character>[] list;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			for(int i=0; i<N; i++) {
				char[] cs = br.readLine().toCharArray();
				for(int j=0; j<N; j++) {
					map[i][j] = cs[j];
					if(map[i][j] == 'X') {
						sr = i;
						sc = j;
					}
				}
			} // 맵 생성
			
			Q = Integer.parseInt(br.readLine());
			list = new ArrayList[Q];
			for(int i=0; i<Q; i++) {
				list[i] = new ArrayList<>();
				StringTokenizer st = new StringTokenizer(br.readLine());
				int M = Integer.parseInt(st.nextToken());
				char[] cs = st.nextToken().toCharArray();
				for(int j=0; j<M; j++) {
					list[i].add(cs[j]);
				}
			} // 입력 받기 완료
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ");
			for(int i=0; i<Q; i++) {
				int r = sr;
				int c = sc;
				int direction = 0; // 위 0, 오른쪽 1, 아래 2, 왼쪽 3  
				for(int j=0; j<list[i].size(); j++) {
					char ch = list[i].get(j);
					if(ch == 'R') {
						direction = direction+1;
						if(direction == 4) {
							direction = 0;
						}
					}else if(ch == 'L') {
						direction = direction-1;
						if(direction == -1) {
							direction = 3;
						}	
					}else if(ch == 'A') {
						int nr = r+dr[direction];
						int nc = c+dc[direction];
						if(!check(nr,nc)) continue;
						if(map[nr][nc] == 'T') continue;
						r = nr;
						c = nc;
					}
				}
				if(map[r][c] == 'Y') {
					sb.append(1).append(" ");
				}else {
					sb.append(0).append(" ");
				}
			}
			System.out.println(sb);
		}
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}
}

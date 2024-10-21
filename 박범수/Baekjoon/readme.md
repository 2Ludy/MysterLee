# 10월 2주차

## 2293.[동전1](https://www.acmicpc.net/problem/2293) 
### 분류: DP

$10=x_1+2x_2+5x_2$
- target = 10
- coefficients = [1,2,5]
- result = [1,0,0,0,0,0,0,0,0,0]

*0은 그자체로 하나의 경우로서 존재할 수 있어 result[0]=1로 초기화*

### coefficient = 1,
```
for (i=1, i<= 10, i++){
    result[i] = result[i]+ result[i-coefficient]
}
```
- result[1]=result[1]+result[0] 
  - 1은 그자체로 존재하기 때문에 하나의 경우를 물려받음
- result[2]=result[2]+result[1] 
  - 2는 1로부터 존재할 수 있는 경우를 물려받음
- result[2]=result[3]+result[2] 
  - 3은 1로부터 존재할 수 있는 경우를 물려받음
- ....
- result[10]=result[10]+result[9] 
  - 10는 1로부터 존재할 수 있는 경우를 물려받음

### coefficient = 2,
```
for (i=2, i<= 10, i++){
    result[i] = result[i]+ result[i-coefficient]
}
```
- result[2]=result[2]+result[0] 
  - 2은 그자체로 존재하기 때문에 하나의 경우를 물려받음
- result[3]=result[3]+result[1] 
  - 3는 2로부터 존재할 수 있는 경우와 1로부터 존재할 수 있는 경우를 물려받음 
- result[4]=result[4]+result[2] 
  - 4는 2로부터 존재할 수 있는 경우와 1로부터 존재할 수 있는 경우를 물려받음
- ....
- result[10]=result[10]+result[8] 
  - 10는 2로부터 존재할 수 있는 경우와 1로부터 존재할 수 있는 경우를 물려받음

### coefficient = 5,
```
for (i=5, i<= 10, i++){
    result[i] = result[i]+ result[i-coefficient]
}
```
- result[5]=result[5]+result[0] 
  - 5은 그자체로 존재하기 때문에 하나의 경우를 물려받음
- result[6]=result[6]+result[1] 
  - 6는 5로 부터 존재할 수 있는 경우와 2로부터 존재할 수 있는 경우와 1로부터 존재할 수 있는 경우를 물려받음 
- result[7]=result[7]+result[2] 
  - 7는 5로 부터 존재할 수 있는 경우와 2로부터 존재할 수 있는 경우와 1로부터 존재할 수 있는 경우를 물려받음
- ....
- result[10]=result[10]+result[8] 
  - 10은 5로 부터 존재할 수 있는 경우와 2로부터 존재할 수 있는 경우와 1로부터 존재할 수 있는 경우를 물려받음


## 2096.[내려가기](https://www.acmicpc.net/problem/2096)
### 분류: DP

*예제입력*
  
| 왼쪽 | 중간 | 오른쪽 |
|-------|-------|-------|
| 1 | 2 | 3 |
| 4 | 5 | 6 |
| 7 | 8 | 9 |

*최대값을 찾고 싶을 때,*

| 왼쪽 | 중간 | 오른쪽 |
|-------|-------|-------|
| 1 | 2 | 3 |
| (1+4) **(2+4)** | (1+5) (2+5) **(3+5)** | (2+6) **(6+3)**|
| (2+4+7) **(2+4+8)** | (3+5+7) (3+5+8) **(3+5+9)**| (6+3+8) **(6+3+9)** |

*이런식으로 행을 순회할 때 마다, 이전값과 현재값을 더해서 최소값 혹은 최대값으로 배열에 할당한다.*



# 10월 3주차

## 16236.[아기상어](https://www.acmicpc.net/problem/16236) 
### 분류: BFS & 구현

이 문제에서 유의할 점은 아기 상어가 물고기를 먹을 때, 우선순위를 고려해야하는 것이다.
- arr: 최단 거리에 존재하는 물고기 위치를 저장한 리스트 
- r_min: 가장 위에 있는 물고기의 위치 -> 행이 최소가 될 때
- c_min: 가장 왼쪽에 있는 물고기의 위치 -> 열이 최소가 될 때

r_min 이 결정된 상태에서 c_min 값을 구하면 아기 상어가 먹을 물고기가 된다.

```
// 같은 거리에서 위, 왼쪽 우선순위를 설정하기 위한 메서드
	private static void select(ArrayList<int[]> arr) {
		int r_min=N-1;
		int c_min=N-1;
		
		for(int[] tmp:arr) {
			r_min = Math.min(r_min,tmp[0]);
		}
		for(int[] tmp:arr) {
			if(tmp[0]==r_min) c_min=Math.min(c_min, tmp[1]);
		}
		eat++;
		data[r_min][c_min]=0; // 지정된 물고기를 먹고 해당 위치를 0으로 초기화한다.
		S = r_min*N+c_min; // 현재 위치를 먹은 물고기 위치로 변경
	}
```

너비 우선 탐색에서는 현재 내 위치에서 최단거리에 있는 물고기들을 탐색하면서 arr 리스트에 추가하고 최단거리 물고기 탐색이 끝나면 bfs를 종료한다

- flag: 최단거리 값을 저장
- check: 먹을 수 있는 물고기가 없을 경우에 예외처리를 위한 bool 값
- dis = 거리
- temp & temp2 : 큐에 들어가고 나오는 값들을 통해 거리를 계산

```
private static int bfs(int s) {
		// bfs 를 위한 설정
		Queue<Integer> q = new LinkedList<>();
		ArrayList<int []> arr = new ArrayList<>();
		boolean[] visited = new boolean[N*N];
		// 큐에 시작 위치 추가
		q.add(s);
		visited[s]=true;
		
		// 이동 거리 계산을 위한 변수
		int dis = 0; 
		int temp = 0;
		int temp2 = 1;
		// 가장 가까운 물고기가 있는 거리를 저정하기 위한 변수
		int flag = Integer.MAX_VALUE;
		// 먹을 물고기 없을 때, 이동거리 0 반환
		boolean check=false; 
	
		
		while(!q.isEmpty()) {			
			int a = q.remove();
			temp2--;
			
			for (int i = 0; i < 4; i++) {
				int r = a/N + dr[i];
				int c = a%N + dc[i];
				
				if(r>=0 && r<N && c>=0 && c<N) {
					if(visited[r*N+c]) continue;
					if(data[r][c]>=0 && data[r][c]<=size) {
						q.add(r*N+c);
						temp++;
						visited[r*N+c]=true;
						if(data[r][c] == size || data[r][c] == 0) continue;
						// 먹을 수 있는 물고기를 arr 리스트에 추가한다..
						flag = dis;
						check = true;
						arr.add(new int[]{r,c});
					}
				}
			}
			// 이동 거리 계산
			if(temp2==0) {
				temp2=temp;
				temp=0;
				dis++;
			}
			// 최단 거리에 있는 물고기 탐색 이후 bfs 탐색하는 것을 막기 위해..
			if(flag < dis) break;
		}
		select(arr);
		if(!check) dis=0;
		return dis;
	}
```

main 에 존재하는 로직은 각 위치에서 최단거리 물고기를 확인 후 이동하는데, 만약 이동거리가 0이면 엄마 상어를 부르기 위해 while 문을 멈춘다. 이동거리가 계산되면 총 이동거리를 업데이트하고 이동마다 먹은 물고기 개수와 상어의 크기를 확인하고 상어 크기를 갱신한다.

```
    // 로직
		eat = 0; // 먹는 물고기 개수
		result = 0; // 총 이동 거리
		size = 2; // 상어 크기
		while(true) {
			int a = bfs(S); // 이동 거리 반환
			if(a==0) break; // 이동 거리 없으면 break
			result+=a; // 총 이동거리 갱신
			// 상어 크기 업데이트 조건
			if(eat==size) {
				eat = 0;
				size++;
			}
		}
		// 출력
		System.out.println(result);
```

## 4963.[섬의 개수](https://www.acmicpc.net/problem/16236) 
### 분류: BFS

연결된 땅을 찾아 더 이상 연결되지 않을 때 하나의 섬이라고 생각한다. 2차원 배열을 탐색하면서 1이 나오면 연결된 모든 땅을 탐색하고 더 이상 탐색할 곳이 없으면 너비 우선 탐색을 종료한다. 
- end: 종료 조건 문자열
- input: 행과 열의 입력값
- result: 섬의 개수

```
    // 종료 문자열이 나올 때까지 반복
		while(!(input = br.readLine()).equals(end)) {
			st = new StringTokenizer(input);
			M = Integer.parseInt(st.nextToken()); // 열
			N = Integer.parseInt(st.nextToken()); // 행
			int result=0; // 섬의 개수
			data = new int[N][M]; // 입력값
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					data[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			// 로직
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(data[i][j]==1) {
						bfs(i,j); // 연결된 섬을 찾는다
						result++;
					}
				}
			}
			sb.append(result).append("\n");
		}
		System.out.println(sb);

```

너비우선탐색에서는 2차원 배열에 다음 행과 열에서 이전에 섬을 다시 탐색하지 않기 위해 탐색한 땅들은 모두 0으로 만든다.

```
	// bfs 로직
	private static void bfs(int x, int y) {
		Queue<int []> q = new LinkedList<>();
		// 큐에 시작 위치 추가
		q.add(new int[] {x,y});
		
		while(!q.isEmpty()) {			
			int[] arr = q.remove();
			
			for (int i = 0; i < 8; i++) {
				int r = arr[0] + dr[i];
				int c = arr[1] + dc[i];
				
				if(r>=0 && r<N && c>=0 && c<M) {
					if(data[r][c]==0) continue;
					data[r][c]=0;
					q.add(new int[] {r,c});
				}
			}
		}
	}

```
package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2293 {
	
	static int n, k;
	static int[] coins, count;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		coins = new int[n];
		
		for(int i=0; i<n; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}
		
		count = new int[k+1];
		count[0] = 1;
		
		for(int i=0; i<n; i++) {
			for(int j=coins[i]; j<=k; j++) {
				count[j] += count[j-coins[i]];
			}
		}
		System.out.println(count[k]);
	}
}

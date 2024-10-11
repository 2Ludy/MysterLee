package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_15989 {
	
	static int T;
	static int N;
	static int[] nums;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			nums = new int[N+1];			
			nums[0] = 1;
			
			for(int i=1; i<=3; i++) {
				for(int j=i; j<=N; j++) {
					nums[j] += nums[j-i];
				}
			}
			
			System.out.println(nums[N]);
		}
	}
}

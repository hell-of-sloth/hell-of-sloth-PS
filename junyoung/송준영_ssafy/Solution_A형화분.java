

import java.io.*;
import static java.lang.Integer.parseInt;
import java.util.*;

public class Solution_A형화분 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int N, P;
	static int[] pump1;
	static int[] pump2;
	static int result;
	
	public static void main(String[] args) throws Exception {
		int T = parseInt(br.readLine());
		
		for (int t=1; t <= T; t++) {
			solve();
			sb.append(String.format("#%d %d\n", t, result));
		}
		System.out.println(sb);
	}
	
	public static void solve() throws Exception {
		st = new StringTokenizer(br.readLine());
		N = parseInt(st.nextToken());
		P = parseInt(st.nextToken());
		
		pump1 = new int[N];
		pump2 = new int[N];
		result = Integer.MIN_VALUE;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			pump1[i] = parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			pump2[i] = parseInt(st.nextToken());
		}
		
		backtrack(0, 0, 0);
	}
	
	public static void backtrack(int depth, int sum, int flag) {
		if (depth == N) {
//			System.out.println(sum);
			result = Math.max(result, sum);
			return;
		}
		
		if (flag == 1) {			// 1번 펌프로 전에 줬을 때
			backtrack(depth + 1, sum + (pump1[depth] - P), 1);
			backtrack(depth + 1, sum + (pump2[depth]), 2);
		} else if (flag == 2) {		// 2번 펌프로 전에 줬을 때
			backtrack(depth + 1, sum + (pump1[depth]), 1);
			backtrack(depth + 1, sum + (pump2[depth] - P), 2);
		} else {					// flag == 0 일때, 처음 시작할 때
			backtrack(depth + 1, sum + pump1[depth], 1);
			backtrack(depth + 1, sum + pump2[depth], 2);
		}
	}
}
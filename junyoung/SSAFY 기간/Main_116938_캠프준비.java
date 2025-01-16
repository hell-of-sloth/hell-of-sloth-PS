// 문제수 : N, 난이도 합 : L 과 R 사이, 난이도 차 X 이상
// 두 문제 이상 골라야함, 두 문제 이상 조합할 수 있는 경우의 수 탐색 필요
// 이 중 L 과 R 사이에 있으며 min, max 차이가 X 이상 차이가 나는 경우만 택 하기
// 모든 조합의 경우를 탐색하며, 조합생성이 백트랙킹이 적합하므로 백트랙킹 사용
// 백트래킹을 이용하여 모든 경우의 수를 탐색하며 조건에 맞는 경우만 카운트

import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));    // 입력을 받기 위한 BufferedReader, 입력 시간을 줄여줌
	static StringTokenizer st;                                                          // 한 줄 입력을 받아서 공백으로 나눠줌
	static int answer = 0;                                      // 정답을 저장할 변수를 전역으로 지정                  
	public static void main(String[] args) throws Exception {
		int N, L, R, X;                                         // 문제수(N), 난이도 합(L, R), 난이도 차이(X)
		st = new StringTokenizer(br.readLine());                // 첫 줄 입력 받기
		N = Integer.parseInt(st.nextToken());                   // N에 첫 번째 입력 저장
		L = Integer.parseInt(st.nextToken());                   // L에 두 번째 입력 저장
		R = Integer.parseInt(st.nextToken());                   // R에 세 번째 입력 저장
		X = Integer.parseInt(st.nextToken());                   // X에 네 번째 입력 저장
        
		int[] arr = new int[N];                                 // 문제의 난이도를 저장할 배열 생성
		
		st = new StringTokenizer(br.readLine());                // 두 번째 줄 입력 받기
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());          // 문제의 난이도를 배열에 저장
		}
		
        
		backtracking(arr, N , 0, L, R, 0, X, -1, 10000000, 0);  // 백트래킹 함수 호출
		
		System.out.println(answer);                             // 정답 출력
	}
	
    // 백트래킹 함수
    // arr : 문제의 난이도를 저장한 배열, N : 문제의 수, level : 현재 탐색한 문제의 수, L : 난이도 합의 최소값, R : 난이도 합의 최대값,
    // sum : 현재까지의 난이도 합, X : 난이도 차이, max : 현재까지의 최대 난이도, min : 현재까지의 최소 난이도, cnt : 현재까지 선택한 문제의 수
	public static void backtracking(int[] arr, int N, int level, int L, int R, int sum, int X, int max, int min, int cnt) {
		
		// 종료조건
		if (N == level) {
			if (cnt < 2) {                      // 2개 이상 선택 안할시
				return;
			} else if ((max - min) < X) {       // X보다 안 클경우
				return;
			} else if (sum < L || sum > R) {    // 범위 초과시
				return;
			} else {                            // 모든 조건 만족시 정답 증가
				answer++;
				// System.out.println("Debug: " + sum + min + max);
				return;
			}
		}
		
        // 현재 난이도를 선택하지 않는 경우
		backtracking(arr, N, level+1, L, R, sum, X, max, min, cnt);
        
        // 현재 난이도를 선택하는 경우
		backtracking(arr, N, level+1, L, R, sum+arr[level], X, Math.max(arr[level], max), Math.min(arr[level], min), cnt+1);
	}

}

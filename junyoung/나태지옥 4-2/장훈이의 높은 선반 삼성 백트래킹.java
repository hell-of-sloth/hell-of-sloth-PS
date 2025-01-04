// 체감 난이도 6/10 백트래킹을 생각하기 어려웠음
// 처음에는 투포인터인줄 알았으나 문제를 다시 보고 이 방법으로 최적해를 찾을 수 없다는 것을 알았음
// 전체의 조합을 다 탐색해야함
// 전체의 조합을 구하는 경우는 백트랙킹을 사용하면 편리 -> B 이상인 경우에만 최소값을 갱신함


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int answer;                              // 답은 전역으로 설정
    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());    // 테스트 케이스 수

        for (int t = 1; t <= T; t++) {
            solve();
            System.out.println("#" + t + " " + answer);
        }
    }

    public static void solve() throws Exception {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        // 배열 입력 파트
        int[] heights = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        answer = Integer.MAX_VALUE;                 // 답을 최대값으로 초기화
        backtracking(heights, B, 0, 0); // 백트래킹으로 모든 경우의 수 탐색
    }

    public static void backtracking(int[] heights, int B, int total, int depth) {
        if (depth == heights.length) {
            if (total >= B) {                       // B 이상이어야 함
                answer = Math.min(answer, total - B);
                // System.out.println(answer);
            }
            return;
        }

        backtracking(heights, B, total + heights[depth], depth + 1);    // 현재 키를 선택하는 경우
        backtracking(heights, B, total, depth + 1);                     // 현재 키를 선택하지 않는 경우
    }
}

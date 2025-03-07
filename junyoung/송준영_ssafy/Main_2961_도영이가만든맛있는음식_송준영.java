
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Main_2961_도영이가만든맛있는음식
 * 난이도 3/10
 * 백트랙킹, 조합
 * 신맛은 * 연산, 쓴맛은 + 연산 주의
 * 조합할수 있는 모든 재료의 수를 다 탐색해서 가장 신맛, 쓴맛 차이가 작은 값을 찾는 문제
 * 신맛, 쓴만 차이를 구할 때는 Math.abs() 함수를 사용 -> 혹시 몰라서
 * backtracking 함수의 flag를 이용해서 재료를 한 번이라도 넣었는지 체크 -> 안 넣었으면 결과에 반영 X
 * 인덱스 체크 배열을 대신 저장해서 그 배열에 따라 결과값을 갱신해도 되지만 시간이 더 걸릴 것 같아서 그냥 flag 매개변수 넣었음
 * N, ingredients, result 는 static으로 선언해서 접근 빠르게(?) 했음 + 매개변수 안써도 되서 좀 더 깔끔한? 느낌??
 */
public class Main_2961_도영이가만든맛있는음식 {
    // 빠른 입력 처리 및 토큰 분할
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    // static 변수 선언 -> 매개변수로 지정 안해줘도 접근 가능 + 복사 안해서 빠를려나?
    static int N;
    static int[][] ingredients;
    static int result;
    public static void main(String[] args) throws Exception {
        // 입력 처리
        N = Integer.parseInt(br.readLine());
        ingredients = new int[N][2];

        result = Integer.MAX_VALUE;     // 결과값 초기화, 최대값으로 초기화

        // 재료 입력 [0] 신맛, [1] 쓴맛
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            ingredients[i][0] = Integer.parseInt(st.nextToken());
            ingredients[i][1] = Integer.parseInt(st.nextToken());
        }

        // 백트랙킹 시작 (모든 조합 찾기)
        // depth = 0, 신맛 = 1 (곱하기니까), 쓴맛 = 0 (더하기니까), flag = false (한 번이라도 넣었는지 체크, 처음이니까 false)
        backtrack(0, 1, 0, false);

        // 결과 출력
        System.out.println(result);
    }
    
    /**
     * 백트랙킹 함수 -> 모든 재료의 조합을 찾는다
     * @param depth     현재 재료의 인덱스
     * @param sour      현재까지의 신맛
     * @param bitter    현재까지의 쓴맛
     * @param flag      한 번이라도 재료를 넣었는지 체크
     */
    public static void backtrack(int depth, int sour, int bitter, boolean flag) {
        // 탈출 조건 -> 모든 재료를 한 번씩 확인 했을 때
        if (depth == N) {
            if (flag) result = Math.min(result, Math.abs(sour - bitter));   // 한 번이라도 넣었으면 결과값 갱신
            return;
        }

        // 재료를 넣는 경우, sour, bitter를 현재 인덱스 배열 값으로 추가해서 갱신 -> sour는 곱하기, bitter는 더하기
        backtrack(depth+1, sour * ingredients[depth][0], bitter + ingredients[depth][1], true);

        // 재료를 넣지 않는 경우 그냥 그대로 다음 인덱스
        if (flag) {
            backtrack(depth+1, sour, bitter, true);     // 한 번이라도 넣었으면 flag = true
        } else {
            backtrack(depth+1, sour, bitter, false);    // 한 번도 안 넣었으면 flag = false
        }
    }
}
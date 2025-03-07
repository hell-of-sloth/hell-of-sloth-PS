import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * SWEA_4008_숫자만들기
 * 난이도 4/10
 * 순열
 * 115ms 27,700kb
 * 
 * NP 배운겸 NP를 써먹어보았다.
 * 순열로 모든 사칙연산의 경우를 구하고 숫자에 대입해서 계산하면 된다
 * 재귀로 순열했을때를 비교해 볼까? -> 5001ms 걸림 -> 시간 초과
 * NP가 훨씬 빠르네;;; -> 115ms
 * 
 */
public class SWEA_4008_숫자만들기_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;       // 숫자 개수
    static int[] cals;  // 사칙연산 개수
    static int[] nums;  // 숫자 배열
    static int[] arr;   // 사칙연산 배열

    static int maxVal;  // 최대값
    static int minVal; // 최소값
    
    static int[] permu;

    public static void main(String[] args) throws Exception {
        // 테케 수 입력
        int T = parseInt(br.readLine());

        // 테케 수 만큼 반복
        for (int t = 1; t <= T; t++) {
            solve();
            sb.append(String.format("#%d %d\n", t, (maxVal - minVal)));
        }

        // 출력
        System.out.println(sb);
    }

    /**
     * 메인 로직 메서드
     * @throws Exception
     */
    public static void solve() throws Exception {
        // 입력 처리 및 초기화
        N = parseInt(br.readLine());

        cals = new int[4];
        nums = new int[N];
        arr = new int[N - 1];

        maxVal = Integer.MIN_VALUE;
        minVal = Integer.MAX_VALUE;

        permu = new int[N - 1];

        st = new StringTokenizer(br.readLine());

        int idx = 0;
        // 0 : +  1 : -  2 : *  3 : /
        for (int i = 0; i < 4; i++) {
            cals[i] = parseInt(st.nextToken());
            while (cals[i]-- != 0) {
                arr[idx++] = i;
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = parseInt(st.nextToken());
        }

        // NP를 위한 정렬
        Arrays.sort(arr);

        // NP를 써서 다음 순열이 있을 때 까지 반복
        do {
            calculate();
        } while (np());

        // permu(0, 0);
    }
    
    /**
     * 계산 메서드
     * 사칙연산 배열을 순회하며 계산
     * 최대값, 최소값 갱신
     */
    public static void calculate() {
        // 처음 값은 숫자 배열의 첫번째 값
        int result = nums[0];

        // 사칙연산 배열을 순회하며 계산
        for (int i = 0; i < N - 1; i++) {
            int com = arr[i];
            // int com = permu[i];

            switch (com) {
                case 0:
                    result += nums[i + 1];
                    break;
                case 1:
                    result -= nums[i + 1];
                    break;
                case 2:
                    result *= nums[i + 1];
                    break;
                case 3:
                    result /= nums[i + 1];
                    break;
            }
        }

        // 최대값, 최소값 갱신
        maxVal = Math.max(maxVal, result);
        minVal = Math.min(minVal, result);
    }
    
    /**
     * 다음 순열이 있을 때 까지 반복, Next Permutaion
     * @return  다음 순열이 있으면 true, 없으면 false
     */
    public static boolean np() {

        // 1. 꼭대기 찾기
        int i = (N - 1) - 1;
        while (i > 0 && arr[i - 1] >= arr[i])
            --i;

        // 마지막 순열이면 종료
        if (i == 0)
            return false;

        // 2. 교환할 값 찾기
        int j = (N - 1) - 1;
        while (arr[i - 1] >= arr[j])
            --j;

        // 3. 교환
        swap(i - 1, j);

        // 4. 교환 후 다음 자리들 내림차순 정렬
        int k = (N - 1) - 1;
        while (i < k)
            swap(i++, k--);

        return true;
    }

    /**
     * NP에서 사용하는 swap 메서드
     * @param x
     * @param y
     */
    public static void swap(int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    public static void permu(int depth, int flag) {
        if (depth == N - 1) {
            calculate();
            return;
        }

        for (int i = 0; i < N - 1; i++) {
            if ((flag & 1 << i) != 0)
                continue;

            permu[depth] = arr[i];
            permu(depth + 1, flag | 1 << i);
        }
    }
}

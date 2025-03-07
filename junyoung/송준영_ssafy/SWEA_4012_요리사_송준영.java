import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * SWEA_4012_요리사
 * 난이도 4/10
 * 조합, NP
 * 174ms 44,200kb
 * 
 * NP를 사용하여 조합을 구현하였다.
 * NP 안하면 272ms 걸림
 * 
 * calculate 안에서 처음 음식과 두번째 음식을 나눠 계산했는데 이게 빠른 방법이었던 것 같음
 * -> 안하고 한번에 계산하면 336ms 걸림, 다만 메모리는 10,000kb 정도 줄어들었다
 * 
 * NP를 사용한 조합을 구현하는 방법을 익히자
 */
public class SWEA_4012_요리사_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
 
    static int N;               // 음식 개수
    static int[][] synergy;     // 시너지 배열
    static int[] idxArr;        // 조합을 위한 배열

    static int minVal;          // 최소값

    public static void main(String[] args) throws Exception {
        // 테케 수 입력
        int T = parseInt(br.readLine());

        // 테케 수 만큼 반복
        for (int t = 1; t <= T; t++) {
            solve();
            sb.append(String.format("#%d %d\n", t, minVal));
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
        synergy = new int[N][N];
        idxArr = new int[N];

        minVal = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                synergy[i][j] = parseInt(st.nextToken());
            }
        }

        // 조합 구현을 위해 초기 세팅
        for (int i = 0; i < (N / 2); i++) {
            idxArr[i] = 1;
        }

        // NP를 사용위해 정렬을 먼저 수행
        Arrays.sort(idxArr);

        // NP를 써서 다음 순열이 있을 때 까지 반복
        do {
            calculate();
        } while (NP());
    }
    
    /**
     * NP를 이용해 구한 조합을 사용해서 각 음식의 시너지 조합을 검출하여 최소값을 구하는 메서드
     */
    public static void calculate() {
        int firstVal = 0, secondVal = 0; // 첫번째 음식과 두번째 음식의 시너지 값
        int[] firstArr = new int[N / 2], secondArr = new int[N / 2]; // 첫번째 음식과 두번째 음식의 인덱스 배열
        int fIdx = 0, sIdx = 0; // 첫번째 음식과 두번째 음식의 인덱스 배열의 인덱스

        // NP에서 만들어진 조합을 바탕으로 첫번째 음식과 두번째 음식을 나눠 배열에 저장
        for (int i = 0; i < N; i++) {
            if (idxArr[i] == 1) {
                firstArr[fIdx++] = i;
            } else {
                secondArr[sIdx++] = i;
            }
        }

        // 각 음식의 시너지 값을 계산
        for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < N / 2; j++) {
                if (i == j)
                    continue;

                firstVal += synergy[firstArr[i]][firstArr[j]];
                secondVal += synergy[secondArr[i]][secondArr[j]];
            }
        }

        // System.out.println(Arrays.toString(idxArr));
        // System.out.print(firstVal + " : ");
        // System.out.println(secondVal);

        // 최소값 갱신
        minVal = Math.min(minVal, Math.abs(firstVal - secondVal));
    }

    /**
     * NP를 사용한 조합 구현
     * @return
     */
    public static boolean NP() {
        int i = N - 1;

        while (i > 0 && idxArr[i - 1] >= idxArr[i])
            --i;

        int j = N - 1;

        if (i == 0)
            return false;

        while (idxArr[i - 1] >= idxArr[j])
            --j;

        swap(i - 1, j);

        int k = N - 1;
        while (i < k) {
            swap(i++, k--);
        }

        return true;
    }

    /**
     * NP에서 사용하는 swap 메서드
     * @param x
     * @param y
     */
    public static void swap(int x, int y) {
        int temp = idxArr[x];
        idxArr[x] = idxArr[y];
        idxArr[y] = temp;
    }
}
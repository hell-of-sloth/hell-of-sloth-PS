import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.StringTokenizer;

/**
 * Main_10972_다음순열
 * 난이도 2/10
 * NP
 * 176ms 17,600kb
 * 
 * 다음 순열을 구하는 문제
 * 있으면 다음 순열 출력, 없으면 -1 출력
 * 
 * NP를 쓰면 간편하게 구할 수 있다.
 * 4문제 정도 NP를 쓰니까 이제 점점 외워진다 (마참내!)
 */
public class Main_10972_다음순열_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    // 입력 변수 선언
    static int N;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        // 입력 처리 및 초기화
        N = parseInt(br.readLine());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = parseInt(st.nextToken());
        }

        // 만약 다음 순열이 있으면 출력, 없으면 -1 출력
        // 삼항 연산자로도 가능 할 듯
        if (NP()) {
            for (int e : arr) {
                sb.append(e + " ");
            }
            System.out.println(sb);
        } else {
            System.out.println(-1);
        }
    }

    /**
     * 다음 순열을 구하는 메서드
     * @return  다음 순열이 있으면 true, 없으면 false
     */
    public static boolean NP() {

        int i = N - 1;

        while (i > 0 && arr[i - 1] >= arr[i])
            --i;

        if (i == 0)
            return false;

        int j = N - 1;

        while (arr[i - 1] >= arr[j])
            --j;

        swap(i - 1, j);

        int k = N - 1;

        while (i < k) {
            swap(i++, k--);
        }

        return true;
    }
    
    /**
     * 배열의 두 값을 바꾸는 메서드
     * @param x
     * @param y
     */
    public static void swap(int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
}

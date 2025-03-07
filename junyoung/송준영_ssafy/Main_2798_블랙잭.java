import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Main_2798_블랙잭
 * 난이도 2/10
 * 조합
 * 3장의 카드를 ㅃ보는 모든 경우의 수중 M을 안 넘기면서 M에 가장 가까운 수를 찾아야한다
 * 3장 제한이 있어서 백트래킹 보다는 for문을 3개 사용하는게 더 빠를 것 같아 삼중 for문 이용
 * 
 */
public class Main_2798_블랙잭 {
    // 빠른 입력과 토큰분할
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    // 변수 선언
    static int N, M;
    static int[] card;          // 카드 배열 저장
    static int result = 0;      // 결과값 저장
    public static void main(String[] args) throws Exception {
        // 입력 처리
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        card = new int[N];
        for (int i = 0; i < N; i++) {
            card[i] = Integer.parseInt(st.nextToken());
        }

        // 3장의 카드를 뽑는 모든 경우의 수를 계산
        for (int i = 0; i < N-2; i++) {
            for (int j = i+1; j < N-1; j++) {
                for (int k = j+1; k < N; k++) {
                    // 3장의 카드의 합을 계산
                    int sum = card[i] + card[j] + card[k];

                    // M을 넘지 않으면서 M에 가장 가까운 수를 찾는다
                    if (sum <= M && sum > result) {
                        result = sum;
                    }
                }
            }
        }

        // 결과 출력
        System.out.println(result);
    }
}

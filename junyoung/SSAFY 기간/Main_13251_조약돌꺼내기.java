import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Main_13251_조약돌꺼내기
 * 난이도 5/10
 * 수학, 조합론
 * 처음 문제를 풀 때 조합 공식으로 풀어보려 했지만 수의 범위가 long을 넘어가는 경우가 발생하여
 * 오버플로우가 발생 -> 다른 방법 고안
 * 확률을 처음부터 구해서 확률을 더하는 방법으로 구상
 * 오버플로우가 나지 않고 성공
 * (확률 계산 공식을 까먹어서 생각하는데 애를 먹었다)
 */
public class Main_13251_조약돌꺼내기 {
    // 빠른 입출력 및 토큰 분할
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        // 입력 처리
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(br.readLine());
        int[] stones = new int[M];                  // 돌의 개수를 저장할 배열

        int sumStone = 0;                           // 총 돌의 개수
        double total = 0;                           // 확률의 합 -> 결과
        for (int i = 0; i < M; i++) {
            stones[i] = Integer.parseInt(st.nextToken());       // 돌의 개수 입력
            sumStone += stones[i];                              // 총 돌의 개수 계산
        }

        // 각 색깔별 확률 계산
        for (int i : stones) {
            total += calPercent(i, K, sumStone);
        }
        
        // 결과 출력
        System.out.println(total);
    }

    // N : 뽑고자하는 색깔 수, K : 뽑으려는 돌 수, sumStone : 총 돌 수
    // 각 색깔별 같은 색을 뽑을 확률을 구하는 메서드
    public static double calPercent(int N, int K, int sumStone) {
        // 뽑으려는 돌 수가 총 돌 수보다 많다면 확률은 0
        if (N < K) {
            return 0.0;
        }

        double temp = 1.0;                      // 확률을 저장할 변수
        double dN = (double)N;                  // double로 형변환
        double dsumStone = (double)sumStone;    // double로 형변환


        for (int i = 0; i < K; i++) {
            // System.out.println(dN + " : " + dsumStone);
            temp *= (dN-- / dsumStone--);       // 확률 계산 후 분자 분모 각각 1씩 감소
        }

        // System.out.println("답 뭐야 : " + temp);

        return temp;                            // 확률 반환
    }
}

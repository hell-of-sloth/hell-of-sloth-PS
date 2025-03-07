import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import static java.lang.Integer.parseInt;

/**
 * Main_1969_DNA
 * 난이도 3/10
 * 구현 그리디
 * 116ms 14mb
 * 
 * 각 열의 DNA 문자 중 가장 많이 나온 문자를 출력하고, 다른 문자들의 개수를 더한 값을 출력하는 문제
 * 각 열에서 가장 많이 나온 문자를 넣으면 가장 적게 된다 -> 각 단계의 최적해가 결과도 최적해 -> 그리디
 * 이 그리디 문제는 그래도 그리디인 것을 쉽게 확인 가능했다.
 */
public class Main_1969_DNA_송준영 {
    // 빠른 입출력을 위한 BufferedReader, StringTokenizer, StringBuilder
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;        // N: DNA의 수, M: DNA의 길이
    static char[][] DNA;    // DNA 배열
    static int result = 0;  // 결과값
    public static void main(String[] args) throws Exception {
        // 입력 및 초기화 처리
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        M = parseInt(st.nextToken());

        DNA = new char[N][M];
        for (int i = 0; i < N; i++) {
            DNA[i] = br.readLine().toCharArray();
        }

        // 각 열에서 가장 많이 나온 문자를 출력하고, 다른 문자들의 개수를 더한 값을 출력
        for (int i = 0; i < M; i++) {
            int[] cnt = new int[4];
            for (int j = 0; j < N; j++) {
                switch (DNA[j][i]) {
                    case 'A':
                        cnt[0]++;
                        break;
                    case 'C':
                        cnt[1]++;
                        break;
                    case 'G':
                        cnt[2]++;
                        break;
                    case 'T':
                        cnt[3]++;
                        break;
                }
            }

            int max = 0;    // 가장 많이 나온 문자의 개수
            int maxIdx = 0; // 가장 많이 나온 문자의 인덱스
            for (int j = 0; j < 4; j++) {
                if (cnt[j] > max) {
                    max = cnt[j];
                    maxIdx = j;
                }
            }
            result += N - max;  // 다른 문자들의 개수를 더한다
            sb.append(getDNA(maxIdx));  // 가장 많이 나온 문자를 sb에 삽입
        }
        sb.append("\n").append(result); // 결과값을 sb에 삽입

        // 출력
        System.out.println(sb);
    }

    /**
     * DNA 문자열을 반환하는 함수
     * @param idx   DNA 문자열의 인덱스
     * @return      DNA 문자 (A, C, G, T)
     */
    public static char getDNA(int idx) {
        switch (idx) {
            case 0:
                return 'A';
            case 1:
                return 'C';
            case 2:
                return 'G';
            case 3:
                return 'T';
        }
        return ' ';
    }
}

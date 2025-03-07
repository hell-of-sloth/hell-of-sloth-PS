import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import static java.lang.Integer.parseInt;   // import static

/**
 * Main_13300_방배정
 * 난이도 2/10
 * 구현, 배열, 약간의 수학?
 * 이 문제는 각 학년별로 성별을 나누어 학생 수를 저장하고
 * 그 학생수를 방 최대인원으로 나눠서 필요한 방의 개수를 계산하는 문제이다
 * 로직을 생각하지 못 하면 어려울 수도 있을 듯 하다
 * 나눴을때 나머지가 있냐 없냐에 따라 또 달라서
 * 그거 처리를 주의 깊게 봐야한다
 */
public class Main_13300_방배정 {
    // 빠른 입출력과 문자 나누기
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        int N, K;
        int[][] students = new int[2][7];   // 성별, 학년별 학생 수를 저장할 배열
        int result = 0;                     // 필요한 방의 개수

        // 입력 처리
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        K = parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            students[parseInt(st.nextToken())][parseInt(st.nextToken())] += 1;
        }
        
        // 배열의 모든 요소를 돌면서 계산
        for (int i = 0; i < 2; i++) {
            for (int j = 1; j < 7; j++) {
                result += (students[i][j] / K);             // K로 나눈 몫이 필요한 방의 개수
                if (students[i][j] % K != 0) result += 1;   // K로 나눈 나머지가 있으면 방 하나 더 필요
            }
        }

        // 출력
        System.out.println(result);
    }
}

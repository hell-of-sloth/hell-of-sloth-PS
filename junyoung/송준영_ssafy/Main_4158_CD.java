import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import static java.lang.Integer.parseInt;

/**
 * Main_4158_CD
 * 난이도 3/10
 * 투포인터(다른 배열에 각각 하나씩 있는)
 * 852ms
 * 
 * 두 배열이 주어지고 두 배열에 공통으로 들어있는 원소의 개수를 구하는 문제
 * 오름차순으로 주어진게 힌트다!
 * 각각의 배열의 탐색 인덱스를 만들어서 0부터 시작하느데
 * 두 배열의 인덱스가 끝에 도달할 때까지 반복하면서
 * 두 배열의 값이 같으면 카운트를 증가 + 각 인덱스 포인터 증가
 * 다르면 더 작은 얘 꺼 포인터 증가하고 다시 반복
 * 
 * 이렇게 찾으면 다 찾을수 있다!
 * 
 * 그리고 0 0 입력될 때 까지 반복이라 조심해야함 (초기화 같은거)
 */
public class Main_4158_CD {
    // 빠른 입력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    
    // static 변수 선언
    static int N, M;        // 두 배열의 크기
    static int[] sangCD;    // 상근이의 CD
    static int[] sunCD;     // 선영이의 CD

    public static void main(String[] args) throws Exception {
        // 0 0 입력될 때 까지 반복
        while (true) {
            // 입력 처리
            st = new StringTokenizer(br.readLine());
            N = parseInt(st.nextToken());
            M = parseInt(st.nextToken());

            // 0 0 입력되면 종료
            if (N == 0 && M == 0) {
                break;
            }

            // 배열 초기화
            sangCD = new int[N];
            sunCD = new int[M];

            for (int i = 0; i < N; i++) {
                sangCD[i] = parseInt(br.readLine());
            }

            for (int i = 0; i < M; i++) {
                sunCD[i] = parseInt(br.readLine());
            }

            // 투포인터로 공통 CD 개수 찾기
            int sangidx = 0, sunidx = 0; // 각 배열의 탐색 인덱스
            int cnt = 0; // 공통 CD 개수

            // 두 배열의 인덱스가 끝에 도달할 때까지 반복
            while (sangidx < N && sunidx < M) {
                if (sangCD[sangidx] == sunCD[sunidx]) { // 두 배열의 값이 같으면 카운트를 증가 + 각 인덱스 포인터 증가
                    cnt++;
                    sangidx++;
                    sunidx++;
                    continue;
                } else if (sangCD[sangidx] > sunCD[sunidx]) { // 다르면 더 작은 얘 꺼 포인터 증가하고 다시 반복
                    sunidx++;
                } else if (sangCD[sangidx] < sunCD[sunidx]) {
                    sangidx++;
                }
            }

            // sb에 결과 넣기 -> 빠른 출력을 위해서
            sb.append(cnt).append("\n");
        }
        // 출력
        System.out.println(sb);
    }
}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.StringTokenizer;

/**
 * Main_11723_집합_송준영
 * 난이도 2/10
 * 비트마스킹, 구현
 * 1044ms 312,700kb
 * 
 * 비트마스킹으로 풀 수 있는 문제이다
 * S를 정수로 두고 비트들을 해당 인덱스에 요소가 있는지 없는지 검사하는 boolean 자료형으로 생각하면 편하다 -> S == boolean[21];
 * switch 문으로 시간을 줄여주었으며 ^=, |= 연산자가 된다는 것을 알았다...!
 * 모든 비트 1로 처리시 for문을 써도 되지만 정수를 그냥 박아버리는게 더 빠를 것 같아 계산기로 계산후 정수를 박아버렸다.
 * => 결과 1056ms -> 1044ms 로 12ms 정도 줄어 듦
 */
public class Main_11723_집합_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int M = parseInt(br.readLine());    // 명령어 개수 입력

        int S = 0;  // 비트마스킹으로 확인 할 집합 S
        int x;      // 명령어 뒤에 올 숫자

        // 명령어 수 만큼 반복
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            // switch 문으로 분기 나누기
            switch (st.nextToken()) {
                case "add":
                    x = parseInt(st.nextToken());

                    S |= 1 << x;    // x 만큼 비트 이동후 "1" 로 체크 -> (x 번째에 요소 있다)
                    break;
                case "remove":
                    x = parseInt(st.nextToken());

                    if ((S & 1 << x) != 0) {
                        S ^= 1 << x;    // x 번째에 "1" 비트가 있으면 -> 0으로 바꿔줌 -> (x 번째 요소 제거)
                    }
                    break;
                case "check":
                    x = parseInt(st.nextToken());

                    // x 번째의 비트 확인
                    if ((S & 1 << x) != 0) {
                        sb.append(1).append("\n");
                    } else {
                        sb.append(0).append("\n");
                    }
                    break;
                case "toggle":
                    x = parseInt(st.nextToken());

                    // XOR 연산으로 x 번째 비트가 "0"이면 "1"로 / "1"이면 "0"으로 바꿔주기
                    S ^= 1 << x;
                    break;
                case "all":
                    // for (int j = 1; j <= 20; j++) {
                    //     S = S | 1 << j;
                    // }
                    
                    S = 2097151;    // 1 * 21, 1~21번째 자리까지 다 "1"로 바꿔주기
                    break;
                case "empty":
                    S = 0;  // 모든 비트 0으로 만들기
                    break;
            }
        }
        
        // 총 결과 출력
        System.out.println(sb);
    }
}

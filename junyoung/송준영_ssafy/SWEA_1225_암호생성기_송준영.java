import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.StringTokenizer;

/**
 * SWEA 1225 암호생성기
 * 난이도 3/10
 * 시뮬레이션
 * 101ms 25,800kb
 * 
 * 문제에 주어진 조건대로 구현하면 되는 문제이다
 * 8개의 숫자를 입력받고 1 ~ 5까지 순서대로 빼주면서 0이 되는 숫자를 만나면 종료한다
 * 그리고 순서대로 출력하면 되는 문제인데
 * 
 * 문제 그대로 앞에 요소를 빼고 뒤로 보내는 방식으로 구현해도 될 것 같은데
 * 나는 데이터는 옮기지 않고 index만 옮기는 형식으로 구현 했다
 * % 연산자를 이용해서 일정 범위에서 순환하게 만들어서 구현했다
 */
public class SWEA_1225_암호생성기_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int[] arr;   // 암호 배열

    public static void main(String[] args) throws Exception {
        // 10번 반복
        for (int t = 0; t < 10; t++) {
            solve();
        }
        // 출력
        System.out.println(sb);
    }
    
    // 메인 로직 메서드
    public static void solve() throws Exception {
        int T = parseInt(br.readLine()); // 테스트 케이스 번호

        // 암호 배열 초기화
        arr = new int[8];                   
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 8; i++) {
            arr[i] = parseInt(st.nextToken());
        }

        int index = 0;  // 암호의 맨 앞을 가리키는 인덱스
        int minus = 1;  // 다음 암호자리 얼만큼 뺄지

        // 암호의 자리 수 중 음수나 0이 나올때 까지 반복 (무한으로 즐겨요)
        while (true) {

            arr[index] -= minus; // 현재 암호 맨 앞자리 감소량 만큼 빼주기

            // 뺐는데 그 자리의 수가 0이거나 더 작을때
            if (arr[index] <= 0) {
                arr[index] = 0; // 0으로 맞춰주고

                // 순서 이동
                index++;
                index %= 8;
                break;          // 탈출
            }

            // 빼는 값 증가
            minus %= 5;
            minus++;

            // 순서 이동
            index++;
            index %= 8;
        }
        
        // 결과 암호값 sb에 넣어주기
        sb.append(String.format("#%d ", T));
        for (int i = 0; i < 8; i++) {
            sb.append(arr[index]).append(" ");

            // 순서 이동
            index++;
            index %= 8;
        }
        sb.append("\n");
    }
}

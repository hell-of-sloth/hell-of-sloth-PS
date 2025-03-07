import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.StringTokenizer;

/**
 * SWEA_1952_수영장
 * 난이도 8/10
 * 백트래킹
 * 102ms 26mb
 * 
 * 처음에는 DP문제인 줄 알았는데 DP는 잘 몰라서 DP로 풀었으면 매우 어려웠을것 같다
 * 백트래킹이라고 주어져서 그나마 풀었지 없었으면 2시간은 걸렸을 것이다.
 * 
 * 1일, 1달, 3달, 1년 이용권이 있는데 이것들을 조합 할 수 있는 모든 경우를 구해야한다.
 * 1년은 1개 뿐이라 시작 값을 1년으로 주었다
 * 1일을 체크 할때 해당 날짜 이용일이 없으면 자동으로 + 0 되므로 따로 방문 체크 안해줘도 된다는 것이었다!
 * -> 방문체크 안해도 근데 2ms밖에 줄이지 못 했음
 * 
 * 다음으로 넘어갈때 3달, 1달, 1일 이렇게 세 분기로 나눠지는데
 * ☆☆☆ 3달의 경우 + 1 이 아닌 3달을 점프해서 + 3을 해주면 된다! ☆☆☆
 */
public class SWEA_1952_수영장_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int[] ticket;    // 1일, 1달, 3달, 1년
    static int[] months;    // 12개월 이용일
    static int total;       // 최소 이용료

    public static void main(String[] args) throws Exception {
        // 테스트 케이스 수 입력
        int T = parseInt(br.readLine());

        // 테스트 케이스만큼 반복
        for (int t = 1; t <= T; t++) {
            solve();
            sb.append(String.format("#%d %d\n", t, total));
        }

        // 결과 출력
        System.out.println(sb);
    }
    
    /**
     * 메인 로직 메서드
     * @throws Exception
     */
    public static void solve() throws Exception {
        // 배열 초기화 및 할당
        ticket = new int[4];
        months = new int[12];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            ticket[i] = parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 12; i++) {
            months[i] = parseInt(st.nextToken());
        }

        // 1년 이용권을 시작으로 백트래킹 시작
        total = ticket[3];
        backtrack(0, 0);
    }
    
    /**
     * 백트래킹 메서드
     * @param depth 현재 체크하고 있는 달
     * @param sum   현재까지의 이용료
     */
    public static void backtrack(int depth, int sum) {
        if (total < sum) {  // 현재 최소 이용금액보다 지금 이용료가 더 크면 가지치기
            return;
        }
        if (depth >= 12) {  // 끝까지 탐색 완료시
            total = Math.min(total, sum);
            return;
        }

        backtrack(depth + 3, sum + ticket[2]);  // 3달 이용권은 3달씩 점프
        backtrack(depth + 1, sum + ticket[1]);  // 1달 이용권은 1달씩 점프
        backtrack(depth + 1, sum + ticket[0] * months[depth]);  // 1일 이용료는 해당 달의 일수 * 요금, 이용일이 없으면 자동으로 + 0
    }
}

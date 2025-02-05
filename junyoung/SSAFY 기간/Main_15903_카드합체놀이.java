import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Main_15903_카드합체놀이
 * 난이도 2/10
 * 우선순위 큐
 * 카드를 합치는 횟수 m이 주어지고 카드의 수 n이 주어진다
 * 작은 수 카드 두개를 합쳐야 나중에 총합이 제일 작아진다
 * min을 이용하기에는 시간을 더 줄일 수 있다 -> 우선순위 큐를 이용
 * 우선순위 큥에서 연속 두 개를 뽑아 더한 값을 두 번 넣으면 문제의 조건을 만족한다
 * 나중에 다 꺼내서 다 더하면 됨
 * 카드 하나의 최대 값이 1,000,000 이고 카드 수가 1000개 이하이므로
 * 혹시 몰라서 long으로 선언했다
 */
public class Main_15903_카드합체놀이 {
    // 빠른 입력과 토큰분할
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        // 입력 처리 및 우선순위큐 + 변수 선언
        PriorityQueue<Long> pq = new PriorityQueue<>();
        int n , m;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        // 우선순위 큐에 카드를 넣음
        for (int i = 0; i < n; i++) {
            pq.add(Long.parseLong(st.nextToken()));
        }

        // 카드를 m번 합치고 합친 값으로 넣기
        for (int i = 0; i < m; i++) {
            long a = pq.poll();
            long b = pq.poll();
            pq.add(a+b);
            pq.add(a+b);
        }

        // 결과 값
        long sum = 0;

        // 우선순위 큐에서 다 꺼내서 다 더함
        while (!pq.isEmpty()) {
            sum += pq.poll();
        }

        // 결과 출력
        System.out.println(sum);
    }
}

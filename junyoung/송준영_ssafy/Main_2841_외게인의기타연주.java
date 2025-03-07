import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;
import static java.lang.Integer.parseInt;

/**
 * Main_2841_외게인의기타연주
 * 난이도 4/10
 * 스택
 * 608ms
 * 
 * 6개의 스택으로 각 줄의 프렛을 저장한 후 push, pop의 수를 전부 세서 출력하면 된다
 * 각 줄에 프랫이 peek보다 크면 push, 작으면 크거나 같은게 나올때 까지 pop을 해줘야 한다
 * 만약 같으면 무시하고 걍 넘어가기
 */
public class Main_2841_외게인의기타연주 {
    // 빠른 입출력 
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    // 변수 선언
    static int N, P;
    static int count;

    // 6개의 스택을 저장할 리스트, 스택은 덱으로 구현
    static List<Deque<Integer>> alien = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        // 입력 처리
        st = new StringTokenizer(br.readLine());
        N = parseInt(st.nextToken());
        P = parseInt(st.nextToken());

        // 6개의 스택을 만들어준다, 인덱스로 접근 할 거라 7개 생성
        for (int i = 0; i <= 6; i++) {
            alien.add(new ArrayDeque<>());
        }

        // 입력 처리 + 스택 처리
        for (int i = 0; i < N; i++) {
            int line, pret;             // 줄, 프렛
            
            // 입력 처리
            st = new StringTokenizer(br.readLine());
            line = parseInt(st.nextToken());
            pret = parseInt(st.nextToken());

            // 해당 스택이 비었으면 그냥 넣어주기 + 카운트
            if (alien.get(line).isEmpty() ) {
                alien.get(line).offerLast(pret);
                count++;
                // System.out.println("no add");
                continue;
            }

            // 스택의 맨 위에 있는 프렛 저장
            int top = alien.get(line).peekLast();

            // top과의 크기 비교에 따라 분기 나누기
            if (top < pret) {           // top보다 크면 그냥 넣어주기 + 카운트
                alien.get(line).offerLast(pret);
                // System.out.println(top + " " + pret + " " + "big add");
                count++;
            } else if (top > pret) {    // top보다 작으면 같거나 큰게 나올때 까지 pop + 넣어주기 + 카운트
                while (!alien.get(line).isEmpty() && alien.get(line).peekLast() > pret) {
                    alien.get(line).pollLast();
                    // System.out.println("minus");
                    count++;
                }

                if (alien.get(line).isEmpty() || alien.get(line).peekLast() < pret) {
                    alien.get(line).offerLast(pret);
                    // System.out.println("no or big add");
                    count++;
                }

                // 같으면 무시
            }
        }

        // 결과 출력
        System.out.println(count);
    }
}

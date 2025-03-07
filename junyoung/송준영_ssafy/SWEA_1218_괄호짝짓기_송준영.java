import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * SWEA 1218 괄호짝짓기
 * 난이도 1/10
 * 스택
 * 84ms 25,000kb
 * 
 * 무난무난한 괄호 문제
 * 기본 괄호문제에서 괄호만 4개로 늘어난것 뿐 조건만 추가해주면 된다
 * 스택의 top을 보고 열리는 괄호이고 다음 넣을 괄호가 짝이 맞으면 빼준다
 * top이 닫히는 거면 노답이라 바로 0 리턴
 * 입력 만큼 다 돈 후 stk이 비어있으면 다 짝이 맞은거니까 1이고 남았으면 0 이다
 */
public class SWEA_1218_괄호짝짓기_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    // static 변수 선언
    static int N;
    static char[] gwal;

    // 괄호 검사를 스택 -> deque로 이용
    static Deque<Character> stk;

    public static void main(String[] args) throws Exception {
        // 테스트 10번 만큼 반복
        for (int t = 1; t <= 10; t++) {
            sb.append(String.format("#%d %d\n", t, solve()));
        }
        // 출력
        System.out.println(sb);
    }
    
    /**
     * 괄호 짝짓기 메서드
     * @return  1: 짝이 맞음, 0: 짝이 안맞음
     * @throws Exception
     */
    public static int solve() throws Exception {
        // 입력 처리 및 static 초기화
        N = parseInt(br.readLine());
        gwal = br.readLine().toCharArray();
        stk = new ArrayDeque<>();

        // 입력된 괄호만큼 체크해서 스택과 비교하기
        for (char g : gwal) {
            if (stk.isEmpty()) {                    // 스택이 비어있으면 그냥 넣어준다
                stk.offerLast(g);
            } else if (stk.peekLast() == '(') {     // 밑의 로직은 다 똑같다, 괄호 모양만 다를 뿐
                if (g == ')') {                     // 열리는 괄호거나 다른 모양이면 넣어주고 같은 모양의 닫히는 괄호면 빼준다
                    stk.pollLast();
                } else {
                    stk.offerLast(g);
                }
            } else if (stk.peekLast() == '[') {
                if (g == ']') {
                    stk.pollLast();
                } else {
                    stk.offerLast(g);
                }
            } else if (stk.peekLast() == '{') {
                if (g == '}') {
                    stk.pollLast();
                } else {
                    stk.offerLast(g);
                }
            } else if (stk.peekLast() == '<') {
                if (g == '>') {
                    stk.pollLast();
                } else {
                    stk.offerLast(g);
                }
            } else {                                // 스택의 top이 닫히는 괄호면 무조건 0 리턴
                return 0;
            }
        }

        // 스택이 비었는지 아닌지 검사
        if (stk.isEmpty()) {
            return 1;   // 비었으면 1 리턴
        }

        return 0;       // 안비었으면 0 리턴
    }
}

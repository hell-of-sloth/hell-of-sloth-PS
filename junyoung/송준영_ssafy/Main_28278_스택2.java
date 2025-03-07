import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * Main_28278_스택2
 * - 구현
 * - 난이도 1/10
 * - 기본적인 스택 구현 문제이다
 * - 출력이 많아서 sb(StringBuilder)를 써야 시간이 좀 줄어든다 => 4312ms -> 940ms
 * 
 */
public class Main_28278_스택2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));    // 빠른 입력을 위한 BufferedReader
    static StringTokenizer st;                                                          // 명령을 쪼개기 위한 StringTokenizer
    static Deque<Integer> stack = new ArrayDeque<>();                                   // 스택, Stack은 Vector를 상속 -> Deprecate 이슈로 Deque 권장
    static StringBuilder sb = new StringBuilder();                                      // 출력을 위한 StringBuilder
    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());                    // 명령의 수

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());                // 명령 입력
            int command = Integer.parseInt(st.nextToken());         // 명령의 첫 번째 값
            switch (command) {                                      // 명령에 따른 분기

                // 1 -> push X: 정수 X를 스택에 넣기
                case 1:
                    stackPush(Integer.parseInt(st.nextToken()));
                    break;
                
                // 2 -> pop: 스택에서 가장 위에 있는 정수를 빼고 그 수를 출력. 만약 스택에 들어있는 정수가 없는 경우에는 -1을 출력
                case 2:
                    stackPop();
                    break;
                // 3 -> size: 스택에 들어있는 정수의 개수를 출력
                case 3:
                    stackSize();
                    break;
                // 4 -> empty: 스택이 비어있으면 1, 아니면 0을 출력
                case 4:
                    stackEmpty();
                    break;
                // 5 -> top: 스택의 가장 위에 있는 정수를 출력. 만약 스택에 들어있는 정수가 없는 경우에는 -1을 출력
                case 5:
                    stackTop();
                    break;
            }
        }
        System.out.println(sb.toString());
    }

    /**
     * 스택에 정수 n을 넣는다.
     * @param n
     */
    public static void stackPush(int n) {
        stack.push(n);
    }

    /**
     * 스택에서 가장 위에 있는 정수를 빼고 그 수를 출력. 만약 스택에 들어있는 정수가 없는 경우에는 -1을 출력
     */
    public static void stackPop() {
        if (stack.isEmpty()) {              // 스택이 비어있음 확인
            sb.append("-1").append("\n");
        } else {
            sb.append(stack.pop()).append("\n");
        }
    }

    /**
     * 스택에 들어있는 정수의 개수를 출력
     */
    public static void stackSize() {
        sb.append(stack.size()).append("\n");
    }

    /**
     * 스택이 비어있으면 1, 아니면 0을 출력
     */
    public static void stackEmpty() {
        if (stack.isEmpty()) {                  // 스택이 비어있음 확인
            sb.append("1").append("\n");
        } else {
            sb.append("0").append("\n");
        }
    }

    /**
     * 스택의 가장 위에 있는 정수를 출력. 만약 스택에 들어있는 정수가 없는 경우에는 -1을 출력
     */
    public static void stackTop() {
        if (stack.isEmpty()) {                  // 스택이 비어있음 확인
            sb.append("-1").append("\n");
        } else {
            sb.append(stack.peek()).append("\n");
        }
    }
}

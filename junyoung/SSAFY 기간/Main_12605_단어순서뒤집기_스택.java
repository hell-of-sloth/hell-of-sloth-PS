import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_12605_단어순서뒤집기_스택 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());

        // N번 반복
        for (int i = 0; i < N; i++) {
            Stack<String> stk = new Stack<>();
            st = new StringTokenizer(br.readLine());

            // 스택에 토큰 삽입
            while (st.hasMoreTokens()) {
                stk.push(st.nextToken());
            }

            // sb 쌓기 -> 스택형식으로 쌓으므로 추가 스택 필요 x
            sb.append("Case #").append(i + 1).append(": ");
            while (!stk.isEmpty()) { // 스택이 빌 때까지 추가
                sb.append(stk.pop()).append(" ");
            }
            sb.append("\n");
        }

        // 출력
        System.out.println(sb);
    }
}

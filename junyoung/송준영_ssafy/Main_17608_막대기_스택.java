// 체감 난이도 1/10, 스택
// 이 문제는 스택인 걸 알면 간단하다
// 넣고자 하는 것보다 스택의 위가 높은 것을 빼면서 넣으면 오른쪽에서 봤을때 보이는 것만 남는다
// 이 성질을 이용하면 된다.

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main_17608_막대기_스택 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        ArrayDeque<Integer> stack = new ArrayDeque<>();     // 스택 사용, ArrayDeque로 선언
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());
            while (!stack.isEmpty() && stack.peek() <= x) {
                stack.pop();    // 스택의 위가 더 작으면 빼기
            }
            stack.push(x);      // 스택에 넣기
        }
        System.out.println(stack.size());
    }
}

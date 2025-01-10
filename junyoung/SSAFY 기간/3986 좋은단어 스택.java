// 체감 난이도 1/10 스택
// 다행히 보자마자 스택이 생각남

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());
        int answer = 0;

        for (int i = 0; i < N; i++) {
            if (solve()) {
                answer++;
            }
        }

        System.out.println(answer);
    }

    public static boolean solve() throws Exception {
        String words = br.readLine();
        Stack<Character> stack = new Stack<>();

        for (char i : words.toCharArray()) {
            if (stack.isEmpty()) {
                stack.push(i);
            } else {
                if (stack.peek() == i) {
                    stack.pop();
                } else {
                    stack.push(i);
                }
            }
        }

        if (stack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}

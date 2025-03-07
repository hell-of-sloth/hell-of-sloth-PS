import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;

/**
 * Main_1935_후위표기식2
 * 난이도 3/10
 * 스택
 * 108ms
 * 
 * 후위표기식을 계산하는 문제
 * 그런데 숫자가 아니라 미지수가 주어짐
 * 미지수는 각 숫자에 대응 -> Map을 쓰면 좋지 않을까?
 * 소수가 나올수 있으므로 double로 스택을 선언
 * 입력식(char 배열)을 탐색하면서 문자면 숫자로 맵핑해서 스택에 넣고
 * 연산자면 스택에서 두개를 꺼내서 연산한 후 다시 스택에 넣음
 * 결국 스택에는 하나의 값만 남게 되고 그것이 답이 된다
 */
public class Main_1935_후위표기식2 {
    // 빠른 입력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // static 변수 선언
    static int N;                                           // 미지수의 개수
    static char[] arr;                                      // 입력받은 후위표기식
    static Map<Character, Double> map = new HashMap<>();    // 미지수에 대응하는 숫자를 저장할 Map
    static Deque<Double> stack = new ArrayDeque<>();        // 후위표기식을 계산할 스택
    public static void main(String[] args) throws Exception {
        // 입력 처리
        N = Integer.parseInt(br.readLine());
        arr = br.readLine().toCharArray();      // 후위표기식을 char 배열로 변환
        for (int i = 0; i < N; i++) {
            map.put((char) ('A' + i), Double.parseDouble(br.readLine()));       // 미지수에 대응하는 숫자를 Map에 저장
        }

        // 후위표기식 계산
        for (char c : arr) {
            if (c >= 'A' && c <= 'Z') {         // 미지수면 숫자로 변환해서 스택에 넣음
                stack.push((double) map.get(c));
            } else {                            // 연산자면 스택에서 두개를 꺼내서 연산한 후 다시 스택에 넣음
                double b = stack.pop();
                double a = stack.pop();

                // switch 문으로 연산자에 따라 계산, 시간 줄이기 위해 switch문 사용
                switch (c) {
                    case '+':
                        stack.push(a + b);
                        break;
                    case '-':
                        stack.push(a - b);
                        break;
                    case '*':
                        stack.push(a * b);
                        break;
                    case '/':
                        stack.push(a / b);
                        break;
                }
            }
        }

        // 결과 출력
        System.out.printf("%.2f", stack.pop());
    }
}

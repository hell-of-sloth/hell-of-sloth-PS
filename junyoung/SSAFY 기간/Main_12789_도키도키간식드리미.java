import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Main_12789_도키도키간식드리미
 * 난이도는 3/10 정도
 * 큐와 스택을 둘 다 써야하는 문제
 * 큐에 순서대로 넣고 순서대로 빼면서 확인 -> 스택에 넣거나 통과하거나
 * 인원이 순서대로 못가면 Sad 출력 -> 순서 확인을 위한 변수 설정
 * 앞, 뒤에서만 접근하니 ArrayDeque 사용해서 시간복잡도 O(1)로 접근
 * 출력은 한 줄밖에 없어서 그냥 출력했음
 */
public class Main_12789_도키도키간식드리미 {
    // 빠른 인풋 처리 및 스플릿
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    // 큐와 스택 선언
    static Queue<Integer> q = new ArrayDeque<>();
    static ArrayDeque<Integer> stk = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());        // 학생 수 입력
        st = new StringTokenizer(br.readLine());        // 학생 번호 입력

        for (int i = 0; i < N; i++) {
            q.offer(Integer.parseInt(st.nextToken()));  // 먼저 큐에 순서대로 넣기
        }

        int cur = 1;                                    // 현재 순서

        // 우선 큐에 있는 학생들을 순서대로 빼서 확인
        while(!q.isEmpty()) {
            if (q.peek() == cur) {                              // 만약 큐의 맨 앞이 현재 순서와 같다면
                q.poll();                                       // 큐에서 빼고
                cur++;                                          // 순서 증가      
            } else if (!stk.isEmpty() && stk.peek() == cur) {   // 만약 스택의 맨 위가 현재 순서와 같다면
                stk.pop();                                      // 스택에서 빼고
                cur++;                                          // 순서 증가
            } else {                                            // 둘 다 순서가 안 맞으면
                stk.push(q.poll());                             // 스택에 넣기 큐 앞에 있는 인원 넣기, 현재순서 증가 x
            }
        }

        // 큐의 인원이 다 빠지면 이제 스택을 비워야함
        while (!stk.isEmpty()) {
            if (stk.peek() == cur) {                // 만약 스택의 맨 위가 현재 순서와 같다면
                stk.pop();                          // 스택에서 빼고
                cur++;                              // 순서 증가
            } else {                                // 만약 아니면 이제 갈 수 없으므로
                System.out.println("Sad");        // Sad 출력하고 break
                break;
            }
        }

        // while문 탈출 후 현재 순서가 학생 수 + 1이면 모두 갈 수 있음
        if (cur == N + 1) {
            System.out.println("Nice");
        }
        
    }
}

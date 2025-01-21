import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2346_풍선터뜨리기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));    // 빠른 입력을 위한 BufferedReader
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();                                      // 출력을 위한 StringBuilder
    public static void main(String[] args) throws Exception {

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];                 // 풍선 안의 정수를 저장
        boolean[] popped = new boolean[N];      // 풍선이 터졌는지 여부
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        // 첫 번째 풍선부터 시작
        int current = 0;

        // 풍선을 터뜨리는 순서대로 N번 반복
        for(int i = 0; i < N; i++){
            // 현재 풍선 출력
            sb.append(current + 1).append(" ");
            popped[current] = true;                     // 풍선 터뜨림 표시
            
            // 마지막 풍선까지 터뜨렸으면 종료
            if(i == N - 1) break;
            
            int k = arr[current];                       // 현재 풍선 안의 정수
            // k가 양수인지 음수인지 확인 후 이동
            if(k > 0){
                // k번 이동
                for(int move = 0; move < k; move++){
                    current = (current + 1) % N;         // 오른쪽 이동
                    while(popped[current]) {             // 이미 터진 풍선이면 다음 위치로
                        current = (current + 1) % N;
                    }
                }
            } else {
                int steps = -k; // k가 음수이므로 -k는 양수
                for(int move = 0; move < steps; move++){
                    current = (current - 1 + N) % N;     // 왼쪽 이동
                    while(popped[current]) {             // 이미 터진 풍선이면 이전 위치로
                        current = (current - 1 + N) % N;
                    }
                }
            }
        }
        
        System.out.println(sb.toString());
    }
}

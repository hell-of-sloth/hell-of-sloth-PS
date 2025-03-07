import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1333_부재중전화 {
    public static void main(String[] args) throws Exception {
        // BufferedReader와 StringTokenizer를 사용하여 입력을 받음
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken()); // 노래의 개수
        int L = Integer.parseInt(st.nextToken()); // 각 노래의 길이(초)
        int D = Integer.parseInt(st.nextToken()); // 전화벨 주기(초)

        int albumTime = N * L + (N - 1) * 5;
        
        int k = 0;
        while (true) {
            int t = k * D;  // 전화벨이 울리는 시작 시각
            
            if (t >= albumTime) {
                System.out.println(t);
                break;
            }
            
            boolean canHear = true; // 해당 시각에 전화벨 소리를 들을 수 있는지 여부
            
            for (int i = 0; i < N; i++) {
                int songStart = i * (L + 5);
                int songEnd = songStart + L;  // 종료 시각에 전화벨이 끝나면 듣지 못함
                
                if (t < songEnd && t + 1 > songStart) {
                    canHear = false;
                    break;
                }
            }
            
            if (canHear) {
                System.out.println(t);
                break;
            }
            
            k++;  // 다음 전화벨 울림 시각으로 이동
        }
    }
}

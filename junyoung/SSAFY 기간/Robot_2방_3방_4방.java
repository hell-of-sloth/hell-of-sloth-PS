// 체감 난이도 4/10, 구현, 시뮬레이션, 배열이동
// A, B, C 별 로직이 다름 -> 각각 구현
// BFS 인 줄 알았으나 직진만 가능하므로 BFS 필요 없음 
// -> 그래서 queue를 쓴 흔적이 있는데 안 쓰고 구현 가능 할 듯 합니다.


// 필요 임포트
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Robot_2방_3방_4방 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));        // 빠른 입력을 위한 BufferedReader
    static StringTokenizer st;                                                              // 문자열 스플릿팅
    static StringBuilder sb = new StringBuilder();                                          // 빠른 출력을 위한 StringBuilder
    public static void main(String[] args) throws Exception{
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            // 이 부분은 String.format으로도 가능
            sb.append("#").append(t+1).append(" ").append(solve()).append("\n");
        }
        System.out.println(sb.toString());          // 결과 출력
    }
    
    // 메인 문제풀이 함수
    static public int solve() throws Exception{
        int N = Integer.parseInt(br.readLine());    // 배열 크기
        String[][] arr = new String[N][N];          // 배열    
        String temp;                                // 임시 저장 변수
        Queue<int[]> q = new LinkedList<>();        // 큐 -> LinkedList로 구현
        
        int result = 0;                             // 결과값
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());    // 한 줄 입력
            for (int j = 0; j < N; j++) {
                temp = st.nextToken();                  // 한 줄에서 한 문자씩 읽어옴

                // A, B, C일 경우 큐에 저장
                if (temp.equals("A")) {
                    q.offer(new int[] { i, j, 1 });
                } else if (temp.equals("B")) {
                    q.offer(new int[] { i, j, 2 });
                } else if (temp.equals("C")) {
                    q.offer(new int[] { i, j, 3});
                }
                arr[i][j] = temp;
            }
        }
        
        // 큐에서 하나씩 꺼내서 각 로봇들 처리
        while(!q.isEmpty()) {
            int tempInt = bfs(N, arr, q.poll());    // 로봇 처리 함수에서 나온 값 저장
            result += tempInt;                      // 결과값에 더함
        }
        
        return result;                              // 결과값 반환
    }
    
    // 로봇 처리 함수
    static public int bfs(int N, String[][] arr, int[] start) {

        int sx, sy;                             // 시작점 x, y
        int nx, ny;                             // 다음점 x, y 
                                                // -> 시작점을 그대로 이용 하는 것 보다 따로 변수를 만들어 하는게 더 좋아 보였음 -> 나중에 쓸수도 있으니
        
        int count = 0;                          // 이동 횟수
        
        sx = start[0];                          // 시작점 x
        sy = start[1];                          // 시작점 y
        
        // 시작점에서 각 방향으로 직진 ( 1 -> A, 2 -> B, 3 -> C )
        // 직진시 이동 거리를 계산하여 count에 더함
        if (start[2] == 1) {            //  1 -> A 로직
            ny = sy + 1;

            // 배열 범위 안이고 S일 경우 계속 직진 (오른쪽)
            while(0 <= ny && ny < N && arr[sx][ny].equals("S")) {
                count += 1;
                ny += 1;
            }
        } else if (start[2] == 2) {     //  2 -> B 로직
            ny = sy + 1;

            // 배열 범위 안이고 S일 경우 계속 직진 (오른쪽)
            while(0 <= ny && ny < N && arr[sx][ny].equals("S")) {
                count += 1;
                ny += 1;
            }

            ny = sy - 1;
            
            // 배열 범위 안이고 S일 경우 계속 직진 (왼쪽)
            while(0 <= ny && ny < N && arr[sx][ny].equals("S")) {
                count += 1;
                ny -= 1;
            }
        } else {                        //  3 -> C 로직
            ny = sy + 1;

            // 배열 범위 안이고 S일 경우 계속 직진 (오른쪽)
            while(0 <= ny && ny < N && arr[sx][ny].equals("S")) {
                count += 1;
                ny += 1;
            }
            
            ny = sy - 1;

            // 배열 범위 안이고 S일 경우 계속 직진 (왼쪽)
            while(0 <= ny && ny < N && arr[sx][ny].equals("S")) {
                count += 1;
                ny -= 1;
            }

            nx = sx + 1;

            // 배열 범위 안이고 S일 경우 계속 직진 (아래)
            while(0 <= nx && nx < N && arr[nx][sy].equals("S")) {
                count += 1;
                nx += 1;
            }

            nx = sx - 1;
            
            // 배열 범위 안이고 S일 경우 계속 직진 (위)
            while(0 <= nx && nx < N && arr[nx][sy].equals("S")) {
                count += 1;
                nx -= 1;
            }
        }
        return count;   // 이동 거리 반환
    }
}
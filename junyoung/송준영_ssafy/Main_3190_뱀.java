import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;
// import static 한 번 써봤음 ㅎㅎ
import static java.lang.Integer.parseInt;

/**
 * Main_3190_뱀
 * 난이도는 6/10 정도
 * 구현, 시뮬레이션, 큐
 * 첨에 문제 잘 못 이해해서 조금 헤맸음... 문제는 진짜 잘 읽는게 중요하다
 * -> 명령 시간이 게임 시작 후 기준 시간인데 명령 마다 시간 후인 줄 알았음
 * -> 예) 1 D, 3 D, 5 D, 인경우 => 1, 3, 5 인데 1, 4, 9 로 이해
 * 
 * 따라서 처음에 뱀를 큐로 지정하고 비교하려고 보니 각 명령마다 x가 10000 인줄 알고..
 * 다른 방법 시도하다가(DP?) 깨달아서 다시 큐로 돌아옴
 * 큐로 구현하는게 훨씬 쉬움
 * 
 * 뱀의 좌표를 queue로 관리
 * 뱀의 머리를 peekLast()로 계속 확인해서 맵 밖이거나 자기 몸에 부딪히면 종료
 * 사과를 먹으면 꼬리를 자르지 않고, 사과를 먹지 않으면 꼬리를 자름
 * 
 * 자신 몸 부딪힌거 확인은 Arrays.equals()로 비교하려 했는데 실패 -> 배열의 참조값 비교해버림
 * 따라서 안의 배열을 꺼내서 Arrays.equals()로 비교해서 확인
 * 
 * 안 헤맸으면 한 2/3정도 푸는 시간 줄일 수 있었을 듯...
 * 
 * 다들 문제 잘 읽으세요!
 */
public class Main_3190_뱀 {
    // 빠른 인풋 처리 및 스플릿
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws Exception{

        // 입력 처리
        int N = parseInt(br.readLine());
        int[][] map = new int[N][N];
        int K = parseInt(br.readLine());

        // 사과 위치는 맵에 1로 표시 => boolean으로 해도 됨
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            map[parseInt(st.nextToken()) - 1][parseInt(st.nextToken()) - 1] = 1;
        }

        // 뱀의 방향 전환 정보, 배열로 인덱싱 접근을 위해 선언
        int L = parseInt(br.readLine());
        String[] com = new String[10001]; 
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            com[parseInt(st.nextToken())] = st.nextToken();
        }

        // 뱀의 위치를 저장할 큐
        ArrayDeque<int[]> snake = new ArrayDeque<>();
        snake.offer(new int[]{0, 0});       // 뱀의 시작 위치

        // 우하좌상
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        int dir = 0;        // 방향
        int time = 0;       // 시간

        // 뱀의 머리 위치를 계속 갱신
        // 벽에 부딪히거나 자기 몸에 부딪히면 종료
        while (true) {
            time++;                             // 시간 증가
            int[] head = snake.peekLast();      // 뱀의 머리 위치  
            int nx = head[0] + dx[dir];         // 다음 위치 x
            int ny = head[1] + dy[dir];         // 다음 위치 y

            // 벽에 부딪히면 종료
            if (!isIn(nx, ny, N)) {
                break;
            }

            // 자기 몸에 부딪히면 종료
            // ☆☆ 이 부분 공부 필요 ☆☆ -> Arrays.equals(), ArrayDeqye.contains() 공부바람
            // 처음에 ArrayDeqye.contains()로 비교하려 했는데 배열 내 equals() 메서드가 재정의 되어있지 않아 참조값을 비교해버림
            // ArrayDeqye.contains()는 equals() 메서드를 사용해서 비교함
            // 그래서 그냥 일일히 비교함
            // Arrays.equals()는 배열 내의 값이 같은지 비교하는 메서드
            for (int[] i : snake) {
                // 뱀의 머리가 자기 몸에 부딪히면 종료
                if (Arrays.equals(i, new int[]{nx, ny})) {
                    System.out.println(time);
                    return;
                }
            }
            
            // 사과가 없으면 꼬리를 자름
            snake.offer(new int[]{nx, ny});
            if (!(map[nx][ny] == 1)) {
                snake.poll();
            } else {
                map[nx][ny] = 0;        // 사과 먹었으니까 0으로 바꿔줌
            }                           // 안 바꿔주면 다시 먹을 수 있음

            // com에 따라 dir 변경
            // 나누기 나머지 성질 이용
            if (com[time] != null) {
                if (com[time].equals("L")) {
                    dir = (dir + 3) % 4;    // 왼쪽으로 이동
                } else {
                    dir = (dir + 1) % 4;    // 오른쪽으로 이동
                }
            }
        }

        // 벽에 부딪히면 시간 출력
        System.out.println(time);
    }

    // 범위 체크 메서드
    public static boolean isIn(int x, int y, int N) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}

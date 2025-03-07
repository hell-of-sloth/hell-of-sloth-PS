import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.StringTokenizer;

/**
 * SWEA_1873_상호의배틀필드_송준영
 * 난이도 4/10
 * 시뮬레이션
 * 91ms 27mb
 * 
 * 맵을 돌면서 탱크의 위치를 찾아내고, 이동 및 발사를 처리해 주면 된다
 * 이동은 명령어의 방향에 따라 달라지고 발사는 현재 탱크의 방향에 따라 달라진다
 * 이동과 발사는 자주 사용되므로 따로 메서드를 통해 구현 하였고 switch문으로 실행속도를 높였다
 * 
 * 이동 및 발사 마다 map을 업데이트 해주고 마지막에 출력해주었다
 */
public class SWEA_1873_상호의배틀필드_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int H, W, N;     // 맵 크기, 명령어 수
    static char[][] map;    // 맵
    static char[] commands; // 명령어

    static int[] tank;      // 탱크 위치
    
    // 방향 벡터
    static int[] dx = { -1, 0, 1, 0 };  
    static int[] dy = { 0, 1, 0, -1 };

    public static void main(String[] args) throws Exception {
        // 테스트 케이스만큼 반복
        int T = parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb.append(String.format("#%d ", t)); // 테스트 케이스 번호 입력
            solve();                                    // 여기서 메인 메서드를 돌며 sb에 결과가 저장
        }

        // 출력
        System.out.println(sb);
    }
    
    /**
     * 메인 메서드
     * @throws Exception
     */
    public static void solve() throws Exception {
        // 입력 처리 및 static 변수 초기화
        st = new StringTokenizer(br.readLine());
        H = parseInt(st.nextToken());
        W = parseInt(st.nextToken());

        map = new char[H][W];
        tank = new int[2];

        for (int i = 0; i < H; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < W; j++) {
                if (map[i][j] == '<' || map[i][j] == '^' || map[i][j] == '>' || map[i][j] == 'v') { // 탱크 위치 찾기
                    tank[0] = i;
                    tank[1] = j;
                }
            }
        }

        N = parseInt(br.readLine());
        commands = br.readLine().toCharArray();

        // 명령어 처리 (S 일때만 발사고 나머지는 이동이므로 if else로 처리)
        for (char c : commands) {
            if (c == 'S') {
                shoot();
            } else {
                move(c);
            }
        }

        // 결과를 sb에 입력
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                sb.append(map[i][j]);
            }
            sb.append('\n');
        }
    }

    /**
     * 탱크 이동 메서드
     * @param com   이동 방향 명령어
     */
    public static void move(char com) {
        int direction = 0;      // 탱크 방향
        char tankShape = ' ';   // 탱크 모양

        // 방향에 따라 탱크 모양과 방향 설정
        switch (com) {
            case 'U':
                direction = 0;
                tankShape = '^';
                break;
            case 'R':
                direction = 1;
                tankShape = '>';
                break;
            case 'D':
                direction = 2;
                tankShape = 'v';
                break;
            case 'L':
                direction = 3;
                tankShape = '<';
                break;
        }

        // 탱크가 가고자 하는 위치
        int nx = tank[0] + dx[direction], ny = tank[1] + dy[direction];

        map[tank[0]][tank[1]] = tankShape;  // 일단 현재 탱크 모양 바꿈 -> 이동 못하면 방향만 바뀌니까 -> 이건 무조건 실행됨
        if (isIn(nx, ny) && map[nx][ny] == '.') {   // 이동 가능하면
            map[nx][ny] = tankShape;        // 이동할 위치에 바뀐 탱크 모양을 넣고
            map[tank[0]][tank[1]] = '.';    // 이전 위치는 평지로 바꿈
            
            // 탱크 위치 업데이트
            tank[0] = nx;
            tank[1] = ny;
        }
    }

    /**
     * 탱크 발사 메서드
     */
    public static void shoot() {
        char tankShape = map[tank[0]][tank[1]]; // 현재 탱크 모양
        int direction = 0; // 탱크 방향

        // 현재 맵의 탱크 모양에 따라 탱크 방향 설정
        switch (tankShape) {
            case '^':
                direction = 0;
                break;
            case '>':
                direction = 1;
                break;
            case 'v':
                direction = 2;
                break;
            case '<':
                direction = 3;
                break;
        }

        // 포탄이 처음 도달하는 위치
        int nx = tank[0] + dx[direction], ny = tank[1] + dy[direction];

        // 포탄이 맵을 벗어나거나 강철벽(또는 벽)을 만나기 전까지 계속 진행
        while (isIn(nx, ny)) {
            if (map[nx][ny] == '#') { // 강철벽을 만나면 그냥 break
                break;
            } else if (map[nx][ny] == '*') { // 벽을 만나면 벽을 평지로 바꾸고 break
                map[nx][ny] = '.';
                break;
            }

            // 포탄 위치 업데이트
            nx += dx[direction];
            ny += dy[direction];
        }
    }
    
    /**
     * 범위 체크 함수
     * @param x 행
     * @param y 열
     * @return  범위 안이면 true, 아니면 false
     */
    public static boolean isIn(int x, int y) {
        return x >= 0 && x < H && y >= 0 && y < W;
    }
}

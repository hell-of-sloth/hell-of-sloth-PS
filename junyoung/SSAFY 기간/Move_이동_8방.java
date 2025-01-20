// 체감 난이도 5/10, 구현, 시뮬레이션, 배열 이동
// 각 방향이 8방향이라 분기가 8개나 되어서 이 부분 구현에 주의 해야한다.
// 각 참가자는 참가비를 낸다. 문제 설명을 5번 읽어 이해했다.
// 그래서 맵 밖으로 낙오시 -1000이 된다
// 맵 안에 남아있으면 해당 위치의 숫자 * 100을 얻는다.
// 그리고 참가비 1000을 뺀다.

import java.io.BufferedReader;      // 입력을 위한 BufferedReader
import java.io.InputStreamReader;   // 입력을 위한 InputStreamReader
import java.util.StringTokenizer;   // 문자열 스플릿을 위한 StringTokenizer

public class Move_이동_8방 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));    // 빠른 입력을 위한 BufferedReader
    static StringTokenizer st;                                                          // StringTokenizer 선언
    static StringBuilder sb = new StringBuilder();                                      // 빠른 출력을 위한 StringBuilder
    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());                                        // 테스트 케이스 수 입력

        for (int t = 0; t < T; t++) {
            sb.append(String.format("#%d %d\n", t+1, solve()));                  // 포매팅 방식 적용 후 sb에 저장, solve() 함수 실행
        }   
        System.out.println(sb);                                                         // 결과 출력
    }

    // 메인 문제 함수
    public static int solve() throws Exception {
        st = new StringTokenizer(br.readLine());        // 맵 크기, 유저 수 입력 
        int X = Integer.parseInt(st.nextToken());       // X 크기
        int Y = Integer.parseInt(st.nextToken());       // Y 크기
        int N = Integer.parseInt(st.nextToken());       // 유저 수

        // 맵 입력
        String[][] map = new String[X+1][Y+1];          // 맵 배열, 1부터 시작하므로 +1
        for (int i = 1; i <= X; i++) {
            st = new StringTokenizer(br.readLine());    // 맵 입력, StringTokenizer로 스플릿
            for (int j = 1; j <= Y; j++) {
                map[i][j] = st.nextToken();
            }
        }

        // 유저 입력
        int[][] users = new int[N][3];                  // 유저 배열, x좌표, y좌표, 이동 횟수
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());    // 유저 입력, StringTokenizer로 스플릿
            for (int j = 0; j < 3; j++) {
                users[i][j] = Integer.parseInt(st.nextToken());   // 유저 배열에 저장
            }
        }
        
        int total = 0;                              // 총 점수

        // 유저 이동
        for (int[] user : users) {                  // 유저 수 만큼 반복
            int x = user[0];                        // x 좌표 (행)
            int y = user[1];                        // y 좌표 (열)
            int trial = user[2];                    // 이동 횟수

            for (int i = 0; i < trial; i++) {
                int dir = map[x][y].charAt(0) - '0';        // 방향 char -> int
                int dist = map[x][y].charAt(1) - '0';       // 거리 char -> int
                
                // 8방향 이동 처리, 맵 밖으로 나가면 -1000 후 break해서 탈출 -> 다른 인원으로
                if (dir == 1) {                     // 1번 방향
                    x -= dist;
                    if (x < 1) {                    // 맵 밖으로 나가면 -1000
                        total -= 1000;
                        break;
                    }
                } else if (dir == 2) {              // 2번 방향
                    x -= dist;
                    y += dist;
                    if (x < 1 || y > Y) {           // 맵 밖으로 나가면 -1000
                        total -= 1000;
                        break;
                    }
                } else if (dir == 3) {              // 3번 방향
                    y += dist;
                    if (y > Y) {                    // 맵 밖으로 나가면 -1000
                        total -= 1000;
                        break;
                    }
                } else if (dir == 4) {              // 4번 방향
                    x += dist;
                    y += dist;
                    if (x > X || y > Y) {           // 맵 밖으로 나가면 -1000
                        total -= 1000;
                        break;
                    }
                } else if (dir == 5) {              // 5번 방향
                    x += dist;
                    if (x > X) {                    // 맵 밖으로 나가면 -1000
                        total -= 1000;
                        break;
                    }
                } else if (dir == 6) {              // 6번 방향
                    x += dist;
                    y -= dist;
                    if (x > X || y < 1) {           // 맵 밖으로 나가면 -1000
                        total -= 1000;
                        break;
                    }
                } else if (dir == 7) {              // 7번 방향
                    y -= dist;
                    if (y < 1) {                    // 맵 밖으로 나가면 -1000
                        total -= 1000;
                        break;
                    }
                } else if (dir == 8) {              // 8번 방향
                    x -= dist;
                    y -= dist;
                    if (x < 1 || y < 1) {           // 맵 밖으로 나가면 -1000
                        total -= 1000;
                        break;
                    }
                }
            }

            // 맵 안에 남아있으면 해당 위치의 숫자 * 100을 얻는다.
            if (x >= 1 && x <= X && y >= 1 && y <= Y) {
                total += Integer.parseInt(map[x][y]) * 100;
                total -= 1000;                      // 참가비 1000을 뺀다.
            }
        }
        return total;                               // 총 점수 반환
    }
}

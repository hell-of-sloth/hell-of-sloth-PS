import java.io.*;
import static java.lang.Integer.parseInt;
import java.util.*;

/**
 * 백준 21608 상어초등학교
 * 난이도 8/10
 * 구현, 시뮬레이션
 * 316ms 25,600kb
 * 
 * 좀 조건이 많은 자리배치 문제이다
 * 비교할 조건이 4개나 있어 일일이 다 비교하려면 머리아파져서 그냥 class 하나를 만들어 comparable을 구현해서 정렬하였다
 * 좋아하는 학생 수, 빈칸 수, 행, 열 순으로 정렬하였으며, compareTo를 재정의하여 정렬하였다
 * 이 덕분에 node가 담긴 리스트를 정렬하면 자동으로 해당 문제의 우선순위 조건대로 정렬이 된다.
 * 그래서 정렬 후 제일 앞에 있는 node를 선택하여 자리에 배치하면 된다는 접근방식으로 풀어보았다.
 * 
 * 자리 주변에 좋아하는 얘가 있는지, 빈칸이 몇개인지 각각 검사를 해줘야해서 반복적인 부분이 많이 첨가
 * -> 메소드로 분리하여 재사용성을 높였다.
 * 
 * 다른풀이보다 시간복잡도가 상대적으로 높아 줄이고 싶었는데 내가 생각한 방법은 이게 최선인것 같다고 하더라(chatGPT 피셜)
 * 정렬 말고 일일이 다 비교해도 되지만 이 방법이 나에겐 그래도 간편하고 좋은것 같다 -> 다른 풀이를 보면서 다른 방법도 봐보기
 */
public class Main_21608_상어초등학교_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;                                   // 교실 크기
    static int[][] seats;                           // 학생들을 앉힐 자리 배열
    static int[] seatOrder;                         // 학생 순서
    static Map<Integer, Set<Integer>> favoriteMap;  // 각 학생별 좋아하는 학생들, 있는지 없는지 접근(Set) 및 학생별로 바로 접근하기 위해 map으로 구현
    static List<Node> cmpList;                      // 비교할 노드 리스트
    static int result;                              // 만족도

    // 방향 벡터
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };

    public static void main(String[] args) throws Exception {
        // 입력 처리 및 초기화
        N = parseInt(br.readLine());
        seats = new int[N + 1][N + 1];
        seatOrder = new int[N * N];
        favoriteMap = new HashMap<>();

        // 좋아하는 얘 배열 초기화 및 학생 순서 정하기
        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = parseInt(st.nextToken());
            Set<Integer> favoriteTmp = new HashSet<>();

            // 좋아하는 얘들 추가
            for (int j = 0; j < 4; j++) {
                int tmpNum = parseInt(st.nextToken());
                favoriteTmp.add(tmpNum);
            }

            favoriteMap.put(idx, favoriteTmp);  // 좋아하는 얘들 Set을 각 학생 map에 추가, key : 학생번호, value : 좋아하는 학생들
            seatOrder[i] = idx;                 // 학생 순서 배열에 추가
        }

        // 학생 순서로 자리 배치
        for (int student : seatOrder) {
            seatCmp(student);
        }

        // 만족도 조사
        resultCal();

        // 결과 출력
        System.out.println(result);
    }

    /**
     * 학생 자리 배치하는 메서드
     * @param student   학생 번호
     */
    public static void seatCmp(int student) {
        cmpList = new ArrayList<>();        // 비교할 노드 리스트 초기화

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (seats[i][j] == 0) {     // 빈자리일 경우 비교대상이므로 리스트에 추가
                    cmpList.add(new Node(checkFav(i, j, student), checkBlank(i, j), i, j));
                }
            }
        }

        Collections.sort(cmpList);                  // 좋아하는 학생 수, 빈칸 수, 행, 열 순으로 정렬
        Node select = cmpList.get(0);         // 가장 앞에 있는 노드 선택

        seats[select.row][select.col] = student;    // 선택된 자리에 학생 배치
    }
    
    /**
     * 좋아하는 학생 수 체크 메서드
     * @param x         행
     * @param y         열
     * @param student   학생 번호
     * @return          좋아하는 학생 수
     */
    public static int checkFav(int x, int y, int student) {
        int nx, ny;
        int cnt = 0;    // 좋아하는 학생 수

        for (int i = 0; i < 4; i++) {
            nx = x + dx[i];
            ny = y + dy[i];
            if (isIn(nx, ny)) { // 범위 내에 있을 경우
                // 좋아하는 학생이 있는지 확인, 있으면 cnt 증가
                if (favoriteMap.get(student).contains(seats[nx][ny])) {
                    cnt++;
                }
            }
        }
        
        return cnt;     // 좋아하는 학생 수 반환
    }

    /**
     * 빈칸 수 체크 메서드
     * @param x 행
     * @param y 열
     * @return  빈칸 수
     */
    public static int checkBlank(int x, int y) {
        int nx, ny;
        int cnt = 0;    // 빈칸 수

        for (int i = 0; i < 4; i++) {
            nx = x + dx[i];
            ny = y + dy[i];
            if (isIn(nx, ny)) { // 범위 내에 있을 경우
                // 빈칸이 있는지 확인, 있으면 cnt 증가
                if (seats[nx][ny] == 0) {
                    cnt++;
                }
            }
        }

        return cnt;     // 빈칸 수 반환
    }

    /**
     * 만족도 계산 메서드
     */
    public static void resultCal() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                int temp = checkFav(i, j, seats[i][j]); // 좋아하는 학생 수 체크
                switch (temp) { // 좋아하는 학생 수에 따라 만족도 증가
                    case 0:
                        break;
                    case 1:
                        result += 1;
                        break; 
                    case 2:
                        result += 10;
                        break;
                    case 3:
                        result += 100;
                        break;
                    case 4:
                        result += 1000;
                        break;
                }
            }
        }
    }

    /**
     * 범위 체크 메서드
     * @param x 행
     * @param y 열
     * @return  범위 내에 있으면 true, 아니면 false
     */
    public static boolean isIn(int x, int y) {
        return x >= 1 && x < N + 1 && y >= 1 && y < N + 1;
    }

    /**
     * 비교할 노드 클래스
     * 좋아하는 학생 수, 빈칸 수, 행, 열 순으로 정렬
     */
    public static class Node implements Comparable<Node>{
        public int favorite;    // 좋아하는 학생 수
        public int blank;       // 빈칸 수
        public int row;         // 행
        public int col;         // 열
        
        // 생성자
        public Node(int favorite, int blank, int row, int col) {
            this.favorite = favorite;
            this.blank = blank;
            this.row = row;
            this.col = col;
        }
        
        // 정렬을 위한 compareTo 재정의
        // 좋아하는 학생 수(내림), 빈칸 수(내림), 행(오름), 열(오름) 순으로 정렬
        @Override
        public int compareTo(Node o) {
            if (this.favorite != o.favorite) {
                return o.favorite - favorite;
            } else if (this.blank != o.blank) {
                return o.blank - this.blank;
            } else if (this.row != o.row) {
                return this.row - o.row;
            } else if (this.col != o.col) {
                return this.col - o.col;
            }
    
            return 0;
        }
    }
}

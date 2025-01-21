// 체감 난이도 5/10, 구현, 시뮬레이션, 배열이동
// 점퍼가 이동 중간에 있어도 밟힘 -> 이부분 구현이 중요 및 어려움
// -> 점퍼 좌표를 Set으로 저장하여 빠른 검증 -> Set에 배열 저장시 주소 값으로 저장 ㅚ어 버림
// -> 따라서 Arrays.toString으로 문자열 변환 후 저장하면 값을 저장 할 수 있다는 것을 이용

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Maze_미로_4방 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));    // 빠른 입력을 위한 BufferedReader
    static StringTokenizer st;                                                            // 문자열 스플릿팅
    static StringBuilder sb = new StringBuilder();                                        // 빠른 출력을 위한 StringBuilder
    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int[] result = solve();    // 메인 문제풀이 함수 실행
            sb.append(String.format("#%d %d %d\n", t+1, result[0], result[1]));    // 포매팅 방식 적용 후 sb에 저장
        }
        System.out.println(sb.toString());
    }
    
    // 메인 문제풀이 함수
    static public int[] solve() throws Exception{
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());      // 배열 크기
        int sx = Integer.parseInt(st.nextToken());     // 시작 지점 x값
        int sy = Integer.parseInt(st.nextToken());     // 시작 지점 y값
        
        // 점퍼 좌표 입력
        int jumperNum = Integer.parseInt(st.nextToken());     // 점퍼 개수
        HashSet<String> jumpers = new HashSet<>();            // 빠른 찾기를 위해 Set이용
        int jumTempX, jumTempY;                               // 점퍼 좌표
        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < jumperNum ; i++) {
            jumTempX = Integer.parseInt(st.nextToken());        // 점퍼 x좌표
            jumTempY = Integer.parseInt(st.nextToken());        // 점퍼 y좌표
            
            // 점퍼 좌표를 스트링으로 변환후 저장 ☆☆☆ -> 파이썬 처럼 배열로 저장하면 객체라 주소값을 비교해버림
            jumpers.add(Arrays.toString(new int[] { jumTempX, jumTempY }));
        }
        
        // 이동 명령 입력
        int moveNum = Integer.parseInt(br.readLine());      // 이동 명령 개수
        int[][] moves = new int[moveNum][2];                // 이동 명령 저장 배열
        int dir, dist;                                      // 방향, 거리
        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i < moveNum ; i++) {
            dir = Integer.parseInt(st.nextToken());         // 방향
            dist = Integer.parseInt(st.nextToken());        // 거리
            moves[i] = new int[] { dir, dist };             // 이동 명령 배열로 저장
        }
        
        // 처음 시작점이 점퍼인 경우 처리
        if (jumpers.contains(Arrays.toString(new int[] {sx, sy}))) {
            return new int[] { 0, 0 };                      // 0, 0 반환
        }
        
        // 이제부터 명령 처리 시작
        int nx, ny;                                         // 다음 좌표 설정
        nx = sx;                                            // 시작점 x값
        ny = sy;                                            // 시작점 y값

        // 이동 명령 처리, for문으로 반복
        for (int[] com : moves) {
            // System.out.println("계산전 검증 : " + com[0] + " " + com[1]);
            
            // 스위치 케이스 문으로 각 방향 처리
            // 이동하는 중에 점퍼 만나도 튕기므로 처리 위해 for문으로 반복 마다 검증 + 좌표 위치 증가
            switch(com[0]) {                                // 스위치 케이스는 탈출 위해선 break 필요!
                case 1:                                     // 1번 방향 (위)
                    for (int i = 0 ; i < com[1] ; i++) {
                        nx -= 1;

                        // 점퍼 만나면 0, 0 반환
                        if (jumpers.contains(Arrays.toString(new int[] {nx, ny}))) {
                            return new int[] { 0, 0 };
                        }
                    }
                    break;
                case 2:                                     // 2번 방향 (오른쪽)
                    for (int i = 0 ; i < com[1] ; i++) {
                        ny += 1;

                        // 점퍼 만나면 0, 0 반환
                        if (jumpers.contains(Arrays.toString(new int[] {nx, ny}))) {
                            return new int[] { 0, 0 };
                        }
                    }
                    break;
                case 3:                                     // 3번 방향 (아래)
                    for (int i = 0 ; i < com[1] ; i++) {
                        nx += 1;

                        // 점퍼 만나면 0, 0 반환
                        if (jumpers.contains(Arrays.toString(new int[] {nx, ny}))) {
                            return new int[] { 0, 0 };
                        }
                    }
                    break;
                case 4:                                     // 4번 방향 (왼쪽)
                    for (int i = 0 ; i < com[1] ; i++) {
                        ny -= 1;

                        // 점퍼 만나면 0, 0 반환
                        if (jumpers.contains(Arrays.toString(new int[] {nx, ny}))) {
                            return new int[] { 0, 0 };
                        }
                    }
                    break;
            }
            
            // System.out.println("검증 : " + nx + " " + ny);
            
            // 범위 밖으로 나갈 경우 처리
            if (nx < 0 || nx > N || ny < 0 || ny > N) {
                return new int[] { 0, 0 };                    // 0, 0 반환
            }
        }
        
        // 마지막 도착 위치 반환
        return new int[] {nx, ny};
    }
}

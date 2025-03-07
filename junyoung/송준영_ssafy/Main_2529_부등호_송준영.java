import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import static java.lang.Integer.parseInt;

/**
 * Main_2529_부등호_송준영
 * 난이도 6/10
 * 백트래킹, 완탐, 문자열포맷 처리
 * 204ms 21200KB
 * 
 * 이 문제는 많이 풀어본 문제와 같이 조건이 있는 순열 같은 문제이다.
 * 부등호와 그 전 숫자에 따라 다음 숫자가 정해진다.
 * 다만 처리를 해줘야할 것이 많다
 * 부등호 수보다 1 많게 숫자를 만들어야 하므로 k+1로 잡아준다
 * 앞에 부등호가 없을 경우 (depth == 0)일때도 처리 해줘야한다.
 * 그리고 문자열을 숫자로 변환해서 비교해줘야하며
 * 문자열 길이가 9자리 일수 있으므로 long으로 처리해줘야한다. -> 틀릴 뻔한 점
 * 그리고 문자열 포매팅을 사용해서 출력해줘야한다.
 * 
 * 새로 알게된점! ☆☆☆ 문자열 포매팅은 동적으로 처리가 가능하다! ☆☆☆
 * %와 d 사이에 + + 로 변수를 넣어주면 그 변수가 포매팅으로 작동한다!
 * 
 * 첫 제출시 틀려서 반례를 찾던중
 * 가능한 상황이 1개 뿐이라면 max나 min 중 하나가 초기값으로 남아 있는 경우가 발생했다.
 * 따라서 가능한 값을 발견하면 우선 그 값을 min, max에 둘 다 넣어주고 그 후 비교를 시켜주니 맞았다!
 */
public class Main_2529_부등호_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    // static 변수 선언
    static int k;                   // 부등호의 개수
    static String[] budeung;        // 부등호 배열
    static int[] nums = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };   // 숫자 배열
    static boolean[] visited;       // 방문 여부
    static long maxNum = 0;         // 최대값, 어차피 처음 찾는 값으로 초기화 예정으로 굳이 작은 값 안넣어도 됨
    static long minNum = 0;         // 최소값, 어차피 처음 찾는 값으로 초기화 예정으로 굳이 큰 값 안넣어도 됨

    public static void main(String[] args) throws Exception {
        // 부등호 개수 입력
        k = parseInt(br.readLine());

        // 부등호 배열 초기화 + 방문 배열 초기화
        budeung = new String[k];
        visited = new boolean[10];
        
        // 부등호 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            budeung[i] = st.nextToken();
        }
        
        // 백트래킹 시작
        backtrack(0, 0, new StringBuilder());

        // sb에 최대값, 최소값 넣어주기 + 출력
        sb.append(String.format("%0" + (k+1) + "d\n", maxNum));
        sb.append(String.format("%0" + (k+1) + "d\n", minNum));
        System.out.println(sb);
    }
    
    /**
     * 백트래킹 메서드
     * @param depth     현재 depth
     * @param lastNum   이전 숫자
     * @param temp      현재까지 넣은 숫자
     */
    public static void backtrack(int depth, int lastNum, StringBuilder temp) {
        // depth가 k+1이 되면 부등호 수보다 1개 많은 숫자가 만들어진 것이므로 결과를 비교한다
        if (depth == k+1) {
            // 문자열을 숫자로 변환 (long)
            // 시간 줄이기 위해 변수에 저장해놓고 비교 (248ms -> 204ms)
            long result = Long.parseLong(temp.toString());

            // 처음 발견한 값이면 최대값, 최소값 초기화
            if (maxNum == 0 && minNum == 0) {
                maxNum = result;
                minNum = result;
            }

            // 최대값, 최소값 비교
            maxNum = Math.max(maxNum, result);
            minNum = Math.min(minNum, result);
            return;
        }   

        // 임시 문자열 길이 저장 -> 재귀 호출 후 길이 초기화
        int len = temp.length();

        // 0부터 9까지 탐색
        for (int i = 0; i < 10; i++) {
            if (!visited[i]) {      // 방문하지 않았다면
                if (depth != 0) {   // 처음이 아니라면 부등호에 따라 비교
                    if (budeung[depth-1].equals("<") && i <= lastNum) {
                        continue;   // 부등호에 맞지 않으면 continue
                    } else if (budeung[depth-1].equals(">") && i >= lastNum) {
                        continue;   // 부등호에 맞지 않으면 continue
                    }
                } 

                // 부등호 만족시 or depth == 0 이면 방문 체크, 문자열에 추가, 재귀 호출
                visited[i] = true;
                temp.append(i);
                backtrack(depth + 1, i, temp);
                temp.setLength(len);
                visited[i] = false;
            }
        }
    }
}

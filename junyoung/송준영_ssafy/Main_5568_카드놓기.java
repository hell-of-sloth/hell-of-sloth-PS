import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Integer.parseInt;  // import static

/**
 * Main_5568_카드놓기
 * 난이도 4/10
 * 백트래킹, 순열
 * 이 문제는 주어진 카드 중에서 k개를 뽑아 만들 수 있는 모든 순열을 구하는 문제이다
 * 순열을 구하는 방법은 백트래킹을 이용하면 된다
 * 순열의 중복 제거를 위해 Set을 이용하였음
 * 다만 set에 넣을 때 StringBuilder를 이용하여 문자열로 변환하여 넣었음 -> List 스트링시 중복 적용 안됨
 * 그리고 마지막에 set의 크기를 출력하면 된다
 */
public class Main_5568_카드놓기 {
    // 빠른 입출력
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Set<String> set = new HashSet<>();               // 중복 제거를 위한 Set
    public static void main(String[] args) throws Exception {
        // 입력 처리
        int n = parseInt(br.readLine());
        int k = parseInt(br.readLine());
        int[] cards = new int[n];               // 카드를 저장할 배열
        boolean visited[] = new boolean[n];     // 방문 여부를 체크할 배열
        for (int i = 0; i < n; i++) {
            cards[i] = parseInt(br.readLine());
        }
        // 백트래킹을 이용한 순열 구하기, 뒤에서만 접근하므로 ArrayList를 이용 -> Stack 써도 될듯? 그러면 더 빠를려나?
        makeCard(n, k, 0, cards, visited, new ArrayList<Integer>());
        System.out.println(set.size());
    }

    // 백트래킹을 이용한 순열 구하기
    // n: 카드의 개수, k: 뽑을 카드의 개수, depth: 현재 뽑은 카드의 개수, cards: 카드 배열, visited: 방문 여부 배열, list: 뽑은 카드를 저장할 리스트
    public static void makeCard(int n, int k, int depth, int[] cards, boolean[] visited, List<Integer> list) {
        // k개의 카드를 뽑았다면 set에 추가
        if (depth == k) {
            StringBuilder sb = new StringBuilder();     // StringBuilder를 이용하여 문자열로 변환
            for (int s : list) {                        // List 스트링시 중복 적용 안됨 -> StringBuilder에 저장
                sb.append(s);
            }
            set.add(sb.toString());                     // set에 추가   
            return;
        }

        // 백트래킹을 이용한 순열 구하기
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {          // 방문하지 않은 카드라면
                visited[i] = true;      // 방문 체크
                list.add(cards[i]);     // 리스트에 추가
                makeCard(n, k, depth + 1, cards, visited, list);    // 재귀 호출
                list.remove(depth);     // 호출후 다시 나왔으면 넣은 숫자 제거
                visited[i] = false;     // 방문 체크도 해제
            }
        }
    }
}

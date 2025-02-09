import java.io.*;
import java.util.*;

/**
 * Main_1759_암호만들기
 * 난이도 6/10
 * 백트래킹, 조합, 문자처리?
 * 암호는 최소 "한" 개의 모음과 최소 "두" 개의 자음으로 구성되어 있어야함
 * 알파벳이 주어지고 이 중 L개를 뽑아서 암호를 만들어야한다
 * 이 때 암호는 사전순으로 "증가하는 순서"로 출력해야함
 * 꽤나 조건이 많은 문제이다
 * 처음에 별생각 없이 했다가 오름차순이 아니어서 수정하였다
 * 모음, 자음 세는 로직을 어떻게 최적화할 수 있을까..? -> 나중에 더 생각
 * 오름차순으로 단어를 조합하면서 해당 길이를 만족하면 자음, 모음의 개수를 세서 조건 확인
 */
public class Main_1759_암호만들기 {
    // 빠른 입력과 출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    // L, C를 저장할 변수 + 알파벳을 저장할 배열 + 방문 여부를 저장할 배열
    // 다른 메서드에서 바로 접근 할 수 있게 static으로 선언
    static int L, C;
    static String[] arr;
    static boolean[] visited;
    public static void main(String[] args) throws Exception{
        // 입력 처리
        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        // 알파벳을 저장하고 정렬 -> 사전순으로 출력해야하므로
        arr = new String[C];
        visited = new boolean[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            arr[i] = st.nextToken();
        }
        Arrays.sort(arr);

        // 백트래킹으로 암호 만들기
        makePw(0, L, -1, new StringBuilder());
        
        // 결과 출력
        System.out.println(sb);
    }

    /**
     * 백트래킹으로 암호를 만들어내는 메서드
     * @param depth     현재까지 뽑은 알파벳의 개수
     * @param L         뽑아야되는 알파벳의 개수
     * @param step      현재까지 뽑은 알파벳의 인덱스 -> 오름차순 출력을 위해
     * @param tempSb    현재까지 뽑은 알파벳을 저장할 StringBuilder
     */
    public static void makePw(int depth, int L, int step, StringBuilder tempSb) {
        // 종료 조건
        if (depth == L) {
            char[] vowels = {'a', 'o', 'e', 'i', 'u'};      // 모음 저장
            int vowel = 0;                                  // 모음 개수 저장 -> 자음은 문자열에서 나머지이므로 따로 선언 x                
            boolean isVowel;                                // 모음인지 확인할 변수         
            
            // 모음, 자음 개수 세기
            for (int i = 0; i < L; i++) {
                isVowel = false;

                // 모음인지 확인
                for (char c : vowels) {
                    if (tempSb.charAt(i) == c) {
                        isVowel = true;
                        break;
                    }
                }
                if (isVowel) {    // 모음이면 모음 개수 증가
                    vowel++;
                }
            }

            // 모음이 1개 이상이고 자음이 2개 이상이면 sb에 추가
            if (vowel > 0 && (L - vowel) > 1) {
                sb.append(tempSb).append("\n");
            }
            return;
        }

        // 암호 만들기
        for (int i = 0; i < C; i++) {
            if (!visited[i] && step < i) {              // 뽑지 않은 알파벳이고 이전에 뽑은 알파벳보다 뒤에 있는 알파벳만 뽑음
                visited[i] = true;                      // 방문 체크
                tempSb.append(arr[i]);                  // StringBuilder에 추가
                makePw(depth + 1, L, i, tempSb);        // 다음 알파벳으로 재귀
                tempSb.setLength(tempSb.length()-1);    // 뽑은 알파벳 제거
                visited[i] = false;                     // 방문 체크 해제
            }
        }
    }
}

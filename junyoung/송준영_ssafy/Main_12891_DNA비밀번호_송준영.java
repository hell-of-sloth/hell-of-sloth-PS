import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import static java.lang.Integer.parseInt;

/**
 * Main_12891_DNA비밀번호_송준영
 * 난이도 2/10
 * 슬라이딩 윈도우
 * 276ms 23,800KB
 * 
 * 슬라이딩 윈도우 이동시 각 알파벳의 개수를 갱신
 * 갱신 후 조건에 맞는지 개수 세기 -> 맞으면 답안++
 * 알파벳 4개만 검사하면되서 오버헤드가 그리 크지는 않음
 */
public class Main_12891_DNA비밀번호_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int S, P;    // 문자열 길이, 비밀번호 길이
    static char[] arr;  // 문자열
    static int[] dna;   // 필요한 알파벳 개수 조건
    static int[] cnt;   // 윈도우의 각 알파벳 개수
    static int answer = 0;  // 정답

    public static void main(String[] args) throws Exception {
        // 입력 처리
        st = new StringTokenizer(br.readLine());
        S = parseInt(st.nextToken());
        P = parseInt(st.nextToken());

        // 문자열 입력
        arr = new char[S];
        arr = br.readLine().toCharArray();

        // 필요한 알파벳 개수 입력 및 초기화
        dna = new int[4];
        cnt = new int[4];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            dna[i] = parseInt(st.nextToken());
        }

        // 초기 윈도우 설정
        for (int i = 0; i < P; i++) {
            cnt[getDNA(arr[i])]++;
        }

        // 처음에 가능하면 정답++
        if (isPossible()) {
            answer++;
        }

        // 슬라이딩 윈도우 실행
        for (int i = P; i < S; i++) {
            cnt[getDNA(arr[i - P])]--;
            cnt[getDNA(arr[i])]++;
            if (isPossible()) {
                answer++;
            }
        }

        // 출력
        System.out.println(answer);
    }

    /**
     * 알파벳에 따른 인덱스 반환
     * @param c 알파벳
     * @return  A:0, C:1, G:2, T:3
     */
    public static int getDNA(char c) {
        switch (c) {
            case 'A':
                return 0;
            case 'C':
                return 1;
            case 'G':
                return 2;
            case 'T':
                return 3;
        }
        return -1;
    }

    /**
     * 현재 윈도우의 각 알파벳 개수가 조건에 맞는지 확인
     * @return  조건에 맞으면 true, 아니면 false
     */
    public static boolean isPossible() {
        for (int i = 0; i < 4; i++) {
            if (dna[i] > cnt[i]) {
                return false;
            }
        }
        return true;
    }
}

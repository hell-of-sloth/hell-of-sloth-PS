import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Main_1931_회의실배정
 * 난이도 2/10
 * 그리디
 * 524ms 45mb
 * 
 * 회의실 배정 문제
 * 회의가 겹치지 않게 최대한 많은 회의를 배정하는 문제이며 그리디를 써서 풀면 되는 문제이다
 * 회의 끝 시간을 기준으로 정렬한 후 넣고자 하는 회의의 첫 시간이 넣은 회의의 끝 시간보다 크거나 같으면 넣는다
 */
public class Main_1931_회의실배정_송준영 {
    // 빠른 입출력을 위한 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    // static StringBuilder sb = new StringBuilder();

    static int N;                       // 회의의 수
    static Meet[] meetings;             // 회의 정보 (회의 시작 시간, 회의 끝 시간)
    static List<Meet> possibleMeet;     // 가능한 회의 리스트 -> 답
    

    public static void main(String[] args) throws Exception {
        // 입력 처리 및 초기화
        N = parseInt(br.readLine());
        meetings = new Meet[N];
        possibleMeet = new ArrayList<>();

        // 회의 정보 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            meetings[i] = new Meet(parseInt(st.nextToken()), parseInt(st.nextToken()));
        }

        // 회의 끝 시간을 기준으로 정렬 후 처음 회의 넣고 시작하기
        Arrays.sort(meetings);
        possibleMeet.add(meetings[0]);

        // 회의 시간을 비교해서 넣고자 하는 회의의 시작 시간이 넣은 회의의 끝 시간보다 크거나 같으면 넣는다
        for (int i = 1; i < N; i++) {
            if (possibleMeet.get(possibleMeet.size() - 1).end <= meetings[i].start) {
                possibleMeet.add(meetings[i]);
            }
        }

        // 가능한 회의 리스트의 크기 출력 -> 결과 출력
        System.out.println(possibleMeet.size());
    }

    /**
     * 회의 정보 클래스
     * Comparable을 구현하여 회의 끝 시간을 기준으로 정렬할 수 있도록 한다
     */
    static class Meet implements Comparable<Meet>{
        int start, end;

        public Meet(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Meet o) {
            return this.end == o.end ? this.start - o.start : this.end - o.end;
        }
    }
    
}

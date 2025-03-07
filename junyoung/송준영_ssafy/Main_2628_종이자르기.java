
import java.io.BufferedReader;
import java.io.InputStreamReader;
import static java.lang.Integer.parseInt;   // import static으로 사용하면 Integer.parseInt()가 아니라 parseInt()로 사용 가능
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Main_2628_종이자르기
 * 난이도 3/10
 * 정렬?
 * 가로, 세로 잘랐을때 가장 긴 길이를 찾아서 곱하면 최대 넓이가 나온다
 * 가장 긴 길이는 자르는 부분이 순서대로 주어지지 않으므로 정렬을 해준 후 앞과 뒤의 차가 곧 길이이다
 * 그 길이중 가장 큰 값을 찾아서 곱해주면 된다
 */
public class Main_2628_종이자르기 {
    // 빠른 입력 처리 및 토큰 분할
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        // 입력 처리
        int w, h;
        st = new StringTokenizer(br.readLine());
        h = parseInt(st.nextToken());
        w = parseInt(st.nextToken());
        int N = parseInt(br.readLine());
        
        // 가로, 세로 길이를 저장할 리스트 생성
        List<Integer> wList = new ArrayList<>();
        List<Integer> hList = new ArrayList<>();

        // 가로, 세로 길이의 처음과 끝을 저장 -> 나중에 차이 구하기 위해서
        wList.add(0);
        wList.add(w);
        hList.add(0);
        hList.add(h);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int type = parseInt(st.nextToken()); // 가로, 세로 타입
            int value = parseInt(st.nextToken()); // 자르는 부분

            // 가로, 세로에 따라 저장
            if (type == 0) {
                wList.add(value);
            } else {
                hList.add(value);
            }
        }

        // 정렬 (오름차순)
        Collections.sort(wList);
        Collections.sort(hList);
        // System.out.println(wList.toString());
        // System.out.println(hList.toString());

        // 가로, 세로 최대 길이 찾기
        int wMax = 0;
        int hMax = 0;
        
        // 차이가 가장 큰 부분이 가장 긴 길이
        for (int i = 1; i < wList.size(); i++) {
            wMax = Math.max(wMax, wList.get(i) - wList.get(i-1));
            // System.out.println("w: " + wMax);
        }
        for (int i = 1; i < hList.size(); i++) {
            hMax = Math.max(hMax, hList.get(i) - hList.get(i-1));
            // System.out.println("h: " + hMax);
        }

        // 출력
        System.out.println(wMax * hMax);
    }
}

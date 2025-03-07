import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * Main_2635_수이어가기
 * 난이도 3/10
 * 완전탐색
 * N입력하면 두번째 수가 1 ~ N이 가능하다 -> 두번째 수를 1 ~ N까지 돌면서 수열을 만들어보고 가장 긴 수열을 찾는다.
 * 수열저장은 배열보다는 동적 입력이 자유로운 List를 사용했다. -> LinkedList나 Queue를 사용해도 될듯
 * 인덱스+1 이 곧 리스트 크기이다
 * 그래서 인덱스 크기가 기존 보다 크면 결과에 갱신하고 수열 리스트도 갱신해준다
 */
public class Main_2635_수이어가기{
    // 빠른 입력과 출력을 위한 BufferedReader와 StringBuilder
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());        //  첫번째 수 입력

        int resultNum = 0;                              // 결과 수열의 크기
        List<Integer> resultList = new LinkedList<>();  // 결과 수열

        // 두번째 수가 1 ~ N까지 가능하다 -> 반복해서 다 찾기
        for (int i = 1; i <= N; i++) {
            int index = 1;                          // 수열의 인덱스, 처음에 첫 번째수가 들어가있으므로 1부터 시작
            List<Integer> l = new LinkedList<>();   // 수열을 저장할 리스트

            // 수열의 첫번째 수와 두번째 수를 넣어준다
            l.add(N);
            l.add(i);

            // 수열을 만들어나간다, 음수가 계산될 때까지 인덱스를 늘리며 반복
            while(l.get(index-1) - l.get(index) >= 0) {
                l.add(l.get(index-1) - l.get(index));
                index++;
            }
            
            // System.out.println("인덱스: " + (index+1));

            // 결과값 갱신
            if (resultNum < index+1) {
                resultNum = index+1;
                resultList = l;
            }
            // System.out.println("테스트: " + l.toString());
        }

        // 결과 출력
        System.out.println(resultNum);
        for (int i : resultList) {
            sb.append(i).append(" ");
        }
        System.out.println(sb.toString());
    }
}

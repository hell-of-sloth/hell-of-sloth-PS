
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Main_2605_줄세우기
 * 난이도 2/10
 * 완전탐색, 도중 삽입?
 * String 배열을 int 배열로 바꾸는 방법을 배웠음! Array a말고 Arrays!
 * 도중 삽입을 해야해서 삽입에 효율적인 LinkedList를 사용했다
 * LinkedList를 생성할때 List(인터페이스)로 받으면 LinkedList에만 있는 메서드를 못 씀 -> offer
 * LinkedList는 add(index, value)로 index에 value를 넣을 수 있다
 * 학생 수 * 줄 길이 => 대략 100 * 100 => 10000정도 => 1초 안에 가능
 */
public class Main_2605_줄세우기 {
    // 입력 처리
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    // 출력 처리
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        // 입력 받기
        int N = Integer.parseInt(br.readLine());
        // String 배열 -> int 배열
        int[] order = Arrays.stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt).toArray();
        // LinkedList 생성 -> 삽입에 효율적
        LinkedList<Integer> list = new LinkedList<>();
        
        // 학생들을 줄세우기
        for (int i = 0; i < order.length; i++) {
            
            if (order[i] == 0) {        // 0(첫 순서)이면 맨 뒤에 넣기
                list.offer(i+1);
            } else {                    // 아니면 list.size() - order[i]에 넣기 -> 뒤에서 order[i]만큼 뺀 곳에 넣기
                // System.out.println("list.size() - order[i]: " + (list.size() - order[i]));
                list.add(list.size() - order[i], i+1);
            }
        }

        // 빠른출력을 위해 StringBuilder 사용
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(" ");
        }

        // 출력
        System.out.println(sb);
    }   
}
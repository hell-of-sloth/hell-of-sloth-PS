import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

/**
 * Main_17413_단어뒤집기2
 * 난이도는 4/10 정도
 * 구현, 문자열, 스택
 * <>와 ' '시 처리를 해줘야되는데 이부분을 주의해야함
 * 스택을 이용하여 일반 문자는 스택에 저장하여 공백이나 <를 만나면 pop하여 sb에 넣으면 뒤집어져서 넣어짐
 * <> 사이 문자는 그대로 sb에 넣으면 됨
 * 주의 - 공백이나 <를 만나면 스택에 있는 문자열을 모두 빼서 sb에 넣어야함
 * for문 끝나면 잊지말고 스택에 있는 문자열을 모두 빼서 sb에 넣기
 */
public class Main_17413_단어뒤집기2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  // 빠른 입력을 위한 BufferedReader
    static StringBuilder sb = new StringBuilder();              // 빠른 출력을 위한 StringBuilder
    static ArrayDeque<Character> deque = new ArrayDeque<>();    // 끝에서 push, pop시 시간복잡도 O(1)
    public static void main(String[] args) throws Exception {
        String input = br.readLine();   // 입력 문자열

        // 문자열 길이 만큼 반복
        for (int i = 0; i < input.length(); i++) {

            // '<' 문자열이 나오면 '>' 문자열이 나올 때까지 반복
            if (input.charAt(i) == '<') {

                // 덱에 있는 문자열을 모두 빼서 뒤집어서 StringBuilder에 저장
                while (!deque.isEmpty()) {
                    sb.append(deque.pollLast());
                }

                // 임시 인덱스 변수를 만들어서 '>' 문자열이 나올 때까지 반복
                int temp_index = i;
                sb.append(input.charAt(i)); // '<' 문자열 저장

                // '>' 문자열이 나올 때까지 반복
                while (input.charAt(temp_index) != '>') {
                    temp_index++;                           // 인덱스 증가
                    sb.append(input.charAt(temp_index));    // StringBuilder에 저장
                }
                i = temp_index;                             // i에 temp_index 저장 -> for 문 돌면 다음 문자열부터 시작

            } else if (input.charAt(i) == ' ') {            // 공백 문자열이 나오면
                
                // 덱에 있는 문자열을 모두 빼서 뒤집어서 StringBuilder에 저장
                while (!deque.isEmpty()) {
                    sb.append(deque.pollLast());
                }

                // 공백 문자열 저장
                sb.append(' ');
            } else {                                // 나머지 문자열은 덱에 저장
                deque.add(input.charAt(i));
            }
        }

        // for문이 끝나면 덱에 남아있는 문자열을 모두 빼서 뒤집어서 StringBuilder에 저장
        while (!deque.isEmpty()) {
            sb.append(deque.pollLast());
        }

        // 결과 출력
        System.out.println(sb.toString());
    }
}

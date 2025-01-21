/**
 * 직각삼각형 모양의 숫자 출력하는 클래스
 */
public class DigitTest1 {

	public static void main(String[] args) {
		int temp = 1;                               // 출력할 숫자

        // 5줄 출력
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (i > j) {                        // 직각삼각형 모양으로 출력, 행 보다 열이 크거나 같을때만 숫자 출력
					System.out.print("   ");
				} else {
					System.out.printf("%2d ", temp);
					temp++;                         // 숫자 출력했으면 다음에 출력할 수 증가
				}
			}
			System.out.println();                   // 줄바꿈
		}
	}

}

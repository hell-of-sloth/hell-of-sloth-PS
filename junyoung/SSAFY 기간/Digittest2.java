    
public class Digittest2 {

    public static void main(String[] args) {
        
        int temp = 1;                       // 출력할 숫자
        int criterium = 0;                  // 기준점
        for (int i = 0; i < 5; i++) {

            // 중간을 기준으로 증가하다가 감소하는 패턴
            // 0 1 2 1 0
            if (i < 3) {                    // 0 1 2
                criterium = i;
            } else {                        // 1 0
                criterium = 4 - i;
            }

            // 5개씩 출력
            for (int j = 0; j < 5; j++) {

                // 기준점을 기준으로 출력
                if ( j < criterium || j > 4 - criterium) {  // 기준점을 기준으로 공백과 숫자 출력
                    System.out.print("   ");
                } else {                                    // 숫자 출력
                    System.out.printf("%-2d ", temp);
                    temp++;                                 // 숫자 출력했으면 다음에 출력할숫자 증가 
                }
            }
            System.out.println();                           // 줄바꿈
        }
    }
}
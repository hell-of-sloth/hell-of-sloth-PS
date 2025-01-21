// 예외 입력은 없다고 가정

import java.io.BufferedReader;
import java.io.InputStreamReader;

class GameTest {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));    // 빠른 입력을 위한 BufferedReader
    public static void main(String[] args) throws Exception {
        System.out.print("번호를 입력하세요. ");            // 1: 5판 3선승, 2: 3판 2선승, 3: 1판 1선승
        int N = Integer.parseInt(br.readLine());            // 게임 선택
        int humanWin = 0;                                   // 인간 승리 횟수
        int computerWin = 0;                                // 컴퓨터 승리 횟수
        
        // 게임 진행
        if (N == 1) {                                       // 5판 3선승 일때 분기
            for (int i = 0; i < 5; i++) {
                if (game() == 1) {                          // 게임 결과가 1이면 인간 승리
                    humanWin++;
                } else if (game() == -1) {                  // 게임 결과가 -1이면 컴퓨터 승리
                    computerWin++;
                }

                // 3선승이면 게임 종료
                if (humanWin == 3 || computerWin == 3) {    // 누구든 3선승이면 게임 종료
                    break;
                }
            }
        } else if (N == 2) {                                // 3판 2선승 일때 분기
            for (int i = 0; i < 3; i++) {
                if (game() == 1) {                          // 게임 결과가 1이면 인간 승리
                    humanWin++;
                } else if (game() == -1) {                  // 게임 결과가 -1이면 컴퓨터 승리
                    computerWin++;
                }

                // 2선승이면 게임 종료
                if (humanWin == 2 || computerWin == 2) {    // 누구든 2선승이면 게임 종료
                    break;
                }
            }
        } else {
            for (int i = 0; i < 1; i++) {                   // 1판 1선승 일때 분기
                if (game() == 1) {                          // 게임 결과가 1이면 인간 승리
                    humanWin++;
                } else if (game() == -1) {                  // 게임 결과가 -1이면 컴퓨터 승리
                    computerWin++;  
                }

                // 한 판이면 게임 종료
            }
        }

        // 최종 결과 출력
        if (humanWin > computerWin) {                           // 인간 승리 횟수가 컴퓨터 승리 횟수보다 많으면 인간 승리
            System.out.println("### 인간 승!!");
        } else if (humanWin < computerWin) {                    // 반대로 컴퓨터 승리 횟수가 인간 승리 횟수보다 많으면 컴퓨터 승리
            System.out.println("### 컴퓨터 승!!");
        } else {
            System.out.println("### 무승부!!");                // 둘 다 같으면 무승부
        }
    }

    // 가위바위보 게임 메서드
    public static int game() throws Exception {
        int computer = (int) (Math.random() * 3) +1;            // 컴퓨터 가위바위보, 랜덤으로 구현
        System.out.print("가위바위보 중 하나 입력:");           // 인간 가위바위보 입력
        String human = br.readLine();                           // 인간 가위바위보 입력
        
        // 가위바위보 결과 출력
        // 리턴값 1: 인간 승, 0: 비김, -1: 컴퓨터 승
        if (computer == 1) {                            // 컴퓨터 가위일때
            if (human.equals("가위")) {             // 인간 가위일때
                System.out.println("비겼습니다!!");         // 비겼을때
                return 0;
            } else if (human.equals("바위")) {      // 인간 바위일때
                System.out.println("이겼습니다!!");         // 이겼을때    
                return 1;   
            } else {                                         // 인간 보일때
                System.out.println("졌습니다!!");           // 졌을때
                return -1;
            }
        } else if (computer == 2) {                     // 컴퓨터 바위일때
            if (human.equals("가위")) {             // 인간 가위일때
                System.out.println("졌습니다!!");           // 졌을때
                return -1;
            } else if (human.equals("바위")) {      // 인간 바위일때
                System.out.println("비겼습니다!!");         // 비겼을때
                return 0;
            } else {                                        // 인간 보일때
                System.out.println("이겼습니다!!");        // 이겼을때
                return 1;
            }
        } else {                                        // 컴퓨터 보일때
            if (human.equals("가위")) {             // 인간 가위일때
                System.out.println("이겼습니다!!");         // 이겼을때
                return 1;
            } else if (human.equals("바위")) {      // 인간 바위일때
                System.out.println("졌습니다!!");           // 졌을때
                return -1;
            } else {                                        // 인간 보일때
                System.out.println("비겼습니다!!");         // 비겼을때
                return 0;
            }
        }
    }
}

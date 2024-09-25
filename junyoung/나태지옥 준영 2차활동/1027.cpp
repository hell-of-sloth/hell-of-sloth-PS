/*
세준시에는 고층 빌딩이 많다.
세준시의 서민 김지민은 가장 많은 고층 빌딩이 보이는 고층 빌딩을 찾으려고 한다. 
빌딩은 총 N개가 있는데, 빌딩은 선분으로 나타낸다. i번째 빌딩 (1부터 시작)은 
(i,0)부터 (i,높이)의 선분으로 나타낼 수 있다. 고층 빌딩 A에서 다른 고층 빌딩 
B가 볼 수 있는 빌딩이 되려면, 두 지붕을 잇는 선분이 A와 B를 제외한 다른 고층 
빌딩을 지나거나 접하지 않아야 한다. 가장 많은 고층 빌딩이 보이는 빌딩을 구하고, 
거기서 보이는 빌딩의 수를 출력하는 프로그램을 작성하시오.
*/

#include <iostream>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int N;
    cin >> N;

    int building[N];
    for (int i = 0; i < N; i++) {
        cin >> building[i];
    }

    int result = 0;

    for (int i = 0; i < N; i++) {
        int max_building = 0;
        
        int temp_building = building[i];

        double left_max;
        double l_incline;
        for (int k = i - 1; k > -1 ; k--) {
            l_incline = ((double)building[k] - (double)temp_building) / ((double)i - (double)k);
            if (k == i - 1) {
                max_building++;
                left_max = l_incline;
            }
            else {
                if (l_incline > left_max) {
                    max_building++;
                    left_max = l_incline;
                }
            }
        }

        double right_max;
        double r_incline;
        for (int k = i + 1; k < N; k++) {
            r_incline = ((double)building[k] - (double)temp_building) / ((double)k - (double)i);
            if (k == i + 1) {
                max_building++;
                right_max = r_incline;
            }
            else {
                if (r_incline > right_max) {
                    max_building++;
                    right_max = r_incline;
                }
            }
        }

        if (max_building > result) {
            result = max_building;
        }
    }

    cout << result;

    return 0;
}
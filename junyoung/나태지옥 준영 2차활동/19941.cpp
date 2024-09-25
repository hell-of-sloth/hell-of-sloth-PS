/*
식탁의 길이 N, 햄버거를 선택할 수 있는 거리 K, 
사람과 햄버거의 위치가 주어졌을 때, 
햄버거를 먹을 수 있는 사람의 최대 수를 구하는 프로그램을 작성하시오.
*/

#include <iostream>
#include <string>

using namespace std;

int main() {

    int N, K;

    cin >> N >> K;

    string burger;
    int count = 0;
    int eaten[N] = {0,};

    cin >> burger;

    for (int i = 0; i < N; i++) {
        if (burger[i] == 'P') {
            for (int j = i - K; j <= i + K; j++) {
                if (j < 0 || j >= N) continue;
                if (burger[j] == 'H' && eaten[j] == 0) {
                    eaten[j] = 1;
                    count++;
                    break;
                }
            }
        }
    }

    cout << count;

    return 0;
}
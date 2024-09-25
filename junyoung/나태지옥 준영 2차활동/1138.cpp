/*
사람들은 자기보다 큰 사람이 왼쪽에 몇 명 있었는지만을 기억한다. 
N명의 사람이 있고, 사람들의 키는 1부터 N까지 모두 다르다.

각 사람들이 기억하는 정보가 주어질 때, 줄을 어떻게 서야 하는지 출력하는 
프로그램을 작성하시오.
*/

#include <iostream>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int N;
    cin >> N;

    int key[N];
    for (int i = 0; i < N; i++) {
        cin >> key[i];
    }

    int result[N] = {0, };

    for (int i = 0; i < N; i++) {
        int cnt = 0;
        for (int j = 0; j < N; j++) {
            if (result[j] == 0 && cnt == key[i]) {
                result[j] = i + 1;
                break;
            }
            if (result[j] == 0) {
                cnt++;
            }
        }
    }

    for (int i = 0; i < N; i++) {
        cout << result[i] << " ";
    }

    return 0;
}
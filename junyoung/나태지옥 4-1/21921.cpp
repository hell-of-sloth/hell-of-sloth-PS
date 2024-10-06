/*
찬솔이는 
X일 동안 가장 많이 들어온 방문자 수와 그 기간들을 알고 싶다.

찬솔이를 대신해서 
X일 동안 가장 많이 들어온 방문자 수와 기간이 몇 개 있는지 구해주자.
*/

#include <iostream>
#include <algorithm>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int N, X;
    cin >> N >> X;

    int visitors[N];
    for (int i = 0; i < N; i++) {
        cin >> visitors[i];
    }

    int max_sum = 0;
    int terms = 1;
    int sum_visitors = 0;

    for (int i = 0; i < N - X + 1; i++) {
        if (i == 0) {
            for (int j = 0; j < X; j++) {
                sum_visitors += visitors[j];
            }
            max_sum = sum_visitors;
        } 
        else {
            sum_visitors = sum_visitors - visitors[i - 1] + visitors[i + X - 1];


            if (sum_visitors > max_sum) {
                max_sum = sum_visitors;
                terms = 1;
            } 
            else if (sum_visitors == max_sum) {
                terms++;
            }
        }
    }

    if (max_sum == 0) {
        cout << "SAD";
    } 
    else {
        cout << max_sum << "\n" << terms;
    }

    return 0;
}
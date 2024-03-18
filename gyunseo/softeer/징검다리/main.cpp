#include <algorithm>
#include <iostream>
#define endl '\n'

using namespace std;
const int MAX = 3000;
int N, arr[MAX + 1], dp[MAX + 1];

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N;
    for (int i = 1; i <= N; i++)
        cin >> arr[i];
    // f(i) = i번째 돌을 마지막으로 밟았을 때 철수가 최대로 밟았던 돌의 개수
    // LIS
    fill(dp, dp + MAX + 1, 1);
    // 역순으로 돌 키를 순회하면서 자기보다 키작은 돌 중에서 가장 큰 함수
    // 반환값을 갖는 걸 고르면 된다
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j < i; j++) {
            if (arr[j] < arr[i]) {
                dp[i] = max(dp[j] + 1, dp[i]);
            }
        }
    }
    int ans = -1;
    // for (int i = 1; i <= N; i++)
    // cout << dp[i] << " ";
    // cout << endl;
    for (int i = 1; i <= N; i++) {
        if (dp[i] > ans)
            ans = dp[i];
    }
    cout << ans << endl;
}

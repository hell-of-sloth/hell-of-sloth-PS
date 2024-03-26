#include <bits/stdc++.h>

#define endl '\n'
#define VI vector<int>
#define watch(x) cout << (#x) << " is " << x << endl
#define FOR(s, e) for (int i = s; i < e; i++)
using namespace std;
using ll = long long;
#define VL vector<long long>

const int MAX = 200000;
const ll INF = (int)1e18;
int N;
string S;
// dp table은 i번째 문자가 k로 되고, j만큼의 adjacent character pair가 있을 때
// min cost table이다
ll dp[MAX + 1][2][2];
VL costs;

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N;
    cin.ignore();
    getline(cin, S);
    for (int i = 0; i < N; i++) {
        int cost;
        cin >> cost;
        costs.push_back(cost);
    }

    fill(&dp[0][0][0], &dp[MAX - 1][1][2], INF);
    dp[0][0][S[0] - '0'] = 0;
    dp[0][0][(S[0] - '0') ^ 1] = costs[0];

    FOR(1, N) {
        int cur_digit = S[i] - '0';
        for (int k = 0; k < 2; k++) {
            dp[i][0][k] = dp[i - 1][0][k ^ 1] + (cur_digit == k ? 0 : costs[i]);
            dp[i][1][k] = min(dp[i - 1][0][k], dp[i - 1][1][k ^ 1]) +
                          (cur_digit == k ? 0 : costs[i]);
        }
    }
    cout << min(dp[N - 1][1][0], dp[N - 1][1][1]) << endl;
    return 0;
}
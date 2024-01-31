#include <iostream>
#include <cstring>
#include <algorithm>
#include <cassert>
#define endl '\n'
using namespace std;
const int MAX = (int)1e6;
int N, ans = (int)1e9;
int dp[MAX + 1];
void DP(int n, int level) {
  if (n == 1) {
    if (level < ans) ans = level;
    return;
  }
  if (n % 3 == 0) {
    DP(n / 3, level + 1);
  }
  if (n % 2 == 0) { 
    DP(n / 2, level + 1);
  }
  DP(--n, level + 1);
}
void Solve() {
  fill(dp, dp + (MAX + 1), MAX);
  dp[1] = 0;
  dp[2] = 1;
  dp[3] = 1;
  for (int i = 4; i <= MAX; i++) {
    if (i % 2 == 0 && dp[i/2] != MAX) {
      dp[i] = min(dp[i], dp[i/2] + 1);
      
    }
    if (i % 3 == 0 && dp[i/3] != MAX) {
      dp[i] = min(dp[i], dp[i/3] + 1);
      
    }
    if (dp[i - 1] != MAX) {
      dp[i] = min(dp[i], dp[i - 1] + 1);
      
    }
    assert(1);
  }
  cout << dp[N] << endl;
}
void ReadUserInput() {
  cin >> N;
}
int main() {
  ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
  ReadUserInput();
  Solve();
  return 0;
}

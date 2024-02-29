#include <bits/stdc++.h>
using namespace std;

int cnt;
queue<pair<int, int>> Q;

void hanoi(int N, int from, int to) {
  // if (N == 1) {
  //   cnt++;
  //   Q.push({from, to});
  //   return;
  // }
  if (N == 0) return;
  hanoi(N - 1, from, 6 - from - to);
  // 마지막 옴기기
  cnt++;
  Q.push({from, to});
  hanoi(N - 1, 6 - from - to, to);
}

int main() {
  // ios::sync_with_stdio(0);
  // cin.tie(0);
  int n;
  cin >> n;
  hanoi(n, 1, 3);
  cout << cnt << '\n';
  while (!Q.empty()) {
    cout << Q.front().first << ' ' << Q.front().second << '\n';
    Q.pop();
  }
}
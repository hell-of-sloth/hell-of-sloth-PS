#include <bits/stdc++.h>
using namespace std;

deque<int> Q;

int field[100002];
int vis[100002];

int Xs[] = {1, 0, -1, 0};
int Ys[] = {0, 1, 0, -1};

int main() {
  // ios::sync_with_stdio(0);
  // cin.tie(0);

  int a, b;
  cin >> a >> b;  // a에서 b로 가는 것이 목표

  if (a == b) {
    cout << 0;
    return 0;
  }
  Q.push_back(a);
  vis[a] = 1;

  while (!Q.empty()) {
    int cur = Q.front();
    Q.pop_front();

    for (int x : {cur * 2, cur + 1, cur - 1}) {
      if (x < 0 || x > 100000) continue;  // x<0 안해서 틀림 ㅠㅠ
      if (vis[x] != 0 && vis[x] <= vis[cur]) continue;
      if (x == cur * 2) {
        Q.push_front(x);
        vis[x] = vis[cur];
      } else {
        Q.push_back(x);
        vis[x] = vis[cur] + 1;
      }
    }
  }
  cout << vis[b] - 1;
}
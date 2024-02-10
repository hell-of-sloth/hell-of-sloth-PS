#include <bits/stdc++.h>
using namespace std;
#define X first
#define Y second

int Xs[] = {-1, 0, 1, 0};
int Ys[] = {0, 1, 0, -1};

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);

  int N, M;
  cin >> M >> N;
  int field[1002][1002];  // 대신 string field[102]도 가능
  queue<pair<int, int>> Q;
  bool iszero = false;
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < M; j++) {
      cin >> field[i][j];
      if (field[i][j] == 0)
        iszero = true;
      else if (field[i][j] == 1)
        Q.push({i, j});
    }
  }
  if (!iszero) {  // 기본을 하루로 잡음 문제 특성상 답에 -1 해줘야함
    cout << 0;
    return 0;
  }
  while (!Q.empty()) {
    pair<int, int> cur = Q.front();
    Q.pop();
    for (int i = 0; i < 4; i++) {
      int x = cur.X + Xs[i];
      int y = cur.Y + Ys[i];
      if (x < 0 || x >= N || y < 0 || y >= M) continue;
      if (field[x][y] != 0) continue;
      Q.push({x, y});
      field[x][y] = field[cur.X][cur.Y] + 1;
    }
  }

  int mx = 0;
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < M; j++) {
      if (field[i][j] == 0) {
        cout << -1;
        return 0;
      } else
        mx = max(field[i][j], mx);
    }
  }
  cout << mx - 1;
}
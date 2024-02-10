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
  cin >> N >> M;
  int field[102][102];  // 대신 string field[102]도 가능
  string line;
  for (int i = 0; i < N; i++) {
    cin >> line;
    for (int j = 0; j < M; j++) {
      field[i][j] = (line[j] - '0') ? 0 : -1;
    }
  }
  queue<pair<int, int>> Q;
  Q.push({0, 0});
  field[0][0] = 1;
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
  cout << field[N - 1][M - 1];
}
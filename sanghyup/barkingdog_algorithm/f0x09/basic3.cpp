#include <bits/stdc++.h>
using namespace std;

int field[102][102][102];
int dis[102][102][102];

int Xs[] = {1, 0, 0, 0, 0, -1};
int Ys[] = {0, 1, -1, 0, 0, 0};
int Zs[] = {0, 0, 0, 1, -1, 0};

queue<tuple<int, int, int>> Q;

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);
  int H, N, M;
  cin >> M >> N >> H;
  for (int i = 0; i < H; i++) {
    for (int j = 0; j < N; j++) {
      fill(dis[i][j], dis[i][j] + 101, -1);
    }
  }
  for (int i = 0; i < H; i++) {
    for (int j = 0; j < N; j++) {
      for (int k = 0; k < M; k++) {
        cin >> field[i][j][k];
        if (field[i][j][k] == 1) {
          Q.push({i, j, k});
          dis[i][j][k] = 0;
        }
      }
    }
  }

  while (!Q.empty()) {
    int cx, cy, cz;  // x 높이, y 행, z 열
    tie(cx, cy, cz) = Q.front();
    Q.pop();
    for (int i = 0; i < 6; i++) {
      int x = cx + Xs[i];
      int y = cy + Ys[i];
      int z = cz + Zs[i];
      if (x < 0 || y < 0 || z < 0 || x >= H || y >= N || z >= M) continue;
      if (field[x][y][z] != 0 || dis[x][y][z] != -1) continue;
      Q.push({x, y, z});
      dis[x][y][z] = dis[cx][cy][cz] + 1;
    }
  }
  int count = 0;
  for (int i = 0; i < H; i++) {
    for (int j = 0; j < N; j++) {
      for (int k = 0; k < M; k++) {
        if (dis[i][j][k] == -1 && field[i][j][k] == 0) {
          cout << -1;
          return 0;
        }
        count = max(dis[i][j][k], count);
      }
    }
  }
  cout << count;
}
#include <bits/stdc++.h>
using namespace std;

int field[202][202];
int dis[202][202][32];

queue<tuple<int, int, int>> Q;

int Xs[] = {0, 1, 0, -1, 1, 1, 2, 2, -1, -1, -2, -2};
int Ys[] = {1, 0, -1, 0, 2, -2, 1, -1, 2, -2, 1, -1};
int Zs[] = {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1};

int main() {
  // ios::sync_with_stdio(0);
  // cin.tie(0);
  int H, N, M;
  cin >> H >> M >> N;
  H += 1;
  // if (M == 1 && N == 1) { //61~64 코드 사용시 필요
  //   cout << 0;
  //   return 0;
  // }

  for (int i = 0; i < N; i++) {
    for (int j = 0; j < M; j++) {
      cin >> field[i][j];
    }
  }

  Q.push({0, 0, 0});
  dis[0][0][0] = 1;

  while (!Q.empty()) {
    // for (int i = 0; i < N; i++) {
    //   for (int k = 0; k < H; k++) {
    //     for (int j = 0; j < M; j++) {
    //       cout << dis[i][j][k] << ' ';
    //     }
    //     cout << "   ";
    //   }
    //   cout << '\n';
    // }
    // cout << '\n';

    int cx, cy, cz;
    tie(cx, cy, cz) = Q.front();
    Q.pop();
    if (cx == N - 1 && cy == M - 1) {
      cout << dis[cx][cy][cz] - 1;
      return 0;
    }

    for (int i = 0; i < 12; i++) {
      int x = cx + Xs[i];
      int y = cy + Ys[i];
      int z = cz + Zs[i];

      if (x < 0 || y < 0 || z < 0 || x >= N || y >= M || z >= H) continue;
      if (field[x][y] == 1) continue;
      if (dis[x][y][z] != 0) continue;
      // if (x == (N - 1) && y == (M - 1)) { //19~21 코드 필요
      //   cout << dis[cx][cy][cz];
      //   return 0;
      // }
      dis[x][y][z] = dis[cx][cy][cz] + 1;
      Q.push({x, y, z});
    }
  }
  cout << -1;
}
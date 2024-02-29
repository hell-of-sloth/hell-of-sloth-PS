#include <bits/stdc++.h>
using namespace std;

int Xs[] = {1, 0, -1, 0};
int Ys[] = {0, 1, 0, -1};

int field[102][102];
int vis[102][102];
int N;
queue<pair<int, int>> EQ;  // edges

void clearvis() {
  for (int i = 0; i < 102; i++) {
    fill(vis[i], vis[i] + 102, 0);
  }
}

void findedges() {  // 엣지 그룹은 2부터 시작함
  int groupN = 1;
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++) {
      if (field[i][j] == 1) {
        groupN++;
        clearvis();
        queue<pair<int, int>> Q;
        Q.push({i, j});
        vis[i][j] = 1;
        while (!Q.empty()) {
          int cx, cy;
          tie(cx, cy) = Q.front();
          Q.pop();

          for (int i = 0; i < 4; i++) {
            int x = cx + Xs[i];
            int y = cy + Ys[i];

            if (x < 0 || y < 0 || x >= N || y >= N) continue;
            if (field[x][y] == 0) {
              field[cx][cy] = groupN;
              EQ.push({cx, cy});
              continue;
            }
            if (vis[x][y]) continue;
            Q.push({x, y});
            vis[x][y] = 1;
          }
          if (field[cx][cy] == 1) field[cx][cy] = -1;
        }
      }
    }
  }
}
int main() {
  // ios::sync_with_stdio(0);
  // cin.tie(0);
  cin >> N;
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++) cin >> field[i][j];
  }
  // 입력 완료

  findedges();

  int shortest = 100000;

  while (!EQ.empty()) {
    queue<pair<int, int>> Q;
    clearvis();
    int cx, cy;
    tie(cx, cy) = EQ.front();
    EQ.pop();
    Q.push({cx, cy});
    vis[cx][cy] = 1;  // 시작 자리 포함 나중에 빼주기
    int groupN = field[cx][cy];
    while (!Q.empty()) {
      tie(cx, cy) = Q.front();
      Q.pop();
      for (int i = 0; i < 4; i++) {
        int x = cx + Xs[i];
        int y = cy + Ys[i];

        if (x < 0 || y < 0 || x >= N || y >= N) continue;
        if (vis[x][y] > 0) continue;
        if (field[x][y] < 0) continue;
        if (field[x][y] != 0 && field[x][y] != groupN) {
          shortest = min(shortest, vis[cx][cy] - 1);
          break;
        }
        vis[x][y] = vis[cx][cy] + 1;
        Q.push({x, y});
      }
    }
  }
  cout << shortest;
}
// 이문제는 항상 빙산이 내부에 있어서 상관 없지만 DFS 탐색시 범위 초과 조건 항상
// 생각하기!

#include <bits/stdc++.h>
using namespace std;

int Xs[] = {1, 0, -1, 0};
int Ys[] = {0, 1, 0, -1};

int field[302][302];
int ice[302][302];
int vis[302][302];

int main() {
  // ios::sync_with_stdio(0);
  // cin.tie(0);
  int N, M;
  cin >> N >> M;

  int tcount = 0;
  pair<int, int> start = {0, 0};
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < M; j++) {
      cin >> field[i][j];
      if (field[i][j] != 0) {
        ice[i][j] = 1;
        tcount++;
      }
      if (start.first == 0 && field[i][j] != 0) {
        start = {i, j};
      }
    }
  }
  int year = 0;
  bool changedstart = false;
  // 입력완료

  while (true) {
    int count = 0;

    queue<pair<int, int>> buff;
    // BFS
    queue<pair<int, int>> Q;
    Q.push(start);
    vis[start.first][start.second]++;  // 만약 year보타 크면 visited
    while (!Q.empty()) {
      pair<int, int> cur = Q.front();
      Q.pop();
      count++;
      int cx, cy;
      tie(cx, cy) = cur;
      for (int i = 0; i < 4; i++) {
        int x = cx + Xs[i];
        int y = cy + Ys[i];

        if (!ice[x][y]) {
          field[cx][cy]--;
          continue;
        }
        if (ice[x][y] && vis[x][y] > year) continue;
        vis[x][y]++;
        Q.push({x, y});
      }
      if (field[cx][cy] <= 0) {
        field[cx][cy] = 0;
        buff.push({cx, cy});
      } else {
        if (!changedstart) {
          start = {cx, cy};
          changedstart = true;
        }
      }
    }

    if (!tcount) {
      cout << 0;
      return 0;
    }
    if (count != tcount) break;
    year++;
    changedstart = false;

    while (!buff.empty()) {
      ice[buff.front().first][buff.front().second] = 0;
      buff.pop();
      tcount--;
    }
  }
  cout << year;
}
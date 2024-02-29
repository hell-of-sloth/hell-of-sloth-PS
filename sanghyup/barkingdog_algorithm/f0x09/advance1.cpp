#include <bits/stdc++.h>
using namespace std;

int Xs[] = {1, 0, -1, 0};
int Ys[] = {0, 1, 0, -1};

int N, M;

string field[1002];
int dis[1002][1002][2];

queue<tuple<int, int, int>> Q;

// 1차원(!broken)의 경우 모든 방향 push 후 pop할때 위 차원으로 올림
// 2차원의 경우 벽 없는 곳만 push
void BFS(tuple<int, int, int> cur) {
  Q.pop();
  int cx, cy, cz;
  tie(cx, cy, cz) = cur;
  if (cz == 0 && field[cx][cy] == '1') {
    Q.push({cx, cy, 1});
    dis[cx][cy][1] = dis[cx][cy][cz] + 1;
    return;
  }
  for (int i = 0; i < 4; i++) {
    int x = cx + Xs[i];
    int y = cy + Ys[i];
    if (x < 0 || y < 0 || x >= N || y >= M) continue;
    if (cz == 1 && field[x][y] == '1') continue;
    if (dis[x][y][cz] != 0) continue;
    Q.push({x, y, cz});
    dis[x][y][cz] = dis[cx][cy][cz] + 1;
  }
}

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);
  cin >> N >> M;
  for (int i = 0; i < N; i++) {
    cin >> field[i];
  }

  Q.push({0, 0, 0});
  dis[0][0][0] = 1;
  while (!Q.empty()) {
    BFS(Q.front());
  }
  if (dis[N - 1][M - 1][0] == 0 && dis[N - 1][M - 1][1] == 0)
    cout << -1;
  else if (dis[N - 1][M - 1][0] == 0)
    cout << dis[N - 1][M - 1][1] - 1;
  else if (dis[N - 1][M - 1][1] ==
           0)  // 아예 위 차원으로 올라가지 못하는 경우(all 0) 때문에 필요
    cout << dis[N - 1][M - 1][0];
  else
    cout << min(dis[N - 1][M - 1][0], dis[N - 1][M - 1][1] - 1);
}

// 기존 접근
/*
#include <bits/stdc++.h>
using namespace std;

int Xs[] = {1, 0, -1, 0};
int Ys[] = {0, 1, 0, -1};

int N, M;

string field[1002];
int dis[1002][1002];
queue<tuple<int, int, bool>> Q;
queue<tuple<int, int, bool>> buff;

void DFS(tuple<int, int, bool> cur) {
  // for (int i = 0; i < N; i++) {
  //   for (int j = 0; j < M; j++) {
  //     cout << setw(2) << dis[i][j];
  //   }
  //   cout << '\n';
  // }
  // cout << '\n';

  int cx, cy;
  bool iswall;
  tie(cx, cy, iswall) = cur;
  for (int i = 0; i < 4; i++) {
    int x = cx + Xs[i];
    int y = cy + Ys[i];

    if (x < 0 || y < 0 || x >= N || y >= M) continue;
    if (iswall && field[x][y] == '1') continue;
    if (dis[x][y] != -1) continue;

    if (field[x][y] == '0') {
      dis[x][y] = dis[cx][cy] + 1;
      Q.push({x, y, dis[cx][cy]});
    }
    if (field[x][y] == '1') {
      dis[x][y] = dis[cx][cy] + 2;
      buff.push({x, y, true});
    }
  }
}

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);
  cin >> N >> M;
  for (int i = 0; i < N; i++) {
    cin >> field[i];
  }
  for (int i = 0; i < N; i++) {
    fill(dis[i], dis[i] + M, -1);
  }

  Q.push({0, 0, false});
  dis[0][0] = 0;

  while (!Q.empty() || !buff.empty()) {
    while (!buff.empty()) {
      if (Q.empty() || (dis[get<0>(Q.front())][get<1>(Q.front())] ==
                        dis[get<0>(buff.front())][get<1>(buff.front())])) {
        DFS(buff.front());
        buff.pop();
      } else
        break;
    }
    if (!Q.empty()) {
      DFS(Q.front());
      Q.pop();
    }
  }
  cout << dis[N - 1][M - 1];
}

// buff가 차있고, Q는 empty 인 경우에도 실행 해야함! // 벽을 뚫지 않은 경로도
끝까지 살려야함!!

*/
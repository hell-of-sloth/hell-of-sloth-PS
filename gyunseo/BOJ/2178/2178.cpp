#include <iostream>
#include <queue>
#include <tuple>
#define endl '\n'
using namespace std;
const int MAX = 100;
queue<pair<int, int>> q;
string board[MAX];
// dist가 is_visited의 역할도 같이한다.
int dist[MAX][MAX];
int di[4] = {-1, 1, 0, 0};
int dj[4] = {0, 0, -1, 1};
int N, M;

bool OOB(int i, int j) {
  if (i < 0 || i >= N) return true;
  if (j < 0 || j >= M) return true;
  return false;
}

void ReadUserInput() {
  cin >> N >> M;
  for (int i = 0; i < N; i++) {
    cin >> board[i];
  }
}

void Solve() {
  int cur_i, cur_j;
  q.push({0, 0});
  dist[0][0] = 1;
  while(!q.empty()) {
    tie(cur_i, cur_j) = q.front();
    q.pop();
    if (cur_i == (N - 1) && cur_j == (M - 1)) {
      cout << dist[cur_i][cur_j] << endl;
      return;
    }
    for (int k = 0; k < 4; k++) {
      int next_i, next_j;
      next_i = cur_i + di[k];
      next_j = cur_j + dj[k];
      if (OOB(next_i, next_j)) continue;
      if (board[next_i][next_j] == '0') continue;
      if (dist[next_i][next_j] >= 1) continue;
      dist[next_i][next_j] = dist[cur_i][cur_j] + 1;
      q.push({next_i, next_j});
    }
  }
}
int main() {
  ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
  ReadUserInput();
  Solve();
  return 0;
}

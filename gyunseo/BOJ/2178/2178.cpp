#include <iostream>
#include <queue>
#include <tuple>
#define endl '\n'
using namespace std;
const int MAX = 100;
queue<tuple<int, int, int>> q;
int board[MAX][MAX];
int is_visited[MAX][MAX];
int di[4] = {-1, 1, 0, 0};
int dj[4] = {0, 0, -1, 1};
int N,M;
bool OOB(int i, int j) {
  if (i < 0 || i >= N) return true;
  if (j < 0 || j >= M) return true;
  return false;
}
void ReadUserInput() {
  cin >> N >> M;
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < M; j++) {
      cin >> board[i][j];
    }
  }
}

void Solve() {
  int cur_i, cur_j, cur_cnt;
  q.push(make_tuple(0, 0, 1));
  while(!q.empty()) {
    tie(cur_i, cur_j, cur_cnt) = q.front();
    q.pop();
    is_visited[cur_i][cur_j] = 1;
    if (cur_i == (N - 1) && cur_j == (M - 1)) {
      cout << cur_cnt << endl;
      return;
    }
    for (int k = 0; k < 4; k++) {
      int next_i, next_j;
      next_i = cur_i + di[k];
      next_j = cur_j + dj[k];
      if (OOB(next_i, next_j)) continue;
      if (is_visited[next_i][next_j] == 1) continue;
      if (board[next_i][next_j] == 0) continue;
      q.push(make_tuple(next_i, next_j, cur_cnt + 1));
    }
  }
}
int main() {
  ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
  ReadUserInput();
  Solve();
  return 0;
}

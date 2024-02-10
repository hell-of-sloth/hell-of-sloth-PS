#include <bits/stdc++.h>
using namespace std;

#define X first
#define Y second
int Xs[] = {-1, 0, 1, 0};
int Ys[] = {0, -1, 0, 1};

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);
  int N, M;
  cin >> N >> M;
  int board[502][502];
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < M; j++) {
      cin >> board[i][j];
    }
  }
  int count = 0;
  int lArea = 0;
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < M; j++) {
      if (board[i][j]) {
        queue<pair<int, int>> Q;
        int area = 0;
        board[i][j] = 0;
        Q.push({i, j});
        area++;
        while (!Q.empty()) {
          // area++ //instead of area++ in 29 and 39
          pair<int, int> cur = Q.front();
          Q.pop();
          for (int k = 0; k < 4; k++) {
            int xk = cur.X + Xs[k];
            int yk = cur.Y + Ys[k];
            if (xk < 0 || xk >= N || yk < 0 || yk >= M) continue;
            if (!board[xk][yk]) continue;
            area++;
            Q.push({xk, yk});
            board[xk][yk] = 0;
          }
        }
        lArea = max(lArea, area);
        count++;
      }
    }
  }
  cout << count << '\n' << lArea;
}
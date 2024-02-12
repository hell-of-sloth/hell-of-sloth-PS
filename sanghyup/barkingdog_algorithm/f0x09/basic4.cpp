#include <bits/stdc++.h>
using namespace std;

int Xs[] = {1, 1, 2, 2, -1, -1, -2, -2};
int Ys[] = {2, -2, 1, -1, 2, -2, 1, -1};

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);
  int it;
  cin >> it;
  while (it--) {
    int board[302][302];
    queue<pair<int, int>> Q;
    int I;
    cin >> I;
    for (int i = 0; i < I; i++) {
      fill(board[i], board[i] + I, 0);
    }
    int x, y;
    cin >> x >> y;
    int tx, ty;
    cin >> tx >> ty;

    Q.push({x, y});
    board[x][y] = 1;
    while (!Q.empty()) {
      int cx, cy;
      tie(cx, cy) = Q.front();
      Q.pop();
      if (cx == tx && cy == ty) {
        cout << board[cx][cy] - 1 << '\n';
        break;
      }
      for (int i = 0; i < 8; i++) {
        x = cx + Xs[i];
        y = cy + Ys[i];
        if (x < 0 || y < 0 || x >= I || y >= I) continue;
        if (board[x][y] != 0) continue;
        Q.push({x, y});
        board[x][y] = board[cx][cy] + 1;
      }
    }
  }
}
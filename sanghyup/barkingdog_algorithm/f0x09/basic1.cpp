#include <bits/stdc++.h>
using namespace std;

#define X first
#define Y second

int Xs[] = {1, 0, -1, 0};
int Ys[] = {0, 1, 0, -1};

int main() {
  // ios::sync_with_stdio(0);
  // cin.tie(0);
  int its;
  cin >> its;
  while (its--) {
    int N, M, K;
    cin >> M >> N >> K;
    int field[50][50];
    pair<int, int> input[2501];
    for (int j = 0; j < 50; j++) {
      fill(field[j], field[j] + 50, 0);
    }
    queue<pair<int, int>> Q;
    for (int i = 0; i < K; i++) {
      int a, b;
      cin >> b >> a;
      field[a][b] = 1;
      input[i] = {a, b};
    }
    int count = 0;
    for (int j = 0; j < K; j++) {
      pair<int, int> start = input[j];
      if (field[start.X][start.Y] == 1) {
        Q.push(start);
        field[start.X][start.Y] = 0;
        count++;
        while (!Q.empty()) {
          pair<int, int> cur = Q.front();
          Q.pop();
          for (int i = 0; i < 4; i++) {
            int x = cur.X + Xs[i];
            int y = cur.Y + Ys[i];
            if (x < 0 || y < 0 || x >= N || y >= M) continue;
            if (field[x][y] != 1) continue;
            Q.push({x, y});
            field[x][y] = 0;
          }
        }
      }
    }
    cout << count << '\n';
  }
}
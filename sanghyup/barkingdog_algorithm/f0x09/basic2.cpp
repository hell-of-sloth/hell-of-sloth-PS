#include <bits/stdc++.h>
using namespace std;

#define X first
#define Y second

int Xs[] = {1, 0, -1, 0};
int Ys[] = {0, 1, 0, -1};

string field[100];
int Nvis[100][100];
int Dvis[100][100];
int N;

void Nbfs(pair<int, int> cur, char col) {
  queue<pair<int, int>> Q;
  Q.push(cur);
  Nvis[cur.X][cur.Y] = 1;
  while (!Q.empty()) {
    cur = Q.front();
    Q.pop();
    for (int i = 0; i < 4; i++) {
      int xn = cur.X + Xs[i];
      int yn = cur.Y + Ys[i];
      if (xn < 0 | yn < 0 || xn >= N || yn >= N) continue;
      if (field[xn][yn] != col || Nvis[xn][yn] == 1) continue;
      Q.push({xn, yn});
      Nvis[xn][yn] = 1;
    }
  }
}
void Dbfs(pair<int, int> cur, char col) {
  queue<pair<int, int>> Q;
  Q.push(cur);
  Dvis[cur.X][cur.Y] = 1;
  while (!Q.empty()) {
    cur = Q.front();
    Q.pop();
    for (int i = 0; i < 4; i++) {
      int xn = cur.X + Xs[i];
      int yn = cur.Y + Ys[i];
      if (xn < 0 | yn < 0 || xn >= N || yn >= N) continue;
      if (col == 'B') {
        if (field[xn][yn] != 'B') continue;
      } else {
        if (field[xn][yn] == 'B') continue;
      }
      if (Dvis[xn][yn] == 1) continue;
      Q.push({xn, yn});
      Dvis[xn][yn] = 1;
    }
  }
}

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);
  cin >> N;
  for (int i = 0; i < N; i++) {
    cin >> field[i];
  }

  int Ncount = 0, Dcount = 0;
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++) {
      if (Nvis[i][j] == 0) {
        Nbfs({i, j}, field[i][j]);
        Ncount++;
      }
      // 적록 색약
      if (Dvis[i][j] == 0) {
        Dbfs({i, j}, field[i][j]);
        Dcount++;
      }
    }
  }
  cout << Ncount << ' ' << Dcount;
}
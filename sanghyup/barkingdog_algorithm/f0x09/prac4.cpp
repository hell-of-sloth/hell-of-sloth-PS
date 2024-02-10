#include <bits/stdc++.h>
using namespace std;
#define X first
#define Y second

int Xs[] = {-1, 0, 1, 0};
int Ys[] = {0, -1, 0, 1};
int field[1002][1002];
int burnt[1002][1002];

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);
  int N, M;
  cin >> N >> M;
  queue<pair<int, int>> fire;
  queue<pair<int, int>> person;

  // init field and queue
  for (int i = 0; i < N; i++) {
    string line;
    cin >> line;
    for (int j = 0; j < M; j++) {
      if (line[j] == '#') {
        field[i][j] = -1;
        burnt[i][j] = -1;
      } else if (line[j] == 'F') {
        fire.push({i, j});
        burnt[i][j] = -1;
      } else if (line[j] == 'J') {
        person.push({i, j});
        field[i][j] = 1;
      }
    }
  }
  // fire can go if >=0 person can go only 0;
  int fcount = -1, pcount = 1;  // fcount-- pcount++
  while (!person.empty()) {
    // fire
    while (!fire.empty()) {
      pair<int, int> fcur = fire.front();
      if (burnt[fcur.X][fcur.Y] != fcount) {
        fcount--;
        break;
      }
      fire.pop();

      for (int i = 0; i < 4; i++) {
        int x = fcur.X + Xs[i];
        int y = fcur.Y + Ys[i];
        if (x < 0 || x >= N || y < 0 || y >= M) continue;
        if (burnt[x][y] < 0) continue;
        fire.push({x, y});
        burnt[x][y] = burnt[fcur.X][fcur.Y] - 1;
      }
    }
    // person
    while (!person.empty()) {
      pair<int, int> pcur = person.front();
      if (field[pcur.X][pcur.Y] != pcount) {
        pcount++;
        break;
      }
      person.pop();
      if (pcur.X == 0 || pcur.X == N - 1 || pcur.Y == 0 || pcur.Y == M - 1) {
        cout << field[pcur.X][pcur.Y];
        return 0;
      }

      for (int i = 0; i < 4; i++) {
        int x = pcur.X + Xs[i];
        int y = pcur.Y + Ys[i];
        if (x < 0 || x >= N || y < 0 || y >= M) continue;
        if (field[x][y] != 0 || burnt[x][y] < 0) continue;
        person.push({x, y});
        field[x][y] = field[pcur.X][pcur.Y] + 1;
      }
    }
  }
  cout << "IMPOSSIBLE";
}
#include <bits/stdc++.h>
using namespace std;

int base[2][2];

int zOrder(int N, int x, int y) {
  if (N == 1) {
    return (base[x][y]);
  }
  int sep = (1 << (N - 1));
  if (x < sep) {
    if (y < sep)
      return zOrder(N - 1, x, y);
    else
      return sep * sep + zOrder(N - 1, x, y - sep);
  } else {
    if (y < sep)
      return 2 * sep * sep + zOrder(N - 1, x - sep, y);
    else
      return 3 * sep * sep + zOrder(N - 1, x - sep, y - sep);
  }
}

int main() {
  // ios::sync_with_stdio(0);
  // cin.tie(0);
  int tmp = 0;
  for (int i = 0; i < 2; i++) {
    for (int j = 0; j < 2; j++) {
      base[i][j] = tmp++;
    }
  }
  int N, x, y;
  cin >> N >> x >> y;
  cout << zOrder(N, x, y);
}
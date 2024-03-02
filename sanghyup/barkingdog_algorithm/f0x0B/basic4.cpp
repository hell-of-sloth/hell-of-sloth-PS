#include <bits/stdc++.h>
using namespace std;

int field[65][65];

bool issame(int x, int y, int N) {
  int base = field[x][y];
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++) {
      if (field[x + i][y + j] != base) return false;
    }
  }
  return true;
}

void compress(int x, int y, int N) {
  if (issame(x, y, N)) {
    cout << field[x][y];
  } else {
    cout << '(';
    int half = N / 2;
    compress(x, y, half);
    compress(x, y + half, half);
    compress(x + half, y, half);
    compress(x + half, y + half, half);
    cout << ')';
  }
}

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);
  int n;
  cin >> n;
  string input;
  for (int i = 0; i < n; i++) {
    cin >> input;
    for (int j = 0; j < n; j++) {
      field[i][j] = input[j] - '0';
    }
  }

  compress(0, 0, n);
}
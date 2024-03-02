#include <bits/stdc++.h>
using namespace std;

int start;
char field[7000][7000];

void triange(int N) {
  if (N == 3) {
    for (int i = 0; i < 5; i++) {
      field[start][i] = '*';
    }
    field[start - 1][1] = '*';
    field[start - 1][3] = '*';
    field[start - 2][2] = '*';
    return;
  }
  int prev = N / 2;
  triange(prev);
  for (int i = start + 1 - prev; i <= start; i++) {
    for (int j = 0; j < N; j++) {
      field[i - prev][j + prev] = field[i][j];
      field[i][j + N] = field[i][j];
    }
  }
}

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);

  int N;
  cin >> N;
  start = N - 1;

  for (int i = 0; i < N; i++) {
    for (int j = 0; j < 2 * N + 1; j++) {
      field[i][j] = ' ';
    }
  }
  // 설정 끝

  triange(N);

  // 출력
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < 2 * N + 1; j++) {
      cout << field[i][j];
    }
    cout << '\n';
  }
}
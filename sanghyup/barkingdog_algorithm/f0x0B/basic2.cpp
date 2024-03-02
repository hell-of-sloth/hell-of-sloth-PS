#include <bits/stdc++.h>
using namespace std;
int cnt[3];  // -1, 0, 1;
int field[3000][3000];

bool isSame(int x, int y, int N) {
  int base = field[x][y];
  for (int i = 0; i < pow(3, N); i++) {
    for (int j = 0; j < pow(3, N); j++) {
      if (field[x + i][y + j] != base) {
        return false;
      }
    }
  }
  return true;
}

void paper(int x, int y, int N) {
  if (isSame(x, y, N)) {  // cnt[paper[x][y] + 1] += 1; 이렇게 처리 가능
    if (field[x][y] == -1) {
      cnt[0]++;
    } else if (field[x][y] == 0) {
      cnt[1]++;
    } else
      cnt[2]++;
    return;
  }
  for (int i = 0; i < 3; i++) {
    for (int j = 0; j < 3; j++) {
      paper(x + i * (pow(3, N - 1)), y + j * (pow(3, N - 1)), N - 1);
    }
  }
}

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);
  int input;
  cin >> input;
  int N = 0;
  // N = log(input) / log(3); 이거 쓰면 안됨... 아마 float int 변환 rounding
  // 문제인듯
  while (input != 1) {
    N++;
    input /= 3;
  }
  for (int i = 0; i < (pow(3, N)); i++) {
    for (int j = 0; j < (pow(3, N)); j++) {
      cin >> field[i][j];
    }
  }
  paper(0, 0, N);
  for (auto i : cnt) {
    cout << i << '\n';
  }
}
#include <bits/stdc++.h>
using namespace std;

string field[2200];

void pattern(int N) {
  if (N == 1) {
    field[0] = "*";
    return;
  }
  pattern(N / 3);
  string spaces;
  for (int i = 0; i < N / 3; i++) {
    spaces += ' ';
  }
  for (int i = 0; i < N / 3; i++) {
    field[N / 3 + i] += field[i] + spaces + field[i];
  }
  for (int i = 0; i < N / 3; i++) {
    field[2 * N / 3 + i] += field[i] + field[i] + field[i];
  }
  for (int i = 0; i < N / 3; i++) {
    field[i] += field[i] + field[i];
  }
}
int main() {
  // ios::sync_with_stdio(0);
  // cin.tie(0);
  int N;
  cin >> N;
  pattern(N);
  for (int i = 0; i < N; i++) {
    cout << field[i] << '\n';
  }
}

// 풀이 1
/*
// Authored by : BaaaaaaaaaaarkingDog
// Co-authored by : haneulkimdev
// http://boj.kr/338f1dddcb68434fbcf4552bdf7330f7
#include <bits/stdc++.h>
using namespace std;

char board[2300][2300];

// solve(n, x, y) : board[x][y] to board[x+n-1][y+n-1]에 올바르게 '*'과 ' '을
넣는 함수 void solve(int n, int x, int y) { if (n == 1) { board[x][y] = '*';
    return;
  }
  for (int i = 0; i < 3; i++) {
    for (int j = 0; j < 3; j++) {
      if (i == 1 && j == 1)
        continue;
      solve(n / 3, x + n / 3 * i, y + n / 3 * j);
    }
  }
}

int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);

  int n;
  cin >> n;
  for (int i = 0; i < n; i++)
    fill(board[i], board[i]+n, ' ');

  solve(n, 0, 0);
  for (int i = 0; i < n; i++)
    cout << board[i] << '\n';
}
/*
board[...][n]에 NULL 대신 ' '이 들어있을 경우 뒤에 불필요한 공백이 추가로 출력될
수 있음. board는 전역 변수이기 때문에 초기 값이 전부 NULL이어서 잘 동작함.
*/

// 풀이 2
/*
// Authored by : cpprhtn
// Co-authored by : BaaaaaaaaaaarkingDog
// http://boj.kr/1e1d8717951a425c94049401b38053b6
#include <bits/stdc++.h>
using namespace std;

void print_star(int i, int j, int num) {
  if ((i / num) % 3 == 1 && (j / num) % 3 == 1)
    cout << ' ';
  else {
    if (num / 3 == 0)
      cout << '*';
    else
      print_star(i, j, num / 3);
  }
}

int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);
  int num;
  cin >> num;
  for (int i = 0; i < num; i++) {
    for (int j = 0; j < num; j++) print_star(i, j, num);
    cout << '\n';
  }
}
*/
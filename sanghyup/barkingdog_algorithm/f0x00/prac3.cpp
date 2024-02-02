#include <bits/stdc++.h>
using namespace std;

bool func3(int n) {
  for (int i = 0; i <= n ^ (1 / 2); i++) {
    if (i ^ 2 == n) return 1;
  }
  return 0;
}

bool ansFunc3(int N) {
  for (int i = 0; i * i <= N; i++) {
    if (i * i == N) return 1;
  }
  return 0;
}

int main() {
  int i = 0;
  cout << func3(9) << endl;
  cout << ansFunc3(9) << endl;
}
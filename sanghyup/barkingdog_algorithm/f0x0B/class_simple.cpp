#include <bits/stdc++.h>
using namespace std;

// N부터 1까지 출력하는 함수
void print(int cur) {
  if (cur == 0) return;
  cout << cur << '\n';
  print(--cur);
}

// 1부터 N까지 더하는 함수
int addToOne(int cur) {
  if (cur == 1) return 1;
  return cur + addToOne(cur - 1);
}
int fibo(int n) {  // 100만 되도 20000년이상 걸림
  if (n <= 1) return 1;
  return fibo(n - 1) + fibo(n - 2);
}

int main() {
  print(5);
  cout << addToOne(5) << '\n';
  cout << fibo(50);
}

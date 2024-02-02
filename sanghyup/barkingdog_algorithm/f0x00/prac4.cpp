#include <bits/stdc++.h>
using namespace std;

int ansFunc4(int N) {
  int ans = 1;
  while (2 * ans <= N) ans *= 2;
  return ans;
}

int main() { cout << ansFunc4(97615282) << endl; }
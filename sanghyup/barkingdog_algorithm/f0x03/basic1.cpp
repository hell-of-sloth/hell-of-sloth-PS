#include <bits/stdc++.h>
using namespace std;

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);

  int count[10] = {};

  int a, b, c;
  cin >> a >> b >> c;

  string num = to_string(a * b * c);
  for (auto a : num) {
    count[a - '0'] += 1;
  }

  for (auto a : count) {
    cout << a << '\n';
  }
}
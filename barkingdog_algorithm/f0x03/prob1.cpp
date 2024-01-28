#include <bits/stdc++.h>
using namespace std;

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);

  string S;
  cin >> S;

  int alphs[26] = {};

  // fill(alphs, alphs + 26, 0);

  for (char c : S) {
    alphs[c - 'a']++;
  }
  for (int c : alphs) {
    cout << c << ' ';
  }
}
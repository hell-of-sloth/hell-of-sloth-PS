#include <bits/stdc++.h>
using namespace std;

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);
  int N;
  cin >> N;
  stack<int> S;

  int val;
  while (N--) {
    cin >> val;
    if (val) {
      S.push(val);
    } else {
      S.pop();
    }
  }
  int sum = 0;
  while (!S.empty()) {
    sum += S.top();
    S.pop();
  }
  cout << sum;
}
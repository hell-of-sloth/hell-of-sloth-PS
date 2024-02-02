#include <bits/stdc++.h>

using namespace std;

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);
  stack<int> dat;
  int N;
  cin >> N;
  string op;
  int val;
  while (N--) {
    cin >> op;
    if (op == "push") {
      cin >> val;
      dat.push(val);
    } else if (op == "pop") {
      if (dat.empty()) {
        cout << -1 << '\n';
        continue;
      }
      cout << dat.top() << '\n';
      dat.pop();
    } else if (op == "top") {
      if (dat.empty()) {
        cout << -1 << '\n';
        continue;
      }
      cout << dat.top() << '\n';
    } else if (op == "size") {
      cout << dat.size() << '\n';
    } else if (op == "empty") {
      cout << dat.empty() << '\n';
    }
  }
}
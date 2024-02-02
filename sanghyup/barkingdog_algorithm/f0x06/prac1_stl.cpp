#include <bits/stdc++.h>
using namespace std;

queue<int> Q;

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);
  int N;
  cin >> N;

  string op;
  int val;
  while (N--) {
    cin >> op;
    if (op == "push") {
      cin >> val;
      Q.push(val);
    } else if (op == "pop") {
      if (Q.empty())
        cout << -1 << '\n';
      else {
        cout << Q.front() << '\n';
        Q.pop();
      }
    } else if (op == "size") {
      cout << Q.size() << '\n';
    } else if (op == "empty")
      cout << Q.empty() << '\n';
    else if (op == "front") {
      if (Q.empty())
        cout << -1 << '\n';
      else {
        cout << Q.front() << '\n';
      }
    } else if (op == "back") {
      if (Q.empty())
        cout << -1 << '\n';
      else {
        cout << Q.back() << '\n';
      }
    }
  }
}
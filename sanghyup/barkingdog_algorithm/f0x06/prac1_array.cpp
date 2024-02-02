#include <bits/stdc++.h>
using namespace std;

int dat[10005];
int head = 0;
int tail = 0;

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
      dat[tail++] = val;
    } else if (op == "pop") {
      if (head == tail)
        cout << -1 << '\n';
      else {
        cout << dat[head++] << '\n';
      }
    } else if (op == "size") {
      cout << tail - head << '\n';
    } else if (op == "empty")
      cout << ((head - tail) ? 0 : 1) << '\n';
    else if (op == "front") {
      if (head == tail)
        cout << -1 << '\n';
      else {
        cout << dat[head] << '\n';
      }
    } else if (op == "back") {
      if (head == tail)
        cout << -1 << '\n';
      else {
        cout << dat[tail - 1] << '\n';
      }
    }
  }
}
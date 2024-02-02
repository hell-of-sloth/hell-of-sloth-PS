#include <bits/stdc++.h>

using namespace std;

int dat[100001];
int p;

void push(int x) { dat[p++] = x; }

void empty() {
  if (p == 0)
    cout << 1 << '\n';
  else
    cout << 0 << '\n';
}
void top() {
  if (p != 0)
    cout << dat[p - 1] << '\n';
  else
    cout << -1 << '\n';
}
void size() { cout << p << '\n'; }

void pop() {
  if (p != 0) {
    cout << dat[--p] << '\n';
  } else
    cout << -1 << '\n';
}

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
      push(val);
    } else if (op == "pop") {
      pop();
    } else if (op == "top") {
      top();
    } else if (op == "size") {
      size();
    } else if (op == "empty") {
      empty();
    }
  }
}
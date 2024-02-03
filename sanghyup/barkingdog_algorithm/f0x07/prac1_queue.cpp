#include <bits/stdc++.h>
using namespace std;

const int MX = 1000005;
int dat[2 * MX + 1];
int head = MX, tail = MX;

int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);

  int n;
  cin >> n;
  while (n--) {
    string q;
    cin >> q;
    if (q == "push_back") {
      int val;
      cin >> val;
      dat[tail++] = val;
    } else if (q == "push_front") {
      int val;
      cin >> val;
      dat[--head] = val;
    } else if (q == "pop_front") {
      if (head == tail)
        cout << -1 << '\n';
      else {
        cout << dat[head++] << '\n';
      }
    } else if (q == "pop_back") {
      if (head == tail)
        cout << -1 << '\n';
      else {
        cout << dat[--tail] << '\n';
      }
    } else if (q == "size")
      cout << tail - head << '\n';
    else if (q == "empty")
      cout << (head == tail ? 1 : 0) << '\n';
    else if (q == "front") {
      if (head == tail)
        cout << -1 << '\n';
      else
        cout << dat[head] << '\n';
    } else {  // back
      if (head == tail)
        cout << -1 << '\n';
      else
        cout << dat[tail - 1] << '\n';
    }
  }
}
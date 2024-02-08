#include <bits/stdc++.h>
using namespace std;

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);
  int N;
  cin >> N;
  string input;
  int count = 0;
  while (N--) {
    stack<char> S;
    cin >> input;
    for (auto c : input) {
      if (S.empty())
        S.push(c);
      else if (S.top() == c)
        S.pop();
      else
        S.push(c);
    }
    if (S.empty()) count++;
  }
  cout << count;
}
#include <bits/stdc++.h>
using namespace std;

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);
  string input;
  cin >> input;
  char prev = '\0';
  int count = 0;
  stack<char> S;
  for (auto c : input) {
    if (c == '(')
      S.push(c);
    else if (c == ')') {
      if (prev == '(') {
        S.pop();
        count += S.size();
      } else {
        S.pop();
        count++;
      }
    }
    prev = c;
  }
  cout << count;
}
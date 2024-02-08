#include <bits/stdc++.h>
using namespace std;

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);
  stack<char> S;
  string input;
  while (1) {
    while (!S.empty()) S.pop();
    bool isWrong = false;
    getline(cin, input);
    if (input == ".") break;
    for (auto c : input) {
      if (c == '(' || c == '[')
        S.push(c);
      else if (c == ')') {
        if (S.empty() || S.top() != '(') {
          isWrong = true;
          break;
        } else
          S.pop();
      } else if (c == ']')
        if (S.empty() || S.top() != '[') {
          isWrong = true;
          break;
        } else
          S.pop();
    }
    if (!S.empty() || isWrong)
      cout << "no" << '\n';
    else
      cout << "yes" << '\n';
  }
}
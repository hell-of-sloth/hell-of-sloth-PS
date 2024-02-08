#include <bits/stdc++.h>
using namespace std;

int depth[1000001];

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);
  stack<char> S;
  string input;
  bool isWrong = false;
  cin >> input;
  char prev = '\0';
  for (auto c : input) {
    if (c == '(' || c == '[')
      S.push(c);
    else if (c == ')') {
      if (S.empty() || S.top() != '(') {
        isWrong = true;
        break;
      } else if (prev == '(') {
        S.pop();
        depth[S.size()] += 2;
      } else {
        depth[S.size() - 1] += depth[S.size()] * 2;
        depth[S.size()] = 0;
        S.pop();
      }
    } else if (c == ']')
      if (S.empty() || S.top() != '[') {
        isWrong = true;
        break;
      } else if (prev == '[') {
        S.pop();
        depth[S.size()] += 3;
      } else {
        depth[S.size() - 1] += depth[S.size()] * 3;
        depth[S.size()] = 0;
        S.pop();
      }
    prev = c;
  }
  if (!S.empty() || isWrong)
    cout << 0 << '\n';
  else
    cout << depth[0] << '\n';
}
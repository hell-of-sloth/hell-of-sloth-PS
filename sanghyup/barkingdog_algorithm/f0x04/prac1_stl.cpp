#include <bits/stdc++.h>
using namespace std;

int main() {
  // ios::sync_with_stdio(0);
  // cin.tie(0);

  string init;
  int N;
  cin >> init;
  cin >> N;

  list<char> editor;
  for (auto c : init) {
    editor.push_back(c);
  }
  auto it = editor.end();

  char op, key;
  while (N--) {
    cin >> op;
    if (op == 'L' && it != editor.begin())
      it--;
    else if (op == 'D' && it != editor.end())
      it++;
    else if (op == 'B') {
      if (it == editor.begin()) continue;
      it = editor.erase(--it);
    } else if (op == 'P') {
      cin >> key;
      editor.insert(it, key);
    }
  }

  for (auto c : editor) cout << c;
}
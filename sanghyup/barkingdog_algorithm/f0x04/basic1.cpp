#include <bits/stdc++.h>
using namespace std;

int main() {
  int N;
  cin >> N;

  string input;
  while (N--) {
    cin >> input;
    list<char> output;
    auto it = output.begin();

    for (auto c : input) {
      if (c == '<' && it != output.begin()) {
        it--;
      } else if (c == '>' && it != output.end()) {
        it++;
      } else if (c == '-' && it != output.begin())
        it = output.erase(--it);
      else if (c != '<' && c != '>' && c != '-') {
        output.insert(it, c);
      }
    }
    for (auto c : output) cout << c;
    cout << '\n';
  }
}
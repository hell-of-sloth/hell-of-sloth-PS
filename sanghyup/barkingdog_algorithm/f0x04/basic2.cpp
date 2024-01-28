#include <bits/stdc++.h>
using namespace std;

int main() {
  list<int> cl;
  int N, k;
  cin >> N >> k;
  cout << '<';
  for (int i = 1; i <= N; i++) cl.push_back(i);
  auto it = cl.end();
  while (!cl.empty()) {
    for (int i = 0; i < k; i++) {
      it++;
      if (it == cl.end()) it++;
    }
    cout << *it;
    it = cl.erase(it);
    it--;
    if (cl.size() > 0) cout << ", ";
  }
  cout << '>';
}
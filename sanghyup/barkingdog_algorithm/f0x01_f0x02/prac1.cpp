#include <bits/stdc++.h>
using namespace std;

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);

  int N, target;
  cin >> N >> target;

  for (int i = 0; i < N; i++) {
    int val;
    cin >> val;
    if (val < target) {
      cout << val << ' ';
    }
  }
}

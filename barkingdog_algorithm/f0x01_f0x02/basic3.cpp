#include <bits/stdc++.h>
using namespace std;

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);
  int dwarfs[9];
  int sum = 0;
  for (int &dwarf : dwarfs) {
    cin >> dwarf;
    sum += dwarf;
  }
  sort(dwarfs, dwarfs + 9);
  for (int i = 0; i < 8; i++) {
    for (int j = i + 1; j < 9; j++) {
      if ((sum - dwarfs[i] - dwarfs[j]) == 100) {
        for (int k = 0; k < 9; k++) {
          if (k != i && k != j) cout << dwarfs[k] << '\n';
        }
        return 0;
      }
    }
  }
}
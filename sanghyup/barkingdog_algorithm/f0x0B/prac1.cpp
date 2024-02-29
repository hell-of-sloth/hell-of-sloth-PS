#include <bits/stdc++.h>
using namespace std;

long long a, b, c;
// long long findMod(long long b) {
//   if (b == 1) return a % c;
//   if ((b > 0) && ((b & (b - 1)) == 0)) {
//     long long prev = findMod(b / 2);
//     return (prev * prev) % c;
//   } else {
//     return (findMod(b - 1) * a) % c;
//   }
// }

long long findMod(long long b) {
  if (b == 1) return a % c;
  if (b % 2) {  // 홀수
    return (findMod(b - 1) * a) % c;
  }
  long long prev = findMod(b / 2);
  return (prev * prev) % c;
}

int main() {
  // ios::sync_with_stdio(0);
  // cin.tie(0);
  cin >> a >> b >> c;
  cout << findMod(b);
}
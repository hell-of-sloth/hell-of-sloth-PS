#include <bits/stdc++.h>
using namespace std;

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);

  int nums[100001];
  int count[1000001];

  int match = 0;
  int n;
  cin >> n;
  for (int i = 0; i < n; i++) {
    cin >> nums[i];
  }
  int x;
  cin >> x;
  for (int i = 0; i < n; i++) {
    if (count[nums[i]]) match++;
    if ((0 <= (x - nums[i])) && ((x - nums[i]) <= 1000000))
      count[x - nums[i]]++;
  }
  cout << match;
}
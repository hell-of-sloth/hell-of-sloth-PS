#include <bits/stdc++.h>
using namespace std;

bool func2(int arr[], int len) {
  int needs[101] = {};
  for (int i = 0; i < len; i++) {
    if (needs[arr[i]] != 0)
      return 1;
    else {
      needs[100 - arr[i]] = 1;
    }
  }
  return 0;
}

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);
  int arr1[10] = {4, 13, 63, 87};
  cout << func2(arr1, 4);
}
#include <iostream>
#include <algorithm>
#include <vector>
#define endl '\n'

using namespace std;

const int MAX = (int)1e5;

int N, M;
vector<int> arr;

void Solve(int target) {
  int s, e;
  int mid;
  s = 0;
  e = N - 1;
  while (s <= e) {
    mid = (s + e) / 2;
    if (arr[mid] == target) {
      cout << 1 << endl;
      return;
    }
    if (arr[mid] < target) {
      s = mid + 1;
      continue;
    }
    if (arr[mid] > target) {
      e = mid - 1;
      continue;
    }
  }
  cout << 0 << endl;
}

void ReadUserInput() {
  int target_num, new_num;
  cin >> N; 
  for (int i = 0; i < N; i++) {
    cin >> new_num;
    arr.push_back(new_num);
  }
  sort(arr.begin(), arr.end()); 
  cin >> M;
  for (int i = 0; i < M; i++) {
    cin >> target_num;
    Solve(target_num);
  }
}

int main() {
  ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
  ReadUserInput();
  return 0;
}

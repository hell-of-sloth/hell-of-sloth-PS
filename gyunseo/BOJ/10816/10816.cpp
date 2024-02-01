#include <iostream>
#include <unordered_map>
#define endl '\n'

using namespace std;

int N, M;
unordered_map<int, int> mp;
void Solve() {
}

void ReadUserInput() {
  int n;
  cin >> N;
  for (int i = 0; i < N; i++) {
    cin >> n;
    mp[n]++;
  }
  cin >> M;
  for (int i = 0; i < M;i ++) {
    cin >> n;
    cout << mp[n] << " ";
  }
}

int main() {
  ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
  ReadUserInput();
  return 0;
}

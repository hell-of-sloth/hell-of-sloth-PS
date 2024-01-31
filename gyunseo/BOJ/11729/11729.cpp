#include <iostream>
#define endl '\n'
using namespace std;
int N;
void ReadUserInput() {
  cin >> N;
}

void DFS(int n, int src, int dst) {
  if (n == 1) {
    cout << src << " " << dst << endl;
    return;
  }
  if (n == 2) {
    cout << src << " " << 6 - src - dst << endl;
    cout << src << " " << dst << endl;
    cout << 6 - src - dst << " " << dst << endl;
    return;
  }
  DFS(n - 1, src, 6 - src - dst);
  DFS(1, src, dst);
  DFS(n - 1, 6 - src - dst, dst);
}

void Solve() {
  cout << (1 << N) - 1 << endl;
  DFS(N, 1, 3);
}

int main() {
  ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
  ReadUserInput();
  Solve();
  return 0;
}

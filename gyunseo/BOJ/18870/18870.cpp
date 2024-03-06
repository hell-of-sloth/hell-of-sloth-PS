#include <algorithm>
#include <cassert>
#include <iostream>
#include <vector>
#define endl '\n'
#define ASSERT(exp, msg) assert(exp &&msg)
#define WATCH(X) cout << (#X) << " is " << X << endl
using namespace std;
// 1 ≤ N ≤ 1,000,000
//-10^9 ≤ Xi ≤ 10^9

const int MAX = (int)1e6;
int N;
vector<int> X_origin, X;

void solve() {
    sort(X.begin(), X.end());
    X.erase(unique(X.begin(), X.end()), X.end());
    for (auto x : X_origin) {
        cout << lower_bound(X.begin(), X.end(), x) - X.begin() << " ";
    }
    cout << endl;
}

void read_user_input() {
    cin >> N;
    for (int i = 0; i < N; i++) {
        int x;
        cin >> x;
        X_origin.push_back(x);
        X.push_back(x);
    }
}
int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    read_user_input();
    solve();
    return 0;
}

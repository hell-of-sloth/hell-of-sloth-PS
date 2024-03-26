#include <iostream>
#include <vector>
#include <algorithm>

#define endl '\n'
#define VI vector<int>
using namespace std;
using ll= long long;
ll N, K;
vector<ll> A;
int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    ll answer = 0;
    cin >> N >> K;
    answer = K * (K + 1) / 2;
    for (int i = 0; i < N; i++) {
        ll e;
        cin >> e;
        A.push_back(e);
    }
    sort(A.begin(), A.end());
    A.erase(unique(A.begin(), A.end()), A.end());
    for (const auto &e: A) {
        // cout << "e: " << e << endl;
        if (e <= K)
            answer -= e;
    }
    cout << answer << endl;
    return 0;
}
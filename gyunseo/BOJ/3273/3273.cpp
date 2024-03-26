#include <bits/stdc++.h>
#define endl '\n'
#define VI vector<int>
#define FOR(idx, a, b) for (int idx = a; idx < b; idx++)
using namespace std;

VI V;
int n, x;
int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> n;
    FOR(i, 0, n) {
        int e;
        cin >> e;
        V.push_back(e);
    }
    cin >> x;
    sort(V.begin(), V.end());
    int s = 0, e = V.size() - 1;
    int ans = 0;
    while (s < e) {
        int summation = V[s] + V[e];
        if (summation == x) {
            ++ans;
            ++s;
            --e;
            continue;
        }
        if (summation < x) {
            ++s;
            continue;
        }
        if (summation > x) {
            --e;
            continue;
        }
    }
    cout << ans << endl;
    return 0;
}
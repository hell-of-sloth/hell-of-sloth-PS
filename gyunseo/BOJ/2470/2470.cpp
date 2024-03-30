#include <bits/stdc++.h>
using namespace std;
using ll = long long;
#define endl '\n'
#define VI vector<int>
#define VL vector<ll>
#define FOR(idx, s, e) for (int idx = s; idx < e; ++idx)
int N;
VL V;
int ans[2] = {(int)1e9, (int)1e9};
int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N;
    FOR(i, 0, N) {
        int e;
        cin >> e;
        V.push_back(e);
    }
    sort(V.begin(), V.end());
    int s = 0, e = V.size() - 1;
    while (s < e) {
        int tmp_sum = V[s] + V[e];
        if (tmp_sum == 0) {
            cout << V[s] << " " << V[e] << endl;
            return 0;
        }
        if (tmp_sum < 0) {
            if (abs(tmp_sum) < abs(ans[0] + ans[1])) {
                ans[0] = V[s];
                ans[1] = V[e];
            }
            ++s;
        } else {
            if (abs(tmp_sum) < abs(ans[0] + ans[1])) {
                ans[0] = V[s];
                ans[1] = V[e];
            }
            --e;
        }
    }
    cout << ans[0] << " " << ans[1] << endl;
    return 0;
}

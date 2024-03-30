#include <bits/stdc++.h>
#define endl '\n'
#define FOR(idx, s, e) for (int idx = (s); idx < (e); ++idx)
#define RFOR(idx, e, s) for (int idx = (e); idx >= (s); --idx)
#define VI vector<int>
#define VL vector<ll>
#define PI pair<int, int>
#define PL pair<ll, ll>
#define F first
#define S second
using namespace std;
using ll = long long;
const int MAX = 200000, R = 1, C = 2;
int H, W, M;

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> H >> W >> M;
    vector<bool> is_row_tched(MAX + 1, false), is_col_tched(MAX + 1, false);
    ll untched_r = H, untched_c = W;
    VI T(MAX + 1), A(MAX + 1), X(MAX + 1);
    FOR(i, 0, M) cin >> T[i] >> A[i] >> X[i];
    // zero-base idxed
    FOR(i, 0, M) --A[i];
    VL cnt(MAX + 1, 0);
    // M - 1 부터 0까지 역으로 순회
    RFOR(i, M - 1, 0) {
        if (T[i] == R) {
            if (!is_row_tched[A[i]]) {
                is_row_tched[A[i]] = true;
                --untched_r;
                cnt[X[i]] += untched_c;
            }
        } else {
            if (!is_col_tched[A[i]]) {
                is_col_tched[A[i]] = true;
                --untched_c;
                cnt[X[i]] += untched_r;
            }
        }
    }
    cnt[0] += untched_r * untched_c;
    vector<PL> ans;
    FOR(i, 0, MAX + 1) {
        if (cnt[i])
            ans.push_back({i, cnt[i]});
    }
    cout << ans.size() << endl;
    for (const auto &e : ans) {
        cout << e.F << " " << e.S << endl;
    }
}
#include <bits/stdc++.h>

#define F first
#define S second
#define FOR(idx, s, e) for (int idx = (s); idx < (e); ++idx)
#define RFOR(idx, e, s) for (int idx = (e); idx >= (s); --idx)
#define VI vector<int>
#define PI pair<int, int>
#define VL vector<ll>
#define PL pair<ll, ll>
#define endl '\n'
#define watch(x) cout << (#x) << " is " << x << endl
#define ASSERT(exp, msg) assert((exp) && (msg))
using namespace std;
const int MAX = 200'000;
using ll = long long;
using llu = unsigned long long;
ll N, A, B;
VL D(MAX + 100);
int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N >> A >> B;
    ll MOD = A + B;
    FOR(i, 0, N) cin >> D[i];
    FOR(i, 0, N) D[i] = D[i] % MOD;
    FOR(i, 0, N) {
        if (D[i] > (ll)A) {
            cout << "No" << endl;
            return 0;
        }
    }
    cout << "Yes" << endl;
    return 0;
}

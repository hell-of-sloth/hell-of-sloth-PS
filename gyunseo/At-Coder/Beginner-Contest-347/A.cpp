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
#define ASSERT(exp, msg) assert((exp) &&(msg))
using namespace std;
using ll = long long;
const int MAX = 100;
int N, K;
VI A(100);
int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N >> K;
    FOR(i, 0, N) cin >> A[i];
    FOR(i, 0, N) {
        if (A[i] % K == 0)
            cout << A[i] / K << " ";
    }cout << endl;
    return 0;
}

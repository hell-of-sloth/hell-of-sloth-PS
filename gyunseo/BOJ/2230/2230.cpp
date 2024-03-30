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
const int MAX = (int)1e5;
int N, M;
VI A(MAX);
bool OOB(int s, int e) {
    if (s < 0 || s >= N) return true;
    if (e < 0 || e >= N) return true;
    return false;
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N >> M;
    FOR(i, 0, N) cin >> A[i];
    sort(A.begin(), A.begin() + N);
    //DEBUG
    // FOR(i, 0, N) watch(A[i]);
    int s=0, e=0;
    int ans = 2 * (int)1e9 + 10;
    while(!OOB(s, e)&&s<=e) {
        int delta = abs(A[e] - A[s]);
        if (delta >= M) {
            if (delta < ans) ans = delta;
            ++s;
            continue;
        }
        if(delta < M) {
            ++e;
            continue;
        }
    }
    cout << ans << endl;
}
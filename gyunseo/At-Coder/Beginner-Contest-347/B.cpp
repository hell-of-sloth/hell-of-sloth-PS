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
string S;
VI comb(100);
map<string, bool> mp;
int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    getline(cin, S);
    // watch(S);
    int N = S.size();
    int ans =0;
    FOR(i, 0, N) {
        string tmp = "";
        FOR(j, i, N) {
            tmp += S[j];
            // watch(tmp);
            if (mp[tmp]) continue;
            mp[tmp] = true;
            ++ans;
        }
    }
    cout << ans << endl;
    return 0;
}
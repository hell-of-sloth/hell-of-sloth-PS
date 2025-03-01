#include <iostream>
#include <vector>
#include <tuple>
#include <queue>
#include <map>

#define fastio cin.tie(0)->sync_with_stdio(0)
#define endl '\n'
#define watch(x) cout << (#x) << " is " << (x) << endl
using namespace std;

using ll = long long;
using PL = pair<ll, ll>;
map<ll, ll> mp;
queue<PL> q;

ll A, B;
void readInput() {
    cin >> A >> B;
}

void solve() {
    mp[A]++;
    q.push({A, 1});
    while (!q.empty()) {
        ll curr, dist;
        tie(curr, dist) = q.front();
        q.pop();
        for (ll k = 0; k < 2; ++k) {
            ll nn;
            if (k==0) nn = 2 * curr;
            else if (k==1) nn = curr * 10 + 1;

            if (nn > B) continue;
            if (mp[nn]) continue;
            mp[nn] = dist + 1;
            q.push({nn, dist + 1});
        }
    }
}

int main() {
    fastio;
    readInput();
    solve();
    if (mp[B] == 0) cout << -1 << endl;
    else cout << mp[B] << endl;
    return 0;
}
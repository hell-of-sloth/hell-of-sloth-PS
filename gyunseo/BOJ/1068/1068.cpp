#include <iostream>
#include <vector>
#include <cassert>
#include <set>

#define fastio cin.tie(0)->sync_with_stdio(0)
#define endl '\n'
#define ASSERT(exp, msg) assert((exp) &&(msg))
#define EACH(x, a) for (const auto &x: (a))
#define watch(x) cout << (#x) << " is " << (x) << endl

using namespace std;
using ll = long long;
using VL = vector<ll>;

const ll DOES_NOT_EXIST = -2, PARENT_OF_ROOT = -1;
ll N, query;
VL parents(57, DOES_NOT_EXIST);
set<ll> s;

bool f(ll x) {
    if (x == PARENT_OF_ROOT) return false;
    if (x == query) return true;
    return false or f(parents[x]);
}

void readInput() {
    cin >> N;
    for (ll i = 0; i < N; ++i) cin >> parents[i];
    cin >> query;
}

bool isLeafNode(ll x) {
    if (parents[x] == DOES_NOT_EXIST) return false;
    // 이 친구를 parent로 모시는 애가 없으면 됨
    for (ll i = 0; i < N; ++i) {
        if (i == x) continue;
        if (parents[i] == x) return false;
    }
    return true;
}

void debugParentsVec() {
    for (ll i = 0; i < N; ++i) {
        cout << "parents[" << i <<"]: " << parents[i] << " ";
    }cout << endl;
}
void solve() {
    ll ans = 0;
    for (ll i = 0; i < N; ++i) {
        if (f(i)) s.insert(i);
    }
    for (auto it = s.begin(); it != s.end(); ++it) {
        ll idx = *it;
        parents[idx] = DOES_NOT_EXIST;
    }
    for (ll i = 0; i < N; ++i) {
        if (isLeafNode(i)) ++ans;
    }
    // debugParentsVec();
    cout << ans << endl;
}

int main() {
    fastio;
    readInput();
    solve();
    return 0;
}
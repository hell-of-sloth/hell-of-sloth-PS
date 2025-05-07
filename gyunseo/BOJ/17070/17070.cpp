#include <iostream>
#include <queue>
#include <vector>
#include <tuple>

#define fastio cin.tie(0)->sync_with_stdio(0)
#define endl '\n'
#define watch(x) cout << (#x) << " is " << (x) << endl
#define I first
#define J second

using namespace std;
using ll = long long;
using VL = vector<ll>;
using PL = pair<ll, ll>;

ll N;
vector<VL> board(17, VL(17, -1));
queue<pair<PL, PL>> q;
// ->, 아래, 대각선 오른쪽 아래
ll di[3] = {0, 1, 1}, dj[3] = {1, 0, 1};

void readInput() {
    cin >> N;
    for (ll i = 1; i <= N; ++i) {
        for (ll j = 1; j <= N; ++j) cin >> board[i][j];
    }
}

bool OOB(ll i, ll j) {
    if (i <= 0 or i > N) return true;
    if (j <= 0 or j > N) return true;
    return false;
}

void solve() {
    PL tail, head;
    ll ans = 0;
    q.push({{1, 1}, {1, 2}});
    while(!q.empty()) {
        tie(tail, head) = q.front();
        q.pop();
        for (ll k = 0; k < 3; ++k) {
            ll nhi = head.I + di[k], nhj = head.J + dj[k];
            if (OOB(nhi, nhj)) continue;
            if (board[nhi][nhj] == 1) continue;
            if (k == 2 and (board[nhi - 1][nhj] == 1 or board[nhi][nhj - 1] ==1)) continue;

            if ((k == 0 or k == 1) and (tail.I + 1 == nhi and tail.J + 1 == nhj)) continue;
            // watch(nhi); watch(nhj);
            if (nhi == N and nhj == N) ++ans;
            else {
                q.push({{head.I, head.J}, {nhi, nhj}});
            }
        }

    }
    cout << ans << endl;
}

int main() {
    fastio;
    readInput();
    solve();
    return 0;
}
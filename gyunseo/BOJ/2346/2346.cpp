#include <iostream>
#include <vector>
#include <cmath>

#define endl '\n'
#define fastio cin.tie(0)->sync_with_stdio(0)
#define VL vector<ll>
#define watch(x) cout << (#x) << " is " << x << endl

using namespace std;
using ll = long long;
const ll MAX_N = 1000;
ll N, hitCount;
VL v(MAX_N + 7, 0);
vector<bool> isHit(MAX_N + 7, false);

void readInput() {
    cin >> N;
    for (ll i = 0; i < N; ++i) {
        cin >> v[i];
        // watch(v[i]);
    }
}

ll getRemainder(ll a, ll b) {
    if (a >= 0) return a % b;
    return a - ((ll)floor((double)a / b) * b);
}

void solve() {
    // hit idx 0 balloon
    ll idx = 0;
    isHit[idx] = true;
    ++hitCount;
    cout << idx + 1 <<  " ";
    // cout << idx << " exploded" << endl;
    while (hitCount < N){
        ll shift_num = v[idx];
        // 오른쪽이 1, 왼쪽이 -1
        ll dir = (shift_num > 0) ? 1 : -1;
        ll shift_amount = shift_num * dir;
        while (shift_amount) {
            ll next_idx = getRemainder(idx + dir , N);
            // watch(next_idx);
            // 방향쪽으로 미리 Peek을 해본다

            // 만약 풍선이 터져있으면, 빼고 이동
            if (isHit[next_idx]) {
                idx = next_idx;
                continue;
            }
            // 안 터졌으면, shift_amount를 차감하면서, 그쪽으로 포인터를 이동
            idx = next_idx;
            --shift_amount;
        }
        cout << idx + 1 << " ";
        // cout << idx << " exploded" << endl;
        isHit[idx] = true;
        ++hitCount;
    }
}

int main() {
    fastio;
    readInput();
    solve();
    return 0;
}
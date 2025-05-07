#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
#include <set>

#define fastio cin.tie(0)->sync_with_stdio(0)
#define watch(x) cout << (#x) << " is " << (x) << endl


using namespace std;
using ll = long long;
using VL = vector<ll>;
ll N, M, initNum = 100, ans = (ll)1e9 + 7, closestNum = (ll)1e9 + 7;
VL nums;
set<ll> brokenNums;

void readInput() {
    cin >> N;
    cin >> M;
    for (ll i = 0; i < M; ++i) {
        ll brokenNum;
        cin >> brokenNum;
        brokenNums.insert(brokenNum);
    }
}

ll calcNums() {
    ll ret = 0, x = 1;
    for (auto it = nums.rbegin(); it != nums.rend(); ++it) {
        ret += (*it) * x;
        x *= 10;
    }
    return ret;
}

void DFS(ll level) {
    if (nums.size() == 7) return;

    for (ll i = 0; i < 10; ++i) {
        if (brokenNums.count(i) == 1)
            continue;
        nums.push_back(i);
        ll calced = calcNums();
        if (abs(calced - N) < abs(closestNum - N))
            closestNum = calced;
        if (abs(calced - N) == abs(closestNum - N)) {
            if (to_string(calced).size() < to_string(closestNum).size()) closestNum = calced;
        }
        DFS(level + 1);
        nums.pop_back();
    }
}

void setClosestNum() {
    for (ll i = 0; i < 10; ++i) {
        if (brokenNums.count(i) == 1)
            continue;
        nums.push_back(i);
        ll calced = calcNums();
        if (abs(calced - N) < abs(closestNum - N))
            closestNum = calced;
        if (abs(calced - N) == abs(closestNum - N)) {
            if (to_string(calced).size() < to_string(closestNum).size())
                closestNum = calced;
        }
        DFS(1);
        nums.pop_back();
    }
}


void solve() {

    // 1. 가장 가까운 숫자를 만들지 않고 그냥 쁠마만 눌렀을 때
    ans = min(abs(N - initNum), ans);
    // watch(ans);
    // 2. 가장 가까운 숫자를 만들고, 그 다음 쁠마만 눌렀을 때
    setClosestNum();
    // watch(closestNum);
    ll tmpAns = abs(N - closestNum) + to_string(closestNum).size();
    ans = min(tmpAns, ans);

    cout << ans << endl;

}

int main() {
    fastio;
    readInput();
    solve();
    return 0;
}
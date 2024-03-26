#include <bits/stdc++.h>
using namespace std;
using ll =long long;
#define endl '\n'
#define VI vector<int>
#define FOR(s, e) for(int _ = s; _ < e; ++_)
#define watch(x) cout << (#x) << " is " << x << endl
const int MAX = 1'000'000'000;
int N, M;
VI V;
bool check(int mid) {

    ll ret_sum = 0;
    // N
    for (const auto &e : V) {
        if (e > mid) ret_sum += e - mid;
    }
    if (ret_sum >= M) return true;
    return false;
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N >> M;
    FOR(0, N) {
        int height;
        cin >> height;
        V.push_back(height);
    }
    //DEBUG
    // for(const auto &e: V) {
    //     watch(e);
    // }
    // N 최대 백만 NlogN?
    // lo를 구하는 것
    // 0<=lo<=MAX
    // check(lo) = true, check(hi=MAX +1) = false
    // 절단기를 mid로 들었을 때, 가져가는 나무의 합이 M이상인가?
    int lo = 0, hi = MAX + 1;
    // logN
    while (lo + 1 < hi) {
        int m = (lo + hi) / 2;
        if (check(m)) lo = m;
        else hi = m;
    }
    cout << lo << endl;

}
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
using ll = long long;
using ull = unsigned long long;

int N;

auto cmp = [](int &root, int &child) -> bool {
    int root_abs = abs(root), child_abs = abs(child);
    if (root_abs == child_abs) {
        return root > child;
    }
    return root_abs > child_abs;
};

priority_queue<int, VI, decltype(cmp)> PQ(cmp);

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N;
    int e;
    FOR(i, 0, N) {
        cin >> e;
        if (e == 0) {
            if (PQ.empty())
                cout << 0 << endl;
            else {
                cout << PQ.top() << endl;
                PQ.pop();
            }
            continue;
        }
        PQ.push(e);
    }
    return 0;
}
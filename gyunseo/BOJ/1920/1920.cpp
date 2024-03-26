#include <bits/stdc++.h>
using namespace std;
#define endl '\n'
#define watch(x) cout << (#x) << " is " << x << endl
#define FOR(s, e) for (int i = s; i < e; i++)
#define VI vector<int>
int N, M;
VI V;

bool num_exists(int num) {
    int s = 0, e = V.size() - 1;
    while (s <= e) {
        int m = (s + e) / 2;
        if (num == V[m]) {
            return true;
        }
        if (num > V[m]) {
            s = m + 1;
            continue;
        }
        if (num < V[m]) {
            e = m - 1;
            continue;
        }
    }
    return false;
}
int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N;
    FOR(0, N) {
        int e;
        cin >> e;
        V.push_back(e);
    }
    sort(V.begin(), V.end());
    cin >> M;
    FOR(0, M) {
        int query_num;
        cin >> query_num;
        if (num_exists(query_num))
            cout << 1 << endl;
        else
            cout << 0 << endl;
    }
    return 0;
}
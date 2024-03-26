#include <bits/stdc++.h>
using namespace std;

#define endl '\n'
#define watch(x) cout << (#x) << " is " << x << endl
#define FOR(s, e) for(int _ = s; _ < e; _++)
#define VI vector<int>

int N, M;
VI V;

bool check_upper(const int i, const int k) {
    if (V[i]>k) return true;
    else return false;
}
bool check_lower(const int i, const int k) {
    if (V[i] >= k) return true;
    else return false;
}
int upper(int k) {
    // v[i]는 k 초과인가?
    // check(h, k)는 무조건 true
    // check(l, k)는 무조건 false
    int l = -1, h = V.size();
    while (l + 1 < h) {
        int m = (l + h) / 2;
        if (check_upper(m, k)) h = m;
        else l = m;
    }
    return h;
}

int lower(int k) {
    // V[i]는 k 이상인가?
    // check(h, k)는 무조건 true
    // check(l, k)는 무조건 false
    int l = -1, h = V.size();
    while (l + 1 < h) {
        int m = (l + h) / 2;
        // check(m, k) == check(h, k)이면 h = m
        if (check_lower(m, k)) h = m;
        else l = m;
    }
    return h;
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
        cout << upper(query_num) - lower(query_num) << " ";
    }cout << endl;
    return 0;
}
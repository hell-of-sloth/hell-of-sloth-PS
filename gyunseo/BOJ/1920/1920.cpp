#include <algorithm>
#include <iostream>
#include <vector>

#define fastio cin.tie(0)->sync_with_stdio(0)
#define endl '\n'
#define watch(x) cout << (#x) << ": " << (x)

#define REP(idx, end) for (auto idx = 0; idx < (end); ++idx)
#define ALL(v) (v).begin(), (v).end()
#define EACH(e, v) for (auto &e : (v))
#define PB push_back
using namespace std;

int N, M;
vector<int> numVec, queryVec;

int lowerBound(const vector<int> &v, const int target) {
    int st = -1, en = v.size(), mid;
    while (st + 1 < en) {
        mid = (st + en) / 2;
        if (!(v[mid] >= target))
            st = mid;
        else
            en = mid;
    }
    return en;
}

int upperBound(const vector<int> &v, const int target) {
    int st = -1, en = v.size(), mid;
    while (st + 1 < en) {
        mid = (st + en) / 2;
        if (!(v[mid] > target))
            st = mid;
        else
            en = mid;
    }
    return en;
}

bool cmp(const int &a, const int &b) { return a < b; }
int main() {
    fastio;
    cin >> N;
    REP(i, N) {
        int e;
        cin >> e;
        numVec.PB(e);
    }
    sort(ALL(numVec), cmp);
    // EACH(e, numVec) {
    // watch(e);
    // cout << " ";
    //}
    cin >> M;
    REP(i, M) {
        int query;
        cin >> query;
        int bisectLeftIdx = lowerBound(numVec, query),
            bisectRightIdx = upperBound(numVec, query);
        int numOfElements = bisectRightIdx - bisectLeftIdx;
        if (numOfElements)
            cout << 1 << endl;
        else
            cout << 0 << endl;
    }
    return 0;
}

#include <cassert>
#include <iostream>

#define fastio cin.tie(0)->sync_with_stdio(0);
#define ASSERT(exp, msg) assert(exp &&msg);
#define REP(idx, end) for (auto idx = 0; idx < (end); ++idx)
#define EACH(e, v) for (auto &e : (v))
#define watch(x) cout << (#x) << ": " << x

using namespace std;

int N, ans;
int board[14][14];
bool checkCol[14], checkDiag1[28], checkDiag2[28];

bool isPromising(int nextLevel, int candidate) {
    if (checkCol[candidate])
        return false;
    if (checkDiag1[nextLevel + candidate])
        return false;
    if (checkDiag2[nextLevel + N - 1 - candidate])
        return false;
    return true;
}

void setCheckArrays(int nextLevel, int candidate, bool val) {
    checkCol[candidate] = val;
    checkDiag1[nextLevel + candidate] = val;
    checkDiag2[nextLevel + N - 1 - candidate] = val;
}
void DFS(int curLevel, int promisingCol) {
    if (curLevel == N - 1) {
        ans++;
        return;
    }
    
    REP(cand, N) {
        if (!isPromising(curLevel + 1, cand)) continue;
        setCheckArrays(curLevel + 1, cand, true);
        DFS(curLevel + 1, cand);
        setCheckArrays(curLevel + 1, cand, false);
    }
}

int main() {
    fastio;
    cin >> N;
    ASSERT((N >= 1 && N < 15), "N은 1이상 15미만");
    REP(cand, N) {
        if (!isPromising(0, cand)) continue;
        setCheckArrays(0, cand, true);
        DFS(0, cand);
        setCheckArrays(0, cand, false);
    }
    cout << ans << endl;
    return 0;
}

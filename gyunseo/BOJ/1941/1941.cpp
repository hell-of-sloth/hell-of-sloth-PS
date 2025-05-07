#include <iostream>
#include <vector>
#include <cassert>
#include <queue>
#include <string>
#include <set>
#include <tuple>
#include <algorithm>

#define fastio cin.tie(0)->sync_with_stdio(0)
#define endl '\n'
#define watch(x) cout << (#x) << " is " << (x) << endl
#define EACH(x, a) for (const auto &x : a)
#define ASSERT(exp, msg) assert((exp) &&(msg))
#define I first
#define J second

using namespace std;
using ll = long long;
using VL = vector<ll>;
using PL = pair<ll, ll>;
const ll S = 1, Y = 2;
vector<VL> board(5, VL(5, 0));

VL di({-1, 1, 0, 0}), dj({0, 0, -1, 1});
set<set<PL>> ans;
vector<bool> mask(25, 0);
vector<PL> coords;
ll ansCnt;

void readInput() {
    string str;
    for (ll i = 0; i < 5; i++) {
        getline(cin, str);
        // watch(str);
        for (ll j = 0; j < 5; ++j) {
            board[i][j] = str[j] == 'S' ? S : Y;
            // watch(board[i][j]);
        }
    }

}

bool OOB(ll i, ll j) {
    if (i < 0 or i >= 5) return true;
    if (j < 0 or j >= 5) return true;
    return false;
}

bool isAvailable(vector<PL> &comb) { 
    vector<VL> dist(5, VL(5, 0));
    set<PL> combCoords(comb.begin(), comb.end());
    queue<PL> q;
    ll sCnt = 0;
    dist[comb[0].I][comb[0].J] = 1;
    q.push(comb[0]);

    while (not q.empty()) {
        auto [ci, cj] = q.front();
        q.pop();

        for (ll k = 0; k < 4; ++k) {
            ll ni = ci + di[k], nj = cj + dj[k];
            if (OOB(ni, nj)) continue;
            ASSERT(not OOB(ni, nj), "next i and j must be in boundary");
            if (combCoords.count({ni, nj}) == 0) continue;
            if (dist[ni][nj] > 0) continue;

            dist[ni][nj] = dist[ci][cj] + 1;
            q.push({ ni, nj });
        }
    }

    EACH(coord, comb) {
        auto [ci, cj] = coord;
        
        // 방문 못한 곳이 있으면 available한 조합이 아니다
        if (dist[ci][cj] == 0) return false;
        if (board[ci][cj] == S) ++sCnt;
    }
    if (sCnt < 4) return false;
    
    return true;
}

void solve() {
    for (ll i = 0; i < 5; ++i) {
        for (ll j = 0; j < 5; ++j) {
            coords.push_back({ i, j });
        }
    }
    
    fill(mask.begin() + 18, mask.end(), true);
    do {
        vector<PL> comb;
        for (ll i =0; i < 25; ++i) {
            if (mask[i] == true) {
                comb.push_back(coords[i]);
            }
        }
        
        if (isAvailable(comb)) {
            ans.insert(set<PL>(comb.begin(), comb.end()));
        }

        
    }while(next_permutation(mask.begin(), mask.end()));

    cout << ans.size() << endl;
}

int main() {
    fastio;
    readInput();
    solve();
    return 0;
}
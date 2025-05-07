#include <iostream>
#include <vector>
#include <string>
#include <cstdlib>
#include <set>
#include <tuple>
#include <algorithm>

#define fastio cin.tie(0)->sync_with_stdio(0)
#define endl '\n'
#define watch(x) cout << (#x) << " is " << (x) << endl
#define I first
#define J second

using namespace std;
using ll = long long;
using VL = vector<ll>;
using PL = pair<ll, ll>;

vector<VL> board(9, VL(9, 0));
vector<VL> isUsedInRow(9, VL(10, 0)), isUsedInCol(9, VL(10, 0)), isUsedInSec(9, VL(10, 0));
vector<PL> coords;
ll N;

ll calcSector(ll i, ll j) { return (i / 3) * 3 + (j / 3); }
void readInput() {

    string row;
    for (ll i = 0; i < 9; ++i) {
        getline(cin, row);
        for (ll j = 0; j < 9; ++j){
            board[i][j] = row[j] - '0';
            if (board[i][j] == 0) {
                coords.push_back({ i, j });
            }
            else {
                isUsedInRow[i][board[i][j]] = 1;
                isUsedInCol[j][board[i][j]] = 1;
                ll secNum = calcSector(i, j);
                isUsedInSec[secNum][board[i][j]] = 1;
            }
        }
    }
}



void DFS(ll level) {
    if (level == N) {
        for (ll i = 0; i < 9; ++i) {
            for (ll j = 0; j<9; ++j) {
                cout << board[i][j];
            }cout << endl;
        }
        exit(0);
    }

    auto [ci, cj] = coords[level];

    for (ll num = 1; num <= 9; ++num) {
        if (isUsedInRow[ci][num]) continue;
        if (isUsedInCol[cj][num]) continue;
        ll secNum = calcSector(ci, cj);
        if (isUsedInSec[secNum][num]) continue;

        isUsedInRow[ci][num] = 1,isUsedInCol[cj][num] = 1, isUsedInSec[secNum][num] = 1;
        board[ci][cj]= num;
        DFS(level + 1);
        board[ci][cj] = 0;
        isUsedInRow[ci][num] = 0, isUsedInCol[cj][num] = 0, isUsedInSec[secNum][num] = 0;
    }
}
void solve() {
    sort(coords.begin(), coords.end());
    N = coords.size();
    DFS(0);
}

int main() {
    fastio;
    readInput();
    solve();
    return 0;
}
#include <algorithm>
#include <iostream>
#include <tuple>
#include <vector>
#define endl '\n'
#define F first
#define S second

using namespace std;
const int MAX = (int)1e6;
int N, W;
vector<pair<int, int>> metals;

void solve() {
    int ans = 0, i = 0;
    while (W) {
        if (metals[i].S <= W) {
            ans += metals[i].S * metals[i].F;
            W -= metals[i].S;
        } else {
            ans += W * metals[i].F;
            break;
        }
        i++;
    }
    cout << ans << endl;
}
void read_user_input() {
    cin >> W >> N;
    for (int i = 0; i < N; i++) {
        int weight, cost;
        cin >> weight >> cost;
        metals.push_back({cost, weight});
    }
    sort(metals.begin(), metals.end(), greater<>());
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    read_user_input();
    solve();
    return 0;
}

#include <bits/stdc++.h>
#define endl '\n'
using namespace std;
const string s = "wbwbwwbwbwbw";
int W, B;

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> W >> B;
    const int N = s.size();
    for (int i = 0; i < N; i++) {
        int nw = 0, nb = 0;

        for (int j = 0; j < W + B; j++) {
            if (s[(i + j) % N] == 'w')
                ++nw;
            else
                ++nb;
        }

        if (nw == W && nb == B) {
            cout << "Yes" << endl;
            return 0;
        }
    }
    cout << "No" << endl;
    return 0;
}
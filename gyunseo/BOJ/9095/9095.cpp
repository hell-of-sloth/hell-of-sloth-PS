#include <iostream>
#define endl '\n'

using namespace std;

int T, n;
int dp_table[12];
void solve(int num) { cout << dp_table[num] << endl; }
void read_user_input() {
    dp_table[1] = 1;
    dp_table[2] = 2;
    dp_table[3] = 4;
    for (int i = 4; i <= 11; i++) {
        dp_table[i] = dp_table[i - 1] + dp_table[i - 2] + dp_table[i - 3];
    }
    cin >> T;
    for (int i = 0; i < T; i++) {
        cin >> n;
        solve(n);
    }
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    read_user_input();
    return 0;
}

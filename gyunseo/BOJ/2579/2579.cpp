#include <iostream>
#define endl '\n'
using namespace std;

const int MAX = 300;
int num_steps, steps[MAX + 1];
int dp_table[MAX + 1];
void solve() {
    dp_table[0] = 0;
    dp_table[1] = steps[1];
    dp_table[2] = dp_table[1] + steps[2];
    for (int i = 3; i <= num_steps; i++) {
        dp_table[i] =
            max(dp_table[i - 2], dp_table[i - 3] + steps[i - 1]) + steps[i];
        // cout << i << "step: " << dp_table[i] << endl;
    }
    cout << dp_table[num_steps] << endl;
}

void read_user_input() {
    cin >> num_steps;
    for (int i = 1; i <= num_steps; i++) {
        cin >> steps[i];
        // cout << "step: " << steps[i] << endl;
    }
}
int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    read_user_input();
    solve();
    return 0;
}

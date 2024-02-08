#include <iostream>
#define endl '\n'

using namespace std;
int N, r, c;

int f(int i, int j, int n) {
    if (n == 0) return 0;
    int half_size = (1 << n - 1);
    if (i < half_size && j < half_size) return f(i, j, n - 1);
    if (i < half_size && j >= half_size) return half_size * half_size + f(i, j - half_size, n - 1); 
    if (i >= half_size && j < half_size) return 2 * half_size * half_size + f(i - half_size, j, n - 1);
    else return 3 * half_size * half_size + f(i - half_size, j - half_size, n - 1);
}
void solve() {
    cout << f(r, c, N) << endl;
}

void read_user_input() {
    cin >> N >> r >> c;
}
int main() { 
    ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    read_user_input();
    solve();
    return 0; 
}


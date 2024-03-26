#include <bits/stdc++.h>
#define VI vector<int>
#define endl '\n'

using namespace std;
int N;
VI A, B;
int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N;
    for (int i = 0; i < N; i++) {
        int e;
        cin >> e;
        A.push_back(e);
    }
    for (int i = 0; i < N - 1; i++) {
        B.push_back(A[i] * A[i + 1]);
    }
    for (auto &e : B) {
        cout << e << " ";
    }
    cout << endl;
    return 0;
}

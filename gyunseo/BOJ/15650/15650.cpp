#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
int N, M;
int main() {
    ios_base::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    cin >> N >> M;
    // N C M
    vector<int> comb;
    for (int i = 0; i < M; i++) comb.push_back(0);
    for (int i = 0; i < (N - M); i++) comb.push_back(1);
    do {
        for (int i = 0; i < N; i++) {
            if (comb[i] == 0)
                cout << i + 1 << " ";
        }
        cout << endl;
    }while(next_permutation(comb.begin(), comb.end()));
}


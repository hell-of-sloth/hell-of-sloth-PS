#include <iostream>

using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int N;
    int visit[100001] = { 0, };
    cin >> N;

    int L[N];
    for (int i = 0; i < N; i++) {
        int input;
        cin >> input;
        L[i] = input;
    }
    
    long long count = 0;
    int end = 0;

    for (int start = 0; start < N; start++) {
        while (end < N) {
            if (visit[L[end]] == 1) {
                break;
            }
            visit[L[end]] = 1;
            end++;
        }
        count += (end - start);
        visit[L[start]] = 0;
    }
    
    cout << count;
}
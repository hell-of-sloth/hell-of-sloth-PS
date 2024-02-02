#include <algorithm>
#include <iostream>
#define endl '\n'

using namespace std;

const int MAX = (int)1e7;
int N, M;
int arr[MAX];

int getLowerIdx(int target) {
    // s와 e가 같을 때 s값이 lower_bound
    int s = 0, e = N, mid;
    while (s < e) {
        mid = (s + e) / 2;

        if (target <= arr[mid]) {
            e = mid;
            continue;
        }
        if (target > arr[mid]) {
            s = mid + 1;
            continue;
        }
    }
    return s;
}
int getUpperIdx(int target) {
    int s = 0, e = N, mid;
    // s와 e가 같을 때 s값이 upper_bound
    while (s < e) {
        mid = (s + e) / 2;
        if (target < arr[mid]) {
            e = mid;
            continue;
        }
        if (target >= arr[mid]) {
            s = mid + 1;
            continue;
        }
    }
    return s;
}

void ReadUserInput() {
    int n;
    cin >> N;
    for (int i = 0; i < N; i++) {
        cin >> arr[i];
    }
    sort(arr, arr + N);
    cin >> M;
    for (int i = 0; i < M; i++) {
        cin >> n;
        cout << getUpperIdx(n) - getLowerIdx(n) << " ";
    }
}

int main() {

    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    ReadUserInput();
    return 0;
}

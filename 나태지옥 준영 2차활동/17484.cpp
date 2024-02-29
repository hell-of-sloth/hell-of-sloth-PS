#include <iostream>
#include <algorithm>

using namespace std;

int N, M;
int min_fuel = 1000000;

void solve(int** arr, int n, int m, int fuel, char direction) { // arr : 연료 배열, n: 세로, m: 가로, fuel: 현재까지의 연료, direction: 이전에 이동한 방향

    if (n == N-1) { // 마지막 줄에 도착했을 때
        min_fuel = min(min_fuel, fuel + arr[n][m]); // 최소 연료량 갱신
        return;
    }

    // 이전 이동 방향에 따라 이동 재귀호출 (이전에 갔던 방향 못 감)
    if (direction == 'l') {
        solve(arr, n+1, m, fuel + arr[n][m], 'd');
        if (m+1 < M) solve(arr, n+1, m+1, fuel + arr[n][m], 'r');
    }
    else if (direction == 'r') {
        solve(arr, n+1, m, fuel + arr[n][m], 'd');
        if (m-1 >= 0) solve(arr, n+1, m-1, fuel + arr[n][m], 'l');
    }
    else if (direction == 'd') {
        if (m+1 < M) solve(arr, n+1, m+1, fuel + arr[n][m], 'r');
        if (m-1 >= 0) solve(arr, n+1, m-1, fuel + arr[n][m], 'l');
    }
    else {
        solve(arr, n+1, m, fuel + arr[n][m], 'd');
        if (m+1 < M) solve(arr, n+1, m+1, fuel + arr[n][m], 'r');
        if (m-1 >= 0) solve(arr, n+1, m-1, fuel + arr[n][m], 'l');
    }
    
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    cin >> N >> M;

    int** fuel_map;
    fuel_map = new int*[N];
    for (int i = 0; i < N; i++) fuel_map[i] = new int[M];

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) cin >> fuel_map[i][j];
    }

    for (int i = 0; i < M; i++) {
        solve(fuel_map, 0, i, 0, '0');
    }
    cout << min_fuel;

    return 0;
}
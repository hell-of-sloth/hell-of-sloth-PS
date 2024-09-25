/*
한 명씩 앉을 수 있는 테이블이 행마다 W개씩 H행에 걸쳐 있을 때, 
모든 참가자는 세로로 N칸 또는 가로로 M칸 이상 비우고 앉아야 한다. 
즉, 다른 모든 참가자와 세로줄 번호의 차가 N보다 크거나 가로줄 번호의 
차가 M보다 큰 곳에만 앉을 수 있다.

논문과 과제에 시달리는 성우를 위해 강의실이 거리두기 수칙을 지키면서 
최대 몇 명을 수용할 수 있는지 구해보자.
*/

#include <iostream>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int H, W, N, M;

    cin >> H >> W >> N >> M;

    int H_count, W_count;

    H_count = H / (N+1);
    W_count = W / (M+1);
    if (H % (N+1) != 0) H_count++;
    if (W % (M+1) != 0) W_count++;

    cout << H_count * W_count;

    return 0;
}
/*
인하대학교 후문 뒤쪽에는 어두운 굴다리가 있다.
겁쟁이 상빈이는 길이 조금이라도 어둡다면 가지 않는다. 
따라서 굴다리로 가면 최단거리로 집까지 갈수 있지만, 
굴다리는 어둡기 때문에 빙빙 돌아서 집으로 간다. 안타깝게 여긴 인식이는 
굴다리 모든 길 0~N을 밝히게 가로등을 설치해 달라고 인천광역시에 민원을 넣었다. 
인천광역시에서 가로등을 설치할 개수 M과 각 가로등의 위치 x들의 결정을 끝냈다. 
그리고 각 가로등은 높이만큼 주위를 비출 수 있다. 하지만 갑자기 예산이 
부족해진 인천광역시는 가로등의 높이가 높을수록 가격이 비싸지기 때문에 
최소한의 높이로 굴다리 모든 길 0~N을 밝히고자 한다. 
최소한의 예산이 들 높이를 구하자. 

단 가로등은 모두 높이가 같아야 하고, 정수이다.
*/

#include <iostream>
#include <queue>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int N, M;
    cin >> N >> M;

    int result = 0;

    if (M == 1) {
        int dist;
        cin >> dist;
        if (dist < N - dist) {
            result = N - dist;
        }
        else {
            result = dist;
        }
    }
    else {
        priority_queue<int> pq;
        int distance[M];
        for (int i = 0; i < M; i++) {
            cin >> distance[i];
            if (i != 0) {
                pq.push(distance[i] - distance[i - 1]);
            }
        }

        int temp_result = pq.top();

        if (temp_result % 2 == 0) {
            result = temp_result / 2;
        }
        else {
            result = temp_result / 2 + 1;
        }

        if (distance[0] > result) {
            result = distance[0];
        }
        if (N - distance[M - 1] > result) {
            result = N - distance[M - 1];
        }
    }

    cout << result;

    return 0;
}
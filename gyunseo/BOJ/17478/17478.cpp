#include <iostream>
#define endl '\n'
using namespace std;
// N(1 ≤ N ≤ 50)
int N;
void print_str_with_prefix_underbars(const string &str, int num) {
    for (int i = 0; i < num; i++)
        cout << "____";
    cout << str << endl;
}

void recursion(int n) {
    // 반복해야 하는 string들
    print_str_with_prefix_underbars("\"재귀함수가 뭔가요?\"", n);
    if (n == N) {

        print_str_with_prefix_underbars(
            "\"재귀함수는 자기 자신을 호출하는 함수라네\"", n);
        print_str_with_prefix_underbars("라고 답변하였지.", n);
        return;
    }

    print_str_with_prefix_underbars("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 "
                                    "이세상 모든 지식을 통달한 선인이 있었어.",
                                    n);
    print_str_with_prefix_underbars("마을 사람들은 모두 그 선인에게 수많은 "
                                    "질문을 했고, 모두 지혜롭게 대답해 주었지.",
                                    n);
    print_str_with_prefix_underbars(
        "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 "
        "찾아와서 물었어.\"",
        n);
    recursion(n + 1);

    print_str_with_prefix_underbars("라고 답변하였지.", n);
}
int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);
    cin >> N;
    cout << "어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다."
         << endl;
    recursion(0);
    return 0;
}
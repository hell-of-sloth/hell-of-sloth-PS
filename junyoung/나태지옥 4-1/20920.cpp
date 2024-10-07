#include <iostream>
#include <algorithm>
#include <string>
#include <vector>
#include <map>

using namespace std;

int N, M;
vector <string> word_list;
map<string, int> word_count_list;
string word;

bool cmp(string a, string b) {
    int a_size = a.size();
    int b_size = b.size();

    if (a_size == b_size && word_count_list[a] == word_count_list[b]) {
        return a < b;
    }
    else if (word_count_list[a] == word_count_list[b]) {
        return a_size > b_size;
    }
    return word_count_list[a] > word_count_list[b];
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    cin >> N >> M;
    for (int i = 0; i < N; i++) {
        cin >> word;
        if (word.size() >= M) {
            if (word_count_list.find(word)==word_count_list.end()) {
                word_count_list[word] = 0;
                word_list.push_back(word);
            }
            word_count_list[word]++;
        }
    }
    sort(word_list.begin(), word_list.end(), cmp);

    for (auto word : word_list) {
        cout << word << "\n";
    }
}
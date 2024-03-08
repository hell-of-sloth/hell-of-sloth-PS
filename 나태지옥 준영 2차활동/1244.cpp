/*
설명이 길어서 링크로 남김
https://www.acmicpc.net/problem/1244
*/

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int student_N, switch_N;
int switches[101];

void man(int num)
{
    for (int i = num; i <= switch_N; i += num)
    {
        switches[i] = !switches[i];
    }
}

void woman(int num)
{
    int left = num - 1;
    int right = num + 1;

    switches[num] = !switches[num];

    while (left >= 1 && right <= switch_N)
    {
        if (switches[left] == switches[right])
        {
            switches[left] = !switches[left];
            switches[right] = !switches[right];
            left--;
            right++;
        }
        else
        {
            break;
        }
    }
}

int main()
{
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    vector<pair<int, int>> student;

    cin >> switch_N;
    for (int i = 1; i <= switch_N; i++)
    {
        cin >> switches[i];
    }
    cin >> student_N;
    for (int i = 0; i < student_N; i++)
    {
        int gender, num;
        cin >> gender >> num;
        student.push_back({gender, num});
    }

    for (auto s : student)
    {
        if (s.first == 1)
        {
            man(s.second);
        }
        else
        {
            woman(s.second);
        }
    }

    for (int i = 1; i <= switch_N; i++)
    {
        cout << switches[i] << " ";
        if (i % 20 == 0)
        {
            cout << "\n";
        }
    }

    return 0;
}
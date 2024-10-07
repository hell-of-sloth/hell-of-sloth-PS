/*
a와 b로만 이루어진 문자열이 주어질 때,  
a를 모두 연속으로 만들기 위해서 필요한 교환의 회수를
최소로 하는 프로그램을 작성하시오.

이 문자열은 원형이기 때문에, 처음과 끝은 서로 인접해 있는 것이다.

예를 들어,  aabbaaabaaba이 주어졌을 때, 2번의 교환이면 a를 모두 
연속으로 만들 수 있다.
*/

#include <iostream>
#include <string>

using namespace std;

int sovle(string s)
{
    int len = s.length();
    int b_count = 0, max_b_count = 0;

    for (int i = 0; i < len; i++)
    {
        if (s[i] == 'b')
        {
            b_count++;
        }
    }

    for (int i = 0; i < len; i++)
    {
        int start = i, end = (i + b_count - 1) % len;
        int temp_b_count = 0;
        string temp_str1, temp_str2, temp_str;

        if (start <= end)
        {
            temp_str = s.substr(start, b_count);
            // cout << temp_str << " 1" << endl;
            for (auto c : temp_str)
            {
                if (c == 'b')
                {
                    temp_b_count++;
                }
            }
        }
        else
        {
            temp_str1 = s.substr(0, end+1);
            temp_str2 = s.substr(start);

            // cout << temp_str1 << temp_str2 << " 2" << endl;

            for (auto c : temp_str1)
            {
                if (c == 'b')
                {
                    temp_b_count++;
                }
            }
            for (auto c : temp_str2)
            {
                if (c == 'b')
                {
                    temp_b_count++;
                }
            }
        }

        if (temp_b_count > max_b_count)
        {
            max_b_count = temp_b_count;
        }
    }

    return b_count - max_b_count;
}

int main()
{

    string s;
    cin >> s;

    cout << sovle(s) << endl;

    return 0;
}
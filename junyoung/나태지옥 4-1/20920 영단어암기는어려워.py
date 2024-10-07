import sys

N, M = map(int, sys.stdin.readline().split())

word_list = {}

for _ in range(N):
    word = sys.stdin.readline().strip()
    if len(word) >= M:
        if word in word_list:
            word_list[word] += 1
        else:
            word_list[word] = 1
            
word_list = sorted(word_list.items(), key=lambda x: (-x[1], -len(x[0]), x[0])) # 빈도수 내림차순, 단어 길이 내림차순, 단어 사전순

for item in word_list:
    print(item[0])
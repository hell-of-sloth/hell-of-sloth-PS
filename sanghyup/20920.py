import sys

input = sys.stdin.readline

dics = {}
words = set()
n , length = map(int, input().split())

for i in range(n):
    word = input().strip()
    if len(word)>=length:
        words.add(word)
        if word in dics:
            dics[word] +=1
        else:
            dics[word] = 1

words = list(words)
words.sort()
words.sort(key= lambda x : len(x), reverse=True)
words.sort(key = lambda x : dics[x], reverse=True)
print(*words, sep= '\n')
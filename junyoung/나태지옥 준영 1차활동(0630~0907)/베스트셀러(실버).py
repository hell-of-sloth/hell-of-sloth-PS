import sys

N = int(sys.stdin.readline().rstrip())

book_L = []
count_L = []

for i in range(N):
    book = sys.stdin.readline().rstrip()
    if book in book_L:
        count_L[book_L.index(book)] += 1
    else:
        book_L.append(book)
        count_L.append(1)
        
max_count = max(count_L)
result = []
for i in range(len(count_L)):
    if count_L[i] == max_count:
        result.append(book_L[i])
        
print(sorted(result)[0])
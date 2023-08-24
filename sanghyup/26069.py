n = int(input())

contaminated = set(['ChongChong'])

for i in range(n):
    a, b = input().split()

    if a in contaminated or b in contaminated:
        contaminated.add(a)
        contaminated.add(b)

print(len(contaminated))
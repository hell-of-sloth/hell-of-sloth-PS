names = set()

for i in range(int(input())):
    name, state = input().split()
    if state == 'enter':
        names.add(name)
    else:
        names.discard(name)
for name in sorted(list(names), reverse=True):
    print(name)
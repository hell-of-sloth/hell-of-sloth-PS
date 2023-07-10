import sys

targetNum = int(sys.stdin.readline().rstrip())
n = int(sys.stdin.readline())
broken = list(sys.stdin.readline().rstrip().rsplit())

underpushedButtons = 0
overpushedButtons = 0

for checkNum in range(targetNum, -2, -1):
    if checkNum == -1:
        underpushedButtons = 999999999
        break
    checkNum = str(checkNum)
    useBrokenKey = False
    for num in broken:
        if num in checkNum:
            useBrokenKey = True
    if useBrokenKey == False:
        underpushedButtons += len(checkNum)
        break
    underpushedButtons+=1

for checkNum in range(targetNum, 1000002):
    if checkNum ==1000001:
        overpushedButtons = 999999999
        break
    checkNum = str(checkNum)
    useBrokenKey = False
    for num in broken:
        if num in checkNum:
            useBrokenKey = True
    if useBrokenKey == False:
        overpushedButtons += len(checkNum)
        break
    overpushedButtons+=1

fromDefault = abs(targetNum - 100)

print(min(overpushedButtons, underpushedButtons, fromDefault))
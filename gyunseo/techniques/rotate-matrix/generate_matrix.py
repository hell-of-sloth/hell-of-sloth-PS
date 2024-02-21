import sys

sys.stdout = open("input.txt", "w")
print = sys.stdout.write

matrix = [[10 * i + j for j in range(10)] for i in range(10)]
for i in range(10):
    for j in range(10):
        print(f"{matrix[i][j]} ")
    print("\n")

sys.stdout.close()

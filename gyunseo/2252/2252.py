import sys

sys.stdin = open("input.txt", "r")
input = sys.stdin.readline
print = sys.stdout.write


N, M = map(int, input().rstrip().split())
student_topology = []

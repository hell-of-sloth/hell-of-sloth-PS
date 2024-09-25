import sys

N, M = map(int, sys.stdin.readline().split())

sites_pwd = {}
for _ in range(N):
    site, pwd = sys.stdin.readline().split()
    sites_pwd[site] = pwd
    
for _ in range(M):
    site = sys.stdin.readline().strip()
    print(sites_pwd[site])
# Copyright 2023 gyunseo
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

import sys


# sys.stdin = open("input.txt", "r")
input = sys.stdin.readline
print = sys.stdout.write

N = int(input().rstrip())
T = []
P = []
dp = [0] * (N + 1)
for i in range(N):
    T_i, P_i = map(int, input().rstrip().split())
    T.append(T_i)
    P.append(P_i)
ans = 0

for i in range(N - 1, -1, -1):

    def check_is_valid_date_for_consult():
        if (i + T[i]) > N:
            return False
        return True

    if check_is_valid_date_for_consult():
        dp[i] = max(ans, P[i] + dp[i + T[i]])
        ans = dp[i]
        continue

    dp[i] = ans

print(f"{ans}\n")

#include <bits/stdc++.h>
using namespace std;

const int MX = 1000005;
char dat[MX];
int pre[MX], nxt[MX];
int unused = 1;

void traverse() {
  int cur = nxt[0];
  while (cur != -1) {
    cout << dat[cur];
    cur = nxt[cur];
  }
}

void insert(int addr, char c) {
  dat[unused] = c;
  pre[unused] = addr;
  nxt[unused] = nxt[addr];
  if (nxt[addr] != -1) pre[nxt[addr]] = unused;
  nxt[addr] = unused;
  unused++;
}

void erase(int addr) {
  nxt[pre[addr]] = nxt[addr];
  if (nxt[addr] != -1) pre[nxt[addr]] = pre[addr];
}

int main() {
  // ios::sync_with_stdio(0);
  // cin.tie(0);

  fill(pre, pre + MX, -1);
  fill(nxt, nxt + MX, -1);
  int it = 0;

  string init;
  int N;
  cin >> init;
  cin >> N;
  for (auto c : init) insert(it++, c);

  char op, key;
  while (N--) {
    cin >> op;
    if (op == 'L' && pre[it] != -1)
      it = pre[it];
    else if (op == 'D' && nxt[it] != -1)
      it = nxt[it];
    else if (op == 'B') {
      if (it == 0) continue;
      erase(it);
      it = pre[it];
    } else if (op == 'P') {
      cin >> key;
      insert(it, key);
      it = nxt[it];
    }
  }
  traverse();
}
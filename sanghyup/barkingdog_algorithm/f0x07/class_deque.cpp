#include <bits/stdc++.h>
using namespace std;

const int MX = 1000005;
int dat[2 * MX + 1];
int head = MX, tail = MX;

void push_front(int x) { dat[--head] = x; }

void push_back(int x) { dat[tail++] = x; }

void pop_front() { head++; }

void pop_back() { tail--; }

int front() { return dat[head]; }

int back() { return dat[tail - 1]; }

void traverse() {
  for (int i = head; i < tail; i++) {
    cout << dat[i] << ' ';
  }
  cout << '\n';
}

void test() {
  push_back(30);  // 30
  traverse();
  cout << front() << '\n';  // 30
  cout << back() << '\n';   // 30
  push_front(25);           // 25 30
  traverse();
  push_back(12);  // 25 30 12
  traverse();
  cout << back() << '\n';  // 12
  push_back(62);           // 25 30 12 62
  traverse();
  pop_front();  // 30 12 62
  traverse();
  cout << front() << '\n';  // 30
  pop_front();              // 12 62
  traverse();
  cout << back() << '\n';  // 62
}

int main(void) { test(); }
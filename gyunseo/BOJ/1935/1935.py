import sys

input = sys.stdin.readline


def is_alphabet(ch):
    if ch >= "A" and ch <= "Z":
        return True
    if ch >= "a" and ch <= "z":
        return True
    return False


if __name__ == "__main__":
    N = int(input().rstrip())
    post = input().rstrip()
    alphabet = {}
    operation = {
        "+": lambda x, y: x + y,
        "-": lambda x, y: x - y,
        "*": lambda x, y: x * y,
        "/": lambda x, y: x / y,
    }
    for i in range(N):
        alphabet[chr(ord("A") + i)] = int(input().rstrip())

    calcStack = []
    operatorStack = []
    for ch in post:

        if is_alphabet(ch):
            calcStack.append(alphabet[ch])
        else:
            operatorStack.append(ch)

        if len(calcStack) >= 2 and operatorStack:
            x, y = calcStack[-2], calcStack[-1]
            del calcStack[-2:]
            calcStack.append(operation[operatorStack.pop()](x, y))

    print(f"{calcStack[-1]:0.2f}")

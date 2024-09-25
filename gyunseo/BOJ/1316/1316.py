import sys

# sys.stdin = open("input.txt", "r")

input = sys.stdin.readline
print = sys.stdout.write


N = int(input().rstrip())
words = [input().rstrip() for _ in range(N)]

alphabets = [chr(ord("a") + i) for i in range(26)]
# print(f"{alphabets}\n")


def check_is_group_word(word_arg):
    word_len = len(word_arg)

    def get_compressed_word():
        ret_compressed_word = ""
        iteration_trigger = True
        s = e = 0
        while s <= e:
            e += 1
            if e == word_len:
                ret_compressed_word += word_arg[s]
                break

            if word_arg[s] != word_arg[e]:
                ret_compressed_word += word_arg[s]
                s = e
                continue

        return ret_compressed_word

    compressed_word = get_compressed_word()
    for alphabet in alphabets:
        if compressed_word.count(alphabet) > 1:
            return False

    return True


ans = 0
for word in words:
    if check_is_group_word(word):
        ans += 1


print(f"{ans}\n")

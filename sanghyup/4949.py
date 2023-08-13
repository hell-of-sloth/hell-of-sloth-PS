while True:
    sent = input()
    if sent == '.':
        break
    stack = []
    harmony = True
    for alph in sent:
        if alph == '[' or alph == '(':
            stack.append(alph)
        if alph == ']':
            if len(stack) == 0 or stack.pop() !='[':
                harmony = False
                break
        elif alph == ')':
            if len(stack) == 0 or stack.pop() !='(':
                harmony = False
                break
    if harmony and len(stack)==0:
        print('yes')
    else: print('no')
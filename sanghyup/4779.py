def cantor(n):
    if n == 0:
        return '-'
    else:
        prev = cantor(n-1)
        return prev + ' '*len(prev)+prev
    
while True:    
    try:
        print(cantor(int(input())))
    except:
        exit()
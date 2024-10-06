import sys

N = int(sys.stdin.readline().rstrip())
count = 0

def curve_converter(curve): # 드래곤 커브한 새 커브를 반환
    
    reverse_curve = curve[::-1]
    increase_curve = []
    
    for i in range(len(reverse_curve)-1):
        first_x, first_y = reverse_curve[i+1]
        second_x, second_y = reverse_curve[i]
        tmp_x = first_x - second_x
        tmp_y = first_y - second_y
        
        if tmp_x == 1:
            increase_curve.append((0, 1))
        elif tmp_x == -1:
            increase_curve.append((0, -1))
        elif tmp_y == 1:
            increase_curve.append((-1, 0))
        elif tmp_y == -1:
            increase_curve.append((1, 0))
            
    start_point = curve[-1]
    new_curve = curve[:]
    
    for direc in increase_curve:
        new_point = (start_point[0] + direc[0], start_point[1] + direc[1])
        new_curve.append(new_point)
        start_point = new_point
        
    return new_curve
        
def dragon_curve(x, y, d, g): # 드래곤 커브의 좌표를 반환
    curve_position = [(x, y)]
    
    if d == 0:
        curve_position.append((x + 1, y))
    elif d == 1:
        curve_position.append((x, y - 1))
    elif d == 2:
        curve_position.append((x - 1, y))
    elif d == 3:
        curve_position.append((x, y + 1))
        
    for _ in range(g):
        curve_position = curve_converter(curve_position)
        
    return curve_position

def find_square(matrix): # 정사각형의 개수를 찾음
    global count
    
    for i in range(100):
        for j in range(100):
            if matrix[i][j] == 1:
                if matrix[i+1][j] == 1 and matrix[i][j+1] == 1 and matrix[i+1][j+1] == 1:
                    count += 1


# main

all_points = []
matrix = [[0] * 101 for _ in range(101)]

for _ in range(N):
    x, y, d, g = map(int, sys.stdin.readline().split())
    
    all_points += dragon_curve(x, y, d, g)
    
    all_points = list(set(all_points))
    
for point in all_points:
    matrix[point[0]][point[1]] = 1
    
find_square(matrix)
print(count)
import sys
import datetime

print = sys.stdout.write

today = datetime.datetime.today()

print(f"{today.strftime('%Y-%m-%d')}\n")

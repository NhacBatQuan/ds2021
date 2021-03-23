from operator import itemgetter
import sys

current_count = 0
current_path = None
count = 0

for line in sys.stdin:
    line = line.strip()
    path,count = line.split("\t",1)
    try:
        count = int(count)
    except:
        continue
    if current_count == count:
        current_path+=path+"\n"
    else:
        if current_path:
            print(current_path+"\t"+str(current_count))
        current_count = count
        current_path = path
if current_path == path:
    print(current_path+"\t"+str(current_count))
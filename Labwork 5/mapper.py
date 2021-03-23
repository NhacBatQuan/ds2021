import sys 

for line in sys.stdin:
    line = line.strip()
    count = len(line.split("/"))
    print(line+"\t"+count)


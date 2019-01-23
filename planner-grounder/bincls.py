#!/usr/bin/python
#
# bincls.py
#  
# Started on  Mon Nov  4 15:00:49 2013 
# Last update Mon Nov  4 15:47:56 2013 PaT
# 

MAPPING_FILE="testinstances-output/testinstances/own/dungeons/v0/dungeon_i20-m20-u3-v0.pddl_DepQBF_and_minisat2_core.finished"
QDIMACS_FILE="testinstances-output/testinstances/own/dungeons/v0/dungeon_i20-m20-u3-v0.pddl_planlen=6.qdimacs"
TOK_BEGIN_MAPPING=">>> start of variable mapping"
TOK_END_MAPPING="<<< end of variable mapping"

def read_qdimacs():
    twocls = []
    with open(QDIMACS_FILE, "r") as f:
        content = f.readlines()
    content = map(lambda x: x.split(),content)
    content = filter(lambda x: x[0].isdigit() or (x[0].startswith('-') and x[0][1:].isdigit()), content)
    for cl in content:
        if cl[-1] != '0':
            print cl
            print "WAH! Clause not properly terminated!"
            exit(1)
    content = map(lambda x: x[0:-1],content)
    content = map(lambda x: map(int,x),content)
    content = filter(lambda x: len(x)==2,content)
#    content = map(len,content)
    return content

def read_mapping():
    interesting = False
    mapping = {}
    with open(MAPPING_FILE, "r") as f:
        for line in f:
            if (line.startswith(TOK_BEGIN_MAPPING)):
                interesting = True
            elif (line.startswith(TOK_END_MAPPING)):
                interesting = False
            elif (interesting):
                k = line.split()
                mapping[k[0]] = k[1]
    return mapping

def get_maxkey(mapping):
    maxkey = max(mapping.keys())
    return int(maxkey)

varmap = read_mapping()
varnum = get_maxkey(varmap)
print varnum

binclauses = read_qdimacs()

#print binclauses

for [x,y] in binclauses:
    if ([-x,-y] in binclauses):
        print "Found pair (" + str(x) + ", " + str(y) +") and (" + str(-x) + ", " + str(-y) +")"
        print str(abs(x))+": "+varmap[str(abs(x))]
        print str(abs(y))+": "+varmap[str(abs(y))] + "\n"

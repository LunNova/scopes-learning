using import Array
using import String
using import itertools
using import ..lun

inline score-strategy-guide (guide round-score)
    # itertools approach
    ->>
        string-split-generator guide h"\n"
        map
            inline (x)
                _ ((x @ 0) - h"A") ((x @ 2) - h"X")
        map round-score
        reduce-sum 0

    # # manual loop approach
    # local score = 0
    # for line in (string-split-generator guide h"\n")
    #     let opponent ours = (line @ 0) (line @ 2)
    #     let opponent ours = (opponent - h"A") (ours - h"X")

    #     score += (round-score opponent ours)
    # score


let test-input = """"A Y
                     B X
                     C Z

fn first-round-score (opponent ours)
    returning i32
    # typeof ours == i32 or i8 ...
    +
        if (opponent == ours)
            3 # draw
        elseif (((ours + 1) % 3) == opponent)
            0 # loss
        else
            6 # win
        (copy ours) # must copy due to error: view of value of type (viewof i32 1030) can not be moved out of its scope
        1

fn second-round-score (opponent ours)
    returning i32
    first-round-score opponent (mod (opponent + (ours - 1)) 3)


print (score-strategy-guide (test-input as String) first-round-score)
print (score-strategy-guide (test-input as String) second-round-score)

print (score-strategy-guide (read-file "aoc/input-2.txt") first-round-score)
print (score-strategy-guide (read-file "aoc/input-2.txt") second-round-score)

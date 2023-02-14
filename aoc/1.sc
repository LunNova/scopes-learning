using import Array
using import Option
using import String
using import UTF-8

using import ..lun

local current-calories = 0:i64


let input = (read-file "./aoc/input-1.txt")

local calories : (GrowingArray i64)

for i part in (enumerate (string-split-generator input 10))
    print "Line:" part
    if ((countof part) > 0)
        dispatch (atoi part)
        case Some (val)
            current-calories += val
        default
            error "invalid line" part
    else
        'append calories current-calories
        current-calories = 0

if (current-calories > 0)
    'append calories current-calories

'sort calories (inline (a) (- a))

using import itertools

print (->> calories (take 3) (reduce-sum 0:i64))

print (->> (reverse-generator calories) (take 3) (reduce-sum 0:i64))

print (fold (sum = 0:i64) for i in (->> calories (take 3) '()) (sum + (i as i64)))

print (->> calories (take 3) (reduce 0:i64 (inline (acc next) (acc + next))))

# i hope this isn't the best way to sum the last 3 elems in an array

local sum = 0:i64
let count = (countof calories)
for idx in (range (count - 3) count)
    sum += calories @ idx
print sum

# print (typeof (slice "test" 0 1))

using import String
using import Array
using import ..lun

let sample-input =
    S""""vJrwpWtwJgWrhcsFMMfFFhFp
         jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
         PmmdzqPrVvPwwTWBwg
         wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
         ttgJtRGJQctTZtZT
         CrZsJsPPZsGzwwsLwLmpwMDw

let input = (read-file "aoc/input-3.txt")

print sample-input

fn item-priority (chr)
    if (chr >= h"A" and chr <= h"Z")
        27 + (chr - h"A")
    elseif (chr >= h"a" and chr <= h"z")
        1 + (chr - h"a")
    else
        error "invalid chr" chr

fn split-rucksacks (line)
    let mid = ((countof line) // 2)
    let first second = (slice line 0 mid) (slice line mid (countof line))
    _ first second

# is there some xor magic for this
fn find-common (first second)
    for fchr in first
        for schr in second
            if (fchr == schr)
                return (copy fchr)
    error "no common item in " first "and" second

fn str-has-char (str chr)
    for c in str
        if (chr == c)
            return true
    false

fn find-common-n (lists)
    returning i8
    viewing lists

    for chr in (lists @ 0)
        if
            ->>
                'forward lists 1
                map
                    inline (str)
                        str-has-char str chr
                reduce true (inline (aggr next) (aggr and next))
            return (copy chr)

    error "no common item in lists"

fn sum-priorities (input)
    returning i32

    ->>
        string-split-generator input h"\n"
        map split-rucksacks
        map find-common
        map item-priority
        reduce-sum 0

fn sum-group-priorities (input)
    returning i32
    local dst : (Array String)

    ->>
        string-split-generator input h"\n"
        cascade
            take 3
            view dst
        map (inline (lists) (defer 'clear lists) (find-common-n lists))
        map item-priority
        reduce-sum 0


print (sum-priorities sample-input)
print (sum-priorities input)

print (sum-group-priorities sample-input)
print (sum-group-priorities input)
;

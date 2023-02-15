using import String
using import Array
using import ..lun

let sample-input =
    (""""vJrwpWtwJgWrhcsFMMfFFhFp
         jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
         PmmdzqPrVvPwwTWBwg
         wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
         ttgJtRGJQctTZtZT
         CrZsJsPPZsGzwwsLwLmpwMDw
    as String)

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
    let first rest = (decons lists)

    for chr in (first as String)
        local all-have = true
        for l in rest
            if (not (str-has-char (l as String) chr))
                all-have = false
                break
        if all-have
            return (copy chr)

    error "no common item in " lists

fn sum-priorities (input)
    ->>
        string-split-generator input h"\n"
        map split-rucksacks
        map find-common
        map item-priority
        reduce-sum 0

fn sum-group-priorities (input)
    ->>
        string-split-generator input h"\n"
        cascade
            take 3
            'cons-sink '()
        map find-common-n
        map item-priority
        reduce-sum 0


print (sum-priorities sample-input)
print (sum-priorities input)

print (sum-group-priorities sample-input)
print (sum-group-priorities input)
;

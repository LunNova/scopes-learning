using import .lun
using import Array
using import itertools

let arr = ((Array i32) 0 1 2 3 4)
for idx result in (array-range-generator ((Array i32) 0 1 2 3 4) 1 3)
    print idx result

print (sum (map (array-range-generator arr 1 3) (inline (idx val) val)))

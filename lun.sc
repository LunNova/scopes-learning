using import Array
using import Option
using import String
using import UTF-8
using import itertools

fn string-scan-next (input start_pos search_char)
    returning (uniqueof (Option usize) 1001)
    for i in (range start_pos (countof input))
        if (input @ i == search_char)
            return (i as (Option usize))
    return (none as (Option usize))

spice prefix:h (str)
    let str = (sc_string_unescape (sc_const_string_extract str))
    if ((countof str) != 1)
        error "prefix:h should only be used for single byte strings"
    str @ 0

inline array-range-generator (input start end)
    if (end >= (countof input))
        error "end index after end of array" end (countof input)
    Generator
        inline ()
            0:usize
        inline (i)
            i < end and i < (countof input)
        inline (i)
            _ i (input @ i)
        inline (i)
            i + 1


inline string-split-generator (input split_char)
    inline next (start)
        let next-match = (string-scan-next input start split_char)
        dispatch next-match
        case Some (val)
            return start val
        default
            return start (countof input)

    Generator
        inline ()
            (next 0:usize)
        inline (a b)
            #print a b
            a < (countof input)
        inline (a b)
            slice input a b
        inline (a b)
            (next (b + 1))

fn read-file (path)
    using import String
    using import C.stdio

    let stream = (fopen path "r")
    if (stream == null)
        error "couldn't open file" path
    if ((fseek stream 0 SEEK_END) != 0)
        error "failed to seek to end of" path
    let length = (ftell stream)
    if (length < 0)
        error "ftell failed for" path
    let length = (length as u64)
    if ((fseek stream 0 SEEK_SET) != 0)
        error "fseek failed for" path

    local buff = (malloc-array i8 length)
    if ((fread buff 1 length stream) != length)
        free buff
        error "failed to fread entire file for" path
    fclose stream

    let result = (String (buff as rawstring) length)
    free buff
    result

fn atoi (str)
    using import C.stdlib
    local test : (mutable@ i8) = null
    let input = (str as (@ i8))
    let result = (strtol input &test 10)
    if (test == input)
        return (none as (Option i64))
    return (result as (Option i64))

# can I get this to infer the init value instead of having to pass in a 0 of the right type?
inline reduce-sum (init)
    reduce init (inline (acc next) (acc + next))

inline reverse-generator (arr)
    Generator
        inline () ((countof arr) - 1)
        inline (i) (i >= 0)
        inline (i) (arr @ i)
        inline (i) (i - 1)

    
locals;

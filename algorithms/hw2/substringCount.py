global dictionary
global L

dictionary = set(["a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa"])
cache = {}
L = len(max(dictionary, key=len))


def substring_count(w):
    if len(w) == 0:
        return 1
    
    if w in cache:
        return cache[w]

    count = 0
    for index in range(min(len(w), L)+1):
        sub = w[:index]
        if sub in dictionary:
            count = count + substring_count(w[index:])

    cache[w] = count
    return count

print substring_count("aaaaaa")


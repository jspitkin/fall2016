global dictionary
global glob
global L


glob = 0
dictionary = set(["a", "aa", "aaa", "aaaa", "aaaaa"])
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


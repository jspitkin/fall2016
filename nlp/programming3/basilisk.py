import BasiliskLite as bl
import sys

def main():
    """ Program entry point. """
    if len(sys.argv) != 3:
        print_usage()
        return -1

    seeds_file_path = sys.argv[1]
    contexts_file_path = sys.argv[2]

    basilisk = bl.BasiliskLite()
    basilisk.read_seeds(seeds_file_path)
    basilisk.read_pattern_contexts(contexts_file_path)

    seed_words_string = "Seed Words: "
    for seed in basilisk.lexicon:
        seed_words_string += seed + " "
    print(seed_words_string.strip())
    print("Unique patterns:", len(basilisk.patterns))
    print()

    for iteration in range(1, 2):
        print("ITERATION", iteration)
        print()
        print("PATTERN POOL")
        pattern_pool = basilisk.get_pattern_pool()
        pattern_index = 1
        for pattern in pattern_pool:
            print(pattern_index, ".", pattern[0], "  ", "(", three_precision(pattern[1]), ")", sep='')

def three_precision(value):
    """ Input - a float value
        Output - a float value with exact precision 3 (zeros are appened if needed) """
    value_string = str(value)
    if len(value_string) >= 5:
        value_string = value_string[0:5]
    else:
        while len(value_string) < 5:
            value_string = value_string + "0"
    return value_string

def print_usage():
    print("usage: python3 basilisk.py <seeds_file> <contexts_file>")

if __name__ == '__main__':
    main()

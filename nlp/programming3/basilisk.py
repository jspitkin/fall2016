# Jake Pitkin October 23 2016
import BasiliskLite as bl
import sys

def main():
    """ Program entry point. """
    if len(sys.argv) != 3:
        print_usage()
        return -1

    seeds_file_path = sys.argv[1]
    contexts_file_path = sys.argv[2]
    ITERATION_COUNT = 5

    basilisk = bl.BasiliskLite()
    basilisk.read_seeds(seeds_file_path)
    basilisk.read_pattern_contexts(contexts_file_path)

    # Print the initial seed words and the number of unique patterns
    seed_words_string = "Seed Words: "
    for seed in basilisk.lexicon:
        seed_words_string += seed + " "
    print()
    print(seed_words_string.strip())
    print("Unique patterns:", len(basilisk.patterns))
    print()

    for iteration in range(1, ITERATION_COUNT+1):
        print("ITERATION", iteration)
        print()
        # Print the pattern pool this iteration and their RlogF score
        print("PATTERN POOL")
        pattern_pool = basilisk.get_pattern_pool()
        pattern_index = 1
        for pattern in pattern_pool:
            print(pattern_index, ". ", pattern[0], "  ", "(", three_precision(pattern[1]), ")", sep='')
            pattern_index += 1
        print()

        # Print the new words added to the lexicon this iteration and their AvgLog Score
        new_words = basilisk.get_new_words(pattern_pool)
        print("NEW WORDS")
        for word in new_words:
            print(word[0], "  ", "(", three_precision(word[1]), ")", sep='')
        if iteration != ITERATION_COUNT:
            print()

def three_precision(value):
    """ Input - a float value
        Output - a float value with exact precision 3 (zeros are appened if needed) """
    value_string = str(round(value,3))
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

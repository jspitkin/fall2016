from __future__ import print_function
import CKYParser as cky
import sys

def main():
    """Program entry point."""
    if len(sys.argv) != 3:
        print_usage()
        return -1

    grammar_file_path = sys.argv[1]
    sentences_file_path = sys.argv[2]

    parser = cky.CKYParser(grammar_file_path)
    sentences = parse_file(sentences_file_path)
    parses = []
    for sentence in sentences:
        parse  = parser.parse_sentence(sentence)
        parses.append(parse)

    print_parses(parses)

def parse_file(path):
    """ Input  -  a file path 
        Output -  a list containing the lines in the file """
    lines = []
    with open(path) as file:
        for line in file:
            lines.append(line)
    return lines

def print_parses(parses):
    """ Prints a collection of parses as specified by the assignment """
    for parse in parses:
        print("PARSING SENTENCE:", parse['sentence'])
        print("NUMBER OF PARSES FOUND:", parse['count'])
        print("CHART:")
        for entry in parse['chart']:
            print("chart[", entry['x'], ',', entry['y'], "]: ", entry['content'], sep='')
        print()



def print_usage():
    print("usage: python parse.py <grammar_file> <sentences_file>")
    
if __name__ == '__main__':
    main()

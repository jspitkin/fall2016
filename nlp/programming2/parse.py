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
            lines.append(line.rstrip())
    return lines

def print_parses(parses):
    """ Input - a dictionary representing a parsed sentence
                sentence - the sentence that was parsed
                count    - number of valid parsings
                chart    - the CKY parsing chart for the sentence
        Output - None. Prints to std out"""
    for index, parse in enumerate(parses):  
        print("CHART:")
        for row in range(0, len(parse['sentence'].split(' '))):
            for col in range(row, len(parse['sentence'].split(' '))):
                print("  ", "chart[", row+1, ",", col+1, "]: ",  sep='', end='')
                constituents = sorted(parse['chart'][row][col])
                constituent_list = ''
                if len(constituents) == 0:
                    constituent_list = "-"
                for constituent in constituents:
                    constituent_list = constituent_list + constituent + ' '
                print(constituent_list.rstrip())
        if index != len(parses) -1:
            print()



def print_usage():
    print("usage: python parse.py <grammar_file> <sentences_file>")
    
if __name__ == '__main__':
    main()

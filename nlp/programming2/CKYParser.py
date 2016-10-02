class CKYParser:
    def __init__(self, grammar_file_path):
        self.grammar = self.parse_grammar_file(grammar_file_path)

    def create_cky_parse_chart(self, sentence):
        """ Input  - a sentence without punctuation with a single white space between words
            Output - a CKY parse chart """
        words = sentence.split(' ')
        chart = [[[] for x in range(len(words))] for y in range(len(words))]

        # iterate over columns (words)
        for col in range(len(words)):
            # add non-terminals for the current word 
            for rule in self.grammar:
                if rule['is_terminal'] and rule['terminal'] == words[col]:
                    chart[col][col].append(rule['lhs'])
            # iterate over rows, from bottom to top
            for row in reversed(xrange(col)):
                # explore all partitionings for words 1 through 'col'
                partitions = self.find_partitions(row, col, chart)
                for partition in partitions:
                    chart[row][col].append(partition)
        return chart
    
    def find_partitions(self, row, col, chart):
        """ Input  - a row and column identifying a cell in the CKY parse chart
            Output - a list of possible partitionings for words 1 through col """  
        found_partitions = []
        for k in range(row + 1, col + 1):
            for rule in self.grammar:
                if not rule['is_terminal']:
                    if rule['first_nonterminal'] in chart[row][k-1]:
                        if rule['second_nonterminal'] in chart[k][col]:
                            found_partitions.append(rule['lhs'])
        return found_partitions


    def parse_sentence(self, sentence):
        chart = self.create_cky_parse_chart(sentence)
        parse_constituents = chart[0][len(sentence.split(' ')) - 1]
        parse_count = 0
        for constituent in parse_constituents:
            if constituent == 'S':
                parse_count += 1
        parse = {'sentence' : sentence,
                 'count'    : parse_count,
                 'chart'    : chart}
        return parse
    
    def parse_grammar_file(self, path):
        """ Input  - a file path of context-free grammar rules in CNF form
            Output - a list of grammar rules """ 
        grammar = []
        with open(path) as file:
            for line in file:
                line = line.strip().split()
                rule = {}
                if len(line) == 3:
                    rule['lhs'] = line[0]
                    rule['is_terminal'] = True
                    rule['terminal'] = line[2]
                    grammar.append(rule.copy())
                elif len(line) == 4:
                    rule['lhs'] = line[0]
                    rule['is_terminal'] = False
                    rule['first_nonterminal'] = line[2]
                    rule['second_nonterminal'] = line[3]
                    grammar.append(rule.copy())
        return grammar

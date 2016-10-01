class CKYParser:
    def __init__(self, grammar_file_path):
        self.grammar = self.parse_grammar_file(grammar_file_path)

    def create_cky_parse_chart(self):
        return

    def parse_sentence(self, sentence):
        parse = {}
        entry = {}
        chart = []
        entry['x'] = 1
        entry['y'] = 2
        entry['content'] = 'hello world'
        chart.append(entry.copy())

        parse['sentence'] = 'bye world'
        parse['count'] = 0
        parse['chart'] = chart
        parse['content'] = entry
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

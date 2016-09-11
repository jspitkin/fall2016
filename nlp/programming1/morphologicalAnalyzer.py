class DictionaryEntry:
    'An entry in the dictionary file.'
    def __init__(self, word, pos, root=None):
        self.word = word
        self.pos = pos
        self.root = root

class Rule:
    'A morphology rule'
    def __init__(self, keyword, affix, replaceChar, originatingPOS, newPOS):
        self.keyword = keyword
        self.affix = affix
        if replaceChar == '-':
            self.replaceChar = ''
        else:
            self.replaceChar = replaceChar
        self.originatingPOS = originatingPOS
        self.newPOS = newPOS

class Definition:
    'A definition for a word after applying morphology rules or if the definition was in the dictionary'
    def __init__(self, word, pos, root, source):
        self.word = word
        self.pos = pos
        self.root = root
        self.source = source

    def __eq__(self, other):
        return self.word == other.word and self.pos == other.pos and self.source == other.source

class MorphologicalAnalyzer:
    'Given a dictionary and a set of rules, performs a morphological analysis on a set of words'
    def __init__(self, dictionaryFilePath, rulesFilePath):
        self.dictionary = self.readDictionaryFile(dictionaryFilePath)
        self.rules = self.readRulesFile(rulesFilePath)
        self.definitions = []

    def morphologicalAnalysis(self, word, originalWord, appliedRules):
        'Given a word, attempts to apply morphology rules to it to find it\'s root and PoS.'
        'On success, a definition is created and added to a colleciton of definitions.'
        # Determine if the word is in the dictionary and if the PoS transformations align with the rules.
        for entry in self.dictionary:
            if word == entry.word:
                pos = entry.pos
                modifiedWord = word
                for rule in reversed(appliedRules):
                    if rule.originatingPOS == pos:
                        pos = rule.newPOS
                        modifiedWord = self.applyRuleToWord(modifiedWord, rule)
                        for entry in self.dictionary:
                            if entry.pos == rule.originatingPOS and modifiedWord  == originalWord:
                                if entry.root is None:
                                    definition = Definition(originalWord, pos, word, "morphology")
                                    if definition not in self.definitions:
                                        self.definitions.append(definition)
                                else:
                                    definition = Definition(originalWord, pos, entry.root, "morphology")
                                    if definition not in self.definitions:
                                        self.definitions.append(definition)

        # Attempt to apply morphology rules to the word and make a recursive call if successful.
        foundRule = False
        for rule in self.rules:
            if len(rule.affix) <= len(word):
                # The affix is a suffix.
                if word[-len(rule.affix):] == rule.affix:
                    wordMinusAffix = word[:-len(rule.affix)]
                    wordMinusAffix = wordMinusAffix + rule.replaceChar
                    appliedRules.append(rule)
                    self.morphologicalAnalysis(wordMinusAffix, originalWord, appliedRules)
                    foundRule = True
                # The affix is a prefix.
                elif word[:len(rule.affix)] == rule.affix:
                    wordMinusAffix = word[len(rule.affix):]
                    wordMinusAffix = rule.replaceChar + wordMinusAffix 
                    appliedRules.append(rule)
                    self.morphologicalAnalysis(wordMinusAffix, originalWord, appliedRules)
                    foundRule = True

        # No derivations found and not in the dictionary - default to noun.
        if foundRule is False and len(appliedRules) == 0:
            definition = Definition(word, "noun", originalWord, "default")
            self.definitions.append(definition)

    def applyRuleToWord(self, word, rule):
        'Applies the given rule to the given word and returns the new word.'
        if rule.keyword == "SUFFIX":
            return word[:(len(word) - len(rule.replaceChar))] + rule.affix
        elif rule.keyword == "PREFIX":
            return rule.affix + word[-len(rule.replaceChar):]

    def analyze(self, path):
        'Reads in a file of words and prints to standard output their morphological analysis.'
        with open(path) as file:
            for line in file:
                splitLine = line.split()
                if len(splitLine) == 1:
                    word = splitLine[0].lower()
                    if not self.wordInDictionary(word):
                        rules = []
                        self.morphologicalAnalysis(word, word, rules)

    def wordInDictionary(self, word):
        'Checks if a word is in the dictionary and creates a definition for it if so.'
        'Returns true if a definition is created and false otherwise.'
        for entry in self.dictionary:
            if entry.word == word:
                if entry.root is None:
                    definition = Definition(word, entry.pos, word, "dictionary")
                    if definition not in self.definitions:
                        self.definitions.append(definition)
                        return True
                else:
                    definition = Definition(word, entry.pos, entry.root, "dictionary")
                    if definition not in self.definitions:
                        self.definitions.append(definition)
                        return True
        return False

    def readDictionaryFile(self, path):
        'Reads in a dictionary file and creates a dictionary to represent it.'
        'Return - A dictionary of the words and their part of speech.'
        dictionary = []
        with open(path) as file:
            for line in file:
                splitLine = line.split()
                if len(splitLine) == 2:
                    entry = DictionaryEntry(splitLine[0].lower(), splitLine[1])
                    dictionary.append(entry)
                elif len(splitLine) == 4:
                    entry = DictionaryEntry(splitLine[0].lower(), splitLine[1], splitLine[3])
                    dictionary.append(entry)
        return dictionary

    def readRulesFile(self, path):
        'Reads in a rules file and creates a list to represent it.'
        'Return - A list of Rule objects.'
        rules = []
        with open(path) as file:
            for line in file:
                splitLine = line.split()
                rule = Rule(splitLine[0], splitLine[1], splitLine[2],
                        splitLine[3], splitLine[5])
                rules.append(rule)
        return rules

    def printDefinitions(self):
        'Prints all the definitions to standard output.'
        '<word> <pos> ROOT=<root> SOURCE=<source>'
        if len(self.definitions) == 0:
            return

        previousWord = self.definitions[0].word
        for d in self.definitions:
            if d.word == previousWord:
                print d.word + " " + d.pos + " ROOT=" + d.root + " SOURCE=" + d.source
            else:
                print '\n' + d.word + " " + d.pos + " ROOT=" + d.root + " SOURCE=" + d.source
            previousWord = d.word

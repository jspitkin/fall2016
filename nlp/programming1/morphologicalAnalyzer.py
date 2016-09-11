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
    'A found definition for a word'
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

    def morphologicalAnalysis(self, word, originalWord, appliedRules, source):
        for entry in self.dictionary:
            if word == entry.word:
                pos = entry.pos
                for rule in reversed(appliedRules):
                    if rule.originatingPOS == pos:
                        pos = rule.newPOS
                    
                if entry.root is None:
                    definition = Definition(originalWord, pos, word, source)
                    if definition not in self.definitions:
                        self.definitions.append(definition)
                else:
                    definition = Definition(originalWord, pos, entry.root, source)
                    if definition not in self.definitions:
                        self.definitions.append(definition)

        foundRule = False
        for rule in self.rules:
            if len(rule.affix) <= len(word):
                # The affix is a suffix.
                if word[-len(rule.affix):] == rule.affix:
                    wordMinusAffix = word[:-len(rule.affix)]
                    wordMinusAffix = wordMinusAffix + rule.replaceChar
                    appliedRules.append(rule)
                    self.morphologicalAnalysis(wordMinusAffix, originalWord, appliedRules, "morphology")
                    foundRule = True
                # The affix is a prefix.
                elif word[:len(rule.affix)] == rule.affix:
                    wordMinusAffix = word[len(rule.affix):]
                    wordMinusAffix = rule.replaceChar + wordMinusAffix 
                    appliedRules.append(rule)
                    self.morphologicalAnalysis(wordMinusAffix, originalWord, appliedRules, "morphology")
                    foundRule = True

        if foundRule is False and len(appliedRules) == 0:
            definition = Definition(word, "noun", originalWord, "default")
            self.definitions.append(definition)

    # Reads in a file of words and prints to standard output their morphological analysis.
    def analyze(self, path):
        with open(path) as file:
            for line in file:
                splitLine = line.split()
                if len(splitLine) == 1:
                    word = splitLine[0].lower()
                    rules = []
                    self.morphologicalAnalysis(word, word, rules, "dictionary")

    # Reads in a dictionary file and creates a dictionary to represent it.
    # Returns - A dictionary of the words and their part of speech.
    def readDictionaryFile(self, path):
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

    # Reads in a rules file and creates a list to represent it.
    # Returns - A list of Rule objects.
    def readRulesFile(self, path):
        rules = []
        with open(path) as file:
            for line in file:
                splitLine = line.split()
                rule = Rule(splitLine[0], splitLine[1], splitLine[2],
                        splitLine[3], splitLine[5])
                rules.append(rule)

        return rules

    # Prints all the definitions to standard output.
    # <word> <pos> ROOT=<root> SOURCE=<source>
    def printDefinitions(self):
        previousWord = self.definitions[0].word
        for d in self.definitions:
            if d.word == previousWord:
                print d.word + " " + d.pos + " ROOT=" + d.root + " SOURCE=" + d.source
            else:
                print '\n' + d.word + " " + d.pos + " ROOT=" + d.root + " SOURCE=" + d.source
            previousWord = d.word

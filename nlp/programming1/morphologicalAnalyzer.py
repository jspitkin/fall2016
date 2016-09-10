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

class MorphologicalAnalyzer:
    'Given a dictionary and a set of rules, performs a morphological analysis on a set of words'
    def __init__(self, dictionaryFilePath, rulesFilePath):
        self.dictionary = self.readDictionaryFile(dictionaryFilePath)
        self.rules = self.readRulesFile(rulesFilePath)

    def morphologicalAnalysis(self, word, originalWord, appliedRules, source):
        if word in self.dictionary:
            wordEntry = self.dictionary[word.lower()]
            pos = wordEntry.pos
            
            for rule in reversed(appliedRules):
                if rule.originatingPOS == pos:
                    pos = rule.newPOS
                else:
                    return

            if wordEntry.root is None:
                self.printDefinition(originalWord, pos, word, source)
            else:
                self.printDefinition(originalWord, pos, wordEntry.root, source)
            return

        foundRule = False
        for rule in self.rules:
            if len(rule.affix) <= len(word):
                # The rule is a suffix, the suffix isn't longer than the word and the word ends in the suffix.
                if word[-len(rule.affix):] == rule.affix:
                    wordMinusAffix = word[:-len(rule.affix)]
                    appliedRules.append(rule)
                    self.morphologicalAnalysis(wordMinusAffix, word, appliedRules, "morphology")
                    fouldRule = True
                # The rule is a prefix, the prefix isn't longer than the word and the word begins in the prefix.
                elif word[:len(rule.affix)] == rule.affix:
                    wordMinuxAffix = word[len(rule.affix):]
                    appliedRules.append(rule)
                    self.morphologicalAnalysis(wordMinuxAffix, word, appliedRules, "morphology")
                    foundRule = True

        if foundRule is False:
            self.printDefinition(originalWord, "noun", originalWord, "default")

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
        dictionary = {}
        with open(path) as file:
            for line in file:
                splitLine = line.split()
                if len(splitLine) == 2:
                    entry = DictionaryEntry(splitLine[0].lower(), splitLine[1])
                    dictionary[splitLine[0].lower()] = entry
                elif len(splitLine) == 4:
                    entry = DictionaryEntry(splitLine[0].lower(), splitLine[1], splitLine[3])
                    dictionary[splitLine[0].lower()] = entry
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

    # Prints a single definition of the form:
    # <word> <pos> ROOT=<root> SOURCE=<source>
    def printDefinition(self, word, pos, root, source):
        print word + " " + pos + " ROOT=" + root + " SOURCE=" + source

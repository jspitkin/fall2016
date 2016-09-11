import morphologicalAnalyzer as ma
import sys

def main():
    'Program entry point.'
    if len(sys.argv) != 4:
        printUsage()
        return -1

    dictionaryFilePath = sys.argv[1]
    rulesFilePath = sys.argv[2]
    testFilePath = sys.argv[3]
    
    # Initialize a MorphologicalAnalyzer with the passed in dictionary and rule set.
    analyzer = ma.MorphologicalAnalyzer(dictionaryFilePath, rulesFilePath)
    # Analyze the test word file.
    analyzer.analyze(testFilePath)
    # Print the found definitions from the morphological analyzation.
    analyzer.printDefinitions()

def printUsage():
    'Prints out the usage information for command line use.'
    print "usage: python morphology.py <dict file> <rule file> <test file>"

if __name__ == "__main__":
    main()


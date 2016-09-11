import morphologicalAnalyzer as ma
import sys

def main():
    if len(sys.argv) != 4:
        printUsage()
        return -1

    dictionaryFilePath = sys.argv[1]
    rulesFilePath = sys.argv[2]
    testFilePath = sys.argv[3]

    analyzer = ma.MorphologicalAnalyzer(dictionaryFilePath, rulesFilePath)
    analyzer.analyze(testFilePath)
    analyzer.printDefinitions()

# Prints out the usage information for command line use.
def printUsage():
    print "usage: python morphology.py <dict file> <rule file> <test file>"

if __name__ == "__main__":
    main()


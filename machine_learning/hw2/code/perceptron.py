class Perceptron:
    def __init__(self):
        self.examples = []
        self.FEATURE_COUNT = 123
        self.bias = 0
        self.weightVector = [0 for x in range(self.FEATURE_COUNT)]
        self.learningRate = 0.5

    def readFile(self, path):
        with open(path) as file:
            for line in file:
                line = line.strip().split(' ')
                featureVector = [0 for x in range(self.FEATURE_COUNT)]
                label = int(line[0])
                for feature in line[1:]:
                    colonIndex = feature.index(':')
                    index = int(feature[:colonIndex])
                    value = int(feature[colonIndex + 1:])
                    featureVector[index] = value
                self.examples.append((label, featureVector))

    def sign(self, value):
        if value >= 0:
            return 1
        else:
            return -1

    def update(self, path):
        self.readFile(path)
        for example in self.examples:
            label, featureVector = example
            vectorSum = self.bias
            for index in range(self.FEATURE_COUNT):
                vectorSum += self.weightVector[index] * featureVector[index]
            if self.sign(vectorSum) != label:
                print "made a mistake"
                for index in range(self.FEATURE_COUNT):
                    self.weightVector[index] += self.learningRate * (label * featureVector[index])

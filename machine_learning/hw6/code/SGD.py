import random
import math
import ioutil

class SGD():
    def __init__(self, feature_count, epoch, sigma, gamma):
        self.FEATURE_COUNT = feature_count
        self.EPOCH = epoch
        self.SIGMA = sigma
        self.GAMMA = gamma
        self.weight_vector = [0 for x in range(self.FEATURE_COUNT)]
        self.likelihoods = []
        random.seed(7)

    def train(self, training_examples):
        for epoch in range(self.EPOCH):
            random.shuffle(training_examples)
            for example in training_examples:
                gradient = self.calculate_gradient(example)
                self.update_weight_vector(gradient)
            # Uncomment to write a plot of the objective to file
            #self.update_log_likelihood(training_examples)
        #ioutil.write_csv('likelihoods.csv', self.likelihoods)

    def test(self, test_examples):
        correct = 0
        mistakes = 0
        total_examples = 0
        for example in test_examples:
            prediction = 0
            for index in range(self.FEATURE_COUNT):
                prediction += self.weight_vector[index] * example['feature_vector'][index]
            prediction = self.sign(prediction)
            if prediction == example['label']: 
                correct += 1
            else:
                mistakes +=1
            total_examples += 1
        accuracy = round(correct/total_examples, 3)
        return {'correct' : correct, 'mistakes' : mistakes, 'examples' : total_examples, 'accuracy' : accuracy}

    def sign(self, value):
        if value >= 0:
            return 1
        else:
            return -1

    def calculate_gradient(self, example):
        gradient = [0 for x in range(self.FEATURE_COUNT)]
        denominator = 0
        for index in range(self.FEATURE_COUNT):
            denominator += self.weight_vector[index] * example['feature_vector'][index]
        denominator = 1 + math.exp(example['label'] * denominator)
        for index in range(self.FEATURE_COUNT):
            gradient[index] = ((-1 * example['label'] * example['feature_vector'][index]) / denominator) + ((2 * self.weight_vector[index]) / (self.SIGMA * self.SIGMA))
        return gradient

    def update_weight_vector(self, gradient):
        for index in range(self.FEATURE_COUNT):
            self.weight_vector[index] = self.weight_vector[index] - (self.GAMMA * gradient[index])

    def update_log_likelihood(self, training_examples):
        likelihood = 0
        w_transpose = 0
        for index in range(self.FEATURE_COUNT):
            w_transpose = self.weight_vector[index] * self.weight_vector[index]
        w_transpose = w_transpose / (self.SIGMA * self.SIGMA)
        for example in training_examples:
            w_x = 0
            for index in range(self.FEATURE_COUNT):
                w_x += self.weight_vector[index] + example['feature_vector'][index]
            w_x = -1 * example['label'] * w_x
            w_x = math.log((1 + math.exp(w_x)))
            likelihood += w_x
        likelihood +=  w_transpose
        self.likelihoods.append(likelihood)

import random
import math

class SGD():
    def __init__(self, feature_count, epoch, sigma, initial_gamma):
        self.FEATURE_COUNT = feature_count
        self.EPOCH = epoch
        self.SIGMA = sigma
        self.initial_gamma = initial_gamma
        self.gamma = initial_gamma
        self.weight_vector = [0 for x in range(self.FEATURE_COUNT)]
        self.likelihoods = []
        random.seed(7)

    def train(self, training_examples):
        for epoch in range(self.EPOCH):
            random.shuffle(training_examples)
            for example in training_examples:
                gradient = self.calculate_gradient(example)
                self.update_weight_vector(gradient)
                self.update_log_likelihood(training_examples)
        ioutil.write_csv('likelihoods.csv', self.likelihoods)

    def test(self, test_examples):
        correct = 0
        mistakes = 0
        total_examples = 0
        false_positives = 0
        false_negatives = 0
        positive_predictions = 0
        negative_predictions = 0
        for example in test_examples:
            prediction = 0
            for index in range(self.FEATURE_COUNT):
                prediction += self.weight_vector[index] * example['feature_vector'][index]
            prediction = self.sign(prediction)
            if prediction == 1:
                positive_predictions += 1
            else:
                negative_predictions += 1
            if prediction == example['label']: 
                correct += 1
            elif prediction == -1 and example['label'] == 1:
                false_negatives += 1
                mistakes += 1
            elif prediction == 1 and example['label'] == -1:
                false_positives +=1
                mistakes +=1
            total_examples += 1
        accuracy = round(correct/total_examples, 3)
        #print('FP:' , false_positives, 'FN:', false_negatives, "+:", positive_predictions, "-:", negative_predictions, sep='\t')
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
            self.weight_vector[index] = self.weight_vector[index] - (self.gamma * gradient[index])

    def update_log_likelihood(self, training_examples):
        likelihood = 0
        w_transpose = 0
        w_x = 0
            
            

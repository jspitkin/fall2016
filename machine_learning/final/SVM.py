# Jake Pitkin November 12 2016
from random import shuffle


class SVM:
    def __init__(self, initial_learning_rate, C, epoch, feature_count):
        self.FEATURE_COUNT = feature_count
        self.weight_vector = [0 for x in range(self.FEATURE_COUNT)]   
        self.initial_learning_rate = initial_learning_rate
        self.learning_rate = initial_learning_rate
        self.C = C
        self.epoch = epoch
        self.classifications = []

    def train(self, training_examples):
        example_count = len(training_examples) * self.epoch
        mistakes = 0
        example_number = 1
        for _ in range(self.epoch):
            shuffle(training_examples)
            for example in training_examples:
                prediction = 0
                self.update_learning_rate(example_number)
                example_number += 1
                for index in range(self.FEATURE_COUNT):
                    prediction += self.weight_vector[index] * example['feature_vector'][index] 
                prediction = example['label'] * prediction
                if prediction <= 0:
                    self.update_weight_vector_misclassification(example)
                    mistakes += 1
                else:
                    self.update_weight_vector_correct_classification()
        results = {'mistakes' : mistakes, 'example_count' : example_count, 'accuracy' : round(1 - mistakes/example_count, 3)}
        print(self.weight_vector)
        return results

    def update_weight_vector_misclassification(self, example):
        for index in range(self.FEATURE_COUNT):
            updated_weight = (1 - self.learning_rate) * self.weight_vector[index]
            updated_weight += self.learning_rate * self.C * example['feature_vector'][index] * example['label']
            self.weight_vector[index] = updated_weight

    def update_weight_vector_correct_classification(self):
        for index in range(self.FEATURE_COUNT):
            updated_weight = (1 - self.learning_rate) * self.weight_vector[index]
            self.weight_vector[index] = updated_weight

    def update_learning_rate(self, t):
        self.learning_rate = self.initial_learning_rate / (1 + (self.initial_learning_rate * (t / self.C)))

    def sign(self, value):
        if value >= 0:
            return 1
        else:
            return -1



    def get_precision(self, true_positives, false_positives):
        if true_positives == 0 and false_positives == 0:
            return 0
        return round(true_positives / (true_positives + false_positives), 3)


    def get_recall(self, true_positives, false_negatives):
        if true_positives == 0 and false_negatives == 0:
            return 0
        return round(true_positives / (true_positives + false_negatives), 3)

    def get_F1_score(self, precision, recall):
        if precision == 0 and recall == 0:
            return 0
        return round((2 * (precision * recall)) / (precision + recall), 3)
    
    def test(self, test_examples):
        example_count = len(test_examples)
        mistakes = 0
        true_positives = 0
        false_positives = 0
        false_negatives = 0
        for example in test_examples:
            vector_sum = 0
            for index in range(self.FEATURE_COUNT):
                vector_sum += self.weight_vector[index] * example['feature_vector'][index]   
            if self.sign(vector_sum) != example['label']:
                mistakes += 1
            if self.sign(vector_sum) == 1 and example['label'] == 1:
                true_positives += 1
            elif self.sign(vector_sum) == 1 and example['label'] == -1:
                false_positives += 1
            elif self.sign(vector_sum) == -1 and example['label'] == 1:
                false_negatives += 1
            if self.sign(vector_sum) == 1:
                self.classifications.append(1)
            else:
                self.classifications.append(0)
        results = {}
        results['mistakes'] = mistakes
        results['example_count'] = example_count
        results['accuracy']  = round(1 - mistakes/example_count, 3)
        results['precision'] = self.get_precision(true_positives, false_positives)
        results['recall'] = self.get_recall(true_positives, false_negatives)
        results['F1_score'] = self.get_F1_score(results['precision'], results['recall'])
        print('TP: ', true_positives, ' FP: ', false_positives, ' FN: ', false_negatives)
        return results

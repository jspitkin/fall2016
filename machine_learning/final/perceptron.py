import random
import ioutil
import data_explore

class Perceptron:
    def __init__(self, learning_rate, epoch, feature_count):
        # There are 124 features in the test and training data.
        self.FEATURE_COUNT = feature_count 
        # Randomly initialize the weight vector
        self.bias = random.uniform(-1,1)
        self.weight_vector = [random.uniform(-1,1) for x in range(self.FEATURE_COUNT)]
        self.learning_rate = learning_rate
        self.mistakes = 0
        self.training_space_size = 0
        self.test_space_size = 0
        self.margin = 0
        self.epoch = epoch
        self.shuffle = True
        self.classifications = []

    def sign(self, value):
        'Returns 1 if value is positive and 0 otherwise.'
        if value >= 0:
            return 1
        else:
            return -1

    def get_test_accuracy(self):
        'Returns the percent of correctly classified examples when testing.'
        if self.test_space_size == 0:
            return 0
        return round(((self.correct_classifications/self.test_space_size)*100),2)

    def get_training_accuracy(self):
        'Returns the percent of updates when training.'
        if self.training_space == 0:
            return 0
        return round(((self.mistakes/(self.training_space_size * self.epoch))*100),2)

    def test(self, path):
        'Takes in a path and runs all the examples it contains through the current Perceptron.'
        self.correct_classifications = 0
        examples = ioutil.read_svm_file(path)
        data = data_explore.get_ranges_and_averages(examples)
        data_explore.bucket_data(examples, data, 20)
        self.test_space_size = len(examples)
        for example in examples:
            vector_sum = self.bias
            label = example[0]
            if label == 0:
                label = -1
            feature_vector = example[1]
            for index in range(self.FEATURE_COUNT):
                vector_sum += self.weight_vector[index] * feature_vector[index]
            if self.sign(vector_sum) == self.sign(label):
                self.classifications.append(1)
                self.correct_classifications += 1
            else:
                self.classifications.append(0)

    def margin_train(self, path):
        'Trains a margin Perceptron. The weight vector is stored in self.weightVector.'
        self.mistakes = 0
        examples = ioutil.read_svm_file(path)
        data = data_explore.get_ranges_and_averages(examples)
        data_explore.bucket_data(examples, data, 20)
        self.training_space_size = len(examples)

        self.training_space_size = len(examples)
        for _ in range(self.epoch):
            for example in examples:
                label, feature_vector = example
                if label == 0:
                    label = -1
                vector_sum = self.bias
                for index in range(self.FEATURE_COUNT):
                    vector_sum += self.weight_vector[index] * feature_vector[index]
                if label * (vector_sum) < self.margin:
                    self.mistakes += 1
                    for index in range(self.FEATURE_COUNT):
                        self.weight_vector[index] += self.learning_rate * (label * feature_vector[index])
            random.shuffle(examples)

    def get_aggressive_learning_rate(self, label, vector_sum, feature_vector):
        'Returns the learning rate for aggressive Perceptron with margin.'
        learning_rate = self.margin - (label * vector_sum)
        denominator = 1
        for index in range(self.FEATURE_COUNT):
            denominator += feature_vector[index] * feature_vector[index]
        learning_rate = learning_rate / denominator
        return learning_rate

    def aggressive_margin_train(self, path):
        'Trains a aggressive Perceptron with margin. The weight vector is stored in self.weightVector.'
        self.mistakes = 0
        examples = ioutil.read_svm_file(path)
        self.training_space_size = len(examples)
        for _ in range(self.epoch):
            for example in examples:
                label, feature_vector = example
                if label == 0:
                    label = -1
                vector_sum = self.bias
                for index in range(self.FEATURE_COUNT):
                    vector_sum += self.weight_vector[index] * feature_vector[index]
                if (label * vector_sum) <= self.margin:
                    self.mistakes += 1
                    aggressive_learning_rate = self.get_aggressive_learning_rate(label, vector_sum, feature_vector)
                    for index in range(self.FEATURE_COUNT):
                        self.weight_vector[index] += aggressive_learning_rate * (label * feature_vector[index])
            if self.shuffle:
                random.shuffle(examples)
   

    def classic_train(self, path):
        'Trains a classic Perceptron. The weight vector is stored in self.weightVector.'
        self.mistakes = 0
        examples = ioutil.read_svm_file(path)
        data = data_explore.get_ranges_and_averages(examples)
        data_explore.bucket_data(examples, data, 20)
        self.training_space_size = len(examples)
        for _ in range(self.epoch):
            for example in examples:
                label, feature_vector = example
                if label == 0:
                    label = -1
                vector_sum = self.bias
                for index in range(self.FEATURE_COUNT):
                    vector_sum += self.weight_vector[index] * feature_vector[index]
                if (label * vector_sum) <= 0:
                    self.mistakes += 1
                    for index in range(self.FEATURE_COUNT):
                        self.weight_vector[index] += self.learning_rate * (label * feature_vector[index])
            random.shuffle(examples)

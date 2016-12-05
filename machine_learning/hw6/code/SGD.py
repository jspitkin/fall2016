from random import randint

class SGD():
    def __init__(self, feature_count, epoch, sigma):
        self.FEATURE_COUNT = feature_count
        self.EPOCH = epoch
        self.SIGMA = sigma
        self.weight_vector = [0 for x in range(self.FEATURE_COUNT)]

    def train(self, training_examples):
        for epoch in range(self.EPOCH):
            random_index = randint(0, len(training_examples))
            random_example = training_examples[random_index]
            gradient = calculate_gradient(random_example)
            update_weight_vector(gradient)

    def test(self, test_examples):
        print("TODO")

    def calculate_gradient(self, training_example):
        print("TODO")

    def update_weight_vector(self, gradient):
        print("TODO")

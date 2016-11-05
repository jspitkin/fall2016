import ioutil
import sys
import perceptron as pt

def main():
    """ Program entry point. """
    if len(sys.argv) != 3:
        print_usage()
        return -1

    #examples = ioutil.read_svm_file("data-splits/data.train")
    training_data_path = sys.argv[1]
    test_data_path = sys.argv[2]
    feature_count = ioutil.get_feature_count(training_data_path)
    learning_rate = 0.1
    epoch = 1
    perceptron = pt.Perceptron(learning_rate, epoch, feature_count)
    perceptron.classic_train(training_data_path)
    perceptron.test(test_data_path)
    print(perceptron.correct_classifications)
    print(perceptron.test_space_size)
    print(perceptron.get_test_accuracy())

def print_usage():
    print("python3 classifer.py <train-path> <test-path>")

if __name__ == "__main__":
    main()

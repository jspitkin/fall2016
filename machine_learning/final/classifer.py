import ioutil
import sys
import decisionTree as dt
import SVM

def main():
    """ Program entry point. """
    if len(sys.argv) != 4:
        print_usage()
        return -1

    training_data_path = sys.argv[1]
    test_data_path = sys.argv[2]
    id_path = sys.argv[3]
    #svm = SVM.SVM(0.001, 0.5, 1)
    #training_examples = ioutil.read_svm_file_binary(training_data_path)
    training_examples = ioutil.read_svm_file(training_data_path)
    test_examples = ioutil.read_svm_file(test_data_path)
    #test_examples = ioutil.read_svm_file_binary(test_data_path)
    train_accuracy = svm.train(training_examples)
    accuracy = svm.test(test_examples)
    print(accuracy)
    accuracy = svm.test(training_examples)
    print(accuracy)
    learning_rate = 0.05
    epoch = 5
    #perceptron = pt.Perceptron(learning_rate, epoch, feature_count)
    #perceptron.margin_train(training_data_path)
    #perceptron.test(test_data_path)
    #print(perceptron.correct_classifications)
    #print(perceptron.test_space_size)
    classifications = svm.classifications
    ids = ioutil.read_id_file(id_path)
    labels = ["example_id", "label"]
    ioutil.write_csv_file('kaggle_submission.csv', labels, ids, classifications)

def print_usage():
    print("python3 classifer.py <train-path> <test-path> <id-path>")

if __name__ == "__main__":
    main()

import ioutil
import sys
import DecisionTree as dt
import SVM

def main():
    """ Program entry point. """
    if len(sys.argv) != 4:
        print_usage()
        return -1

    training_data_path = sys.argv[1]
    test_data_path = sys.argv[2]
    id_path = sys.argv[3]
    training_examples = ioutil.read_svm_file_buckets(training_data_path)
    feature_count = len(training_examples[0]['feature_vector'])
    svm = SVM.SVM(0.25, 4, 10, feature_count)
    test_examples = ioutil.read_svm_file_buckets(test_data_path)
    feature_count = len(training_examples[0]['feature_vector'])
    #tree = dt.DecisionTree(feature_count)
    train_accuracy = svm.train(training_examples)
    print(train_accuracy)
    test_accuracy = svm.test(test_examples)
    print(test_accuracy)
    test_accuracy = svm.test(training_examples)
    print(test_accuracy)
    #tree.construct_tree(training_examples)
    #accuracy = tree.test(test_examples)
    #print(accuracy)
    #accuracy = tree.test(training_examples)
    #print(accuracy)
    #classifications = tree.classifications
    classifications = svm.classifications
    ids = ioutil.read_id_file(id_path)
    labels = ["example_id", "label"]
    ioutil.write_csv_file('kaggle_submission.csv', labels, ids, classifications)

def print_usage():
    print("python3 classifer.py <train-path> <test-path> <id-path>")

if __name__ == "__main__":
    main()

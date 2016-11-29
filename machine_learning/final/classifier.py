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
    #svm = SVM.SVM(1, 20, 1, 130)
    training_examples = ioutil.read_svm_file_buckets(training_data_path)
    test_examples = ioutil.read_svm_file_buckets(test_data_path)
    feature_count = len(training_examples[0]['feature_vector'])
    tree = dt.DecisionTree(feature_count)
    #training_examples = ioutil.read_svm_file_binary(training_data_path)
    #test_examples = ioutil.read_svm_file_binary(test_data_path)
    #train_accuracy = svm.train(training_examples)
    tree.construct_tree(training_examples)
    accuracy = tree.test(test_examples)
    print(accuracy)
    accuracy = tree.test(training_examples)
    print(accuracy)
    classifications = tree.classifications
    ids = ioutil.read_id_file(id_path)
    labels = ["example_id", "label"]
    ioutil.write_csv_file('kaggle_submission.csv', labels, ids, classifications)

def print_usage():
    print("python3 classifer.py <train-path> <test-path> <id-path>")

if __name__ == "__main__":
    main()

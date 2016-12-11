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

    #frequencies = [1, 0.34, 0.4, 1.2]
    frequencies = [0.34]

    for index in range(len(frequencies)):

        training_examples = ioutil.read_svm_file_buckets(training_data_path, frequencies[index])
        test_examples = ioutil.read_svm_file_buckets(test_data_path, frequencies[index])

        feature_count = len(training_examples[0]['feature_vector'])

        tree = dt.DecisionTree(feature_count)

        print("Frequency:", frequencies[index])
        tree.construct_tree(training_examples)
        accuracy = tree.test(test_examples)
        print(accuracy)
        accuracy = tree.test(training_examples)
        print(accuracy)
        print()

        classifications = tree.classifications
        ids = ioutil.read_id_file(id_path)
        labels = ["example_id", "label"]
        ioutil.write_csv_file('kaggle_submission.csv', labels, ids, classifications)


def print_usage():
    print("python3 classifier.py <train-path> <test-path> <id-path>")

if __name__ == "__main__":
    main()

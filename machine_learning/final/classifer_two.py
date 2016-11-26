import ioutil
import sys
import DecisionTree as dt
import RandomForest as rf
import SVM

def main():
    """ Program entry point. """
    if len(sys.argv) != 4:
        print_usage()
        return -1

    m = 500
    feature_count = 360
    N = 10
    training_data_path = sys.argv[1]
    test_data_path = sys.argv[2]
    id_path = sys.argv[3]
    training_examples = ioutil.read_svm_file_buckets(training_data_path)
    test_examples = ioutil.read_svm_file_buckets(test_data_path)
    random_forest = rf.RandomForest(m, N, feature_count) 
    random_forest.set_examples(training_examples, test_examples)
    results = random_forest.create_trees()
    print(results)
    #test_examples = ioutil.read_svm_file_binary(test_data_path)
    #train_accuracy = svm.train(training_examples)
    #classifications = tree.classifications
    #ids = ioutil.read_id_file(id_path)
    #labels = ["example_id", "label"]
    #ioutil.write_csv_file('kaggle_submission.csv', labels, ids, classifications)

def print_usage():
    print("python3 classifer.py <train-path> <test-path> <id-path>")

if __name__ == "__main__":
    main()

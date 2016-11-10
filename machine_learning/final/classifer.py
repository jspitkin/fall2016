import ioutil
import sys
import perceptron as pt
import decisionTree as dt

def main():
    """ Program entry point. """
    if len(sys.argv) != 4:
        print_usage()
        return -1

    training_data_path = sys.argv[1]
    test_data_path = sys.argv[2]
    id_path = sys.argv[3]
    feature_count = ioutil.get_feature_count(training_data_path)
    tree = dt.DecisionTree()
    tree.constructTree(['data-splits/data.train'], 'data-splits/data.train')
    error = tree.reportError(['data-splits/data.test']) 
    #learning_rate = 0.05
    #epoch = 5
    #perceptron = pt.Perceptron(learning_rate, epoch, feature_count)
    #perceptron.margin_train(training_data_path)
    #perceptron.test(test_data_path)
    #print(perceptron.correct_classifications)
    #print(perceptron.test_space_size)
    classifications = perceptron.classifications
    ids = ioutil.read_id_file(id_path)
    labels = ["example_id", "label"]
    ioutil.write_csv_file('kaggle_submission.csv', labels, ids, classifications)

def print_usage():
    print("python3 classifer.py <train-path> <test-path> <id-path>")

if __name__ == "__main__":
    main()

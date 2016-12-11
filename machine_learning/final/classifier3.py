import ioutil
import sys
import RandomForest as RF
from collections import Counter

def main():
    """ Program entry point. """
    if len(sys.argv) != 4:
        print_usage()
        return -1

    training_data_path = sys.argv[1]
    test_data_path = sys.argv[2]
    id_path = sys.argv[3]

    frequencies = [0.34]

    for index in range(len(frequencies)):

        training_examples = ioutil.read_svm_file_buckets(training_data_path, frequencies[index])
        test_examples = ioutil.read_svm_file_buckets(test_data_path, frequencies[index])

        feature_count = len(training_examples[0]['feature_vector'])
        m = 1000 # Number of random examples
        n = 101 # Number of trees
        k = 50 # Number of features in trees

        cross_validation(feature_count, training_examples, test_examples)

        #random_forest = RF.RandomForest(m, n, k, feature_count)
        #random_forest.set_examples(training_examples, test_examples)
        #results = random_forest.create_trees()
        #classifications = tally_votes(results, test_examples)

        #print('Accuracy:', classifications['accuracy'], 'Example Count:', classifications['examples'])
        #final_classifications = []
        #for index in range(len(classifications['classifications'])):
        #    if classifications['classifications'][index] == 1:
        #        final_classifications.append(1)
        #    else:
        #        final_classifications.append(0)
        #ids = ioutil.read_id_file(id_path)
        #labels = ["example_id", "label"]
        #ioutil.write_csv_file('kaggle_submission.csv', labels, ids, final_classifications)

def cross_validation(feature_count, training_examples, test_examples):
    n_values = [101]
    k_values = [50, 60]
    m_values = [10000]

    best_n = 0
    best_k = 0
    best_m = 0
    best_f1 = 0

    for n in n_values:
        for k in k_values:
            for m in m_values:
                random_forest = RF.RandomForest(m, n, k, feature_count)
                random_forest.set_examples(training_examples, test_examples)
                results = random_forest.create_trees()
                print('n:', n, 'k:', k, 'm:', m)
                classifications = tally_votes(results, test_examples)
                print('F1:', classifications['f1'], 'Accuracy:', classifications['accuracy'], 'Example Count:', classifications['examples'])
                print()
                if classifications['f1'] > best_f1:
                    best_f1 = classifications['f1']
                    best_n = n
                    best_k = k
                    best_m = m
    print("BEST")
    print('n:', n, 'k:', k, 'm:', m)

def tally_votes(results, test_examples):
    votes = [[] for x in range(len(test_examples))]
    average_f1 = 0
    for result in results:
        average_f1 += result['f1']
        for index in range(len(result['predictions'])):
            votes[index].append(result['predictions'][index])
    classifications = []
    for index in range(len(test_examples)):
        classifications.append(Counter(votes[index]).most_common(1)[0][0])

    total_examples = 0
    correct = 0
    for index in range(len(test_examples)):
        if classifications[index] == test_examples[index]['label']:
            correct += 1
        total_examples += 1
    average_f1 = round(average_f1 / len(results), 3)
    return {'f1':average_f1, 'classifications':classifications, 'accuracy':round(correct / total_examples, 3), 'examples':total_examples}


def print_usage():
    print("python3 classifier.py <train-path> <test-path> <id-path>")

if __name__ == "__main__":
    main()

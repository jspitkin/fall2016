import ioutil
import sys
import DecisionTree as dt
import SVM
import SGD

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
        # training_examples = ioutil.read_svm_file_buckets(training_data_path, frequencies[index])
        # test_examples = ioutil.read_svm_file_buckets(test_data_path, frequencies[index])
        training_examples = ioutil.read_svm_file_log(training_data_path)
        test_examples = ioutil.read_svm_file_log(test_data_path)

        sigma_values = [10, 100, 1000, 10000]
        gamma_values = [0.001, 0.0001]
        #gamma_values = [0.01]
        #sigma_values = [25, 40, 45, 50, 55, 80]
    
        feature_count = len(training_examples[0]['feature_vector'])
        epoch = 20
        sigma = 1000
        gamma = 0.001
        sgd = SGD.SGD(feature_count, epoch, sigma, gamma)
        sgd.train(training_examples)

        #cross_validation(training_examples, 6, feature_count, epoch, sigma_values, gamma_values)
    
        print('Ratio for average divide:', frequencies[index])
        test_accuracy = sgd.test(test_examples)
        print(test_accuracy)
        test_accuracy = sgd.test(training_examples)
        print(test_accuracy)
        print()
    
        classifications = sgd.classifications
        ids = ioutil.read_id_file(id_path)
        labels = ["example_id", "label"]
        ioutil.write_csv_file('kaggle_submission.csv', labels, ids, classifications)

def cross_validation(training_examples, splits, feature_count, epoch, sigma_values, gamma_values):
    split_examples = [[] for x in range(splits)]
    for index, example in enumerate(training_examples):
        split_examples[index % splits].append(example)
    for sigma in sigma_values:
        for gamma in gamma_values:
            average_accuracy = 0
            for index in range(splits):
                test_examples = split_examples[index]
                training_examples = []
                for train_index in range(splits):
                    if train_index == index:
                        continue
                    for example in split_examples[train_index]:
                        training_examples.append(example)
                sgd = SGD.SGD(feature_count, epoch, sigma, gamma)
                sgd.train(training_examples)
                results = sgd.test(test_examples)
                average_accuracy += results['accuracy']
            print('accuracy:', round(average_accuracy/splits, 3), 'sigma:', sigma, 'gamma:', gamma, sep='\t')

def print_usage():
    print("python3 classifer.py <train-path> <test-path> <id-path>")

if __name__ == "__main__":
    main()

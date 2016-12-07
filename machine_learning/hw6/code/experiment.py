import ioutil
import SGD

def main():
    FEATURE_COUNT = 124
    EPOCH = 200
    TRAIN_DATA_PATH = 'a5a.train'
    TEST_DATA_PATH = 'a5a.test'
    SPLIT_DATA_PATHS = []
    SPLIT_DATA_PATHS.append('splits/split1.train')
    SPLIT_DATA_PATHS.append('splits/split2.train')
    SPLIT_DATA_PATHS.append('splits/split3.train')
    SPLIT_DATA_PATHS.append('splits/split4.train')
    SPLIT_DATA_PATHS.append('splits/split5.train')
    SPLIT_DATA_PATHS.append('splits/split6.train')

    sigma_values = [10, 20, 25, 50, 100, 1000]
    gamma_values = [1, 0.5, 0.1, 0.05, 0.01, 0.001, 0.0001]

    training_examples = ioutil.read_file(TRAIN_DATA_PATH, FEATURE_COUNT)
    test_examples = ioutil.read_file(TEST_DATA_PATH, FEATURE_COUNT)
    split_examples = []
    for index in range(len(SPLIT_DATA_PATHS)):
        split_examples.append(ioutil.read_file(SPLIT_DATA_PATHS[index], FEATURE_COUNT))
    
    # Uncomment to run cross validation
    #cross_validation(split_examples, FEATURE_COUNT, EPOCH, sigma_values, gamma_values)

    sgd = SGD.SGD(FEATURE_COUNT, EPOCH, 50, 0.01)
    sgd.train(training_examples)    
    results = sgd.test(training_examples)
    print(results)
    results = sgd.test(test_examples)
    print(results)

def cross_validation(split_examples, feature_count, epoch, sigma_values, gamma_values):
    for sigma in sigma_values:
        for gamma in gamma_values:
            average_accuracy = 0
            for index in range(6):
                test_examples = split_examples[index]
                training_examples = []
                for train_index in range(6):
                    if train_index == index:
                        continue
                    for example in split_examples[train_index]:
                        training_examples.append(example)
                sgd = SGD.SGD(feature_count, epoch, sigma, gamma)
                sgd.train(training_examples)
                results = sgd.test(test_examples)
                average_accuracy += results['accuracy']
            print('accuracy:', round(average_accuracy/6, 3), 'sigma:', sigma, 'gamma:', gamma, sep='\t')

if __name__ == "__main__":
    main()

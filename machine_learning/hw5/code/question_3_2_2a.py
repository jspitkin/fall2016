import RandomForest as rf
import SVM
import ioutil

def main():
    m = 2000
    N = 5
    feature_count = 257
    train_data_path = 'handwriting/train.data'
    train_label_path = 'handwriting/train.labels'
    test_data_path = 'handwriting/test.data'
    test_label_path = 'handwriting/test.labels'

    random_forest = rf.RandomForest(m, N, feature_count)
    random_forest.set_data_paths(train_data_path, train_label_path, test_data_path, test_label_path)
    results = random_forest.create_trees()
    transformed_examples = random_forest.get_transformed_examples(results, test_label_path)

    for example in transformed_examples:
        for index in range(len(example['feature_vector'])):
            if example['feature_vector'][index] == -1:
                example['feature_vector'][index] = 0

    test_data = ioutil.read_data(test_data_path, test_label_path)
    training_data = ioutil.read_data(train_data_path, train_label_path)
    initial_rate = 0.1
    C = 4
    epoch = 15

    svm = SVM.SVM(initial_rate, C, epoch, N+1)
    svm.train(transformed_examples)
    training_accuracy = svm.test(training_data)
    test_accuracy = svm.test(test_data)

    print()
    print("Training Accuracy:")
    print("Mistakes:", training_accuracy['mistakes'], sep='\t')
    print("Example Count:", training_accuracy['example_count'], sep='\t')
    print("Accuracy:", training_accuracy['accuracy'], sep='\t')
    print("Precision:", training_accuracy['precision'], sep='\t')
    print("Recall: ", training_accuracy['recall'], sep='\t')
    print("F1 Score:", training_accuracy['F1_score'], sep='\t')
    print()
    print("Test Accuracy:")
    print("Mistakes:", test_accuracy['mistakes'], sep='\t')
    print("Example Count:", test_accuracy['example_count'], sep='\t')
    print("Accuracy:", test_accuracy['accuracy'], sep='\t')
    print("Precision:", test_accuracy['precision'], sep='\t')
    print("Recall: ", test_accuracy['recall'], sep='\t')
    print("F1 Score:", test_accuracy['F1_score'], sep='\t')

if __name__ == '__main__':
    main()

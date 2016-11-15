# Jake Pitkin November 14 2016
import ioutil
import SVM
import RandomForest as rf

def main():
    m = 500
    N_choices = [10, 30, 100]
    feature_count = 500
    train_data_path = 'madelon/madelon_train.data'
    train_label_path = 'madelon/madelon_train.labels'
    test_data_path = 'madelon/madelon_test.data'
    test_label_path = 'madelon/madelon_test.labels'

    best_N = 10
    best_accuracy = 0

    for N in N_choices:
        random_forest = rf.RandomForest(m, N, feature_count)
        random_forest.set_data_paths(train_data_path, train_label_path, test_data_path, test_label_path)
        results = random_forest.create_trees()
        transformed_examples = random_forest.get_transformed_examples(results, test_label_path)

        average_accuracy = 0
        for result in results:
            average_accuracy += result['accuracy']
        average_accuracy = average_accuracy / N
        print(average_accuracy)
        if average_accuracy > best_accuracy:
            best_aaccuracy = average_accuracy
            best_N = N

    print(best_N)
    random_forest = rf.RandomForest(m, best_N, feature_count)
    test_data = ioutil.read_data(test_data_path, test_label_path)
    training_data = ioutil.read_data(train_data_path, train_label_path)
    initial_rate = 0.01
    C = 0.5
    epoch = 10

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

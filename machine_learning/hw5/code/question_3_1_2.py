# Jake Pitkin November 12 2016
import ioutil
import SVM

def main():
    bucket_data = ioutil.split_data('madelon/madelon_train.data', 'madelon/madelon_train.labels', 501)
    data_splits = ioutil.read_data_and_split(bucket_data, 5)
    C_values = [2, 0.5, 0.25, 0.125, 0.0625, 0.03125]
    initial_learning_rates = [0.5, 0.01,  0.001]
    epoch = 10
    
    best_accuracy = 0
    best_C = 0
    best_initial_learning_rate = 0

    for C in C_values:
        for initial_learning_rate in initial_learning_rates:
            average_accuracy = 0
            for split_index in range(len(data_splits)):
                test_data = data_splits[split_index]
                data_splits_copy = list(data_splits)
                del data_splits_copy[split_index]
                training_data = [example for sublist in data_splits_copy for example in sublist]

                svm = SVM.SVM(initial_learning_rate, C, epoch, 501)
                svm.train(training_data)
                test_accuracy = svm.test(test_data)
                average_accuracy += test_accuracy['accuracy']
            average_accuracy = round(average_accuracy / len(data_splits), 3)
            print('Epoch:', epoch, 'C:', C, 'Learning Rate:', initial_learning_rate, 'Accuracy:', average_accuracy, sep='\t')
            
            if average_accuracy > best_accuracy:
                best_C = C
                best_accuracy = average_accuracy
                best_initial_learning_rate = initial_learning_rate

    training_data = ioutil.split_data('madelon/madelon_train.data', 'madelon/madelon_train.labels', 501)
    test_data = ioutil.split_data('madelon/madelon_test.data', 'madelon/madelon_test.labels', 501)
    svm = SVM.SVM(best_initial_learning_rate, best_C, epoch, 501)
    svm.train(training_data)
    training_accuracy = svm.test(training_data)
    test_accuracy = svm.test(test_data)
    print()
    print('Best C:', best_C, 'Best rate:', best_initial_learning_rate, sep='\t')
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

# Jake Pitkin October 23 2016
import math

class BasiliskLite:
    def __init__(self):
        self.lexicon = [] # lexicon of seed words and words added each iteration
        self.head_nouns = {} # context pattern : list of head nouns that co-occur
        self.patterns = [] # list of the unique context patterns

    def read_seeds(self, path):
        """ Input - file path of file containing seed words for the lexicon.
            Populates 'lexicon' with the initial seed words. """
        with open(path) as file:
            for line in file:
                if len(line.split()) == 1:
                    self.lexicon.append(line.split()[0].lower())
    
    def read_pattern_contexts(self, path):
        """ Input - file path of file containing context patterns.
            Populates 'head_nouns' and 'patterns' with the context patterns. """
        with open(path) as file:
            for line in file:
                line = line.split()
                noun_phrase = ""
                for index, word in enumerate(line):
                    if word == "*":
                        if len(line) > (index + 1):
                            pattern = line[index+1]
                            head_noun = noun_phrase.split()[-1].lower()
                            if pattern not in self.head_nouns.keys():
                                self.head_nouns[pattern] = set()
                                self.patterns.append(pattern)
                            self.head_nouns[pattern].add(head_noun)
                    noun_phrase += word + " "

    def get_pattern_pool(self):
        """ Output - a pattern pool of top ~10 patterns by RlogF score.
            Ties are sorted alphabetically. """
        # Compute the RlogF score for each pattern
        r_log_f_scores = [] # (pattern, RlogF score)
        for pattern in self.patterns:
            sem_freq = len(self.head_nouns[pattern].intersection(set(self.lexicon)))
            total_freq = len(self.head_nouns[pattern])
            r_log_f_score = (sem_freq / total_freq) * math.log(total_freq, 2)
            r_log_f_scores.append((pattern, r_log_f_score))
        # Sort the patterns in descending order by their score
        sorted_r_log_f_score = sorted(r_log_f_scores, key=lambda x: x[1], reverse=True)
        # Collect the top 10 patterns including any ties for 10th place and not including zeros
        pattern_pool = []
        ties_to_sort = []
        if len(sorted_r_log_f_score) != 0:
            previous_score = sorted_r_log_f_score[0][1]
        for score in sorted_r_log_f_score:
            if score[1] == 0 or (len(pattern_pool) >= 10 and score[1] != previous_score):
                break
            if score[1] == previous_score:
                ties_to_sort.append(score)
            else:
                ties_to_sort = sorted(ties_to_sort, key=lambda x: x[0])
                for sorted_score in ties_to_sort:
                    pattern_pool.append(sorted_score)
                ties_to_sort = []
                ties_to_sort.append(score)
            previous_score = score[1]
        if len(ties_to_sort) != 0 and len(pattern_pool) < 10:
            ties_to_sort = sorted(ties_to_sort, key=lambda x: x[0])
            for tie in ties_to_sort:
                pattern_pool.append(tie)

        return pattern_pool

    def get_candidate_list(self, pattern_pool):
        """ Input  - pattern pool of the top ~10 patterns by RlogF score.
            Output - a list of the unique head nouns that co-occur with the patterns. """
        unique_head_nouns = set()
        for pattern in pattern_pool:
            for head_noun in self.head_nouns[pattern[0]]:
                unique_head_nouns.add(head_noun)
        return unique_head_nouns

    def calculate_avg_log_scores(self, pattern_pool):
        """ Input  - pattern pool of the top ~10 patterns by RlogF score.
            Output - list of all head nouns in canadidate list and their AvgLog score. """
        candidate_list = self.get_candidate_list(pattern_pool) 
        avg_log_scores = []
        # Process each head noun in the candidate list
        for candidate in candidate_list:
            patterns_that_co_occur = []
            # Collect all the patterns that co-occur with the head noun
            for pattern, head_nouns in self.head_nouns.items():
                if candidate in head_nouns:
                    patterns_that_co_occur.append(pattern)
            lexicon_count = {key: 1 for key in patterns_that_co_occur}
            # Compute the semantic frequency for each pattern
            for pattern in patterns_that_co_occur:
                for lexicon_member in self.lexicon:
                    if lexicon_member in self.head_nouns[pattern]:
                        lexicon_count[pattern] += 1
            # Take the sum log2 of the frequencies of each pattern divided by pattern count
            avg_log_score = 0
            for lexicon, count in lexicon_count.items():
                avg_log_score += math.log(count, 2)
            if len(patterns_that_co_occur) == 0:
                continue
            avg_log_score = avg_log_score / len(patterns_that_co_occur)
            avg_log_scores.append((candidate, avg_log_score))
        return avg_log_scores

    def get_new_words(self, pattern_pool):
        """ Input  - pattern pool of the top ~10 patterns by RlogF score.
            Output - top ~5 head nouns to added to the lexion and their AvgLog score.
            Adds the top ~5 head nouns to the lexicon. """
        avg_log_scores = self.calculate_avg_log_scores(pattern_pool)
        new_words = []
        # Sort the candidate list in descending order by their AvgLog score
        avg_log_scores = sorted(avg_log_scores, key=lambda x: x[1], reverse=True)
        # Find the top ~5 and place ties in alphabetical order
        new_words = []
        ties_to_sort = []
        if len(avg_log_scores) != 0:
            previous_score = avg_log_scores[0][1]
        for score in avg_log_scores:
            if score[0] in self.lexicon:
                continue
            if len(new_words) >= 5 and score[1] != previous_score:
                break
            if score[1] == previous_score:
                ties_to_sort.append(score)
            else:
                ties_to_sort = sorted(ties_to_sort, key=lambda x: x[0])
                for sorted_score in ties_to_sort:
                    new_words.append(sorted_score)
                ties_to_sort = []
                ties_to_sort.append(score)
            previous_score = score[1]
        if len(ties_to_sort) != 0 and len(new_words) < 5:
            ties_to_sort = sorted(ties_to_sort, key=lambda x: x[0])
            for tie in ties_to_sort:
                new_words.append(tie)
        # Add the new to the lexicon
        for new_word in new_words:
            self.lexicon.append(new_word[0])
        return new_words

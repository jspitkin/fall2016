import math

class BasiliskLite:
    def __init__(self):
        self.lexicon = []
        self.head_nouns = {}
        self.patterns = []

    def read_seeds(self, path):
        with open(path) as file:
            for line in file:
                if len(line.split()) == 1:
                    self.lexicon.append(line.split()[0].lower())
    
    def read_pattern_contexts(self, path):
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
                                self.head_nouns[pattern] = []
                                self.patterns.append(pattern)
                            if head_noun not in self.head_nouns[pattern]:
                                self.head_nouns[pattern].append(head_noun)
                    noun_phrase += word + " "

    def get_pattern_pool(self):
        # Compute the RlogF score for each pattern
        r_log_f_scores = [] # (pattern, RlogF score)
        for pattern in self.patterns:
            sem_freq = len(set(self.head_nouns[pattern]).intersection(set(self.lexicon)))
            total_freq = len(self.head_nouns[pattern])
            r_log_f_score = (sem_freq / total_freq) * math.log(total_freq, 2)
            r_log_f_scores.append((pattern, r_log_f_score))
        # Sort the patterns in descending order by their score
        sorted_r_log_f_score = sorted(r_log_f_scores, key=lambda x: x[1], reverse=True)
        # Collect the top 10 patterns including any ties for 10th place and not including zeros
        pattern_pool = []
        if len(sorted_r_log_f_score) != 0:
            previous_score = sorted_r_log_f_score[0][1]
            pattern_pool.append(sorted_r_log_f_score[0])
        for score in sorted_r_log_f_score[1:]:
            if score[1] == 0 or len(pattern_pool) >= 10 and score[1] != previous_score:
                break
            pattern_pool.append(score)
            previous_score = score[1]
        return pattern_pool

    def get_candidate_list(self, pattern_pool):
        unique_head_nouns = set()
        for pattern in pattern_pool:
            for head_noun in self.head_nouns[pattern[0]]:
                unique_head_nouns.add(head_noun)
    



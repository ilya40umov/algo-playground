#include "subseq.h"
#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]) {
  char *seq1 = "ADEF0BBYC", *seq2 = "DFEAFYBC";
  printf("Given two sequences '%s' and '%s'.\n", seq1, seq2);
  char *subseq = common_subseq(seq1, seq2);
  if (subseq == NULL) {
    printf("No common subsequence found.\n");
  } else {
    printf("Longest common subsequence: '%s'.\n", subseq);
    free(subseq);
  }
  return EXIT_SUCCESS;
}

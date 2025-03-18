#include "subseq.h"
#include <stdlib.h>
#include <string.h>

static int **alloc_dp(int x, int y);
static void free_dp(int **dp, int x);
static int max(int a, int b);

char *common_subseq(const char *seq1, const char *seq2) {
  size_t len1 = strlen(seq1), len2 = strlen(seq2);

  if (len1 == 0 || len2 == 0) {
    return NULL;
  }

  int **dp = alloc_dp(len1, len2);

  int max_seq_len = 0, max_seq_i = 0, max_seq_j = 0;
  for (int i = 0; i < len1; i++) {
    for (int j = 0; j < len2; j++) {
      if (i == 0) {
        if (j == 0) {
          dp[i][j] = seq1[i] == seq2[j];
        } else {
          dp[i][j] = max(seq1[i] == seq2[j], dp[i][j - 1]);
        }
      } else {
        if (j == 0) {
          dp[i][j] = max(seq1[i] == seq2[j], dp[i - 1][j]);
        } else {
          dp[i][j] = max(dp[i - 1][j - 1] + (seq1[i] == seq2[j]),
                         max(dp[i - 1][j], dp[i][j - 1]));
        }
      }
      if (max_seq_len < dp[i][j]) {
        max_seq_len = dp[i][j];
        max_seq_i = i;
        max_seq_j = j;
      }
    }
  }

  char *subseq = NULL;
  if (max_seq_len > 0) {
    subseq = calloc(sizeof(char), max_seq_len + 1);
    int i = max_seq_i, j = max_seq_j, k = max_seq_len;
    while (k > 0) {
      if (seq1[i] == seq2[j]) {
        subseq[--k] = seq1[i];
        i--;
        j--;
      } else {
        if (i != 0 && dp[i - 1][j] == k) {
          i -= 1;
        } else {
          j -= 1;
        }
      }
    }
  }

  free_dp(dp, len1);

  return subseq;
}

static int **alloc_dp(int x, int y) {
  int **dp = malloc(sizeof(int *) * x);
  for (int i = 0; i < x; i++) {
    dp[i] = calloc(sizeof(int), y);
  }
  return dp;
}

static void free_dp(int **dp, int x) {
  for (int i = 0; i < x; i++) {
    free(dp[i]);
  }
  free(dp);
}

static int max(int a, int b) { return (a > b) ? a : b; }

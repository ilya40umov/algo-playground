#include <criterion/criterion.h>
#include <criterion/new/assert.h>

#include "../src/subseq.h"

static char *result = NULL;

void teardown(void) {
  if (result != NULL) {
    free(result);
  }
}

Test(subseqtests, common_subseq_returns_longest_subseq, .fini = teardown) {
  result = common_subseq("ADEF0BBYC", "DFEAFYBC");
  cr_expect(eq(str, result, "DEFBC"),
            "Longest common substring should be 'DEFBC'");
}

Test(subseqtests, common_subseq_returns_full_str_if_identical,
     .fini = teardown) {
  result = common_subseq("ABCDEFGHK", "ABCDEFGHK");
  cr_expect(eq(str, result, "ABCDEFGHK"),
            "Longest common substring should be 'ABCDEFGHK'");
}

Test(subseqtests, common_subseq_returns_null_if_no_common_substr,
     .fini = teardown) {
  result = common_subseq("ADEF0BBYC", "123456789");
  cr_expect_null(result);
}

Test(subseqtests, common_subseq_returns_null_if_one_str_is_empty,
     .fini = teardown) {
  result = common_subseq("ADEF0BBYC", "");
  cr_expect_null(result);
}

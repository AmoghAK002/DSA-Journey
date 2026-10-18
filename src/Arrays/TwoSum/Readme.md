# Problem 14 — Two Sum

## 1. Problem

**Category:** Arrays  
**Topic:** Pair Search  
**Pattern Used:** Brute Force — Nested Loops  
**Difficulty:** Easy

### Problem Statement

Given an integer array `nums` and an integer `target`, return the indices of the two different elements whose sum equals `target`.

- Exactly one valid answer exists.
- The same element cannot be used twice.
- The answer must contain indices, not the values themselves.

### Example

**Input:**
```java
nums = [3, 2, 4]
target = 6
```

**Output:**
```java
[1, 2]
```

Explanation: `nums[1] + nums[2] = 2 + 4 = 6`.

---

## 2. My Initial Approach

My initial approach was similar to Problem 13, Contains Duplicate, where I compared each element with the remaining elements in the array.

I decided to use brute force with two nested loops:

- The outer loop selects the first element.
- The inner loop checks the remaining elements.
- If their sum equals the target, I return their indices.
- If no matching pair is found during the search, the method returns `null`.

I chose this approach first because I was already familiar with comparing pairs using nested loops from the previous problem.

---

## 3. Why Initial Approach Was Not Optimal

The brute-force approach is correct, but it can perform many comparisons.

For an array of length `n`, the number of pairs checked in the worst case is:

\[
\frac{n(n-1)}{2}
\]

This gives a time complexity of **O(n²)**.

For larger arrays, the number of comparisons grows quickly. A more optimized approach using a hash-based data structure can achieve expected O(n) time, at the cost of additional space.

I started with brute force to understand the problem and establish a correct solution before considering optimization.

---

## 4. Key Insight

The key insight was to compare each element with every element that comes after it and check whether their sum equals the target.

For each pair of indices `i` and `j`, I check:

```java
nums[i] + nums[j] == target
```

If the condition is true, I return the two indices immediately.

Starting the inner loop at `i + 1` avoids comparing an element with itself and avoids checking the same pair twice.

---

## 5. Pattern Used

**Brute Force — Nested Loops**

This pattern systematically checks all possible pairs until a valid pair is found.

It is useful when the input is small, when simplicity is important, or when establishing a baseline solution before optimization.

---

## 6. Why This Pattern Works

Every possible pair of distinct indices is examined exactly once.

- The inner loop checks all remaining elements for the current outer-loop index.
- Once those comparisons finish, the outer loop advances.
- Earlier pairs do not need to be checked again.
- If a pair sums to the target, the method immediately returns its indices.

Because the problem guarantees that exactly one valid answer exists, the search will find a matching pair.

---

## 7. How Did I Recognize the Pattern?

I recognized the pattern because it was similar to Problem 13, Contains Duplicate.

In that problem, I compared each element with the remaining elements to find duplicates. In this problem, I followed the same approach but changed the condition: instead of checking whether two elements were equal, I checked whether their sum equaled the target.

This made brute force a natural starting point.

---

## 8. Invariant

Before each pair comparison, every pair examined previously has failed to produce the target sum.

The inner loop checks the remaining pairs for the current `i`. If all pairs are exhausted without a match, no valid pair exists. Under the problem's guarantee, a match will be found before that happens.

---

## 9. Algorithm

1. Start the outer loop at index `0` and continue until the second-to-last index.
2. For each `i`, start the inner loop at `i + 1`.
3. Compare `nums[i] + nums[j]` with `target`.
4. If the sum equals the target, return `new int[]{i, j}`.
5. If no pair matches, return `null` as a fallback.

---

## 10. Code

```java
package Arrays.TwoSum;

import java.util.*;

public class twoSum {

    public static int[] TwoSum(int[] nums, int target) {

        for (int i = 0; i < nums.length - 1; i++) {

            for (int j = i + 1; j < nums.length; j++) {

                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }

        return null;
    }

    public static void main(String[] args) {

        int[] nums = {-1, -2, -3, -4, -5};
        int target = -8;

        int[] ans = TwoSum(nums, target);

        System.out.println(Arrays.toString(ans));
    }
}
```

**Output:**
```text
[2, 4]
```

The elements at indices `2` and `4` are `-3` and `-5`, whose sum is `-8`.

---

## 11. Why Does This Code Work?

The outer loop selects the first index, while the inner loop selects a different index that comes after it.

The condition `j = i + 1` ensures that:

- An element is never paired with itself.
- The same pair is not checked in reverse order.
- Every possible pair is considered.

When a matching pair is found, `new int[]{i, j}` creates and returns a new integer array containing the two indices.

This is different from returning `new int[]{nums[i], nums[j]}`, which would return the values instead of their indices.

---

## 12. Complexity

### Time Complexity: O(n²)

The solution uses nested loops. In the worst case, it checks:

\[
(n-1)+(n-2)+\cdots+1
=\frac{n(n-1)}{2}
\]

Ignoring constants and lower-order terms, the time complexity is O(n²).

### Auxiliary Space Complexity: O(1)

The method uses only a fixed number of variables, `i` and `j`. The returned array always contains exactly two integers, so its size does not grow with the input.

The auxiliary space is therefore O(1), excluding the returned output.

---

## 13. Edge Cases

| Input | Target | Expected output |
|---|---:|---|
| `[2, 7, 11, 15]` | `9` | `[0, 1]` |
| `[3, 2, 4]` | `6` | `[1, 2]` |
| `[3, 3]` | `6` | `[0, 1]` |
| `[-1, -2, -3, -4, -5]` | `-8` | `[2, 4]` |
| `[1, 5, 8, 12]` | `20` | `[2, 3]` |

These cases test an early match, a later match, repeated values, negative numbers, and a pair near the end of the array.

The problem guarantees a valid pair, so the `null` fallback is not expected to execute for valid inputs. Empty arrays and null inputs are outside the stated constraints of this implementation.

---

## 14. Common Mistakes

### 1. Using the wrong loop variable in the inner condition

Initially, I wrote the inner loop condition using `i < nums.length` instead of `j < nums.length`.

The inner loop must check its own index, `j`. Otherwise, its termination condition does not correctly control the inner loop.

### 2. Printing an array using `toString()`

I initially used:

```java
System.out.println(ans.toString());
```

For an array, this does not print its elements in a readable list. I learned to use:

```java
System.out.println(Arrays.toString(ans));
```

### 3. Confusing indices with values

Returning `new int[]{i, j}` gives the indices. Returning `new int[]{nums[i], nums[j]}` gives the values.

The problem asks for indices, so the former is correct.

### 4. Forgetting the inner-loop starting position

Starting `j` at `i + 1` avoids rechecking earlier pairs and ensures the two indices are different.

### What I Learned from My Mistakes

- Each loop must use the correct variable in its condition.
- Java arrays need `Arrays.toString()` for readable element-by-element output.
- I must distinguish between returning indices and returning values.
- Understanding why a loop starts at a particular index is as important as writing the condition correctly.

---

## 15. My Learning Progression

I started by recognizing the similarity to Problem 13, Contains Duplicate, and chose nested loops.

I then:

1. Worked through the example `[3, 2, 4]` with target `6`.
2. Identified the loop-condition bug and corrected it.
3. Learned how to print an integer array using `Arrays.toString()`.
4. Understood why `new int[]{i, j}` returns the required indices.
5. Tested positive numbers, negative numbers, repeated values, and different matching positions.
6. Explained the correctness of the approach.
7. Analyzed the worst-case time complexity as O(n²) and auxiliary space as O(1).

I now understand both how the solution works and why its loop boundaries matter.

---

## 16. When Should I Use This Pattern?

Consider brute force with nested loops when:

- The problem asks you to find a pair of elements.
- You need to check every possible pair.
- The input is small or a straightforward baseline is useful.
- You want to establish correctness before optimizing.

For larger inputs, consider whether a different data structure or mathematical property can reduce the number of comparisons.

---

## 17. Similar Problems

- Problem 13 — Contains Duplicate
- Three Sum
- Four Sum
- Find a Pair With a Given Sum
- Count Pairs With a Given Sum

These problems involve checking relationships between multiple array elements, though some require different patterns or optimizations.

---

## 18. Memory Trigger

**“For Two Sum, compare each element with every element after it; return the two indices when their sum equals the target.”**

Think of Problem 13, Contains Duplicate, but replace the equality check with a sum-to-target check.

---

## 19. Revision

### Quick Recall

- **Category:** Arrays
- **Topic:** Pair Search
- **Pattern:** Brute Force — Nested Loops
- **Time:** O(n²)
- **Auxiliary space:** O(1)
- **Important loop condition:** `j = i + 1`
- **Return statement:** `return new int[]{i, j};`

### Revision Checklist

- [ ] Explain why two nested loops are used.
- [ ] Explain why the inner loop starts at `i + 1`.
- [ ] Write the solution independently.
- [ ] Dry-run a positive-number and negative-number example.
- [ ] Explain the worst-case time complexity.
- [ ] Explain the auxiliary space complexity.
- [ ] Compare the brute-force approach with a hash-based optimization.

### Commit Message

`Brute Force approach`

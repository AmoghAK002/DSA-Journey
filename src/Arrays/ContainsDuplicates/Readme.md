# Problem 13 — Contains Duplicate

## 1. Problem

Given an integer array `nums`, return `true` if any value appears at least twice. Return `false` if every element is distinct.

Example:

```text
Input:  nums = [4, 2, 7, 2]
Output: true

Input:  nums = [1, 2, 3, 4]
Output: false
```

---

## 2. My Initial Approach

My approach was to take one element and compare it with every element that comes after it.

I decided to use nested loops:

- The outer loop selects an element.
- The inner loop compares that element with the elements after it.
- If two elements are equal, a duplicate exists.

I also realized that I didn't need to compare the same pair twice because equality works in both directions.

For example, comparing `nums[0]` with `nums[2]` is sufficient. There is no need to compare `nums[2]` with `nums[0]` again.

---

## 3. Why Initial Approach Was Not Optimal

The brute-force approach is correct, but its worst-case time complexity is `O(n²)`.

It compares pairs of elements, so the number of comparisons increases quadratically as the array grows.

A more efficient approach can use a data structure such as a `HashSet` to detect previously seen elements in expected `O(n)` time.

However, the nested-loop approach is a valid solution and helps establish the fundamentals of duplicate detection and pairwise comparison.

---

## 4. Key Insight

To determine whether any duplicate exists, compare every pair of distinct elements until a match is found or all necessary pairs have been checked.

Example:

```text
nums = [4, 2, 7, 2]

Compare 4 with 2 → no match
Compare 4 with 7 → no match
Compare 4 with 2 → no match

Compare 2 with 7 → no match
Compare 2 with 2 → match found

Output = true
```

Once a matching pair is found, there is no need to continue because the problem only asks whether a duplicate exists.

---

## 5. Pattern Used

**Brute Force — Nested Loops**

We select an element and compare it with every element that follows it.

The state consists of two loop variables:

```text
i → index of the selected element
j → index of the element being compared
```

---

## 6. Why This Pattern Works

The outer loop selects each element except the last one.

The inner loop starts at `i + 1`, ensuring that:

- An element is never compared with itself.
- A pair is not checked again in reverse order.
- Every pair of distinct indices is checked exactly once, unless the method returns early after finding a duplicate.

If a duplicate exists, at least one pair of equal values will be found.

If every required comparison finishes without finding a match, all elements are distinct.

---

## 7. How Did I Recognize the Pattern?

I recognized that I needed to compare elements against one another rather than compare each element with a fixed target.

I chose nested loops because I needed to examine pairs of elements.

The inner loop starts at `i + 1` because I only need to compare the current element with the elements after it.

This avoids unnecessary comparisons and ensures that I don't compare an element with itself.

---

## 8. Invariant

**Before each comparison, the algorithm has not found any duplicate among the pairs it has already checked.**

If a matching pair is found, the algorithm immediately returns `true`.

If the loops finish without finding a match, every pair of distinct indices has been checked, so the array contains no duplicates.

---

## 9. Algorithm

1. Check whether the array is empty. If it is, return `false`.
2. Select an element using the outer loop, starting from index `0`.
3. Compare it with every element after it using the inner loop.
4. If two elements are equal, return `true`.
5. Continue until all necessary pairs have been checked.
6. If no duplicate is found, return `false`.

---

## 10. Code

```java
package Arrays.ContainsDuplicates;

public class ContainsDuplicate {

    public static boolean containsDuplicate(int[] nums) {
        if (nums.length == 0) return false;

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums = {5};
        System.out.println(containsDuplicate(nums));
    }
}
```

The implementation assumes `nums` is not `null`, consistent with the problem constraints.

---

## 11. Why Does This Code Work?

The outer loop selects each possible starting element, while the inner loop checks every element that comes after it.

If `nums[i] == nums[j]`, the two distinct indices contain the same value, so returning `true` is correct.

Returning immediately is safe because finding even one duplicate satisfies the problem's requirement.

If the loops finish without returning `true`, every pair of distinct indices has been checked. Therefore, no duplicate exists, and returning `false` is correct.

---

## 12. Complexity

### Time Complexity

**O(n²)**

In the worst case, the array contains distinct elements, so the algorithm must check every pair.

For an array of length `n`, the number of comparisons is:

```text
(n - 1) + (n - 2) + ... + 2 + 1
```

This simplifies to:

```text
n(n - 1) / 2
```

Expanding:

```text
(n² - n) / 2
```

Ignoring constants and lower-order terms gives `O(n²)`.

For example:

```text
n = 4 → 6 comparisons
n = 8 → 28 comparisons
```

Doubling the input size makes the comparison count approximately four times larger for sufficiently large inputs.

The algorithm may terminate earlier if it finds a duplicate, but the worst-case time complexity remains `O(n²)`.

### Auxiliary Space Complexity

**O(1)**

The algorithm uses only a fixed number of variables, such as `i` and `j`.

It does not create another array, collection, or data structure whose size grows with the input.

---

## 13. Edge Cases

### Duplicate appears at the beginning

```text
nums = [1, 1, 2, 3]

Output = true
```

The algorithm finds the duplicate quickly.

### Duplicate appears near the end

```text
nums = [4, 7, 2, 9, 7]

Output = true
```

The algorithm returns `true` when it finds the matching `7`.

### All elements are distinct

```text
nums = [1, 2, 3, 4]

Output = false
```

All necessary pairs are checked.

### Every element is the same

```text
nums = [1, 1, 1]

Output = true
```

The first repeated value is enough to return `true`.

### Empty array

```text
nums = []

Output = false
```

The explicit empty-array check returns `false`.

### Single element

```text
nums = [5]

Output = false
```

A single element cannot form a duplicate pair.

---

## 14. Common Mistakes

- **Incorrect outer-loop boundary:** Initially, I was unsure why the outer loop stops at `nums.length - 1`. The last element has no following element to compare against, so it doesn't need to be selected as the starting element.
- **Comparing an element with itself:** Starting the inner loop at `j = i` would compare the current element with itself. Starting at `j = i + 1` avoids this.
- **Checking the same pair twice:** Comparing both `(i, j)` and `(j, i)` is unnecessary because equality is symmetric.
- **Misunderstanding early termination:** Once a duplicate is found, there is no need to inspect the remaining pairs because the answer is already `true`.
- **Underexplaining nested-loop complexity:** Saying only that nested loops mean `O(n²)` is incomplete. The comparison count grows as `1 + 2 + ... + (n - 1)`, which is quadratic.

---

## 15. My Learning Progression

I started with the idea of taking an element and comparing it against the other elements.

While working through the problem, I clarified why the loop boundaries matter:

- The outer loop stops before the last index because there is no next element to compare against.
- The inner loop starts at `i + 1` to avoid self-comparison and repeated pairs.
- The inner loop finishes its necessary comparisons before the outer loop advances, ensuring that no required pair is missed.

I also clarified that the method can return `true` immediately after finding a duplicate. Checking additional pairs would waste time because the result cannot change.

Finally, I derived why the worst-case time complexity is `O(n²)` and why the auxiliary space complexity is `O(1)`.

---

## 16. When Should I Use This Pattern?

Use brute-force pairwise comparison when:

- The problem requires comparing pairs of elements.
- The input is small enough that a quadratic solution is acceptable.
- You want a straightforward baseline solution before optimizing.
- No additional data structure is needed to solve the problem.

Examples include:

- Contains Duplicate using nested loops
- Checking whether any two elements are equal
- Finding pairs that satisfy a condition
- Comparing all pairs of elements in a small array

For large arrays, consider whether a hash-based approach can reduce the time complexity.

---

## 17. Similar Problems

- Two Sum
- Contains Duplicate using a HashSet
- Find All Duplicate Elements
- Find Duplicate in an Array
- Check Whether Any Pair Has a Given Sum
- Find All Pairs With a Given Difference

These problems involve comparing elements or tracking relationships between them. The appropriate pattern depends on the exact requirements.

---

## 18. Memory Trigger

> **Need to compare pairs → choose one element → compare with later elements → stop when a duplicate is found.**

For nested-loop duplicate detection:

> **`i` selects the element; `j = i + 1` checks the remaining elements; a match means `true`.**

---

## 19. Revision

### What is the pattern?

Brute Force — Nested Loops.

### Why does `j` start at `i + 1`?

To avoid comparing an element with itself and checking the same pair twice.

### Why does the outer loop stop at `nums.length - 1`?

The last element has no elements after it to compare against.

### Why can the method return `true` immediately?

One duplicate is sufficient to satisfy the problem.

### How do we know no duplicate was missed?

Every pair of distinct indices is checked unless an earlier duplicate already established the answer.

### Time complexity?

**O(n²)** in the worst case because the number of pairwise comparisons grows quadratically.

### Auxiliary space complexity?

**O(1)** because only a fixed number of variables are used.

### Core idea?

> **Compare every pair of distinct indices once. If any pair contains equal values, return `true`; otherwise, return `false`.**

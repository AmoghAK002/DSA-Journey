# Problem 08: Remove Duplicates from a Sorted Array

## 1. Problem

Given an integer array sorted in non-decreasing order, remove duplicate values **in-place** so that each distinct element appears only once.

Return the number of distinct elements, `k`. The first `k` positions of the original array must contain the distinct values in sorted order.

- Do not create another array.
- The order of distinct elements must be preserved.
- If the input is `null` or empty, return `0`.

**Example**

```text
Input:  [1, 1, 2, 2, 3, 3]
Output: 3

First k positions: [1, 2, 3]
```

## 2. My Initial Approach

Initially, I thought of comparing the elements and returning the count of distinct values without modifying the array.

However, the problem requires both removing duplicates logically **and modifying the original array in-place**. Returning only the count is not sufficient.

## 3. Why My Initial Approach Was Not Optimal

The problem explicitly requires the first `k` positions of the original array to contain the distinct values.

Creating another array would consume additional space unnecessarily. Since the array is already sorted, I can identify distinct values by comparing adjacent elements and reuse positions in the original array.

## 4. Key Insight

Because the array is sorted, duplicate values appear next to each other.

I can compare the current element with the previous element:

- If they are equal, the current element is a duplicate and can be skipped.
- If they differ, the current element is a new distinct value and should be written at the next available position.

I maintain two variables:

- `i`: scans the array to inspect elements.
- `write`: tracks the next position where a distinct element should be stored.

I initialize `write = 1` because, for a non-empty array, the first element is already a distinct value.

## 5. Pattern Used

**Pattern: Linear Scan / Two Pointers**

I recognized that I needed to scan the array once while maintaining a separate position for writing distinct values.

The reading pointer (`i`) examines the input, while the writing pointer (`write`) tracks the compacted portion of the same array.

This allows me to solve the problem in-place without creating another array.

## 6. Why This Pattern Works

The array is sorted, so equal values are adjacent. Comparing `nums[i - 1]` with `nums[i]` is enough to determine whether the current element is distinct from the previous one.

When a new distinct element is found, I write it at `nums[write]` and increment `write`.

The writing position never moves ahead of the reading position. Therefore, writing a distinct value does not overwrite an element that still needs to be inspected.

## 7. Invariant

During the loop:

- `write` equals the number of distinct elements retained so far.
- The first `write` positions contain those distinct values in sorted order.
- Every value in that prefix appears only once.
- `i` scans the remaining elements to determine whether they introduce a new distinct value.

After the loop, `write` equals the total number of distinct elements, so it can be returned as `k`.

**Important correction:** `write` does not always equal `1`. It starts at `1` for a non-empty array and increases whenever a new distinct value is retained.

## 8. Algorithm

1. If the array is `null` or empty, return `0`.
2. Initialize `write = 1`, because the first element is already retained.
3. Traverse the array from index `1` to the last index.
4. Compare the current element with the previous element.
5. If they differ, write the current element at `nums[write]` and increment `write`.
6. Return `write`.

## 9. Code

```java
package Arrays.RemoveDuplicatesFromASortedArray;

public class RemoveDuplicates {

    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int write = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] != nums[i]) {
                nums[write++] = nums[i];
            }
        }

        return write;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 2, 3, 3};
        System.out.println(removeDuplicates(nums));
    }
}
```

## 10. Why Does This Code Work?

The first element is retained automatically, so `write` starts at `1`.

The loop checks each remaining element against its predecessor. When the values differ, the current element is distinct from the previous one, so it is written at the next available position.

The expression `nums[write++] = nums[i]` writes the current value and then increments `write`.

After every iteration, the retained prefix contains the distinct values encountered so far. At the end, `write` is the number of distinct values.

## 11. Complexity Analysis

**Time Complexity: O(n)**

The algorithm traverses the array once, performing a constant amount of work per element.

**Auxiliary Space Complexity: O(1)**

The algorithm uses only a fixed number of variables and modifies the original array. No additional array or collection is created.

## 12. Edge Cases

| Input | Expected return |
|---|---:|
| `null` | `0` |
| `[]` | `0` |
| `[7]` | `1` |
| `[5, 5, 5, 5]` | `1` |
| `[1, 2, 3, 4]` | `4` |
| `[1, 1, 2, 2, 3, 3]` | `3` |
| `[1, 1, 2, 2, 3, 4]` | `4` |

## 13. Common Mistakes I Learned From

- **Returning only the count:** The problem also requires modifying the original array.
- **Comparing the wrong pair:** Comparing the current element with its predecessor aligns with writing the current distinct value.
- **Incorrect loop boundary:** Using `i < nums.length - 1` would skip the final element. The correct boundary is `i < nums.length`.
- **Incorrect initialization:** For a non-empty array, `write` starts at `1` because the first element is already retained.
- **Misunderstanding `write`:** It represents both the next available write index and the number of distinct values retained so far.
- **Creating an unnecessary array:** The original array can be compacted in-place.
- **Ignoring empty input:** Handle `null` and empty arrays before accessing any element.

## 14. My Learning Progression

I initially considered counting distinct values without modifying the array. While implementing the solution, I realized that the problem also requires compacting the distinct values into the original array.

I learned to:

1. Use the sorted property to detect duplicates through adjacent comparisons.
2. Separate the reading pointer from the writing pointer.
3. Initialize the writing pointer to `1` for non-empty arrays.
4. Increment the writing pointer only when a distinct value is retained.
5. Correct the loop boundary so the final element is processed.
6. Understand why the modified array's first `k` positions contain the answer.
7. Derive the O(n) time and O(1) auxiliary space complexities.

## 15. When Should I Use This Pattern?

Use this pattern when:

- The input array is sorted.
- Duplicate values appear next to each other.
- The problem requires modifying the array in-place.
- The desired output can be compacted into the beginning of the array.
- A read pointer can inspect elements while a write pointer tracks the next valid position.

## 16. Similar Problems

- Move Zeroes
- Remove Element
- Remove Duplicates from Sorted Array II
- Merge Two Sorted Arrays
- Two Sum II — Input Array Is Sorted

## 17. Memory Trigger

**Question:** Is the array sorted, and can I compact the valid elements into its beginning without allocating another array?

**Trigger:** Consider a read pointer to inspect elements and a write pointer to maintain the compacted prefix.

## 18. Revision

- [ ] Explain why the sorted property makes duplicate detection straightforward.
- [ ] Explain why `write` starts at `1`.
- [ ] Explain why the loop starts at `i = 1`.
- [ ] Explain why the loop condition is `i < nums.length`.
- [ ] Explain what `write` represents.
- [ ] Explain why the first `write` positions contain the distinct values.
- [ ] Derive O(n) time and O(1) auxiliary space.
- [ ] Reimplement the solution without looking at the code.

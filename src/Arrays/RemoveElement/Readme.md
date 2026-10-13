# Problem 10: Remove Element

## 1. Problem Statement

Given an integer array `nums` and an integer `val`, remove all occurrences of `val` in place and return the number of elements that are not equal to `val`.

The first `k` positions of the original array must contain the elements that are not equal to `val`, where `k` is the returned count.

**Requirements:**
- Modify the original array in place.
- Retain elements that are not equal to `val`.
- Return the number of retained elements.
- Do not create another array.

**Example:**

```text
Input:  nums = [3, 2, 2, 3]
val = 3

Output: 2
Valid portion: [2, 2]
```

## 2. My Initial Approach

I thought of using the same approach as the Move Zeroes problem.

However, there was a twist: instead of moving elements equal to `val` to the end, I had to remove them from the valid portion and retain only elements that are not equal to `val`.

## 3. Why I Needed to Think Further

Initially, I treated this problem like Move Zeroes, where unwanted elements are moved to the end.

But this problem has a different requirement. Elements equal to `val` must be excluded from the first `k` positions, and the method must return the number of retained elements.

I was also confused about whether to return the modified array or the count of elements that are not equal to `val`.

## 4. Key Insight

The modification pointer should advance only when an element not equal to `val` is encountered.

When an element equals `val`, it is skipped, and the modification pointer stays in place.

After scanning the array, the value of `write` represents the number of retained elements.

## 5. Pattern Used

**Two Pointers**

The two pointers have different responsibilities:

- `i`: scans the entire array.
- `write`: tracks the next position where a valid element should be placed.

The `write` pointer advances only when an element is retained.

## 6. Algorithm

1. Handle an empty or null array by returning `0`.
2. Initialize `write` to `0`.
3. Scan the array from left to right.
4. If the current element is not equal to `val`, place it at the position tracked by `write` and advance `write`.
5. Skip elements equal to `val`.
6. Return `write`, which represents the number of retained elements.

## 7. Java Implementation

```java
package Arrays.RemoveElement;

public class RemoveElement {

    public static int valueofValidElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int write = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[write++] = nums[i];
            }
        }

        return write;
    }

    public static void main(String[] args) {
        int[] nums = {5, 5, 7, 4, 5, 2, 3};

        int write = valueofValidElement(nums, 5);

        for (int i = 0; i < write; i++) {
            System.out.print(nums[i] + " ");
        }

        System.out.println();
        System.out.println("Element count: " + write);
    }
}
```

**Output:**

```text
7 4 2 3
Element count: 4
```

## 8. Dry Run

**Input:** `nums = [5, 5, 7, 4, 5, 2, 3]`, `val = 5`

| Current element | Action | `write` after processing |
|---|---|---:|
| `5` | Skip | 0 |
| `5` | Skip | 0 |
| `7` | Place at index 0 | 1 |
| `4` | Place at index 1 | 2 |
| `5` | Skip | 2 |
| `2` | Place at index 2 | 3 |
| `3` | Place at index 3 | 4 |

Final valid portion: `[7, 4, 2, 3]`

Returned count: `4`

The array itself is not resized. Only its first `k` positions are required to contain the retained elements; values beyond that prefix do not matter.

## 9. Why Does This Code Work?

- The scanning pointer examines every element.
- Elements equal to `val` are skipped.
- Elements not equal to `val` are copied to the next available position.
- Since the scan proceeds from left to right, the relative order of retained elements is preserved.
- `write` increases once for every retained element, so its final value equals the required count.

## 10. Complexity Analysis

**Time Complexity: \(O(n)\)**

The array is scanned once. Each iteration performs a constant amount of work, giving linear time complexity.

**Auxiliary Space: \(O(1)\)**

The algorithm modifies the original array and uses only a constant amount of additional memory. No extra array or data structure is created.

## 11. Edge Cases and Testing

| Input | `val` | Expected count | Valid portion |
|---|---:|---:|---|
| `[5, 5, 7, 4, 5, 2, 3]` | 5 | 4 | `[7, 4, 2, 3]` |
| `[]` | 5 | 0 | `[]` |
| `[5, 5, 5]` | 5 | 0 | `[]` |
| `[1, 2, 3]` | 5 | 3 | `[1, 2, 3]` |

The first three cases were verified during practice. The all-valid case is an additional useful test.

## 12. Mistakes and Debugging

**My initial mistake:** I treated this problem like Move Zeroes and thought about moving elements equal to `val` to the end.

However, the requirement is to exclude those elements from the valid prefix.

I initially used an outer condition that checked whether an element was equal to `val`. This meant the code ignored the elements that needed to be retained.

After debugging, I understood that the condition must identify elements that are not equal to `val`.

I also clarified the difference between returning the modified array and returning the count of retained elements.

## 13. My Learning Progression

Initially, I connected this problem to Move Zeroes because both involve modifying an array in place.

I then recognized the difference between moving unwanted elements to the end and retaining only the required elements in the valid prefix.

I corrected my condition, used `write` to track retained elements, and verified that the method returned the correct count.

I also learned that the method returns an integer, while the modified array remains accessible through the original array reference. The `main` method can print the first `k` positions to display the valid portion.

## 14. When Should I Use This Pattern?

Consider this pattern when:
- Elements must be filtered from an array in place.
- The original array must be modified without allocating another array.
- A valid prefix must be maintained.
- The number of retained elements must be returned.

## 15. Memory Trigger

**Question:** How do I remove unwanted values in place and return the number of elements that remain?

**My trigger:** Scan the array, retain elements that satisfy the condition, advance the modification pointer only when retaining an element, and return the final count.

## 16. Similar Problems

- Move Zeroes
- Remove Duplicates from a Sorted Array
- Remove Duplicates from a Sorted Array II
- Partition an array based on a condition

## 17. Revision Checklist

- [ ] Can I explain why elements equal to `val` are skipped?
- [ ] Can I explain the roles of `i` and `write`?
- [ ] Can I explain why `write` is the number of retained elements?
- [ ] Can I distinguish returning a count from returning an array?
- [ ] Can I implement the solution independently?
- [ ] Can I justify \(O(n)\) time and \(O(1)\) auxiliary space?

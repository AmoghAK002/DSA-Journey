# Problem 9: Move Zeroes

## 1. Problem Statement

Given an integer array `nums`, move all zeroes to the end while maintaining the relative order of all non-zero elements.

**Requirements:**
- Modify the original array in place.
- Preserve the relative order of non-zero elements.
- Move all zeroes to the end.
- Use \(O(1)\) auxiliary space.

**Example:**

```text
Input:  [0, 1, 0, 3, 12]
Output: [1, 3, 12, 0, 0]
```

## 2. My Initial Approach

I thought of modifying the array like the previous problem, Remove Duplicates from a Sorted Array, by placing the non-zero elements at the beginning.

I knew how to place the non-zero elements at the beginning, but I initially did not know how to place the zeroes at the end.

## 3. Why I Needed to Think Further

Placing the non-zero elements at the beginning was only part of the requirement.

I also needed to handle the remaining positions so that the final array contained zeroes at the end.

## 4. Key Insight

The modification pointer, `write`, tracks the next position where a non-zero element should be placed.

After all non-zero elements have been processed, the remaining positions must be filled with zeroes.

This completes the transformation without creating another array.

## 5. Pattern Used

**Two Pointers**

The two pointers have different responsibilities:

- `i` scans through the entire array.
- `write` tracks the next position where a non-zero element should be placed.

The `write` pointer advances only when a non-zero element is placed.

## 6. Algorithm

1. Initialize `write` to `0`.
2. Scan the array using `i`.
3. Whenever a non-zero element is found, write it at the position tracked by `write` and advance `write`.
4. After scanning the array, fill every remaining position with zero.
5. The original array now contains all non-zero elements first, followed by zeroes.

## 7. Java Implementation

```java
package Arrays.MoveZeroes;

public class MoveZeroes {

    public static void moveZeroes(int[] nums) {
        int write = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[write++] = nums[i];
            }
        }

        for (int i = write; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};

        moveZeroes(nums);

        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}
```

**Output:**

```text
1 3 12 0 0
```

## 8. Dry Run

**Input:** `[0, 1, 0, 3, 12]`

| Stage | Array state |
|---|---|
| Initial array | `[0, 1, 0, 3, 12]` |
| After placing `1` | `[1, 1, 0, 3, 12]` |
| After placing `3` | `[1, 3, 0, 3, 12]` |
| After placing `12` | `[1, 3, 12, 3, 12]` |
| After filling remaining positions | `[1, 3, 12, 0, 0]` |

The intermediate array may temporarily contain duplicate values because elements are overwritten in place. The remaining positions are corrected afterward.

## 9. Why Does This Code Work?

- The scanning pointer examines each element in order.
- Every non-zero element is placed at the next available position.
- Since non-zero elements are processed from left to right, their relative order is preserved.
- The modification pointer identifies where the remaining zeroes should begin.
- Filling the remaining positions with zeroes produces the required result.

## 10. Complexity Analysis

**Time Complexity: \(O(n)\)**

There are two loops. The first scans the array, and the second processes the remaining positions.

More precisely, the total work is \(O(n) + O(n) = O(2n)\), which simplifies to **\(O(n)\)** in Big-O notation because constant factors are ignored.

**Auxiliary Space: \(O(1)\)**

The algorithm modifies the original array and uses only a constant amount of additional memory.

## 11. Edge Cases and Testing

The following test cases passed:

| Input | Expected output |
|---|---|
| `[0, 1, 0, 3, 12]` | `[1, 3, 12, 0, 0]` |
| `[0, 0, 0, 5]` | `[5, 0, 0, 0]` |
| `[1, 2, 3]` | `[1, 2, 3]` |
| `[0, 0, 0]` | `[0, 0, 0]` |
| `[]` | `[]` |

## 12. Mistakes and Difficulties

**My main difficulty:** I knew how to place non-zero elements at the beginning but initially struggled with moving the zeroes to the end.

I also needed to understand how to modify an array value using the modification pointer.

The important distinction is:

```java
nums[write] = nums[i];
```

This assignment writes the current non-zero element into the destination position. Advancing `write` then prepares the next destination position.

## 13. My Learning Progression

Initially, I knew how to compact non-zero elements at the beginning, based on the previous array problem. However, I did not know how to handle the remaining positions.

While working through the problem, I used the modification pointer to track where non-zero elements should be placed. I then understood that the remaining positions needed to be set to zero.

I implemented the solution and verified it with multiple test cases.

## 14. When Should I Use This Pattern?

Consider the two-pointer pattern when:
- An array must be modified in place.
- Elements must be rearranged while preserving a particular order.
- One pointer scans the array while another tracks a destination position.
- The problem requires constant auxiliary space.

## 15. Memory Trigger

**Question:** How can I move non-zero elements to the beginning of an array and place all zeroes at the end without creating another array?

**My trigger:** Use a modification pointer to track where non-zero elements belong, then handle the remaining positions so that the zeroes end up at the back.

## 16. Similar Problems

- Remove Duplicates from a Sorted Array
- Remove Element
- Move all negative numbers to one side of an array
- Partition an array based on a condition

## 17. Revision Checklist

- [ ] Can I explain the roles of `i` and `write`?
- [ ] Can I explain why the relative order of non-zero elements is preserved?
- [ ] Can I explain why the remaining positions must be filled with zeroes?
- [ ] Can I implement the solution independently?
- [ ] Can I justify \(O(n)\) time and \(O(1)\) auxiliary space?

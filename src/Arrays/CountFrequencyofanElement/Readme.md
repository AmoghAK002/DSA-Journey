# Day 05 — Count Frequency of an Element

## 1. Problem

Given an integer array and a target element, find how many times the target element appears in the array.

Example:

```text
Input:  arr = [5, 6, 7, 5, 9], target = 5
Output: 2
```

---

## 2. My Initial Approach

My initial approach was the same as the final approach.

I realized that I simply needed to go through the array, compare every element with the target, and increase a counter whenever they are equal.

---

## 3. Why Initial Approach Was Not Optimal

There was no separate inefficient initial approach for this problem.

The linear scan is already optimal for an arbitrary unsorted array when counting the frequency of one target.

---

## 4. Key Insight

We compare every element with the target.

- If `arr[i] == target`, increase `count`.
- Otherwise, do nothing.
- After processing the entire array, `count` is the frequency of the target.

Example:

```text
arr = [5, 6, 7, 5, 9]
target = 5

5 → count = 1
6 → count = 1
7 → count = 1
5 → count = 2
9 → count = 2
```

Final answer = `2`.

---

## 5. Pattern Used

**Linear Scan / Single Pass**

We process each element exactly once while maintaining a small amount of state.

State:

```text
count
```

---

## 6. Why This Pattern Works

Every occurrence of the target contributes exactly `1` to the final frequency.

Therefore:

```text
if element == target
    count++
```

After every element has been processed, `count` contains the total number of occurrences.

There is no need to sort the array or store the elements anywhere else.

---

## 7. How Did I Recognize the Pattern?

The problem asks for information about the elements in an array.

I only need to compare each element with one target value.

Therefore, I can process the array in a single pass:

```text
element → compare with target → update count
```

---

## 8. Invariant

> **After processing the elements so far, `count` represents the number of times the target has appeared among those processed elements.**

This remains true throughout the loop.

---

## 9. Algorithm

1. Initialize `count = 0`.
2. Traverse the array from beginning to end.
3. Compare the current element with the target.
4. If they are equal, increment `count`.
5. Return `count`.

---

## 10. Code

```java
public static int frequency(int[] arr, int target) {
    int count = 0;

    for (int i = 0; i < arr.length; i++) {
        if (arr[i] == target) {
            count++;
        }
    }

    return count;
}
```

---

## 11. Why Does This Code Work?

The loop visits every element exactly once.

Whenever an element equals the target, `count` increases by one.

Therefore, every occurrence contributes exactly one increment.

After the loop finishes, all elements have been checked, so `count` is the total frequency of the target.

---

## 12. Complexity

### Time Complexity

**O(n)**

In the worst case, every element must be inspected.

We cannot generally stop early because a target occurrence may appear later.

Example:

```text
[1, 4, 8, 2, 9, 6]
```

If the target is `6`, we must inspect the entire array to know its frequency.

### Space Complexity

**O(1)** auxiliary space.

Only the `count` variable and a few loop variables are used.

---

## 13. Edge Cases

### Target does not exist

```text
arr = [1, 2, 3, 4]
target = 5

Output = 0
```

### Every element is the target

```text
arr = [5, 5, 5]
target = 5

Output = 3
```

### Target appears once

```text
arr = [1, 2, 5, 7]
target = 5

Output = 1
```

### Empty array

If empty arrays are allowed:

```text
arr = []
target = 5

Output = 0
```

The loop simply does not execute.

---

## 14. Common Mistakes

- Misspelling `frequency` as `frequencey`.
- Thinking a non-matching element allows us to stop early.
- Forgetting to increment `count` when a match is found.
- Confusing the number of elements in the array with the frequency of the target.
- Describing `O(n)` vaguely instead of understanding that up to `n` elements may need to be inspected.

---

## 15. My Learning Progression

I already understood the basic approach from the beginning:

> Compare every element with the target and increment the count when they match.

The important thing I learned was understanding `count` as the **state of the algorithm**.

After processing each element:

> `count` tells me how many times the target has appeared so far.

I also understood why we cannot stop early. A non-matching element tells us nothing about whether the target will appear later.

Therefore, the complete solution is:

```text
Scan the array
     ↓
Compare with target
     ↓
Match?
  ↙     ↘
Yes      No
 ↓        ↓
count++  continue
     ↓
Return count
```

---

## 16. When Should I Use This Pattern?

Use a linear scan when:

> **Each element can be processed independently and only a small amount of state needs to be maintained while traversing the data.**

Examples:

- Find maximum
- Find minimum
- Count frequency
- Check if array is sorted
- Find second largest
- Count positive/negative numbers
- Count even/odd numbers
- Calculate sum
- Find an occurrence

---

## 17. Similar Problems

- Find Maximum
- Find Minimum
- Check if Array Is Sorted
- Find Second Largest
- Count Positive Numbers
- Count Negative Numbers
- Count Even/Odd Numbers
- Sum of Array Elements
- Find First Occurrence

---

## 18. Memory Trigger

> **Need information from every element → scan once → maintain small state.**

For frequency specifically:

> **Target + count → scan → match → `count++`.**

---

## 19. Revision

### What is the pattern?

Linear Scan / Single Pass.

### What is the state?

`count`

### What does `count` represent?

The number of times the target has appeared among the elements processed so far.

### Why can't we stop early?

Because another occurrence of the target may exist later in the array.

### Time complexity?

**O(n)**

### Space complexity?

**O(1)**

### Core idea?

> **Scan every element, compare it with the target, and increment the counter when they match.**
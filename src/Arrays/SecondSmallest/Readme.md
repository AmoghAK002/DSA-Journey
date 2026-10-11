# Day 06 — Find the Second Smallest Distinct Element

## 1. Problem

Given an integer array, find the **second-smallest distinct element**.

Examples:

```text
Input:  [8, 3, 5, 3, 1, 8]
Output: 3

Input:  [5, 5, 5]
Output: No second-smallest distinct element

Input:  [-5, -2, -9, -9]
Output: -5
```

A distinct element means a value that is different from the other value being considered. Duplicate occurrences do not count as separate distinct values.

## 2. My Initial Approach

I reused the approach from Day 3, where I found the second-largest distinct element.

I maintained two variables:

- `smallest` — the smallest element found so far.
- `secondSmallest` — the second-smallest distinct element found so far.

I initialized `smallest` with `arr[0]` and `secondSmallest` with `null`.

Then I traversed the array and updated these variables based on the current element.

## 3. Why Initial Approach Was Not Optimal

My approach was already optimal for an arbitrary unsorted array.

Sorting could also solve the problem, but sorting takes O(n log n) time. A single-pass solution takes O(n) time and maintains only two pieces of state.

Therefore, there was no need to sort the array.

## 4. Key Insight

Maintain two values while traversing the array.

### Case 1: A new smallest element is found

If `current < smallest`:

```java
secondSmallest = smallest;
smallest = current;
```

The old smallest becomes the second smallest, and the current element becomes the new smallest.

### Case 2: The second smallest does not exist yet

If `secondSmallest == null` and `current > smallest`:

```java
secondSmallest = current;
```

The current element becomes the second smallest because it is distinct from the smallest.

### Case 3: A better second-smallest candidate is found

If `current > smallest` and `current < secondSmallest`:

```java
secondSmallest = current;
```

The current element lies strictly between the smallest and second-smallest values, so it replaces the previous second smallest.

Otherwise, ignore the element.

## 5. Pattern Used

**Linear Scan / Single Pass with Two-State Maintenance**

We process every element once and maintain the two smallest distinct values seen so far.

## 6. Why This Pattern Works

Every new element falls into one of these categories:

- It is smaller than the current smallest, so both tracked values need to be updated.
- It is larger than the smallest and can become the second smallest.
- It is between the smallest and second smallest, so it improves the second-smallest value.
- It is a duplicate or is too large to affect either value, so we ignore it.

By updating the two variables correctly, we maintain the two smallest distinct values without sorting the array.

## 7. How Did I Recognize the Pattern?

I recognized that this problem resembles Day 3 — Find the Second Largest Distinct Element.

In Day 3, I maintained:

- `largest`
- `secondLargest`

Here, I maintain:

- `smallest`
- `secondSmallest`

The direction of comparison changes, but the fundamental idea remains the same: maintain two ranked values during one traversal.

## 8. Invariant

**After processing each element, `smallest` contains the smallest value seen so far, and `secondSmallest` contains the second-smallest distinct value seen so far, if one exists.**

This invariant explains why the algorithm works.

## 9. Algorithm

1. Initialize `smallest` with the first array element.
2. Initialize `secondSmallest` to `null`.
3. Traverse the array starting from index 1.
4. If the current element is smaller than `smallest`, move the old smallest into `secondSmallest` and update `smallest`.
5. Otherwise, if `secondSmallest` is `null` and the current element is greater than `smallest`, assign it to `secondSmallest`.
6. Otherwise, if the current element lies strictly between `smallest` and `secondSmallest`, update `secondSmallest`.
7. Ignore duplicates and elements that do not change either value.
8. Return `secondSmallest`.

## 10. Code

```java
public static Integer secSmallest(int[] arr) {
    if (arr == null || arr.length == 0) {
        return null;
    }

    int smallest = arr[0];
    Integer secondSmallest = null;

    for (int i = 1; i < arr.length; i++) {
        int current = arr[i];

        if (current < smallest) {
            secondSmallest = smallest;
            smallest = current;
        } else if (secondSmallest == null
                && current > smallest) {
            secondSmallest = current;
        } else if (secondSmallest != null
                && current > smallest
                && current < secondSmallest) {
            secondSmallest = current;
        }
    }

    return secondSmallest;
}
```

**Note:** This implementation returns `null` for a `null` or empty input array and when no second-smallest distinct value exists. If an interviewer specifies a different input contract, follow that contract instead.

## 11. Why Does This Code Work?

The algorithm examines every element and updates the tracked values only when the current element improves the result.

- A new smallest value pushes the old smallest into second place.
- A larger value can initialize the second-smallest value.
- A value between the two tracked values improves the second-smallest value.
- Strict comparisons prevent duplicates from being treated as distinct values.

After the loop, `secondSmallest` holds the required answer if one exists.

## 12. Complexity

### Time Complexity: O(n)

The algorithm traverses the array once. Each element requires only a constant number of comparisons and assignments.

### Auxiliary Space Complexity: O(1)

Only a fixed number of variables are used, regardless of array size.

## 13. Edge Cases

### All elements are equal

```text
Input:  [5, 5, 5]
Output: null
```

No second-smallest distinct value exists.

### Duplicate smallest values

```text
Input:  [4, 1, 1, 2]
Output: 2
```

The duplicate `1` values count as one distinct value.

### Negative values

```text
Input:  [-5, -2, -9]
Output: -5
```

The algorithm works with negative values because it initializes from the array rather than assuming zero is a valid starting point.

### Only two distinct values

```text
Input:  [5, 2, 2, 5]
Output: 5
```

The second-smallest distinct value is `5`.

### Empty array

```text
Input: []
Output: null
```

The method checks for an empty array before accessing `arr[0]`.

### New smallest value appears later

```text
Input: [8, 3, 5, 1]
Output: 3
```

When `1` arrives, the previous smallest (`3`) becomes the second smallest.

## 14. Common Mistakes

- Treating duplicate values as distinct elements.
- Using `<=` or `>=` when strict comparisons are required.
- Forgetting to move the old smallest into `secondSmallest` when a new smallest appears.
- Initializing `smallest` to zero, which can fail for arrays containing only positive or only negative values, depending on the problem.
- Returning primitive `int` when the result may be `null`.
- Accessing `arr[0]` without checking whether the array is empty.
- Forgetting that negative numbers closer to zero are greater than more negative numbers.

## 15. My Learning Progression

I reused the two-variable approach from Day 3 — Find Second Largest Distinct Element.

I identified three conditions:

1. If the current element is smaller than the smallest, update both variables.
2. If the second smallest does not exist and the current element is greater than the smallest, initialize it.
3. If the current element lies strictly between the smallest and second smallest, replace the second-smallest value.

While tracing examples, I learned how duplicates are ignored automatically by strict comparisons.

I also corrected my understanding of negative numbers. For example, `-9 < -5`, so `-9` becomes the new smallest.

Finally, I learned the difference between Java's `int` and `Integer`. Because the result may not exist, `Integer` lets the method return `null`, whereas primitive `int` cannot hold `null`.

## 16. When Should I Use This Pattern?

Use this approach when you need the two smallest distinct values from an unsorted collection and can process elements one at a time.

The same idea can be adapted to maintain the two largest distinct values or other fixed numbers of ranked values.

## 17. Similar Problems

- Find Maximum
- Find Minimum
- Find Second Largest Distinct Element
- Find Third Smallest Element
- Find the Kth Smallest Element
- Find the Two Largest Distinct Elements

Some of these problems require different approaches depending on the value of `k` and the constraints.

## 18. Memory Trigger

**Second smallest → maintain two values → new minimum pushes the old minimum into second place → strict comparisons handle duplicates.**

## 19. Revision

**What are the two state variables?**

`smallest` and `secondSmallest`.

**Why do we move the old smallest into second place?**

Because when a new smallest appears, the previous smallest becomes the next-smallest distinct value.

**How are duplicates handled?**

Strict comparisons prevent equal values from being counted as distinct.

**Why use `Integer` instead of `int` for the result?**

Because `Integer` can represent `null` when no second-smallest distinct value exists.

**Time complexity?**

O(n).

**Auxiliary space complexity?**

O(1).

**Core idea:**

Maintain the two smallest distinct values while traversing the array once.

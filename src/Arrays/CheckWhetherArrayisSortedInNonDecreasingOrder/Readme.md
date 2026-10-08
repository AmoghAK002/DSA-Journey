# Check Whether Array Is Sorted in Non-Decreasing Order

## 1. Problem

Given an integer array, determine whether the array is sorted in **non-decreasing order**.

Non-decreasing means:

```text
arr[i - 1] <= arr[i]
```

for every adjacent pair.

Examples:

```text
[1, 2, 3, 4, 5] → true
[1, 2, 2, 4, 5] → true
[1, 3, 2, 4, 5] → false
```

---

## 2. My Initial Approach

My initial thought was to sort the array and then compare it with the original arrangement to determine whether it was already sorted.

---

## 3. Why Initial Approach Was Not Optimal

Sorting takes:

```text
O(n log n)
```

But sorting is unnecessary.

To determine whether an array is already sorted, we only need to check whether every adjacent pair is in the correct order.

Therefore, we can solve the problem in:

```text
O(n)
```

---

## 4. Key Insight

For a non-decreasing array, every adjacent pair must satisfy:

```text
previous <= current
```

Therefore, instead of trying to prove that the entire array is sorted, I can look for the **one thing that proves it is not sorted**:

```text
previous > current
```

The moment such a pair is found, the array cannot be non-decreasing.

Example:

```text
[1, 2, 3, 2, 5]
```

When we reach:

```text
3 > 2
```

we immediately know the array is not sorted.

---

## 5. Pattern Used

**Linear Scan / Single Pass — Search for a Violation**

We scan the array from left to right and compare adjacent elements.

Instead of looking for evidence that the array is sorted, we look for evidence that it is **not** sorted.

---

## 6. Why This Pattern Works

A non-decreasing array must satisfy this property everywhere:

```text
arr[0] <= arr[1]
arr[1] <= arr[2]
arr[2] <= arr[3]
...
```

If even **one** comparison violates the rule:

```text
arr[i - 1] > arr[i]
```

the entire array fails the requirement.

Therefore, once a violation is found, we can immediately return `false`.

If the entire scan finishes without finding a violation, then every adjacent pair satisfied the required relationship, so the array is sorted.

---

## 7. How Did I Recognize the Pattern?

The key clue was that sorted order is determined by the relationship between **neighboring elements**.

I realized that I didn't need to compare every element with every other element.

I only needed to check:

```text
previous element <= current element
```

for every adjacent pair.

---

## 8. Invariant

> **After checking the elements up to the current position, no violation of non-decreasing order has been found among the adjacent pairs checked so far.**

In other words:

```text
arr[i - 1] > arr[i]
```

has never been true for any pair already examined.

---

## 9. Algorithm

1. Start the loop at index `1`.
2. Compare the current element with the previous element.
3. If:

```java
arr[i - 1] > arr[i]
```

return `false` immediately.
4. Continue checking the remaining adjacent pairs.
5. If the loop finishes without finding a violation, return `true`.

---

## 10. Why Does This Code Work?

```java
public static boolean isSorted(int[] arr) {

    for (int i = 1; i < arr.length; i++) {

        if (arr[i - 1] > arr[i]) {
            return false;
        }
    }

    return true;
}
```

### Why start at `i = 1`?

We access:

```java
arr[i - 1]
```

If `i` started at `0`, we would attempt:

```java
arr[-1]
```

which is an invalid array index.

Therefore, the first element that can have a previous element is index `1`.

### Why `i < arr.length`?

The final comparison must also be checked.

For:

```text
[1, 2, 3, 4, 5]
```

the final comparison is:

```text
4 <= 5
```

which occurs when:

```text
i = 4
```

Since:

```text
4 < 5
```

the loop must allow `i = arr.length - 1`.

### Why return `false` immediately?

If:

```text
previous > current
```

we already have proof that the array is not non-decreasing.

Checking the remaining elements cannot change that fact.

### Why return `true` after the loop?

Because every adjacent pair was checked and no violation was found.

Therefore, the entire array satisfies the non-decreasing condition.

---

## 11. Code

```java
package Arrays.CheckWhetherArrayisSortedInNonDecreasingOrder;

public class ArraySortedInNonDecreasingOrder {

    public static boolean isSorted(int[] arr) {

        for (int i = 1; i < arr.length; i++) {

            if (arr[i - 1] > arr[i]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 45, 5};

        System.out.println(isSorted(arr));
    }
}
```

Output:

```text
false
```

---

## 12. Complexity

### Time Complexity

```text
O(n)
```

In the worst case, every element must be checked.

The algorithm can stop earlier if it finds a violation.

For example:

```text
[5, 1, 2, 3, 4]
```

The violation is found immediately.

### Space Complexity

```text
O(1)
```

Only a constant amount of extra space is used.

No additional array or data structure is required.

---

## 13. Edge Cases

### Already sorted

```text
[1, 2, 3, 4, 5] → true
```

### Equal elements

```text
[5, 5, 5, 5] → true
```

Equality is allowed because the order is **non-decreasing**.

### One element

```text
[5] → true
```

There is no adjacent pair that can violate the ordering.

### Empty array

```text
[] → true
```

The loop executes zero times.

Whether an empty array should be considered sorted is something to clarify if the problem's constraints don't specify it.

### Violation near the beginning

```text
[5, 1, 2, 3, 4] → false
```

The algorithm exits immediately.

### Violation at the end

```text
[1, 2, 3, 4, 2] → false
```

This case is important because it demonstrates why the loop must use:

```java
i < arr.length
```

rather than:

```java
i < arr.length - 1
```

---

## 14. Common Mistakes

### Mistake 1: Checking the wrong condition

I initially thought in terms of:

```text
previous < current
```

But equality is allowed in non-decreasing order.

The correct valid relationship is:

```text
previous <= current
```

Therefore, the violation is:

```text
previous > current
```

### Mistake 2: Using `arr.length - 1`

I initially wrote:

```java
i < arr.length - 1
```

which skips the final adjacent pair.

Correct:

```java
i < arr.length
```

### Mistake 3: Confusing `break` and `return`

`break` only exits the loop.

It does not automatically tell the method that the answer is `false`.

Using:

```java
return false;
```

both exits the loop and returns the final answer.

### Mistake 4: Printing the wrong result

I initially had a print statement that always printed:

```text
Array is not Sorted
```

regardless of what happened in the loop.

The result needs to depend on whether a violation was found.

### Mistake 5: Continuing after proof

Once:

```text
previous > current
```

is found, the array is definitely not sorted.

There is no reason to keep scanning.

---

## 15. My Learning Progression

I initially thought about sorting the array, which would work but would take `O(n log n)` time.

I then realized that sorting was unnecessary because I only needed to check the relationship between adjacent elements.

My first implementation had an off-by-one error because I used:

```java
i < arr.length - 1
```

I also initially used `break` and had a print statement that did not correctly represent the result.

Through testing, I understood that the important idea is to search for a **violation** of the required property.

The final solution uses early return:

```java
if (arr[i - 1] > arr[i]) {
    return false;
}
```

and returns `true` only if the entire array is scanned without finding a violation.

---

## 16. When Should I Use This Pattern?

Use the **"scan for a violation"** pattern when a problem gives you a property that must hold throughout a sequence.

Examples:

- Check if an array is non-decreasing.
- Check if an array is non-increasing.
- Check whether a sequence follows a required ordering.
- Validate whether adjacent elements satisfy a condition.
- Detect whether a sequence violates a given rule.

The general thought process is:

> **What condition would prove that the required property is false?**

Then scan until that violation is found.

---

## 17. Similar Problems

- Check whether an array is non-increasing.
- Check whether an array is strictly increasing.
- Check whether an array is strictly decreasing.
- Check whether an array is sorted.
- Validate whether adjacent elements satisfy a given condition.

---

## 18. Memory Trigger

> **"To check if an array is sorted, look for the violation: previous > current."**

---

## 19. Revision

Before moving on, I should be able to answer:

1. Why do we start at index `1`?
2. Why is `i < arr.length` required?
3. Why does `previous > current` prove the array is not sorted?
4. Why is `[5, 5, 5]` sorted?
5. What happens with one element?
6. Why can we return `false` immediately?
7. What does the invariant mean?
8. What is the time complexity?
9. What is the space complexity?
10. Can I write the solution without looking at the code?
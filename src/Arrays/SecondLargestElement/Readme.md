# Second Largest Distinct Element

## 1. Problem

Given an integer array, find the **second largest distinct element**.

Example:

```text
Input:  [25, 85, 45, 10, 18, 45]
Output: 45
```

If all elements are equal, there is no second-largest distinct element.

Example:

```text
Input:  [10, 10, 10]
Output: null
```

---

## 2. My Initial Approach

My first approach was to sort the array.

After sorting, I could start from the largest element and move backward until I find an element different from the largest. That element would be the second largest distinct value.

For example:

```text
[10, 25, 45, 45, 85]

largest = 85
move backward
45 != 85
therefore second largest = 45
```

---

## 3. Why Initial Approach Was Not Optimal

Sorting takes:

```text
O(n log n)
```

But I only need the largest and second-largest values.

There is no need to completely reorder the array.

I can find both values while scanning the array once.

Therefore, a single-pass solution can achieve:

```text
O(n)
```

---

## 4. Key Insight

Instead of sorting the entire array, maintain two pieces of state while scanning:

```text
largest
secondLargest
```

For every new element (`current`), decide whether it:

1. becomes the new largest,
2. becomes the new second largest, or
3. should be ignored.

The important idea is:

> I don't need to remember the entire array's ordering. I only need to maintain the top two distinct values seen so far.

---

## 5. Pattern Used

**Single Pass / Linear Scan with Maintained State**

The algorithm processes every element once while maintaining the two most important values seen so far.

---

## 6. Why This Pattern Works

At any point during the scan, only two values matter for this problem:

```text
largest
secondLargest
```

If a new element is larger than `largest`, the old `largest` becomes `secondLargest`.

If it lies between `largest` and `secondLargest`, it becomes the new `secondLargest`.

Anything smaller than `secondLargest`, or equal to `largest`, can be ignored.

Therefore, we don't need to sort or store additional elements.

---

## 7. How Did I Recognize the Pattern?

The problem asks for the top two values rather than the entire sorted array.

I realized that since I already know how to maintain a maximum during a linear scan, I can extend that idea by maintaining one additional value:

```text
largest
secondLargest
```

The key clue was that I only needed a small amount of information about the elements processed so far.

---

## 8. Invariant

After processing each element:

> `largest` represents the largest distinct value seen so far, and `secondLargest` represents the second-largest distinct value seen so far, if one exists.

Example:

```text
Processed: [25, 85, 45]

largest = 85
secondLargest = 45
```

This relationship should remain true throughout the scan.

---

## 9. Algorithm

1. Initialize `largest` with the first array element.
2. Initialize `secondLargest` as `null` because a second distinct value has not been found yet.
3. Start scanning from index `1`.
4. Store the current element in `current`.
5. If `current > largest`:
    - Move the old `largest` into `secondLargest`.
    - Make `current` the new `largest`.
6. Otherwise, if `secondLargest` does not exist yet and `current < largest`:
    - Set `current` as `secondLargest`.
7. Otherwise, if `current` is smaller than `largest` but greater than `secondLargest`:
    - Update `secondLargest`.
8. If `current == largest`, ignore it because the problem requires distinct values.
9. After the scan, `secondLargest` contains the answer, or `null` if no second distinct value exists.

---

## 10. Why Does This Code Work?

### Case 1: `current > largest`

Example:

```text
largest = 85
secondLargest = 45
current = 100
```

The old largest value (`85`) cannot simply be discarded because it becomes the new second largest.

Therefore:

```java
secondLargest = largest;
largest = current;
```

Result:

```text
largest = 100
secondLargest = 85
```

The order matters. If `largest` were updated first, the old value `85` would be lost.

---

### Case 2: `secondLargest == null && current < largest`

This means we have not found a second distinct value yet.

Example:

```text
largest = 85
secondLargest = null
current = 45
```

Since:

```text
45 < 85
```

`45` becomes our first candidate for second largest.

```java
secondLargest = current;
```

Using `null` represents:

> "A second distinct value has not been found yet."

---

### Case 3: `current < largest && current > secondLargest`

Example:

```text
largest = 85
secondLargest = 45
current = 60
```

Since:

```text
60 < 85
60 > 45
```

`60` belongs between the current largest and second largest.

Therefore:

```java
secondLargest = current;
```

Result:

```text
largest = 85
secondLargest = 60
```

---

### Case 4: Duplicate of the largest

Example:

```text
largest = 85
secondLargest = 45
current = 85
```

`current < largest` is false:

```text
85 < 85 → false
```

Therefore the duplicate is ignored.

This is necessary because we want the **second largest distinct** element.

---

## 11. Code

```java
package Arrays.SecondLargestElement;

public class SecondLargest {

    public static void main(String[] args) {

        int[] arr = {25, 85, 45, 10, 18, 45};

        int largest = arr[0];
        Integer secondLargest = null;

        for (int i = 1; i < arr.length; i++) {

            int current = arr[i];

            if (current > largest) {
                secondLargest = largest;
                largest = current;

            } else if (secondLargest == null && current < largest) {
                secondLargest = current;

            } else if (current < largest && current > secondLargest) {
                secondLargest = current;
            }
        }

        System.out.println(largest);
        System.out.println(secondLargest);
    }
}
```

---

## 12. Complexity

### Time Complexity

```text
O(n)
```

The array is traversed once.

For `n` elements, the algorithm performs a constant amount of work for each element.

### Space Complexity

```text
O(1)
```

Only a fixed number of variables are used:

```text
largest
secondLargest
current
i
```

No additional array or data structure is created.

---

## 13. Edge Cases

Important cases considered:

### Normal input

```text
[25, 85, 45, 10, 18, 45]

→ 45
```

### Two elements

```text
[10, 20]

→ 10
```

### Duplicate largest

```text
[85, 85, 45, 20]

→ 45
```

### All elements equal

```text
[10, 10, 10]

→ null
```

There is no second-largest **distinct** element.

### Negative numbers

```text
[-10, -20, -5, -30]

largest = -5
secondLargest = -10
```

This exposed why initializing variables with `0` or another arbitrary value is unsafe.

### Duplicate second-largest values

```text
[25, 85, 45, 45, 10]

→ 45
```

Duplicates do not affect the answer because the value only needs to be distinct.

---

## 14. Common Mistakes

### Mistake 1: Initializing with `0`

```java
int secondLargest = 0;
```

This fails for arrays containing only negative numbers.

For example:

```text
[-10, -20, -5]
```

`0` is not even present in the array.

---

### Mistake 2: Losing the old largest

Incorrect:

```java
largest = current;
secondLargest = largest;
```

After the first line, the old largest has already been lost.

Correct:

```java
secondLargest = largest;
largest = current;
```

---

### Mistake 3: Assuming every value smaller than largest is second largest

For:

```text
largest = 85
secondLargest = 45
current = 10
```

`10` is smaller than `85`, but it is not the second largest.

We need:

```text
secondLargest < current < largest
```

---

### Mistake 4: Forgetting distinctness

If:

```text
largest = 85
current = 85
```

the duplicate `85` must not become the second largest.

---

### Mistake 5: Using `int` with `null`

This is invalid:

```java
int secondLargest = null;
```

`int` is a primitive and cannot hold `null`.

`Integer` is a wrapper object and can hold `null`:

```java
Integer secondLargest = null;
```

This introduced me to the Java concepts of **wrapper classes, null, autoboxing, and unboxing**, although those are implementation details rather than the core DSA pattern.

---

## 15. My Learning Progression

I initially struggled with this problem.

I first thought about sorting the array, which would work but would take `O(n log n)` time.

I then understood that I could maintain two values:

```text
largest
secondLargest
```

I initially struggled with how to initialize `secondLargest` and encountered problems involving `null`.

I also made mistakes such as:

- assigning `0` as the initial second largest,
- incorrectly updating `secondLargest`,
- losing the old largest,
- misunderstanding how `else` should work,
- and initially struggling with the state transitions.

Instead of immediately looking at the solution, I worked through examples and gradually derived the conditions myself.

The most important thing I learned was not the final code, but how to maintain a small amount of state that summarizes the elements processed so far.

---

## 16. When Should I Use This Pattern?

Use a **single-pass maintained-state approach** when:

- the answer depends on a small number of values,
- the entire array does not need to be sorted,
- each new element can update the current state,
- and the previous state contains everything needed to process the next element.

Examples include finding:

- maximum
- minimum
- second maximum
- second minimum
- top two values
- running statistics
- certain best/worst candidates

---

## 17. Similar Problems

- Find Maximum Element
- Find Minimum Element
- Find Second Smallest Distinct Element
- Find the Top Two Elements
- Find the Largest and Smallest Element in One Pass
- Find the K-th Largest Element — requires a more advanced pattern such as a heap or quickselect

---

## 18. Memory Trigger

> **"Don't sort when I only need the top two — maintain the two best distinct values while scanning."**

---

## 19. Revision

### Questions to answer during revision

1. Why is sorting unnecessary?
2. What exactly does the invariant guarantee?
3. Why can't I initialize `secondLargest` to `0`?
4. Why must I execute:

```java
secondLargest = largest;
largest = current;
"Remeber the aconcept the autoboxing and unboxing was used during implementation of the code"
```

in that order?
5. What happens when `current == largest`?
6. Why can `secondLargest` initially be `null`?
7. What happens when all elements are equal?
8. What is the time complexity?
9. What is the auxiliary space complexity?
10. Can I explain the algorithm without looking at the code?
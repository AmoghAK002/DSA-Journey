# Find Minimum

## 1. Problem

Given an integer array, find and return the smallest element in the array.

### Example

```text
Input:
[3, 7, 2, 9, 4]

Output:
2
```

### Assumption

The array contains at least one element.

---

## 2. My Initial Approach

My first approach was to sort the entire array.

After sorting:

```text
[3, 7, 2, 9, 4]
        ↓
[2, 3, 4, 7, 9]
```

The minimum element would then be the first element.

```java
Arrays.sort(a);
System.out.println(a[0]);
```

This solution is correct, but it performs more work than necessary.

---

## 3. Why My Initial Approach Was Not Optimal

The problem only asks for **one piece of information**:

> What is the smallest element?

Sorting gives us much more information than we need because it determines the ordering of **every element**.

We don't need to know the complete ordering.

We only need to know the smallest element encountered so far.

Therefore, sorting is unnecessary.

### Complexity of Initial Approach

```text
Time: O(n log n)
Space: O(1) auxiliary space
```

---

## 4. Key Insight 🧠

While traversing the array, I can maintain the smallest element I've seen so far.

For every new element:

```text
If current element < current minimum
        ↓
Update current minimum
```

Otherwise:

```text
Keep current minimum unchanged
```

Example:

```text
Array:       [3, 7, 2, 9, 4]

Start:
currentMin = 3

See 7:
7 > 3
currentMin = 3

See 2:
2 < 3
currentMin = 2

See 9:
9 > 2
currentMin = 2

See 4:
4 > 2
currentMin = 2
```

Final answer:

```text
2
```

---

## 5. Pattern Used

### Pattern: Linear Scan / Single Pass

A linear scan means traversing the input once while maintaining some useful information about the elements processed so far.

Here, the information being maintained is:

```text
current minimum
```

---

## 6. Why This Pattern Works

We don't need to compare every element with every other element.

Each element only needs to answer one question:

> "Am I smaller than the smallest element I've seen so far?"

If yes, it becomes the new minimum.

If no, nothing changes.

Therefore, one traversal is sufficient.

---

## 7. How Did I Recognize the Pattern?

The problem asks for an aggregate property of the entire array:

```text
minimum
```

There is no requirement to:

- preserve ordering
- find pairs
- search repeatedly
- modify the array
- examine relationships between multiple elements

Therefore, I can scan the array once and maintain the answer.

### Recognition Trigger

> **If I only need to find/track something while examining every element once, think about a linear scan.**

---

## 8. Invariant

The key invariant is:

> **After processing each element, `currentMin` contains the smallest element among all elements processed so far.**

For example:

```text
After processing [3]:

currentMin = 3

After processing [3, 7]:

currentMin = 3

After processing [3, 7, 2]:

currentMin = 2

After processing [3, 7, 2, 9]:

currentMin = 2
```

This remains true throughout the entire traversal.

At the end, every element has been processed.

Therefore:

```text
currentMin = minimum element of the entire array
```

---

## 9. Algorithm

```text
1. Take the first element as currentMin.
2. Start traversing from the second element.
3. Compare each element with currentMin.
4. If the current element is smaller, update currentMin.
5. Continue until the array ends.
6. Return currentMin.
```

---

## 10. Code

```java
public static int findMinimum(int[] a) {

    int currentMin = a[0];

    for (int i = 1; i < a.length; i++) {

        if (a[i] < currentMin) {
            currentMin = a[i];
        }
    }

    return currentMin;
}
```

---

## 11. Why Does This Code Work?

Initially:

```text
currentMin = a[0]
```

So the first element is our best candidate.

Then we examine every remaining element.

If:

```text
a[i] < currentMin
```

then the old minimum cannot be the minimum anymore, so we replace it.

If:

```text
a[i] >= currentMin
```

then the current minimum is still at most this element, so we don't change it.

After the final element is processed, `currentMin` must be the smallest element in the entire array.

---

## 12. Complexity

### Time

```text
O(n)
```

Each element is examined exactly once.

### Auxiliary Space

```text
O(1)
```

Only one additional variable, `currentMin`, is used.

---

## 13. Edge Cases

### One element

```text
[5]

→ 5
```

Works.

### All negative numbers

```text
[-5, -2, -10, -1]

→ -10
```

Works because we initialize:

```java
currentMin = a[0];
```

rather than:

```java
currentMin = 0;
```

### Duplicate minimum

```text
[5, 2, 2, 9]

→ 2
```

Works.

### Empty array

Our current implementation does not support an empty array because:

```java
a[0]
```

doesn't exist.

Therefore, our problem assumption is:

> The input contains at least one element.

If an interview problem allows an empty array, we must decide how that case should be handled based on the requirements.

---

## 14. Common Mistake

### Mistake 1: Initializing minimum to 0

```java
int currentMin = 0;
```

This fails for:

```text
[5, 2, 7]
```

because the answer is `2`, not `0`.

It also fails for:

```text
[-5, -2, -10]
```

because the answer is `-10`, not `0`.

### Better approach

Use:

```java
int currentMin = a[0];
```

or, when appropriate:

```java
int currentMin = Integer.MAX_VALUE;
```

---

## 15. My Learning Progression

### Version 1

I initially thought:

```text
Sort the array
↓
Take the first element
```

### Version 2

I realized:

```text
I don't need the complete ordering.
I only need the minimum.
```

### Version 3

Therefore:

```text
Scan once
↓
Maintain current minimum
↓
O(n)
```

This is the important progression to remember.

---

## 16. When Should I Use This Pattern?

Think about a linear scan when:

- You need the maximum/minimum.
- You need to count something.
- You need to find whether something exists.
- You need to track the best value seen so far.
- You need to maintain a running state.
- Each element can be processed independently.
- You don't need relationships between distant elements.

Typical structure:

```text
Initialize state
        ↓
Traverse once
        ↓
Update state
        ↓
Return answer
```

For minimum:

```text
Initialize currentMin
        ↓
Compare each element
        ↓
Update if smaller
        ↓
Return currentMin
```

---

## 17. Similar Problems

Problems that build on this idea include:

- Find Maximum
- Find Second Smallest
- Count Positive/Negative Numbers
- Find Maximum Difference
- Find Minimum Difference
- Running Minimum
- Minimum Element in a Range

These problems may eventually introduce other patterns on top of the basic linear scan.

---

## 18. Memory Trigger 🧠

When I see:

> "Find the minimum/smallest/best value while going through an array"

Think:

```text
Can I scan once?

What information should I maintain?

What should always remain true?
```

For this problem:

```text
CURRENT MIN
     ↓
Compare
     ↓
Update if necessary
     ↓
Continue
```

### One-line memory trigger

> **"Don't sort if I only need the minimum — scan once and maintain the best candidate."**

---

## 19. Revision

### First Revision

Try solving from the problem statement without looking at the code.

- [ ] Can I derive the linear-scan solution?
- [ ] Can I explain why sorting is unnecessary?
- [ ] Can I state the invariant?
- [ ] Can I write the code from memory?

### Second Revision

- [ ] Solve without looking at notes.
- [ ] Explain the pattern aloud.
- [ ] Solve the maximum variation.

### Third Revision

- [ ] Solve a new problem that uses the same underlying idea.
- [ ] Identify the pattern before coding.

---

## Final Takeaway

The important thing I should remember is **not**:

> "Find Minimum uses a for-loop."

It is:

> **"I can maintain the best answer seen so far while traversing the input once."**

For minimum specifically:

> **"While scanning, keep the smallest value seen so far."**

The reusable pattern is:

```text
Initialize state
        ↓
Scan once
        ↓
Compare
        ↓
Update state if necessary
        ↓
Return answer
```
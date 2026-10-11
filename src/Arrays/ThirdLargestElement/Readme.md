# Day 7: Third Largest Distinct Element

## 1. Problem

Given an integer array, find the **third-largest distinct element**.

- Duplicate values must not count as separate ranks.
- Return `null` if the array contains fewer than three distinct values.
- Handle `null` and empty arrays safely.

**Example**

```text
Input:  [1, 84, -1, 0, 78]
Output: 1

Input:  [5, 5, 5]
Output: null

Input:  [10, 8, 12]
Output: 8
```

## 2. My Initial Approach

Initially, I thought of sorting the array and then finding the third-largest distinct element by skipping duplicate values.

Sorting would make the values easier to compare, but it would require more work than necessary for this problem.

## 3. Why My Initial Approach Was Not Optimal

Sorting typically takes **O(n log n)** time for comparison-based sorting algorithms.

However, I only need to track the three largest distinct values. I don't need the entire array to be sorted.

A single traversal can solve the problem in **O(n)** time using **O(1)** auxiliary space.

## 4. Key Insight

I had already solved the Second Largest Distinct Element problem.

I extended that idea by maintaining three variables:

- `largest`: the largest distinct value found so far.
- `secondLargest`: the second-largest distinct value found so far.
- `thirdLargest`: the third-largest distinct value found so far.

When a new value is encountered, I compare it with the tracked values and update the necessary variables.

I also use `Integer` for the second- and third-largest values so that `null` can represent a rank that has not been found yet.

## 5. Pattern Used

**Pattern: Linear Scan / Single Pass with Running State**

I recognized this pattern because I didn't need to store every element or sort the array. I only needed a fixed amount of information while traversing the input.

The same idea can be extended to maintain the top K distinct values, provided the update logic correctly preserves their order and distinctness.

## 6. Why This Pattern Works

A single traversal examines every element and updates a fixed number of variables.

The algorithm doesn't need to revisit earlier elements or create another array. Once an element has been processed, the variables preserve the relevant ranking information.

The key requirement is that every update must preserve the order of the three largest **distinct** values.

## 7. Invariant

After processing each element:

- `largest` represents the largest distinct value encountered so far.
- `secondLargest` represents the second-largest distinct value encountered so far, or `null` if fewer than two distinct values have been found.
- `thirdLargest` represents the third-largest distinct value encountered so far, or `null` if fewer than three distinct values have been found.

Maintaining this invariant ensures that the final value of `thirdLargest` is the required answer.

## 8. Algorithm

1. Handle a `null` or empty array.
2. Initialize the tracking variables.
3. Traverse the remaining elements.
4. Compare each element with the tracked values.
5. Update the variables while preserving their ranking and distinctness.
6. Return `thirdLargest`.

## 9. Code

```java
package Arrays.ThirdLargestElement;

public class ThirdLargest {

    public static Integer thirdLargest(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        int largest = arr[0];
        Integer secondLargest = null;
        Integer thirdLargest = null;

        for (int i = 1; i < arr.length; i++) {
            int current = arr[i];

            if (current > largest) {
                thirdLargest = secondLargest;
                secondLargest = largest;
                largest = current;
            } else if (current < largest) {
                if (secondLargest == null || current > secondLargest) {
                    thirdLargest = secondLargest;
                    secondLargest = current;
                } else if (current < secondLargest
                        && (thirdLargest == null || current > thirdLargest)) {
                    thirdLargest = current;
                }
            }
        }

        return thirdLargest;
    }

    public static void main(String[] args) {
        int[] arr = {1, 84, -1, 0, 78};
        System.out.println(thirdLargest(arr));
    }
}
```

## 10. Why Does This Code Work?

The code maintains the three largest distinct values while scanning the array.

Each new element is evaluated against the existing ranking. When a larger value is found, the tracked values are shifted accordingly. Otherwise, the code checks whether the current value qualifies for a lower rank.

Strict comparisons prevent equal values from occupying multiple ranks.

The null checks allow the algorithm to handle cases where the second- or third-largest distinct value has not yet been found.

## 11. Complexity Analysis

**Time Complexity: O(n)**

The array is traversed once. Each element requires a fixed number of comparisons and updates.

**Auxiliary Space Complexity: O(1)**

Only a fixed number of variables are maintained, regardless of the input size. No additional array or collection is created.

## 12. Edge Cases

| Input | Expected output |
|---|---:|
| `null` | `null` |
| `[]` | `null` |
| `[7]` | `null` |
| `[4, 4, 2, 2]` | `null` |
| `[5, 5, 5]` | `null` |
| `[10, 8, 12]` | `8` |
| `[3, 2, 1, 0]` | `1` |
| `[-10, -30, -20, -40]` | `-30` |
| `[5, 5, 4, 4, 3]` | `3` |
| `[1, 84, -1, 0, 78]` | `1` |

## 13. Common Mistakes I Learned From

- **Counting duplicates as separate ranks:** Three equal values do not provide a third-largest distinct value.
- **Incorrect initialization:** Initializing the three ranks from arbitrary array positions can produce incorrect rankings.
- **Incorrect update order:** When a new second-largest value is found, the previous second-largest value may need to become the third-largest.
- **Combining null checks carelessly:** Java's `&&` and `||` short-circuit evaluation must be understood to avoid unboxing `null`.
- **Using independent `if` statements carelessly:** Later checks can evaluate variables that earlier blocks have already modified.
- **Assuming tests prove correctness:** Passing examples is useful evidence, but the invariant and update logic must also be verified.
- **Ignoring invalid input:** Accessing `arr[0]` without checking whether the array is `null` or empty can cause an exception.

## 14. My Learning Progression

I initially considered sorting the array. I then reused the running-state idea from the Second Largest Distinct Element problem.

During implementation, I learned to:

1. Track multiple ranks using a fixed number of variables.
2. Handle duplicate values using strict comparisons.
3. Use `Integer` and `null` to represent a missing rank.
4. Understand short-circuit evaluation for null safety.
5. Debug conditions by tracing variable values.
6. Improve readability by separating different cases.
7. Test different input orders, duplicates, negative values, and insufficient distinct values.

## 15. When Should I Use This Pattern?

Use a linear scan with running state when:

- The problem asks for the largest, smallest, or top few values.
- The required information can be represented using a fixed number of variables.
- Sorting the entire array is unnecessary.
- The result can be updated incrementally as each element is processed.

## 16. Similar Problems

- Find Maximum Element
- Find Minimum Element
- Find Second Largest Distinct Element
- Find Second Smallest Distinct Element
- Find Kth Largest Element

## 17. Memory Trigger

**Question:** Do I need to sort the array, or can I maintain the required ranking while scanning once?

**Trigger:** If only a few ranked values are required, consider a linear scan with running state.

## 18. Revision

- [ ] Explain why sorting is unnecessary.
- [ ] Explain the invariant for all three variables.
- [ ] Explain how duplicates are excluded.
- [ ] Explain why `Integer` and `null` are used.
- [ ] Explain short-circuit evaluation with `&&` and `||`.
- [ ] Derive the O(n) time and O(1) auxiliary space complexities.
- [ ] Reimplement the solution without looking at the code.

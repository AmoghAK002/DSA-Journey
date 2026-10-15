# Problem 11: Reverse String

## Problem

Given an array of characters `char[] s`, reverse the array in place.

The original array must be modified without creating another array.

**Example**

Input: `['h', 'e', 'l', 'l', 'o']`

Output: `['o', 'l', 'l', 'e', 'h']`

## My Initial Approach

Initially, I thought about using a `for` loop, but I got confused about how to reverse the array. I knew that two pointers could be used, but I needed to understand how they should move and when the swapping should stop.

## Why My Initial Approach Was Not Clear

I knew two pointers were involved, but I had not established the correct stopping condition. Initially, I considered checking whether the right pointer was greater than or equal to zero. However, that only considered the right pointer and did not account for the relationship between both pointers.

## Key Insight

**Swap the outermost characters, then move inward.**

Reversing an array means that the first character must become the last, the second character must become the second-last, and so on.

For example:

`[H, E, L, L, O]`

- Swap `H` and `O`: `[O, E, L, L, H]`
- Move both pointers inward.
- Swap `E` and `L`: `[O, L, L, E, H]`
- The pointers meet at the middle character, which does not need to move.

By repeating this process, each pair of characters reaches its correct reversed position.

## Pattern Used

**Two Pointers**

Two pointers start at opposite ends of the array and move toward each other.

- `i`: Left pointer, starting at index `0`.
- `j`: Right pointer, starting at index `s.length - 1`.

## Why This Pattern Works

Every swap places two characters into their correct final positions: the left character goes to the right side, and the right character goes to the left side.

After each swap, both pointers move inward. Once the pointers meet or cross, every required pair has been processed.

## Invariant

After each swap, all character pairs outside the current interval have been placed in their correct reversed positions.

The portion between the two pointers is the part that remains to be processed.

## Algorithm

1. Initialize the left pointer at index `0`.
2. Initialize the right pointer at the last index.
3. Continue while the left pointer is smaller than the right pointer.
4. Swap the characters at both pointer positions using a temporary variable.
5. Move the left pointer forward and the right pointer backward.
6. Stop when the pointers meet or cross.

## Code

```java
package Arrays.ReverseString;

public class ReverseAString {

    public static void ReverseString(char[] str) {
        if (str == null) {
            return;
        }

        int i = 0;
        int j = str.length - 1;

        while (i < j) {
            char temp = str[i];
            str[i] = str[j];
            str[j] = temp;

            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        char[] str = {'h', 'e', 'l', 'l', 'o'};

        ReverseString(str);

        for (int j = 0; j < str.length; j++) {
            System.out.print(str[j] + " ");
        }
    }
}
```

## Why Does This Code Work?

The method modifies the original character array directly.

The temporary variable `temp` preserves one character while the two positions are swapped.

The condition `i < j` ensures that a swap occurs only while the pointers are on opposite sides of the array. When they meet or cross, no further swaps are necessary.

The null check prevents the method from attempting to access the length of a null array. An empty array also works because its pointers do not enter the loop.

## Complexity

- **Time Complexity: O(n)** — Each iteration performs constant-time work, and the pointers process approximately half the array. The number of iterations grows linearly with the array length.
- **Auxiliary Space Complexity: O(1)** — Only two pointers and one temporary character variable are used. No additional array is created.

## Edge Cases

| Input | Expected result |
|---|---|
| `['h', 'e', 'l', 'l', 'o']` | `['o', 'l', 'l', 'e', 'h']` |
| `['a', 'b', 'c', 'd']` | `['d', 'c', 'b', 'a']` |
| `['a']` | `['a']` |
| `['a', 'b']` | `['b', 'a']` |
| Empty array | Empty array |
| `null` | Method returns without modifying anything |

## Common Mistakes

- Using `j >= 0` without checking the left pointer.
- Continuing to swap after the pointers meet or cross.
- Forgetting to move both pointers after a swap.
- Creating a new array unnecessarily when the requirement is to reverse in place.

## My Learning Progression

Initially, I knew that two pointers were involved but was unsure about the correct loop condition.

I first considered `j >= 0`. By tracing the pointer positions, I understood that the condition needed to account for both pointers. I derived `i < j`, which stops the loop when the pointers meet or cross.

I also understood why the middle character in an odd-length array does not need to be swapped.

## When Should I Use This Pattern?

Use two pointers moving inward when a problem involves:

- Reversing an array in place.
- Comparing elements from opposite ends.
- Processing pairs symmetrically.
- Avoiding an additional array when constant auxiliary space is required.

## Similar Problems

- Problem 9: Move Zeroes
- Problem 10: Remove Element

These problems also use two pointers, although their pointer roles and objectives differ from reversing an array.

## Memory Trigger

**`i < j` → swap → `i++`, `j--`**

Start at opposite ends, swap, move inward, and stop when the pointers meet or cross.

## Revision Checklist

- [ ] Explain why swapping outer pairs reverses the array.
- [ ] Write the two-pointer implementation without looking at the code.
- [ ] Explain why `i < j` is the correct stopping condition.
- [ ] State the time and auxiliary-space complexities.
- [ ] Test odd-length, even-length, single-character, empty, and null inputs.

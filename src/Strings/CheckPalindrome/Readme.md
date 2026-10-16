# Problem 12 — Check for a Palindrome

## 1. Problem Statement

Given a string `str`, determine whether it is a palindrome.

A palindrome reads the same forward and backward.

**Examples**
- `"madam"` → `true`
- `"hello"` → `false`
- `"a"` → `true`
- `""` → `true` (chosen convention)
- `null` → `false` (chosen convention)

**Assumptions:** Case-sensitive comparison. Spaces and punctuation are treated as ordinary characters.

## 2. Classification

- **Category:** Strings
- **Topic:** String manipulation
- **Pattern:** Two Pointers
- **Difficulty:** Easy

## 3. Initial Approach

My first thought was to reverse the string and compare the reversed version with the original.

However, creating a separate reversed string is unnecessary. I realized I could compare characters directly from opposite ends, using the two-pointer idea from Problem 11.

## 4. Key Insight

A string is a palindrome if every pair of characters at corresponding positions from opposite ends matches.

For example, `"madam"`:

- Compare `m` with `m`.
- Compare `a` with `a`.
- The pointers meet at `d`.

If any pair differs, the string is not a palindrome.

## 5. Algorithm

1. Handle `null` by returning `false`.
2. Handle an empty string by returning `true`.
3. Initialize `i = 0` and `j = str.length() - 1`.
4. While `i < j`, compare `str.charAt(i)` with `str.charAt(j)`.
5. If they differ, return `false` immediately.
6. If they match, increment `i` and decrement `j`.
7. If the loop finishes without finding a mismatch, return `true`.

## 6. Java Implementation

```java
package Strings;

public class CheckPalindrome {

    public static boolean isPalindrome(String str) {
        if (str == null) return false;
        if (str.isEmpty()) return true;

        int i = 0;
        int j = str.length() - 1;

        while (i < j) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            } else {
                i++;
                j--;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String str = "a";
        System.out.println(isPalindrome(str));
    }
}
```

## 7. Why the Code Works

At each iteration, both pointers identify a pair of characters that must match for the string to be a palindrome.

- If a pair differs, returning `false` is correct because the palindrome condition has been violated.
- If a pair matches, both pointers move inward to check the next pair.
- If the loop terminates without a mismatch, every required pair has matched, so the method returns `true`.

The loop condition `i < j` ensures that the middle character does not need to be compared with itself.

## 8. Complexity Analysis

**Time complexity: O(n)**

In the worst case, approximately half the characters are compared. This is linear time, or O(n), where `n` is the string length. If the first pair differs, the method finishes in O(1) time.

**Auxiliary space complexity: O(1)**

Only two pointer variables are used. No additional string or array is created.

## 9. Edge Cases and Design Decisions

| Case | Expected result | Reason |
|---|---|---|
| `"madam"` | `true` | All corresponding pairs match |
| `"hello"` | `false` | First pair differs |
| `"a"` | `true` | Single character |
| `"ab"` | `false` | First pair differs |
| `""` | `true` | Empty-string convention |
| `null` | `false` | No string was provided |
| `"Madam"` | `false` | Comparison is case-sensitive |

The implementation explicitly checks `null` before calling `str.isEmpty()`. Reversing this order can cause a `NullPointerException`.

## 10. Common Mistakes

- Reversing the string unnecessarily.
- Using `i <= j` when `i < j` is sufficient.
- Continuing after finding a mismatch.
- Checking `str.isEmpty()` before checking `str == null`.
- Lowercasing the string when the requirement is case-sensitive.
- Assuming that one successful test proves all edge cases work.

## 11. Connection to Previous Problems

**Problem 11 — Reverse a String**

Both problems use two pointers that move inward.

- **Reverse a String:** Swap the characters at the two pointers.
- **Check for a Palindrome:** Compare the characters at the two pointers.

The pointer movement is similar, but the operation performed is different.

## 12. Memory Trigger

**“Compare the outer pair, stop on a mismatch, move inward.”**

## 13. Revision Checklist

- [ ] Explain why two pointers are appropriate.
- [ ] Derive the `i < j` loop condition.
- [ ] Explain why an early `false` return is correct.
- [ ] Explain the `null` check ordering.
- [ ] State O(n) worst-case time and O(1) auxiliary space.
- [ ] Reimplement independently without referring to this README.
- [ ] Verify all listed test cases before recording them as passed.

## 14. Testing Record

The visible conversation confirms that the implementation was run with `"a"` and produced a successful result. Other test cases were discussed, but their execution results were not confirmed here. Run the remaining cases before marking the complete test suite as passed.


## (IMP)Java Learning: `null`, `isEmpty()`, and `length()`

### 1. Why check `str == null` first?

```java
if (str == null) return false;
if (str.isEmpty()) return true;
```

In Java, `null` means the variable does not refer to a String object. Calling a method on `null` causes a `NullPointerException`.

For example:

```java
String str = null;
str.isEmpty(); // Throws NullPointerException
```

Therefore, check whether `str` is `null` before calling any methods on it.

**Important:** The order matters. Checking `str.isEmpty()` first is unsafe because Java cannot invoke that method on `null`.

### 2. Why use `isEmpty()` instead of `length() == 0`?

Previously, I could check whether a string was empty using:

```java
if (str.length() == 0) return true;
```

I learned that Java provides a more readable alternative:

```java
if (str.isEmpty()) return true;
```

Both checks determine whether the string contains zero characters. `isEmpty()` expresses the intention directly, making the code easier to read.

For a non-null string:

```java
String first = "";
System.out.println(first.isEmpty());      // true
System.out.println(first.length() == 0);  // true

String second = "hello";
System.out.println(second.isEmpty());     // false
```

Neither method should be called before checking for `null`.

### 3. What I learned

- `null` and an empty string (`""`) are different.
- Check `null` before calling String methods.
- Use `isEmpty()` for a clear, readable empty-string check.
- `isEmpty()` and `length() == 0` are equivalent for a non-null String.
- In this palindrome implementation, `null` returns `false`, while an empty string returns `true` by design.

package Q1_02_Check_Permutation

import org.junit.Assume

/**
 * Check Permutation: Given two strings, write a method to decide if one is a permutation of the
 * other.
 */

// Solution is O(a.length + b.length)
boolean isPermutation(String a, String b) {
    if (a.length() != b.length()) {
        return false
    }
    
    int[] charFreqs = new int[128]  // Assume ASCII

    for (char c : a.chars) {
        ++charFreqs[c as int]
    }
    for (char c : b.chars) {
        if (--charFreqs[c as int] < 0) {
            return false
        }
    }
    
    return true
}

println isPermutation("racecar", "carecar")
println isPermutation("foo", "foot")
println isPermutation("abcdefg", "gfedcba")
println isPermutation("abcdefg", "abc1efg")

println isPermutation("apple", "papel")
println isPermutation("carrot", "tarroc")
println isPermutation("hello", "llloh")

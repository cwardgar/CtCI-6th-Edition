package Q5_01_Insertion

/**
 Insertion: You are given two 32-bit numbers, N and M, and two bit positions, i and
 j. Write a method to insert M into N such that M starts at bit j and ends at bit i. You
 can assume that the bits j through i have enough space to fit all of M. That is, if
 M = 10011, you can assume that there are at least 5 bits between j and i. You would not, for
 example, have j = 3 and i = 2, because M could not fully fit between bit 3 and bit 2.
 EXAMPLE
 Input: N = 10000000000, M = 10011, i = 2, j = 6
 Output: N = 10001001100
             10001001100
 *
 * @author cwardgar
 * @since 2017-10-14
 */

int insert(int N, int M, int i, int j) {
    int clearMask = createClearMask(i, j)
    int Ncleared = N & clearMask
    return Ncleared | (M << i)
}

int createClearMask(int i, int j) {  // i < j
    int maskLen = j - i + 1
    int unshiftedSetMask = (1 << maskLen) - 1   // 00100000  becomes  00011111
    int shiftedSetMask = unshiftedSetMask << i  // 00011111  becomes  01111100
    return ~shiftedSetMask                      // 01111100  becomes  10000011
}

///////////////////////////////////

int N = 0b10000000000
int M = 0b10011
int i = 3
int j = 7

int ret = insert N, M, i, j
println Integer.toBinaryString(ret)

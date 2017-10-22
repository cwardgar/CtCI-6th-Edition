package Q5_04_Next_Number

/**
 Next Number: Given a positive integer, print the next smallest and the next largest number that
 have the same number of 1 bits in their binary representation.
 *
 * @author cwardgar
 * @since 2017-10-15
 */
int nextLarger(int number) {
    int leastSignificantNonTrailingZero = -1
    int onesCount = 0
    
    int num = number
    for (int i = 0; i < 32; ++i) {
        int bit = (num & 1) == 0 ? 0 : 1
        
        if (bit == 1) {
            ++onesCount
        } else if (bit == 0 && onesCount > 0) {
            leastSignificantNonTrailingZero = i
            break
        }
        
        num >>>= 1
    }
    
    assert leastSignificantNonTrailingZero != -1 : "number is positive, meaning that the sign bit is zero. " +
            "So, if all else fails, we should still find the most significant bit."
    
    num = setBit(number, leastSignificantNonTrailingZero)
    --onesCount  // We just flipped a bit from 0->1, which means we can have one fewer 1 to the right.
    
    int trailingOnes = (1 << onesCount) - 1  // A number with onesCount trailing ones.
    
    int ret = insert(num, trailingOnes, 0, leastSignificantNonTrailingZero - 1)
    if (ret < 0) {
        String paddedBinaryNumber = leftPad(Integer.toBinaryString(number), 32, '0')
        throw new IllegalArgumentException(
                "There is no 32-bit signed integer greater than $paddedBinaryNumber with the same number of ones.")
    }
    return ret
}

int nextSmaller(int number) {
    int leastSignificantNonTrailingOne = -1
    int zerosCount, onesCount
    
    int num = number
    for (int i = 0; i < 32; ++i) {
        int bit = (num & 1) == 0 ? 0 : 1
        
        if (bit == 0) {
            ++zerosCount
        } else {
            ++onesCount
            if (zerosCount > 0) {
                leastSignificantNonTrailingOne = i
                break
            }
        }
    
        num >>>= 1
    }
    
    if (leastSignificantNonTrailingOne == -1) {
        int trailingOnes = (1 << onesCount) - 1
        int leadingOnes = trailingOnes << (32 - onesCount)  // The biggest negative number with same onesCount.
        return leadingOnes
    }
    
    num = clearBit(number, leastSignificantNonTrailingOne)
    
    int trailingOnes = (1 << onesCount) - 1  // A number with onesCount trailing ones.
    trailingOnes <<= (leastSignificantNonTrailingOne - onesCount)
    
    int ret = insert(num, trailingOnes, 0, leastSignificantNonTrailingOne - 1)
    if (ret < 0) {
        String paddedBinaryNumber = leftPad(Integer.toBinaryString(number), 32, '0')
        throw new IllegalArgumentException(
                "There is no 32-bit signed integer greater than $paddedBinaryNumber with the same number of ones.")
    }
    return ret
}

def setBit(int num, int i) {
    num | (1 << i)
}

def clearBit(int num, int i) {
    num & ~(1 << i)
}

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

def numbers = 1..15

println "input: small large"
numbers.each { number ->
    String input  = leftPad(Integer.toBinaryString(number), 5, '0')
    String nextLarger = leftPad(Integer.toBinaryString(nextLarger(number)), 5, '0')
    String nextSmaller = leftPad(Integer.toBinaryString(nextSmaller(number)), 5, '0')
    println "$input: $nextLarger $nextSmaller"
}

String leftPad(String s, int len, String fill) {
    StringBuilder sb = new StringBuilder()
    for (int i = 0; i < len - s.length(); ++i) {
        sb << fill
    }
    sb << s
    sb.toString()
}

package Q5_03_Flip_Bit_to_Win

/**
 Flip Bit to Win: You have an integer and you can flip exactly one bit from a O to a 1. Write code to
 find the length of the longest sequence of 1s you could create.
 EXAMPLE
 Input: 1775  (or: 11011101111)
 Output: 8
 *
 * @author cwardgar
 * @since 2017-10-14
 */

int flipBitToWin(int num) {
    int longestRunOfOnes = 1  // This is the minimum value, which will happen if num is all 0s and we flip one bit.

    int currentRunStart = -1
    int lastRunStart = -1
    int lastRunEnd = -1
    
    int i = 0
    for (; num != 0; ++i) {
        boolean isBitSet = (num & 1) != 0
        
        if (isBitSet && currentRunStart == -1) {
            currentRunStart = i  // Begin new run.
        } else if (!isBitSet && currentRunStart != -1) {
            // End the current run.
            int currentRunEnd = i - 1
            int currentRunLen = currentRunEnd - currentRunStart + 1
            longestRunOfOnes = Math.max(longestRunOfOnes, currentRunLen)
            
            if (lastRunStart != -1 && currentRunStart - lastRunEnd == 2) {  // Runs are separated by a single 0 bit.
                int lastRunLen = lastRunEnd - lastRunStart + 1
                longestRunOfOnes = Math.max(longestRunOfOnes, currentRunLen + lastRunLen + 1)
            }
            
            lastRunStart = currentRunStart
            lastRunEnd = currentRunEnd
            currentRunStart = -1
        }
        
        num >>>= 1
    }
    
    if (currentRunStart != -1) {
        // End the current run.
        int currentRunEnd = i - 1
        int currentRunLen = currentRunEnd - currentRunStart + 1
        longestRunOfOnes = Math.max(longestRunOfOnes, currentRunLen)
    
        if (lastRunStart != -1 && currentRunStart - lastRunEnd == 2) {
            // Runs are separated by a single 0 bit.
            int lastRunLen = lastRunEnd - lastRunStart + 1
            longestRunOfOnes = Math.max(longestRunOfOnes, currentRunLen + lastRunLen + 1)
        }
    }
    
    return longestRunOfOnes
}

/////////////////////////////////////////////

//int num = 0b11011101111
int num = ~0
println Integer.toBinaryString(num)
println flipBitToWin(num)

package Q5_07_Pairwise_Swap

/**
 Pairwise Swap: Write a program to swap odd and even bits in an integer with as few instructions as
 possible (e.g., bit 0 and bit 1 are swapped, bit 2 and bit 3 are swapped, and so on).
 *
 * @author cwardgar
 * @since 2017-10-21
 */

// Only 5 bit-twiddling operations!
int pairwiseSwap(int num) {
    int oddBitsMask  = 0b010101010101010101010101010101010
    int evenBitsMask = 0b101010101010101010101010101010101
    
    int onlyEvenBitsShiftedLeft = (num << 1) & oddBitsMask
    int onlyOddBitsShiftedRight = (num >> 1) & evenBitsMask
    
    return onlyEvenBitsShiftedLeft | onlyOddBitsShiftedRight  // Shifted even and odd bits merged together.
}

/////////////////////////////////////////////

def nums = [
        0b101101,
        0b010010,
        0b000000,
        0b111111
]

nums.each {
    println "${Integer.toBinaryString(it)} <--> ${Integer.toBinaryString(pairwiseSwap(it))}"
}

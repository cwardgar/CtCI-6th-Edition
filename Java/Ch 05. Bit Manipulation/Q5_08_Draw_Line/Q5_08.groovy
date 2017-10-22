package Q5_08_Draw_Line

/**
 Draw Line: A monochrome screen is stored as a single array of bytes, allowing eight consecutive
 pixels to be stored in one byte. The screen has width w, where w is divisible by 8 (that is, no byte will
 be split across rows). The height of the screen, of course, can be derived from the length of the array
 and the width. Implement a function that draws a horizontal line from ( xl, y) to ( x2, y).
 
 The method signature should look something like:
 
 drawline(byte[] screen, int width, int xl, int x2, int y)
 *
 * @author cwardgar
 * @since 2017-10-21
 */

byte[] drawLine(byte[] screen, int width, int x1, int x2, int y) {
    for (int x = x1; x <= x2; ++x) {
        drawPixel(screen, width, x, y)
    }
    
    screen
}

void drawPixel(byte[] screen, int width, int x, int y) {
    int height = screen.length * 8 / width
    
    assert x >= 0 && x < width
    assert y >= 0 && y < height
    
    int widthInBytes = width / 8
    int xInBytes = x / 8  // Could truncate; that's okay.
    
    int screenIndex = widthInBytes * y + xInBytes
    
    screen[screenIndex] |= (0x80 >>> (x % 8))
}

byte[] drawLine2(byte[] screen, int width, int x1, int x2, int y) {
    int height = screen.length * 8 / width
    int widthInBytes = width / 8
    
    assert x1 >= 0 && x1 < width
    assert x2 >= 0 && x2 < width
    assert y >= 0 && y < height
    
    for (int xBegin = x1; xBegin <= x2; ) {
        int firstXinByte = xBegin.intdiv(8) * 8  // Truncation expected.
        int xEnd = Math.min(firstXinByte + 7, x2)
    
        // xBegin will be something like 24 and xEnd will be something like 27.
        assert xBegin.intdiv(8) == xEnd.intdiv(8) : "xBegin ($xBegin) and xEnd ($xEnd) don't belong to the same byte!"
        
        // Pixel counting is left to right, but bit counting is right to left.
        int posWithinByte1 = 7 - (xBegin % 8)  // 7
        int posWithinByte2 = 7 - (xEnd % 8)    // 4
    
        int screenIndex = widthInBytes * y + xBegin.intdiv(8)
        screen[screenIndex] = (byte) intWithSetBits(posWithinByte2, posWithinByte1)
        
        xBegin = firstXinByte + 8
    }
    
    screen
}

// i <= j, numbering starts at zero, counting from the LSB.
int intWithSetBits(int i, int j) {
    assert i >= 0 && i < Integer.BYTES * 8
    assert j >= 0 && j < Integer.BYTES * 8
    assert i <= j
    
    int allOnesStartingAtJ = (1 << (j + 1)) - 1
    int allOnesButLastI = ~((1 << i) - 1)
    allOnesStartingAtJ & allOnesButLastI
}

//////////////////////////////////////////////////////////

void drawScreen(byte[] screen, int width) {
    int height = screen.length * 8 / width
    
    for (int y = 0; y < height; ++y) {
        for (int x = 0; x < width; ++x) {
            if (getBit(screen, width, x, y)) {
                print 'X'
            } else {
                print '_'
            }
        }
        println()
    }
}

boolean getBit(byte[] screen, int width, int x, int y) {
    int widthInBytes = width / 8
    int xInBytes = x / 8  // Could truncate; that's okay.
    
    int screenIndex = widthInBytes * y + xInBytes
    screen[screenIndex] & (0x80 >>> (x % 8))
}

//////////////////////////////////////////////////////////

int width = 32
int height = 24
byte[] screen = new byte[height * width / 8]

byte[] screenWithLine = drawLine(screen, width, 6, 26, 13)
drawScreen screenWithLine, width

println "=================================================================="

screen = new byte[height * width / 8]

screenWithLine = drawLine2(screen, width, 6, 26, 13)
drawScreen screenWithLine, width

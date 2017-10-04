package Q1_07_Rotate_Matrix

/**
 Rotate Matrix: Given an image represented by an NxN matrix, where each pixel in the image is 4
 bytes, write a method to rotate the image by 90 degrees. Can you do this in place?
 *
 * @author cwardgar
 * @since 2017-10-02
 */

def rotateMatrix(int[][] matrix) {
    int n = matrix.length
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < i; ++j) {
            int temp = matrix[i][j]
            matrix[i][j] = matrix[j][i]
            matrix[j][i] = temp
        }
    }
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n / 2; ++j) {
            int temp = matrix[i][j]
            matrix[i][j] = matrix[i][n - j - 1]
            matrix[i][n - j - 1] = temp
        }
    }
    
    matrix
}

def makeMatrix(int nRows, int nCols) {
    int[][] matrix = new int[nRows][nCols]
    int count = 0
    
    for (int i = 0; i < nRows; ++i) {
        for (int j = 0; j < nCols; ++j) {
            matrix[i][j] = ++count
        }
    }
    matrix
}

def printMatrix(int[][] matrix) {
    for (int i = 0; i < matrix.length; ++i) {
        for (int j = 0; j < matrix.length; ++j) {
            printf "%-3d", matrix[i][j]
        }
        println()
    }
}

///////////////////////////////////////////

int[][] matrix = makeMatrix(10, 10)
printMatrix matrix
println()
rotateMatrix matrix
printMatrix matrix

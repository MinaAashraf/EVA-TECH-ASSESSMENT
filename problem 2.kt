
fun getMaxStudentsNum(matrix: MutableList<MutableList<Char>>): Int {
    var max = 0
    if (matrix.isEmpty())
        return max
    for (i in matrix.indices) {
        for (j in matrix[0].indices) {
            // if this seat is not broken
            if (matrix[i][j] == '.') {
                // check if seat is not occupied
                if (!isOccupied(matrix, i, j)) {
                    //place student in this seat
                    matrix[i][j] = 'y'
                    max++
                }
            }
        }
    }
    return max
}

fun isOccupied(matrix: List<List<Char>>, i: Int, j: Int): Boolean {
    // if left seat is occupied (its state is y )
    if (j - 1 >= 0 && matrix[i][j - 1] == 'y') return true
    // if right seat is occupied (its state is y )
    if (j + 1 < matrix[i].count() && matrix[i][j + 1] == 'y') return true
    // if upper left seat is occupied (its state is y )
    if (i - 1 >= 0 && j - 1 >= 0 && matrix[i - 1][j - 1] == 'y') return true
    // if upper right seat is occupied (its state is y )
    if (i - 1 >= 0 && j + 1 < matrix[i].count() && matrix[i - 1][j + 1] == 'y') return true
    // else
    return false
}

fun main() {
    val inputMatix = mutableListOf(
        mutableListOf('#','.','#','#','.','#'),
        mutableListOf('.','#','#','#','#','.'),
        mutableListOf('#','.','#','#','.','#')
        )
    print(getMaxStudentsNum(inputMatix))
}
fun calculateLcmSum(n: Int): Long {
    var sum = 0L
    for (i in 1..n)
        sum += calculateLcm(i, n)
    return sum
}

fun calculateLcm(num1: Int, num2: Int): Long {
    val gcd = calculateGCD(num1, num2)
    val lcm = num1.toLong() * num2 / gcd
    return lcm
}

fun calculateGCD(num1: Int, num2: Int): Int {
    var greaterNum  = num1
    var smallerNum = num2
    while (greaterNum % smallerNum != 0) {
        if (greaterNum < smallerNum) {
            val temp = greaterNum
            greaterNum = smallerNum
            smallerNum = temp
        }
        greaterNum -= smallerNum

    }
    return smallerNum
}

fun main() {
    repeat(readLine()!!.toInt())
    {
        println(calculateLcmSum(readLine()!!.toInt()))
    }
}
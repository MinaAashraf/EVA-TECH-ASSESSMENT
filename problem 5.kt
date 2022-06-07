
fun findRequiredNumber(n: Int): Long {
    var dividend: Long = 0L
    var sum: Int
    for (i in 1..1000000 step 9) {
        dividend = n * i.toLong()
        sum = sumDigits(dividend, n)
        if (sum == n)
            return dividend
    }
    return -1
}

fun sumDigits(dividend: Long, n: Int): Int {
    var dividendNum = dividend
    var sum = 0
    while (dividendNum != 0L) {
        var digit = (dividendNum % 10).toInt()
        sum += digit
        dividendNum /= 10
    }
    return sum
}

fun main() {
    repeat(readLine()!!.toInt()) {
        println(findRequiredNumber(readLine()!!.toInt()))
    }

}
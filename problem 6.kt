import java.util.PriorityQueue
import kotlin.math.sign

class Backup {
    companion object {
        // if num of offices is even number then every separated pair of offices are linked in their normal sequence
        fun getMinCableLength(distances: List<Int>, n: Int): Long {
            var cableLength : Long = 0
            if (n % 2 == 0) {
                for (i in 0 until n step 2)
                    cableLength += distances[i + 1] - distances[i]
                return cableLength
            }

            //else: greedy algorithm is used:
            var pairCableLength: Int
            val pQueue: PriorityQueue<Pair> = PriorityQueue<Pair> { p1, p2 ->
                if (p1.priorityIndicator == p2.priorityIndicator)
                    0
                else
                    (p1.priorityIndicator - p2.priorityIndicator).sign
            }
            var pairFirst = 0
            var pairSec = 0
            for (i in 1 until distances.count()) {
                pairCableLength = distances[i] - distances[i - 1]
                pairFirst = distances[i - 1]
                pairSec = distances[i]
                val pair = Backup.Pair(pairCableLength, pairFirst, pairSec)
                pQueue.add(pair)
            }
            val linkedOffices = mutableSetOf<Int>()
            var tempPair = pQueue.poll()
            var currentPair = pQueue.poll()
            // take the first pair
            cableLength += tempPair.pCableLength
            while (pQueue.isNotEmpty()) {
                linkedOffices.add(tempPair.pFirst)
                linkedOffices.add(tempPair.pSec)
                currentPair = pQueue.poll()
                // if pair offices are not exist in the linked offices
                if (!(currentPair.pFirst in linkedOffices || currentPair.pSec in linkedOffices)) {
                    cableLength += currentPair.pCableLength
                    tempPair = currentPair
                }
            }

            return cableLength
        }
    }

    data class Pair(val pCableLength: Int, val pFirst: Int, val pSec: Int) {
        // priority of pair depends on the sum of pCableLength value and pSec value
        // pCableLength = the distance difference between two offices
        // pSec = the distance of the second office (in the pair) from the beginning
        val priorityIndicator = pCableLength + pSec
    }
}

fun main() {
    val distances = mutableListOf<Int>()
    repeat(readLine()!!.toInt())
    {
        val (n, k) = readLine()!!.split(' ').map { it.toInt() }
        distances.clear()
        repeat(n) {
            distances.add(readLine()!!.toInt())
        }
        println(Backup.getMinCableLength(distances, n))
    }
}
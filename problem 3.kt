/*
fun match(s: String, p: String): Boolean
val pattern = p.replace("[*]+".toRegex(), "(.)*").replace("?", ".")
val regex = pattern.toRegex()
return regex.matches(s)

}
*/

fun match(s: String, p: String): Boolean {

   // if pattern & string are both empties then they matches
    if (p.isEmpty())
        return s.isEmpty()

    // using db by saving previous solutions
    val sSize = s.length
    val pSize = p.length
    var solutionsStore = Array(sSize + 1) { BooleanArray(pSize + 1) { false } }

    // empty pattern matches with empty string
    solutionsStore[0][0] = true

    // * matches the empty string
    for (i in 1..pSize) {
        if (p[i - 1] == '*')
            solutionsStore[0][i] = solutionsStore[0][i - 1]
    }

    for (i in 1..sSize) {
        for (j in 1..pSize) {

            // if pattern char is '*'
            if (p[j - 1] == '*')
                solutionsStore[i][j] = solutionsStore[i][j - 1] || solutionsStore[i - 1][j]

            //if pattern char is '?', or pattern char is identical with corresponding string char
            else if (p[j - 1] == '?' || s[i - 1] == p[j - 1])
                solutionsStore[i][j] = solutionsStore[i - 1][j - 1]

            // else write false
            else
                solutionsStore[i][j] = false
        }
    }

    return solutionsStore[sSize][pSize]

/*val pattern = p.replace("[*]+".toRegex(),"*")
    if (pattern.none { it == '*' || it == '?' })
        return pattern == s

    if (pattern.contains('?') && !pattern.contains('*')) {
        if (pattern.length != s.length)
            return false
        val list = pattern.split('?')
        for (i in 0..s.length) {
            if (s[i] != pattern[i] && pattern[i] != '?')
                return false
        }
        return true

    }
    if (pattern.contains('*') && !pattern.contains('?')) {
        if (pattern.length == 1)
            return true
        val indices = mutableListOf<Int>()
        pattern.forEachIndexed { index, c ->
            if (c == '*')
                indices.add(index)
        }
        print(indices)
        pattern.forEachIndexed { i, it ->
            if (it != '*') {
                if (i == 0 && s.first() != it)
                    return false
                if (i == pattern.length - 1) {
                    return s.last() == it
                }
                var beforeIndex = 0
                var afterIndex = i
                for (index in indices)
                    if (index <= i)
                        beforeIndex = index
                for (index in indices)
                    if (index >= i) {
                        afterIndex = index
                        break
                    }
                println("$beforeIndex,,,$afterIndex")
                if (!s.substring(beforeIndex, afterIndex + 1).contains(it))
                    return false
            }
        }
        return true
    }
    return true*/
}

fun main() {
    val s = readLine()!!;
    val p = readLine()!!;
    println(match(s, p))
}
import java.util.Stack

val operatorsList = listOf("+", "-", "(", ")")

var directOperatorBeforeBracket: String = ""
var leftBracketsWithSign = mutableMapOf<Int, String>()
val myStack: Stack<String> = Stack<String>()

fun convertInfixToPostfix(input: String): String {
    var postFix = ""
    var readyInput = ""
    input.trim().forEachIndexed { index, c ->
        if (c in mutableListOf('+', '(', ')'))
            readyInput += " $c "
        else if (c == '-') {
            if ((readyInput.length - 2 >= 0 && readyInput[readyInput.length - 1] != '(') || readyInput.length - 2 < 0) {
                readyInput += " - "
            } else {
                readyInput += "-"
            }
        } else if (c != ' ')
            readyInput += c
    }

    var inputList = readyInput.trim().split("[\\s]+".toRegex())

    inputList.forEach {
        if (it in operatorsList) {
            if (it == "(") {
                if (myStack.isNotEmpty()) {
                    directOperatorBeforeBracket = myStack.peek()
                    handleNestedBracketsMinusSign()
                }
                myStack.push(it)
            } else if (it == ")") {
                leftBracketsWithSign.remove(leftBracketsWithSign.count())
                var element = ""
                while (true) {
                    element = myStack.pop()
                    if (element == "(")
                        break
                    postFix += " $element"
                }
            } else if (myStack.empty() || myStack.peek() == "(")
                myStack.push(it)
            else if (myStack.peek() == "+" || myStack.peek() == "-") {
                postFix += " " + myStack.pop()
                myStack.push(it)
            }

        } else {
            var literalNum = it
            if (leftBracketsWithSign.isNotEmpty() && leftBracketsWithSign[leftBracketsWithSign.keys.last()] == "-")
                literalNum = (it.toInt() * -1).toString()

            postFix += " $literalNum"
        }
    }
    myStack.forEach { postFix += " $it" }

    return postFix.trim()
}

fun evaluate(input: String): Int {
    if (input.none { it.toString() in operatorsList }) {
        var trimmedInput = input.trim()
        return trimmedInput.toInt()
    }

    val postFixString: List<String> = convertInfixToPostfix(input).split("\\s+".toRegex())
    print(postFixString)
    val myStack: Stack<Int> = Stack()
    var result = 0
    var frOp: Int
    var secOp: Int
    postFixString.forEach {
        if (it != "+" && it != "-")
            myStack.push(it.toInt())
        else {
            secOp = myStack.pop()
            var isPositive = it == "+"
            if (!myStack.empty()) {
                frOp = myStack.pop()
                myStack.push(if (isPositive) frOp + secOp else frOp - secOp)
            } else {
                if (!isPositive)
                    secOp *= -1

                myStack.push(secOp)

            }
        }
    }
    if (myStack.isNotEmpty())
        result = myStack.pop()
    return result

}

fun handleNestedBracketsMinusSign() {
    if (leftBracketsWithSign.isNotEmpty() && leftBracketsWithSign[leftBracketsWithSign.keys.last()] == "-") {
        myStack.pop()
        directOperatorBeforeBracket = if (directOperatorBeforeBracket == "+") "-" else "+"
        myStack.push(directOperatorBeforeBracket)
    }
    if (directOperatorBeforeBracket == "-") {
        myStack.pop()
        myStack.push("+")
        leftBracketsWithSign.put(leftBracketsWithSign.count() + 1, "-")
    } else
        leftBracketsWithSign.put(leftBracketsWithSign.count() + 1, "+")
}


fun main() {
    val input = readLine()!!
    print(evaluate(input))
}






package com.hyunwoo.kopring.week1

fun main() {
    assignment1()
    assignment2()
}

private fun assignment2() {
    val foodList = listOf(
        Food("돼지목살", 6000),
        Food("제로콜라", 2000),
        Food("민트초코오레오", 1500),
        Food("진라면순한맛", 1000),
    )

    val receipt = listOf(
        Receipt.Product(foodList),
        Receipt.Divider,
        Receipt.Sum(foodList.sumOf { it.price })
    )
    receipt.forEach { println(it.print()) }
}

private fun assignment1() {
    val count = readln().toInt()
    repeat(count) {
        val stack = KStack<Char>(mutableListOf())
        val parenthesesSentence = readln()
        var isVPS = true
        parenthesesSentence.forEach {
            when (it) {
                '(' -> stack.push(')')
                else -> {
                    if (stack.isEmpty()) {
                        isVPS = false
                        return@forEach
                    }
                    stack.pop()
                }
            }
        }
        println(if (stack.isEmpty() && isVPS) "YES" else "NO")
    }
}

private data class Food(
    val name: String,
    val price: Int
) {
    override fun toString() = "$name ${price}원"
}

private sealed interface Receipt {
    data class Product(val foods: List<Food>) : Receipt {
        override fun print() = foods.joinToString("\n")
    }

    object Divider : Receipt {
        override fun print() = "----------------"
    }

    data class Sum(val price: Int) : Receipt {
        override fun print() = "총합 ${price}원"
    }

    fun print(): String
}

private class KStack<T>(
    private val stack: MutableList<T>
) {
    fun push(item: T) {
        stack.add(item)
    }

    fun pop() = stack.removeLastOrNull()

    fun size() = stack.size

    fun find(item: T) = stack.contains(item)

    fun isEmpty() = stack.isEmpty()
}

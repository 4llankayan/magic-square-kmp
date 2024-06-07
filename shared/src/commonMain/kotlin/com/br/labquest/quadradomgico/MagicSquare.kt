package com.br.labquest.quadradomgico

class MagicSquare {
    companion object {
        data class MagicSquareResult(val isMagic: Boolean, val magicSum: Int? = null, val hasDuplicates: Boolean = false)

        fun isMagicSquare(square: Array<IntArray>): MagicSquareResult {
            val size = square.size
            val numbers = mutableSetOf<Int>()
            val sum = square[0].sum()

            for (i in 0 until size) {
                for (j in 0 until size) {
                    if (!numbers.add(square[i][j])) {
                        return MagicSquareResult(isMagic = false, hasDuplicates = true)
                    }
                }
            }

            for (i in 0 until size) {
                if (square[i].sum() != sum) {
                    return MagicSquareResult(isMagic = false, hasDuplicates = false)
                }
                if (square.sumOf { it[i] } != sum) {
                    return MagicSquareResult(isMagic = false, hasDuplicates = false)
                }
            }

            if ((0 until size).sumOf { square[it][it] } != sum) {
                return MagicSquareResult(isMagic = false, hasDuplicates = false)
            }
            if ((0 until size).sumOf { square[it][size - it - 1] } != sum) {
                return MagicSquareResult(isMagic = false, hasDuplicates = false)
            }

            return MagicSquareResult(isMagic = true, magicSum = sum, hasDuplicates = false)
        }
    }
}

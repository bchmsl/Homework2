package com.bchmsl.homework2

class NumbersConverter {
    private val tensConnector = "და"
    private val hundredsPrefix = "ას"
    private val thousandsPrefix = "ათას"
    private val unitsSuffix = "ი"
    private val units = listOf(
        "",
        "ერთ",
        "ორ",
        "სამ",
        "ოთხ",
        "ხუთ",
        "ექვს",
        "შვიდ",
        "რვა",
        "ცხრა",
        "ათ",
        "თერთმეტ",
        "თორმეტ",
        "ცამეტ",
        "თოთხმეტ",
        "თხუთმეტ",
        "თექვსმეტ",
        "ჩვიდმეტ",
        "თვრამეტ",
        "ცხრამეტ"
    )
    private val tens = listOf(
        "",
        "",
        "ოც",
        "ოც",
        "ორმოც",
        "ორმოც",
        "სამოც",
        "სამოც",
        "ოთხმოც",
        "ოთხმოც"
    )

    fun convertNumber(number: Int): String {

        val numbersAsArray = mutableListOf<Int>()
        val numString = number.toString()
        numString.forEach {
            numbersAsArray.add(it.digitToInt())
        }

        val result = when (number) {
            in 1..9 -> oneToString(numbersAsArray, number)
            in 10..99 -> twoToString(numbersAsArray, number)
            in 100..999 -> threeToString(numbersAsArray, number)
            in 1000..9999 -> fourToString(numbersAsArray, number)
            else -> {
                ""
            }
        }
        return result
    }

    private fun oneToString(number: List<Int>, numberInt: Int): String {
        val result =
            if (numberInt == 8 || numberInt == 9) {
                units[number[0]]
            } else {
                units[number[0]] + unitsSuffix
            }

        return result
    }

    private fun twoToString(number: List<Int>, numberInt: Int): String {
        val result =
            if (numberInt in 10..19) {
                units[numberInt] + unitsSuffix
            } else {
                if (number[0] % 2 == 0) {
                    if (numberInt % 10 == 0) {
                        tens[number[0]] + unitsSuffix
                    } else {
                        if (number[0] == 0) {
                            tens[number[0]] + oneToString(
                                number.subList(1, 2),
                                numberInt % 10
                            )
                        } else {
                            tens[number[0]] + tensConnector + oneToString(
                                number.subList(1, 2),
                                numberInt % 10
                            )
                        }
                    }
                } else {
                    tens[number[0]] + tensConnector + units[number[1] + 10] + unitsSuffix
                }
            }

        return result
    }

    private fun threeToString(number: List<Int>, numberInt: Int): String {
        val result =
            if (numberInt == 100) {
                hundredsPrefix + unitsSuffix
            } else if (number[0] == 1) {
                hundredsPrefix + " " + twoToString(number.subList(1, 3), numberInt % 100)
            } else if (numberInt % 100 == 0) {
                units[number[0]] + hundredsPrefix + unitsSuffix
            } else {
                units[number[0]] + hundredsPrefix + " " + twoToString(
                    number.subList(1, 3),
                    numberInt % 100
                )
            }
        return result
    }

    private fun fourToString(number: List<Int>, numberInt: Int): String {
        val result = if (numberInt == 1000) {
            thousandsPrefix + unitsSuffix
        } else if (number[0] == 1) {
            thousandsPrefix + " " + threeToString(number.subList(1, 4), numberInt % 1000)
        } else if (numberInt % 1000 == 0) {
            if (number[0] == 8 || number[0] == 9) {
                units[number[0]] + " " + thousandsPrefix + unitsSuffix
            } else {
                units[number[0]] + unitsSuffix + " " + thousandsPrefix + unitsSuffix
            }
        } else {
            if (number[0] == 8 || number[0] == 9) {
                units[number[0]] + " " + thousandsPrefix + " " + threeToString(
                    number.subList(1, 4),
                    numberInt % 1000
                )
            } else {
                units[number[0]] + unitsSuffix + " " + thousandsPrefix + " " + threeToString(
                    number.subList(1, 4),
                    numberInt % 1000
                )
            }

        }
        return result
    }
}

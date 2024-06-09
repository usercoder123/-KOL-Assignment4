val products = listOf(
    Product(1, "apple", "Klever fruit", 2022, "PKleverfruit20221", 100000000),
    Product(2, "banana", "Klever fruit", 9023, "PKleverfruit90222", 100000),
    Product(3, "lemon", "Klever fruit3", 1824, "PKleverfruit18243", 10000),
)

fun main() {
    println("Task 1:")
    showBrandProducts("Klever fruit") { p -> println(p) }

    //Task 2
    println("\nTask 2:")
    val printProduct: (Product) -> Unit = { product -> println("Printed by Task2: $product") }
    showBrandProducts("Klever fruit", printProduct)

    println("\nTask 3:")
    products.forEach { println(it.productCode.validateProductCode()) }

    println("\nTask 4:")
    products.forEach { println("${it.price} VND".getValidatedPrice()) }

    println("\nTask 5:")
    val hexStr = 200.toHexString()
    println(hexStr)

    println("\nTask 6:")
    println(hexStr.toBinaryString())
}

//Task1
fun showBrandProducts(brand: String, showBrandProducts: (Product) -> Unit) {
    products.forEach {
        if (it.brand == brand) {
            showBrandProducts(it)
        }
    }
}

//Task 3
fun String.validateProductCode(): Boolean {
    val pattern = "^P[a-zA-Z]+([1-2][0-9]{3})[0-9]+".toRegex()
    return this.matches(pattern)
}

//Task 4
fun String.getValidatedPrice(): String {
    val pattern = "^[1-9][0-9]{0,2}(,[0-9]{3})+ VND".toRegex()
    var result = ""
    var count = 0
    if (!this.matches(pattern)) {
        if ((this.length - 4) % 3 != 0) {
            result = this.substring(0..<(this.length - 4) % 3) + ","
        }
        for (i in (this.length - 4) % 3..<this.length - 4) {
            result += this[i]
            count++
            if (count == 3 && i != this.length - 5) {
                result += ","
                count = 0
            }
        }
    }
    return "$result VND"
}

//Task 5
fun Int.toHexString(): String = this.toString(16).uppercase()

//Task 6
fun String.toBinaryString(): String = this.toInt(16).toString(2)

data class Product(
    val id: Int,
    val name: String,
    val brand: String,
    val year: Int,
    val productCode: String,
    val price: Int
)

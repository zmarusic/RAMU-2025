import java.time.LocalDate

class Food(
    sku: String,
    name: String,
    netSalePrice: Double,
    override var bestBefore: LocalDate,
    var weight: Double = 0.0
) : Item(sku, name, netSalePrice), Perishable {

    override fun getItemType(): String = "Food"
}
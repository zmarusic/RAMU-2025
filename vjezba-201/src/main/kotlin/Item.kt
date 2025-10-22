abstract class Item(
    val sku: String,
    var name: String,
    var netSalePrice: Double
) {
    /** Returns price including the given tax percentage (e.g., 20 -> +20%). */
    open fun getPrice(taxPercent: Double): Double =
        netSalePrice * (1.0 + taxPercent / 100.0)

    /** A short human-readable descriptor of the item type. */
    abstract fun getItemType(): String

    override fun toString(): String = "%s - %s".format(sku, name)
}
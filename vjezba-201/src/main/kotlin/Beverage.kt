class Beverage(
    sku: String,
    name: String,
    netSalePrice: Double,
    var volume: Double // e.g., liters
) : Item(sku, name, netSalePrice) {

    constructor(sku: String, name: String, volume: Double)
            : this(sku, name, netSalePrice = 0.0, volume = volume)  // choose your default price

    init {
        require(volume > 0.0) { "volume must be > 0" }
    }
    override fun getItemType(): String = "Beverage"
    override fun toString() = "${sku} - ${name} (${volume} L)"
}
class Cloth(
    sku: String,
    name: String,
    netSalePrice: Double
) : Item(sku, name, netSalePrice) {

    override fun getItemType(): String = "Cloth"
}
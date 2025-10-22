import java.time.LocalDate
fun main(args: Array<String>) {

        val hrana = Food("1234", "Sir", 15.0, LocalDate.of(2026, 1, 1), weight = 0.5)
        println(hrana.getPrice(20.0))
        println(hrana)
        println(hrana.getItemType())

        val pice = Beverage("2111", "Coca-cola", 2.0)
        println(pice)
        println(pice.getItemType())


        val odjeca = Cloth("3222", "Odijelo", 46.0)
        println(odjeca)
        println(odjeca.getItemType())
}

data class Order(val id: Int, val amount: Double, val status: String)

fun main() {
    val orders = listOf(
        Order(1, 150.0, "Delivered"),
        Order(2, 75.0, "Pending"),
        Order(3, 200.0, "Delivered"),
        Order(4, 50.0, "Cancelled"),
        Order(5, 300.0, "Pending")
    )
    
    val deliveredOrders = orders.filter { it.status == "Delivered" }
    
   
    val totalAmount = orders.map { it.amount }.sum()
    
   
    val statuses = orders.map { it.status }
    
    
    val pendingOrdersWithDoubleAmount = orders
        .filter { it.status == "Pending" }
        .map { Order(it.id, it.amount * 2, it.status) }
    
    println("Delivered Orders:")
    deliveredOrders.forEach { println("Order ${it.id}: ${it.amount}") }
    
    
    fun applyDiscount(orders: List<Order>, discount: (Order) -> Double): List<Pair<Int, Double>> {
        return orders.map { Pair(it.id, discount(it)) }
    }
    
  
    val discountedPending = applyDiscount(
        orders.filter { it.status == "Pending" }
    ) { order -> order.amount * 0.9 }
    
    println("\nDiscounted Pending Orders: $discountedPending")
    
    println("\nResults")
    println("Delivered orders: ${deliveredOrders.size}")
    println("Total amount: $${totalAmount}")
    println("Order statuses: $statuses")
    println("Pending orders with doubled amount: $pendingOrdersWithDoubleAmount")
}
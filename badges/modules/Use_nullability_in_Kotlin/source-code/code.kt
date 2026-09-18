data class Address(
    val street: String,
    val city: String,
    val zipCode: String?
)

data class User(
    val name: String,
    val age: Int?,
    val email: String?,
    val address: Address?
)

fun displayUserInfo(user: User?) {
    
    if (user == null) {
        println("No user data available")
        return
    }
    
  
    println("Name: ${user.name}")
    
   
    println("Age: ${user.age ?: "Age not provided"}")
    
    val emailDisplay = user.email?.uppercase() ?: "Email not provided"
    println("Email: $emailDisplay")
    
    
    if (user.address == null) {
        println("Address: No address provided")
    } else {
        val address = user.address
        val zipDisplay = address.zipCode ?: "N/A"
        println("Address: Street: ${address.street}, City: ${address.city}, ZIP: $zipDisplay")
    }
}

fun getDefaultUser(): User {
    return User(
        name = "Guest",
        age = null,
        email = "guest@example.com",
        address = null
    )
}



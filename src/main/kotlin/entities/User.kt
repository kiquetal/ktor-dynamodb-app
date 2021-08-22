package entities
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey

data class User(val name:String,var age:Int)


@DynamoDbBean
data class CustomerPersist(
    @get:DynamoDbPartitionKey
    var customerId: String? = null,
    var emailAddress: String? = null,
    var firstName: String? = null,
    var lastName: String? = null
)

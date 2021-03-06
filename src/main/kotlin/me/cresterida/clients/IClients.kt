package me.cresterida.clients

import me.cresterida.repository.EntityRepo
import me.cresterida.repository.UserRepo
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient
import java.net.URI
object Clients {
    fun dynamoLocalClient(): DynamoDbAsyncClient {
        return DynamoDbAsyncClient.builder()
            .region(Region.US_EAST_1)
            .endpointOverride(URI.create("http://localhost:8000"))
            .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create("secret", "secret")))
            .build()

    }
    fun enhancedDynamoClient(client: DynamoDbAsyncClient): DynamoDbEnhancedAsyncClient {
        return DynamoDbEnhancedAsyncClient.builder()
            .dynamoDbClient(client)
            .build()
    }

}
object Repos {

    fun entityRepo()=EntityRepo(Clients.enhancedDynamoClient(Clients.dynamoLocalClient()))
    fun userRepo()=UserRepo(Clients.enhancedDynamoClient(Clients.dynamoLocalClient()))
}

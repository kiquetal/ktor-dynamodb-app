package repository

import clients.Clients
import entities.CustomerPersist
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedAsyncClient

class UserRepo(client: DynamoDbEnhancedAsyncClient) :DynamoDbRepo<CustomerPersist>(CustomerPersist::class.java.simpleName,CustomerPersist::class.java,client) {




}



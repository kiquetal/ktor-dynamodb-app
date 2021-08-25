package me.cresterida.converters

import software.amazon.awssdk.enhanced.dynamodb.AttributeConverter
import software.amazon.awssdk.enhanced.dynamodb.AttributeValueType
import software.amazon.awssdk.enhanced.dynamodb.EnhancedType
import software.amazon.awssdk.enhanced.dynamodb.internal.converter.attribute.EnhancedAttributeValue
import software.amazon.awssdk.services.dynamodb.model.AttributeValue
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter


class MyInstanceConverter: AttributeConverter<Instant> {
    override fun transformFrom(input: Instant?): AttributeValue {

        val format = DateTimeFormatter.ISO_DATE_TIME.withZone(ZoneId.of("UTC"))
            .format(input);

        return EnhancedAttributeValue.fromString(format).toAttributeValue()
    }

    override fun transformTo(input: AttributeValue?): Instant {

        return Instant.from(
            DateTimeFormatter.ISO_DATE_TIME.withZone(ZoneId.of("UTC"))
            .parse(input?.s()))



    }

    override fun type(): EnhancedType<Instant> {
        return EnhancedType.of(Instant::class.java);
    }

    override fun attributeValueType(): AttributeValueType {
        return AttributeValueType.S;
    }

}



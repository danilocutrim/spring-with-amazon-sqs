package br.com.producer.config

import com.amazonaws.SDKGlobalConfiguration
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.sqs.AmazonSQS
import com.amazonaws.services.sqs.AmazonSQSClientBuilder
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.env.Environment
import java.util.Arrays

class AmazonSQSConfig(
    private val env: Environment,
    @Value("\${aws.sqs.endpoint}")
    private val endpoint: String,

    @Value("\${aws.accesskey}")
    private val accessKey: String,

    @Value("\${aws.secretkey}")
    private val secretKey: String,

    @Value("\${aws.region}")
    private val region: String
) {

    private val logger = KotlinLogging.logger {}

    fun sqConfiguration(): AmazonSQS {
        if (env.activeProfiles.contains("dev")) {
            logger.info { "Amazon SQS running with profile = ${Arrays.toString(env.activeProfiles)}" }

            System.setProperty(SDKGlobalConfiguration.DISABLE_CERT_CHECKING_SYSTEM_PROPERTY, "true")

            return AmazonSQSClientBuilder
                .standard()
                .withEndpointConfiguration(AwsClientBuilder.EndpointConfiguration(endpoint, region))
                .withCredentials(AWSStaticCredentialsProvider(BasicAWSCredentials(accessKey, secretKey)))
                .build()
        }

        logger.info { "Amazon SQS running with profile = ${Arrays.toString(env.activeProfiles)}" }
        return AmazonSQSClientBuilder
            .standard()
            .withCredentials(AWSStaticCredentialsProvider(BasicAWSCredentials(accessKey, secretKey)))
            .withRegion(region)
            .build()
    }
}

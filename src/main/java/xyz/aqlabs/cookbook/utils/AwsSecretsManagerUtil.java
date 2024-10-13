package xyz.aqlabs.cookbook.utils;

/*
This utility is used to interface with the AWS SDK to retrieve public and private keys to sign the token
*/

import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

public class AwsSecretsManagerUtil {

    private static final Region REGION = Region.US_WEST_1;

    private final SecretsManagerClient secretsManagerClient;

    public AwsSecretsManagerUtil() {
        this.secretsManagerClient = SecretsManagerClient.builder()
                .region(REGION)
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
    }

    public String getSecret(String secretName) {
        GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
                .secretId(secretName)
                .build();

        GetSecretValueResponse getSecretValueResponse = secretsManagerClient.getSecretValue(getSecretValueRequest);
        return getSecretValueResponse.secretString();
    }
}

# Vault KV
Here are the steps for enabling KV secrets, writing secrets, and creating a policy for Jenkins:

1. Enable KV secrets engine:
```bash
vault secrets enable -path=secrets kv
```

2. Write a secret to the KV secrets engine (e.g., storing credentials for 192.168.0.211):
```bash
vault write secrets/creds/192.168.0.211 username=manshu password=****** # Will use this screts to do ssh and to connect slave nodes
```

3. Create a Jenkins policy file (jenkins-policy.hcl) with the desired policy settings. For example:
```hcl
# jenkins-policy.hcl
path "secrets/creds/*" {
    capabilities = ["read"]
}
```

4. Create and apply the Jenkins policy in Vault:
```bash
vault policy write jenkins jenkins-policy.hcl
```

5. Associate the Jenkins policy with an identity (AppRole, user, etc.) or authentication method that will be used by Jenkins. This step is crucial for granting the appropriate permissions.

Now, Jenkins or any other entity associated with the Jenkins policy will have read access to secrets under the "secrets/creds/" path in the KV secrets engine. Make sure to configure Jenkins to authenticate with Vault using the chosen authentication method (e.g., AppRole) and provide the necessary permissions by associating the Jenkins policy.
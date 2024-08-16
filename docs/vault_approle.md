# Vault AppRole
Below are the steps for enabling AppRole inside Vault and generating role ID and secret ID for Jenkins-Vault Integration:

1. Enable AppRole authentication:
```bash
vault auth enable approle
```

2. Configure the AppRole with the desired settings:
```bash
vault write auth/approle/role/jenkins-role token-num-uses=0 secret_id_num_uses=0 policies="jenkins"
```

3. Retrieve the role ID for the AppRole (jenkins-role):
```bash
vault read auth/approle/role/jenkins-role/role-id
```

   
The output will include the role ID, for example:
```bash
role_id   7798032e-27b4-4b8a-0ed5-e4dcd6244276 # This is sample ID
```

4. Generate a secret ID for the AppRole (jenkins-role):
```bash
vault write -f auth/approle/role/jenkins-role/secret-id
```

The output will include the generated secret ID, secret ID accessor, and related information, for example:
```bash
Key                   Value
---                   -----
secret_id             1e72d7ff-f75b-4fc6-79f7-ac80d791de55 # This is sample ID
secret_id_accessor    cdd624bd-b4cf-ec70-9dce-ff944d8fad90
secret_id_num_uses    0
secret_id_ttl         0s
```

These steps enable AppRole authentication, configure a role (jenkins-role) with specific settings, retrieve the role ID, and generate a secret ID for the AppRole.
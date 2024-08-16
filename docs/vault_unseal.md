# Following are the steps to unseal vault
1. Go inside the vault container
```bash
docker exec -it vault sh
```

2. Export Vault server
```bash
export VAULT_ADDR='http://127.0.0.1:8200'
```

3. Initalize Vault server
```bash
vault operator init
```

Sample output

```bash
Unseal Key 1: zznvQOa3abVga2k7IV09bmAhl7+XaN4qD1Cw/hqvYaRH
Unseal Key 2: edarI2broGkVTvEPV+7cFY9c0tiGUQ8bU51HY8T6mc1T
Unseal Key 3: elFQC/EAx5T48/yHYCW19kNBaU4j1rOCc1a4Aj3EFFql
Unseal Key 4: yGEBUh2a/814kqlpznenREv9BM60BLJ9G7Twe2/Y7wyX
Unseal Key 5: Ie3ar2KfsHN3DtUNvml/N6v9XMJwM6XimIALFZ4FWd2B

Initial Root Token: hvs.2J6vwkzWHZ9ZmATOzmeWumiq

Vault initialized with 5 key shares and a key threshold of 3. Please securely
distribute the key shares printed above. When the Vault is re-sealed,
restarted, or stopped, you must supply at least 3 of these keys to unseal it
before it can start servicing requests.

Vault does not store the generated root key. Without at least 3 keys to
reconstruct the root key, Vault will remain permanently sealed!

It is possible to generate new unseal keys, provided you have a quorum of
existing unseal keys shares. See "vault operator rekey" for more information.
```

*Note*: Save the output somewhere on next step we will use the keys to unseal vault

4. Unseal Vault
```bash
# Run below commands to unseal vault. 
# Note: Replace sample keys with yours keys
vault operator unseal zznvQOa3abVga2k7IV09bmAhl7+XaN4qD1Cw/hqvYaRH
vault operator unseal edarI2broGkVTvEPV+7cFY9c0tiGUQ8bU51HY8T6mc1T
vault operator unseal elFQC/EAx5T48/yHYCW19kNBaU4j1rOCc1a4Aj3EFFql
```

5. Login to vault
```bash
# Note: We should be able to login through CLI or with UI
vault login hvs.2J6vwkzWHZ9ZmATOzmeWumiq
```
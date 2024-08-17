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
Unseal Key 1: samplekey1
Unseal Key 2: samplekey2
Unseal Key 3: samplekey3
Unseal Key 4: samplekey4
Unseal Key 5: samplekey5

Initial Root Token: hvs.samplekey

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
vault operator unseal samplekey1
vault operator unseal samplekey2
vault operator unseal samplekey3
```

5. Login to vault
```bash
# Note: We should be able to login through CLI or with UI
vault login hvs.2J6vwkzWHZ9ZmATOzmeWumiq
```

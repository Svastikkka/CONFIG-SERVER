# GitOps
This tool facilitates the management of configuration changes on bare-metal infrastructure.

All changes to our infra should come through pull requests to this repo.

# Prerequisite
- Docker
- Docker compose
- openjdk-17-jdk (for slave as Jenkins runs on java)

# Need for gitops

GitOps provides several benefits in software development and system architecture. Here are some key advantages:

1. **Centralized Configuration Management:**
   - **Simplified Updates:** Centralized configuration makes it easier to update and manage configurations, as changes can be made in one place and propagated to all connected services.

2. **Versioned Configuration:**
   - **Auditing and History:** GitOps often support versioning of configurations, providing an audit trail and history of changes. This can be valuable for debugging, troubleshooting, and understanding the evolution of configurations over time.

3. **Security and Access Control:**
   - **Access Control:** GitOps can enforce access controls, ensuring that only authorized individuals or systems can modify or retrieve sensitive configuration information.

4. **Ease of Troubleshooting:**
   - **Centralized Logging:** With a centralized configuration server, it's easier to log and monitor configuration changes. This aids in troubleshooting issues related to misconfigurations or unexpected behavior.

In summary, GitOps simplifies configuration management, enhances flexibility, and contributes to better control and security in distributed and dynamic software systems.

It also becomes a single source of truth for our infrastructure enhnacing visibility.

# How to use this repo?

Whenever We want to update or add the configuration deployed service on any server we have to raise MR. While creating a MR there are few things need to commited/checked before raising a MR.

Following are the things
1. We need to check that the service name is added in `PATH.json` file. If not we need to add it.
2. Update the configuration of service for correct server folder. Example we want to update nginx.conf (nginx configuration) for `192.168.0.211` comp/server.
3. Whenever we add a new configuration of a service in PATH.json. we should follow below structure
```json
{
    "configurationFileName": {
      "path": "PATH OF configurationFileName present in remote server",
      "service": "Service Name Deployed On Remote Comp",
      "enable": "true: To auto restart service whenever we reboot." // Default is false
    }
}
``` 

Example
```json
{
   "nginx.conf": {
      "path": "/etc/nginx/nginx.conf", // "PATH OF configurationFileName present in remote server 
      "service": "nginx", // Service Name Deployed On Remote Comp
      "enable": "false" // true: To auto restart service whenever we reboot.
   }
}
```

4. We are now ready to raise an MR now.


# Limitations
- File deletions is not handled and ignored.


# Components
- Jenkins is used for ci/cd
- Jenkins has vault integrated (done via UI) and Vault stores all the ssh user credentials

# Installation Setup (one time for deployment)

**Step 1** Required: Clone this repository using git.
```bash
git clone git@192.168.0.155:balte/gitops.git
```

**Step 2** Required: Go inside the repository
```bash
cd ./gitops
```

**Step 3** Required: Run the below command to initialize intial setup (If we are initializing without any previous/existing data)
```bash
docker compose up -d
```

**Step 4** Optional: Run the below command to initialize intial setup (If we are initializing with any previous/existing data)

**Step 4.1** Restore backups in below directories
- jenkins_home (need to create it manually)
- vault (need to create it manually)

**Step 4.2** Run the below command to start Jenkins and Vault
```bash
docker compose up -d
```

**Step 5** Required: Check services are UP

**Step 5.1** Check Vault is UP by going on the following URL: http://COMP_PRIVATE_IP:8200.  We should able to see its UI

**Step 5.2** Check Jenkins is UP by going on the following URL: http://COMP_PRIVATE_IP:8080.  We should able to see its UI

**Step 6** Required: Configure Jenkins to install default Plugins.

*Reference*: We can go through following video to understand [How to Install Jenkins on Ubuntu Linux](https://www.youtube.com/watch?v=s1o9BKW2rdw)

**Step 7** Required: Unseal Vault

We can refer this doc: [Vault Unseal](./docs/vault_unseal.md)

**Step 8** Required: Enable approle in vault

We can refer this doc: [Vault AppRole](./docs/vault_approle.md)

**Step 9** Required: Enable KV in vault

We can refer this doc: [Vault KV](./docs/vault_kv.md)

**Step 10** Required: Install Required Plugins in Jenkins

1. Pipeline Utility Steps
2. Vault Plugin
3. Gitlab

*Reference*: [How to Install Jenkins Plugins](https://www.youtube.com/watch?app=desktop&v=JX_G2gAGvfk)

*Reference*: [Managing Plugins](https://www.jenkins.io/doc/book/managing/plugins/)

**Step 11** Required: Integrate Jenkins with Vault

Navigate to manage Jenkins and Configure system. and find the vault plugin and fill the URL and then click on add the credentilas to add the approle authentication and select kind as Vault AppRole Credentials and fill out the role ID, Secret ID, path and ID as generic name to identify and click on Add.

*Note*: On advance settings disable the ssl certfication.

*Reference* [How to Integrate HashiCorp Vault With Jenkins](https://www.youtube.com/watch?v=5-RMu9M_Anc)

**Step 12** Create a Job
Try to create a Job Config Server with type **pipeline** and add **Config Server** repository URL and Trigger it manually.


# Reference
- [How to Integrate Gitlab with Jenkins using SSH Connection](https://www.youtube.com/watch?v=fE741bkK1kA)
- [Slave Connection](https://community.jenkins.io/t/node-connection-error/6082)
- [How to Integrate HashiCorp Vault With Jenkins](https://www.youtube.com/watch?v=5-RMu9M_Anc)
- [How to Install Jenkins Plugins](https://www.youtube.com/watch?app=desktop&v=JX_G2gAGvfk)
- [Managing Plugins](https://www.jenkins.io/doc/book/managing/plugins/)
- [Role-based Authorization Strategy](https://plugins.jenkins.io/role-strategy/)
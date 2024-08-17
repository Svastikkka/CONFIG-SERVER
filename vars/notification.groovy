def call(message) {
    def googleChatWebhook = "WEBHOOK_URL"
    def buildUrl = env.BUILD_URL
    def completeMessage = "Pipeline job ${env.JOB_NAME} - ${message} and build URL: http://192.168.0.209:8080/job/${env.JOB_NAME}/${env.BUILD_NUMBER}"

    sh """
        curl -X POST -H "Content-Type: application/json" -d '{"text": "${completeMessage}"}' "${googleChatWebhook}"
    """
}

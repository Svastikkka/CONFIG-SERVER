def call(message) {
    def googleChatWebhook = "https://chat.googleapis.com/v1/spaces/AAAAKYaF-4w/messages?key=AIzaSyDdI0hCZtE6vySjMm-WEfRq3CPzqKqqsHI&token=iB_dMvNwRu4NvGPBwGbf8lyTBXI_z7cm4gNCJLs2BNE"
    def buildUrl = env.BUILD_URL
    def completeMessage = "Pipeline job ${env.JOB_NAME} - ${message} and build URL: http://192.168.0.209:8080/job/${env.JOB_NAME}/${env.BUILD_NUMBER}"

    sh """
        curl -X POST -H "Content-Type: application/json" -d '{"text": "${completeMessage}"}' "${googleChatWebhook}"
    """
}
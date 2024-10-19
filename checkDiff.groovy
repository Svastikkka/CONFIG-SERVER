pipeline {
    agent any

    stages {
        stage('Find PATH.json') {
            steps {
                script {
                    // Define the base directory to search within
                    def baseDir = '.'  // Current directory

                    // Find all files matching the pattern for PATH.json
                    def jsonFiles = findFiles(glob: "**/PATH.json")

                    // Check if any files were found
                    if (jsonFiles.length > 0) {
                        for (file in jsonFiles) {
                            // Print the complete path of the found file
                            echo "Found PATH.json at: ${file}"
                        }
                    } else {
                        echo "No PATH.json files found in the directory structure."
                    }
                }
            }
        }
    }
}

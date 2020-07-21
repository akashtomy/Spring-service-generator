#!groovy
node('linux') {
    echo 'Loading pipeline!'
    def servicePipeline = fileLoader.fromGit('./vars/commonServicesPipeline.groovy', 'https://github.com/corelogic/clp-services-pipeline.git', 'master', '774aeeb5-4206-427f-bdb8-33f22bde0252', '')

    serviceName = '<%=fullServiceName%>'
    projectMonitorUrl = 'insert_me'
    buildPath = 'build'
    jmxFilePath = null

    servicePipeline.buildTestDeploy(serviceName, projectMonitorUrl, buildPath, jmxFilePath)
    echo 'Done running buildTestDeploy'
}

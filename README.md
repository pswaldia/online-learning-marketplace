# HUV20.O JAVA Final Assignment

Template Project for JAVA Track

    1.	sonar-project.properties:
            a.	Sonar scanner will scan based on properties configured in sonar-project.properties. 
            b.	The sonar project will be added in sonarqube as per the artifactid and name set in pom.xml, replace '<SET_USER_ID>' with your gitlab id in below example.
            
        	<groupId>com.example</groupId>
        	<artifactId>SET_USER_ID</artifactId>
        	<version>0.0.1-SNAPSHOT</version>
        	<packaging>jar</packaging>
        
        	<name>SET_USER_ID</name>
        	<description>Demo project for Spring Boot</description>
        	
    2.	Dockerfile: It has been updated for maven build and publish the test reports to sonarqube.
    
    3.	In .gitlab-ci.yml pipeline: 
            a.	The pipeline has been configured to trigger once the PR is merged to “master” branch only.
            b.	It authenticates with Google cloud service account.
            c.	It calls for cloudbuild.yaml file for deploying the service in Cloud run.
            
    4.	cloudbuild.yaml has been designed to handle following steps.
            a.	Build the docker image with respect to the Dockerfile and tag the docker image as asia.gcr.io/<GCP_PROJECT_ID>/<GITLAB_USER_ID> 
            b.	Push the docker image to google cloud registry.
            c.	Deploy the docker image to Google cloud run as a container and create the endpoint of the spring boot application.

# Application Endpoint:

    1.	The pipeline will get trigger automatically once the PR is merged to master branch. 
    2.	In Gitlab, go to CICD --> Pipeline --> latest pipeline number --> click on **deploy** and it prints the pipeline logs. 
    3.	The endpoint of the application will be reflecting in pipeline logs under step 2.
	        Ex:  Step #2: Service URL: https://tomuser-amxbp6pvia-as.a.run.app

# Application logs:
    In Gitlab, go to CICD --> Pipeline --> latest pipeline number --> click on **deploy** and it prints the pipeline logs. Here search for key word "gcoud logging", it will list the logs of the application.
    

# Note:

    1.	Database has to be created with DELOITTE user id on mandatory so the deployment will happen and will be able to connect with database.
    2.  Login to Gitlab using Deloitte mail ID only.
    3.  Update the Gitlab ID in pom.xml as said above in 1b point.
    4. Dont push the .gitlab-ci.yml file to any public repository
    5. Dont change the content in the .gitlab-ci.yml Dockerfile cloudbuild.yaml sonar-project.properties
    6. To check the pipelines go to the CI/CD -> Pipelines
    7. Everytime when the code is pushed to the Master branch the pipeline will be triggered and will be deployed to the Cloudrun
    8. To access URL Go to CI/CD -> Pipelines -> Click on passed -> deploy -> In the terminal you can see service URL
    9. Make sure only working code is pushed to the Master 
    10. If Connection Reset Peer error came -: Search Services->Run as Admin-> Netskope->Stop/Disable it (or if OAuth issue came) 
    11. Connect to VPN-: To access URL of final deployed app if get an error of forbidden 
    12. Dont upload the endpoint URL to any social media, dont share the URL with anyone   
    
 

# Test coverage steps:
    1. mvn clean test
    2. mvn jacoco:report
    3. Open target/site/jacoco/index.html or jacoco.xml
    Above html page contains detailed information on test coverage.

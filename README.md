
# ctc-guarantee-balance-journey-tests

This repo contains all of the acceptance tests which are defined in conjunction with the business using Given, When, 
Then statements written in Gherkin syntax. 
It is built using:

    Cucumber
    Selenium-webDriver
    SBT to build
    
## How to run the journey tests
Tests can be run in Chrome or Firefox using a GUI or headless browser, either locally or in a docker container (described later).

### Journeys Covered

     Balance success journeys
     Balance pending journeys
     Incorrect data error journeys
        
### Start service manager

    sm --start CTC_TRADERS_GUARANTEE_BALANCE_ACCEPTANCE -r
    
### Test execution

    ./run.sh

### Driver/Browser Config
All configuration of the browsers types we test with is handled by a dependency on the [HMRC Webdriver Factory library](https://github.com/hmrc/webdriver-factory)

We have configured a headless browser type by passing additional Chrome options in the Driver class and access this using the `-Dbrowsertype=headless` jvm option in the `~/run_headless.sh` script.

## Security Tests
Security tests are dependant on [HMRC Zap Automation library](https://github.com/hmrc/zap-automation) and configured to run using Zap 2.8.0.  
ZAP tests are configured to scan the request/response of the full suite of journey tests.

### Test execution

#### Jenkins
To run the security tests on Jenkins run - 
    https://build.tax.service.gov.uk/job/Common%20Transit%20Convention%20Traders/job/ctc-guarantee-balance-zap-tests/

#### Locally
    ./zap.sh -daemon -config api.disablekey=true -port 11000 # Start ZAP in Daemon mode 
    ./run_zap.sh

## Accessbility Tests
To support accessibility tests we can run the tests with an additional parameter which will force each page into a failure state, allowing us to capture the html of the error messages.

   ### Test execution
   
   #### Jenkins
To run the accessibility tests on Jenkins run -    
    https://build.tax.service.gov.uk/job/Common%20Transit%20Convention%20Traders/job/ctc-guarantee-balance-a11y-tests/   
   
   #### Locally
    Accessibility tests cannot be run locally
 
   ### Config
   * Extent: ~/src/test/resources/extent-config.xml
   * Suites: ~/src/test/scala/suites/Runner.scala & Runner_WIP.scala
   * Before: Setup test reports directory
   * After:  Write report using junit source
   * Frequency - Report written on suite execution
   
   ### Output
   Reports are written to ~/target/test-reports/html-report/index.html

## Data Cleanup
Cleanup script to drop the 'user-answers' mongo collection.

`./drop_guarantee_balance_frontend_data.sh`

## Screenshots
Screenshot utility allowing screenshots to be taken on demand. This is available to use but not currently being called in any common steps.

### To use
Add `tryTakeScreenShot()` method to steps or page object where required

Add jvm option `-Dscreenshots=true` to `~/.run` scripts to capture screenshot

Screenshots are output to `~/target/screenshots` as a .png image

## Installing and running the tests in Docker

   ### Install Docker
   * Linux: https://docs.docker.com/install/linux/docker-ce/ubuntu/ followed by
            https://docs.docker.com/install/linux/linux-postinstall/
   * Mac (install docker desktop): https://docs.docker.com/docker-for-mac/install/

   ### Start service manager

    sm --start CTC_TRADERS_GUARANTEE_BALANCE_ACCEPTANCE -r

   ### Change to the docker/chrome directory

    cd $WORKSPACE/ctc-guarantee-balance-journey-tests/docker/chrome/

   ### Build the chrome instance (including the . at the end)
   *You only need to do this once:*

     docker build -t chrome .

   ### Start the Docker container
   
   *This will map service manager, chrome and vnc viewer ports to a Docker alias on the container instance.*

   `./rundocker.sh` or
   
   `./rundocker.sh smenv` 
   
   **Note - Passing the "smenv" argument is required if you are running a service manager virtualenv.**
   
   ### Run the journey tests

    cd $WORKSPACE/ctc-guarantee-balance-journey-tests/docker/chrome/
    ./run_remote.sh (runs in remote container using headless browser) or
    ./run_jenkins.sh (runs in remote container using GUI browser)
    
   ### Connect to the VNC server (View running tests)
   
   Connect to the image on `vnc://localhost:5900` with your favorite vnc client (if you're on a mac just use Safari)
   
   When prompted for a password, enter: `secret`

   ### Updating selenium version

   Edit  `~/docker/chrome/Dockerfile`
   
   Update `FROM selenium/standalone-chrome-debug:<version>` to reflect [Selenium version](https://github.com/SeleniumHQ/docker-selenium/releases)

   ### Useful docker commands

    docker ps                                   # Lists all running containers
    docker stop $(docker ps -a -q)              # Stops all running containers
    docker exec -it <container id> /bin/bash    # Bash access to container


# ctc-guarantee-balance-journey-tests

This repo contains all the acceptance tests which are defined in conjunction with the business using Given, When, Then statements written in Gherkin syntax. 

## Prerequisites
* Docker
  * [Ubuntu](https://docs.tax.service.gov.uk/mdtp-handbook/documentation/developer-set-up/install-docker.html#install-docker-ubuntu)
  * [macOS](https://docs.tax.service.gov.uk/mdtp-handbook/documentation/developer-set-up/install-docker.html#install-docker-macos)
* Docker compose
  * [Ubuntu](https://docs.tax.service.gov.uk/mdtp-handbook/documentation/developer-set-up/install-docker.html#install-the-docker-packages)
  * [macOS](https://formulae.brew.sh/formula/docker-compose) (Ensure the symlink step is followed so Docker can find the pkugin)
* [Docker selenium grid](https://github.com/hmrc/docker-selenium-grid)
* [MongoDB](https://docs.tax.service.gov.uk/mdtp-handbook/documentation/developer-set-up/set-up-mongodb.html)
    
## How to run the journey tests
* Ensure that you have cloned docker-selenium-grid and have executed `./start.sh` to run the relevant containers
* Start the relevant services in service manager with:
  * `sm2 --start CTC_TRADERS_GUARANTEE_BALANCE_ACCEPTANCE` for phase 4
  * `sm2 --start CTC_TRADERS_GUARANTEE_BALANCE_ACCEPTANCE_V2` for phase 5
* Run the relevant test with:
  * `./run.sh` for phase 4
  * `./run_v2.sh` for phase 5
* To see the journey tests happening, navigate to `localhost:4444`

## Journeys Covered
* Balance success journeys
* Balance pending journeys
* Incorrect data error journeys

## Security Tests
Security testing is done through the `UITestJobBuilder` in `build-jobs`

## Accessibility Tests
Accessibility testing is also done through the `UITestJobBuilder`, and the report can be generated locally and in Jenkins by appending `testReport` when running the tests.

## Data Cleanup
`./drop_guarantee_balance_frontend_data.sh` is a cleanup script for dropping the `user-answers` collection in MongoDB.
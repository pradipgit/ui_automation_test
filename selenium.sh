#!/bin/bash
#
# Copyright : IBM Corporation 2017, 2017
#
#export CHROME_BIN=chromium-browser
#export DISPLAY=:99.0

#RUN webdriver-manager update
#webdriver-manager update
#RUN protractor
#xvfb-run -a protractor debug e2e/conf.js
#echo $NAMESPACE
# if [ $NAMESPACE == "cam-cicd-v2" ]; then
#   xvfb-run -a protractor e2e/conf.js --suite Login --troubleshoot --params.url="https://${NAMESPACE}.gravitant.net"
# else
#   xvfb-run -a protractor e2e/conf.js --troubleshoot --params.url="https://${NAMESPACE}.gravitant.net"
# fi

set -o nounset
set -o pipefail
set -o errexit

echo $NAMESPACE
echo $SLACK_URL
echo $PASSWORD

echo "
password=$PASSWORD
slackurl=$SLACK_URL
login_url=https://cb-qa-corex.gravitant.net
username=cbadmn@outlook.com " > config.properties

cp config.properties /core-ui-test-automation/properties/config.properties

xvfb-run -a mvn clean test


#!/bin/bash
#
set -o errexit
set -o nounset
namespace=$3
echo $namespace
docker -v
docker ps -a | grep 'core-ui-test-automation' | awk '{print $1}' | xargs --no-run-if-empty docker rm -f
docker login -u "$1" -p "$2" ibmcb-docker-local.artifactory.swg-devops.com
docker pull ibmcb-docker-local.artifactory.swg-devops.com/core-ui-test-automation
docker run --rm -m 16g --oom-kill-disable -e "NAMESPACE=$namespace" -e SLACK_URL=$5 -e PASSWORD=$6 --name core-ui-test-automation ibmcb-docker-local.artifactory.swg-devops.com/core-ui-test-automation

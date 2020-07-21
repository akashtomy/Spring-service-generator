#!/bin/bash

display_help() {
    echo "Usage: $0 [option...]" >&2
    echo "Welcome to the CLP Service Generator, for all your service generation needs."
    echo "Running this command with no parameters will result in the default behavior and generate a service"
    echo
    echo "   -h, --help              Show help"
    echo "   -s, --starters          This will generate a starter"
    echo "   -r, --refresh           This will refresh the service generated"
    echo "                           (Only needed if the CLP Service generator has changed"
    echo
    # echo some stuff here for the -a or --add-options
    exit 1
}

removeImage() {
    docker image rm -f clp-generator-base
}

function checkBase {
    if docker image ls | grep clp-generator-base; then
        echo "Found clp-generator-base docker image."
    else
        echo "Building clp-generator-base image for first use. Please wait."
        docker build -t clp-generator-base -f ./docker/Dockerfile .
    fi
}


case "$1" in
  -h | --help)
      display_help
      exit 0
      ;;
  -s | --starters)
      checkBase
      echo "Generating Service Starter"
      docker run -it --rm -v "$(pwd)/../:/home/yeoman/workspace" clp-generator-base yo createService:starter
      ;;
  -r | --refresh)
      removeImage
      checkBase
       ;;
  *)  # No more options
      checkBase
      echo "Generating Service"
      docker run -it --rm -v "$(pwd)/../:/home/yeoman/workspace" clp-generator-base yo createService
      ;;
esac
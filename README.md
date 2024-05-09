# code-2-present
Generate PPTX presentation from code


## Setup ubuntu dev env 

```
curl -s "https://get.sdkman.io" | bash

source "/home/ubuntu/.sdkman/bin/sdkman-init.sh"
sdk install gradle

sdk install java 11.0.22-tem

gradle init


```

## knowledge and links

* https://www.baeldung.com/apache-poi-slideshow
* https://poi.apache.org/components/slideshow/xslf-cookbook.html
* https://github.com/apache/poi/tree/trunk/poi-examples

## Building and running 

```
./gradlew build 


# generate scripts and install package
./gradlew installDist

# Running
code2present/build/install/code2present/bin/code2present


```

## Example presentation

Inside demo subdirectory you can find the example presentation. 
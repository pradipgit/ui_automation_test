FROM ubuntu:16.04

RUN apt-get update \
    && apt-get install \
    && apt-get -qqy install \
    default-jre \
    sudo \
    curl \
    wget \
    git

RUN sudo apt-get install nano

RUN apt-get -y install software-properties-common

RUN sudo apt-get update

RUN sudo apt-get -y install openjdk-8-jdk
RUN java -version
RUN update-alternatives --config java
RUN update-alternatives --list java
RUN echo "$(which java)"
RUN sudo which java
RUN export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64/jre/bin/java
RUN echo $JAVA_HOME
RUN export PATH=$PATH:$JAVA_HOME/bin
RUN echo $PATH

RUN sudo apt update
RUN sudo apt -y install maven
RUN mvn -version

# Install required packages
RUN apt-get -qqy install \
  libxpm4 \
  libxrender1 \
  libgtk2.0-0 \
  libnss3 \
  libgconf-2-4 \
  libappindicator1 \
  fonts-liberation \
  gtk2-engines-pixbuf \
  xfonts-cyrillic \
  xfonts-100dpi \
  xfonts-75dpi \
  xfonts-base \
  xfonts-scalable \
  imagemagick \
  x11-apps \
  libxss1 \
  xdg-utils \
  xvfb

# Install Goodle Chrome
#RUN wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb \
#RUN wget https://github.com/webnicer/chrome-downloads/raw/master/x64.deb/google-chrome-stable_80.0.3987.87-1_amd64.deb \
#  && sudo dpkg -i google-chrome-stable_80.0.3987.87-1_amd64.deb \
#  && apt-get clean \
#  && rm -rf /var/lib/apt/lists/* \
#  && rm google-chrome-stable_80.0.3987.87-1_amd64.deb

RUN wget https://github.com/mozilla/geckodriver/releases/download/v0.19.1/geckodriver-v0.19.1-linux64.tar.gz
RUN tar -xvf geckodriver-v0.19.1-linux64.tar.gz
RUN sudo mv geckodriver /usr/local/bin/
RUN sudo apt -y install firefox
RUN sudo add-apt-repository ppa:ubuntu-mozilla-daily/firefox-aurora
RUN sudo apt update && sudo apt -y install firefox
RUN firefox --version

#RUN Xvfb :10 -ac
RUN export DISPLAY=:10


WORKDIR /core-ui-test-automation
COPY . /core-ui-test-automation/
RUN chmod -Rf 777 .
#
#RUN export CHROME_BIN=chromium-browser \
#    && export DISPLAY=:99.0

RUN ls -al
RUN pwd

# Define environment variable
ENV DOCKER_ENV=1
# Container entry point

CMD ["/bin/bash", "selenium.sh"]
#CMD ping google.com
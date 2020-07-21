FROM fedora:29

RUN dnf install nodejs -y

RUN npm install --global yo@3.1.0

# yeoman cannot run as root
RUN useradd yeoman

# Copy directory files to container
COPY . /home/yeoman/clp-service-generator

WORKDIR /home/yeoman/workspace

# as yeoman user, npm install and npm link
RUN su yeoman && cd /home/yeoman/clp-service-generator && npm install && npm link

USER yeoman
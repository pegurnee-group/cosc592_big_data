#!/bin/bash
IN="in"
FILTERED="filtered_out"
CREDENTIALS="credentials"
if (( $EUID != 0 )); then
    echo "Please run as root"
    exit
fi
which python3 > /dev/null 2>&1  #check if python3 installed
if [ $? -eq 1 ]
then
    echo "Python3 is not installed, attempting installation"
    echo "Detecting platform"
    python -mplatform | grep -qi Ubuntu && sudo apt-get install python3

    python -mplatform | grep -qi CentOS && yum install -y yum-utils && yum-builddep python && curl -O https://www.python.org/ftp/python/3.5.0/Python-3.5.0.tgz   && tar xf Python-3.5.0.tgz && cd Python-3.5.0 && ./configure && make && make install && ln -s /usr/local/bin/python3 /usr/bin/python3

    python -mplatform | grep -qi Darwin # if OSX
    if [ $? -eq 0 ]
    then
      brew --version > /dev/null 2>&1
      if [ $? -eq 1 ]
      then
        echo "Homebrew not installed"
      else
        brew install python3
      fi


    fi
fi
python3 -c "import setuptools" # check if pip3 is installed
if [ $? -eq 0 ] #true
then
    if [ ! -e /usr/bin/pip3 ]; # if symlink doesn't exist, create one
      then
        sudo ln -s /usr/local/bin/pip3 /usr/bin/pip3
      fi
    echo "Installing PyGithub and GitPython"
    pip3 install --upgrade pip
    pip3 install PyGithub
    pip3 install GitPython
else
    echo "Pip is not installed!"
    echo "Attempting to install Pip"
    easy_install3 pip
    if [ $? -eq 1 ] #failed
    then
      echo "Install failed, try running as root or with 'sudo'"
    else
      echo "Pip was successfully installed, retrying PyGithub and GitPython installation"
      sudo ln -s /usr/local/bin/pip3 /usr/bin/pip3
      pip3 install PyGithub
      pip3 install GitPython
    fi
fi
echo "Updating and upgrading system"
python -mplatform | grep -qi CentOS
if [ $? -eq 0 ]
then
  sudo yum -y upgrade
fi
python -mplatform | grep -qi Darwin
if [ $? -eq 0 ]
then
  su $(logname) -c "brew update"
fi
python -mplatform | grep -qi Ubuntu
if [ $? -eq 0 ]
then
  sudo apt-get -y install update && sudo apt-get -y upgrade
fi
#create folders
if [ ! -f "$CREDENTIALS" ]; then
  echo '{"username":"","password":""}' >  "$CREDENTIALS"
fi
if [ ! -d "$IN" ]; then
  mkdir "$IN"
fi
if [ ! -d "$FILTERED" ]; then
  mkdir "$FILTERED"
fi
sudo service hue stop
sudo service hue start

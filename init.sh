#!/bin/bash
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

    python -mplatform | grep -qi CentOS && yum install yum-utils && yum-builddep python && curl -O https://www.python.org/ftp/python/3.5.0/Python-3.5.0.tgz   && tar xf Python-3.5.0.tgz && cd Python-3.5.0 && ./configure && make && make install && ln -s /usr/local/bin/python3 /usr/bin/python3

    python -mplatform | grep -qi Darwin && brew install python3
    if [ $? -eq 0 ]
    then
      echo "Python3 installed"
    fi
fi
python3 -c "import setuptools" # check if pip3 is installed
if [ $? -eq 0 ] #true
then
    ln -s /usr/local/bin/pip3 /usr/bin/pip3 #temporary, find better way
    echo "Installing PyGithub and GitPython"
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
      ln -s /usr/local/bin/pip3 /usr/bin/pip3 
      pip3 install PyGithub
      pip3 install GitPython
      echo "Updating and upgrading system"
    fi
fi

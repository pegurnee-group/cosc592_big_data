#!/bin/bash
which python3 > /dev/null 2>&1 #check if python3 installed
if [ $? -eq 1 ]
then
    echo "Python3 is not installed, attempting installation, if failed try running as sudoer"
    echo "Detecting platform"
    python -mplatform | grep -qi Ubuntu && sudo apt-get install python3
    python -mplatform | grep -qi CentOS && sudo yum install python3
    python -mplatform | grep -qi Darwin && brew install python3
    if [ $? -eq 0 ]
    then
      echo "Python3 installed"
    fi
fi
python3 -c "import setuptools" # check if pip3 is installed
if [ $? -eq 0 ] #true
then
    echo "Installing PyGithub and GitPython"
    pip3 install PyGithub
    pip3 install GitPython
else
    echo "Pip is not installed!"
    echo "Attempting to install Pip"
    easy_install pip3
    if [ $? -eq 1 ] #failed
    then
      echo "Install failed, try running as root or with 'sudo'"
    else
      echo "Pip was successfully installed, retrying PyGithub and GitPython installation"
      pip3 install PyGithub
      pip3 install GitPython
      echo "Updating and upgrading system"
    fi
fi

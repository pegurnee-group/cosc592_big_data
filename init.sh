which python3 #check if python3 installed
if [ $? -eq 1 ]
then
    echo "Python3 is not installed, please install Python3 and run script again"
    exit 1
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
    fi
fi

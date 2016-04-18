#!/bin/bash

python3 get_repos.py -s$1
python3 filter_useful.py
python3 clone_repos.py

import json, sys

from getopt import getopt
from github import Github

optstring = 'f:s:n:'

opts, args = getopt(sys.argv[1:], optstring)

credentials_file  = 'credentials'
start_value       = 0
download_number   = 3000

for o,v in opts:
  if o == '-f':
    credentials_file = v
  elif o == '-s':
    start_value = int(v)
  elif o == '-n':
    download_number = int(v)

with open(credentials_file) as fp:
  creds = json.load(fp)

g = Github(creds['username'], creds['password'])

with open('in/bigfile.json', 'w') as outf:
  counter = start_value
  increment = 25
  LIMIT = counter + download_number
  while counter < LIMIT:
    for repo in g.get_repos()[counter : counter + increment]:
      #prints public repos: id, language, url
      try:
        outstr = "{}\n".format(json.dumps({ "id":repo.id, "language":repo.language, "url":repo.html_url }))
        outf.write(outstr)
      except Exception as e:
        pass

    outf.flush()
    counter += increment
    print("first {0} repo information obtained".format(counter))

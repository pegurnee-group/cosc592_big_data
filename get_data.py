import json, sys, getopt, datetime, os, shutil

from github import Github

optstring = 'f:s:n:'

opts, args = getopt.getopt(sys.argv[1:], optstring)

credentials_file  = 'credentials'
start_value       = 0
download_number   = 3000
store_file        = 'in/bigfile.json'
archive_file      = 'in/all_repos.json'

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

if os.path.isfile(store_file):
  shutil.copyfile(store_file, '{}.bak'.format(store_file))

with open(store_file, 'w') as outf:
  counter = start_value
  increment = 25
  LIMIT = counter + download_number
  while counter < LIMIT:
    try:
      for repo in g.get_repos(counter)[: increment]:
        if repo.fork:
          LIMIT += 1
          continue
        #prints public repos: id, language, url
        try:
          outstr = "{}\n".format(json.dumps({ "id":repo.id, "language":repo.language, "url":repo.html_url }))
          outf.write(outstr)
        except Exception as e:
          pass
    except Exception as e:
      outf.flush()
      with open('error.log', 'a') as log:
        log.write('Rate limit reached, last repo of note was {}'.format(repo.id))
        log.write('Limit expires: {}'.format(datetime.datetime.fromtimestamp(g.rate_limiting_resettime)))
      print('Rate limit reached, last repo of note was {}'.format(counter))
      print('Limit expires: {}'.format(datetime.datetime.fromtimestamp(g.rate_limiting_resettime)))
      break

    outf.flush()
    counter += increment
    print("first {0} repo information obtained".format(counter))

if archive_file:
  with open(store_file) as newf, open(archive_file, 'a') as archf:
    archf.write(newf.read())

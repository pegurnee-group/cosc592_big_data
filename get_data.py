import json
from github import Github

with open('credentials') as fp:
  creds = json.load(fp)

g = Github(creds['username'], creds['password'])

for repo in g.get_user().get_repos():
    print (repo.name)

for repo in g.get_repos():
  print("{}: {}".format(repo.id, repo.full_name))

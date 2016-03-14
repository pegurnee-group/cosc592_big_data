import json
from github import Github

with open('credentials') as fp:
  creds = json.load(fp)

g = Github(creds['username'], creds['password'])

for repo in g.get_user().get_repos():
    print (repo.name) #prints all of my repos

for repo in g.get_repos():
  #prints public repos, id, language, size, name
  print("{}: {} {} \n\t{}".format(repo.id, repo.language, repo.size, repo.full_name))

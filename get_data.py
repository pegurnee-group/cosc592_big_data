import json

from github import Github

with open('credentials') as fp:
  creds = json.load(fp)

g = Github(creds['username'], creds['password'])

for repo in g.get_user().get_repos():
    print (repo.name) #prints all of my repos

with open('bigfile.txt', 'w') as outf:
  counter = 0
  increment = 25
  LIMIT = 500
  while counter < LIMIT:
    for repo in g.get_repos()[counter : counter + increment]:
      #prints public repos, id, language, size, name
      # print("{}: {} {} \n\t{}".format(repo.id, repo.language, repo.size, repo.html_url))
      outf.write("{}\n".format(json.dumps({ "id":repo.id, "language":repo.language, "url":repo.html_url })))
    outf.flush()
    counter += increment
    print("first {} repos downloaded".format(counter))

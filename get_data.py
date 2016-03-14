import json

from github import Github

with open('credentials') as fp:
  creds = json.load(fp)

g = Github(creds['username'], creds['password'])

for repo in g.get_user().get_repos():
    print (repo.name) #prints all of my repos

with open('bigfile.txt', 'w') as outf:
  for repo in g.get_repos()[:150]:
    #prints public repos, id, language, size, name
    # print("{}: {} {} \n\t{}".format(repo.id, repo.language, repo.size, repo.html_url))
    print(repo.id)
    outf.write("{}, {}, {}\n".format(repo.id, repo.language, repo.html_url))

print("first 150 repos downloaded")

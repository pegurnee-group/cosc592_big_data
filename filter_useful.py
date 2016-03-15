import json

repos = {}
with open('bigfile.json') as fp:
  for data in fp:
    jsdata = json.loads(data)
    if not repos[jsdata['language']]:
      repos[jsdata['language']] = []

    repos[jsdata['language']].append(jsdata)

for key in repos:
  with open("{}.json") as fp:
    for repo in repos[key]:
      fp.write(repo)

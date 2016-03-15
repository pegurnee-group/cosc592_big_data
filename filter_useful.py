import json

repos = {}
with open('bigfile.json') as fp:
  for data in fp:
    jsdata = json.loads(data)
    if not jsdata['language'] in repos.keys():
      repos[jsdata['language']] = []

    repos[jsdata['language']].append(jsdata)

for key in repos:
  with open("{}.json".format(key), 'w') as fp:
    for repo in repos[key]:
      fp.write("{}\n".format(repo))

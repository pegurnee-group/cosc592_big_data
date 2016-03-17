import json
from git import Repo

with open('filtered_out/PHP.json') as fp:
  for jsline in fp:
    repo = json.loads(jsline)
    the_repo = Repo.clone_from('{}.git'.format(repo.url), 'code/{}'.format(repo.language))

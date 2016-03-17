import json
from git import Repo

with open('filtered_out/PHP.json') as fp:
  for jsline in fp:
    repo = json.loads(jsline)
    src = '{}.git'.format(repo['url'])
    dest = 'code/{}/{}'.format(repo['language'], repo['id'])
    print('cloned: '.format(src))
    the_repo = Repo.clone_from(src, dest)

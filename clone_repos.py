import json
from git import Repo

languages = {'PHP', 'C', 'C#', 'C++', 'Java', 'JavaScript', 'Objective-C', 'PHP', 'Python', 'Ruby'}
for language in languages:
  with open('filtered_out/{}.json'.format(language)) as fp:
    for jsline in fp:
      repo = json.loads(jsline)
      src = '{}.git'.format(repo['url'])
      dest = 'code/{}/{}'.format(repo['language'], repo['id'])
      print('cloned: {}'.format(src))

      try:
        the_repo = Repo.clone_from(src, dest)
      except Exception as e:
        pass

print('cloning finished')

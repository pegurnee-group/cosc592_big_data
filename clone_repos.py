#!/usr/bin/env python3

import json
from git import Repo

#languages = {'PHP', 'C', 'C#', 'C++', 'CSS', 'HTML', 'Java', 'JavaScript', 'Objective-C', 'PHP', 'Python', 'Ruby', 'Rust'}
#only worrying about java right now
languages = { 'Java' }
for language in languages:
  with open('filtered_out/{}.json'.format(language)) as fp:
    num_repos = 0
    for jsline in fp:
      repo = json.loads(jsline)
      src = '{}.git'.format(repo['url'])
      dest = 'code/{}/{}'.format(repo['language'], repo['id'])

      try:
        the_repo = Repo.clone_from(src, dest)
      except Exception as e:
        continue

      num_repos += 1

    print('cloned all {} {} repos'.format(num_repos, language))

print('cloning finished')

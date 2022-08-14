# prisma-task


A service with 2 endpoits 
1) GET /api/v1/similar?word=stressed
Returns all words in the dictionary that have the same permutation as the word "stressed". The word in the query should not be returned.

2) GET /api/v1/stats
Return general statistics about the program:
Total number of words in the dictionary
Total number of requests (not including "stats" requests)
Average time for request handling in nanoseconds (not including "stats" requests)


The Algorithim used to solve the issue was counter sort
i choose this because the english alphabet is 26 letters 

The service does loop the dictionary provided line by line
for each line it applies the counter sort which has a runtime complixty of O(n) 
checks an internal map if the sorted string is containted as a key o(1)
  if yes: the value of the key is a list that the original word gets added to
  if not: a new list is created containing the orginal word and gets assigned to the sorted key
after the loop is done we get a map for each sorted key and all premutations from the dictionary provided which result in runtime coplixty of o(n^2)
please note that this happens only on stratup , this functionality does not run again durring ms life


when GET /api/v1/similar gets executed ,
1) the word get validated for wrong input
2) the word get sorted by letters by count sort O(n)
3) the map is checked if contains the key (the sorted word) o(1)
    if yes: the list is returned
    if not: empy list is retuned
the whole action has a runtime of o(n)
after the action is done , a stats calculation is done in  a syncronized manner so multiple requests does not corrupt the calculations


when GET /api/v1/stats gets executed
the stats are returned 





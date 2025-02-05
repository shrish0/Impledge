## Approach

#### A Trie is used to store all words from the list:Each word is inserted character by character into the Trie.Mark the end of each word using a boolean flag (isEndOfWord).

### Sort Words by Length (Descending Order) as it is a fact that the longest word have higher chance of being of compounded word

### Traverses word in Trie character by character. If it reaches the end of a subword and the remaining portion is also found in the Trie, we continue else we remove  that sub word and increase the counter to 1 . and start the iteration again in trie and if thier is a condition that the next word is not in the word we return false as for ex:
```
    ratxcatdog
    rat cat dog are thier but thier is no word x in input file Input_01.txt
```
Iterate Through Words and Find the Longest Compound Words

### How to run ?
run this line in cmd with the directory where folder is

``` 
     javac LongestCompoundWord.java
     java LongestCompoundWord
```

enter input file name you can type Input_01.txt Input_02.txt Input_03.txt
paste your own input file in folder and you can also enter thier name
# Project TenPinBowling

## Basically
To compile this project basically need JAVA with a version major than 8 and maven, to code I use maven3 and java 8.

## Build
Run
```
$ cd TenPinBowling {projectFolder}
$ mvn package
```



## Unit Test
Run
```
$ mvn test
```

## Integration Test
Run
```
$ mvn verify
```
It verifies four cases. An example of 2 players, a perect game, a zero score game and an invalid one.

## GIT
[TenPinBowling](https://github.com/juanmapaniego/TenPinBowling/)

## Whats in source 
In source we can find some basic classes.
#### BowlingFrame
It class is created for manage each section of bowling score.
#### BowlingPlayer 
It class handle the data of each player game.
#### Parser
In it we parse te data file to player data. And throws excecpcion in case some parse error.
#### Printer
It handle the print of result of the list of ṕlayers.



# Expense-Tracker
project from roadmap : https://roadmap.sh/projects/expense-tracker


## BUILD 
```
javac -d out *.java 
```
## RUN

### <ins>Adding a new expense</ins>
```
java -cp out TaskAppli.java add "video games" 500
```
### <ins>Updating and deleting expense</ins>
```
java -cp out TaskAppli.java update 1 "video games" 550
java -cp out TaskAppli.java delete 1
```
### <ins>Marking a task as in progress or done</ins>
```
java -cp out TaskAppli.java mark-in-progress 1
java -cp out TaskAppli.java mark-done 1
```
### <ins>Listing all expense</ins>
```
java -cp out TaskAppli.java summary
```

### <ins>Listing expense by month</ins>
```
java -cp out TaskAppli.java summary 3
```

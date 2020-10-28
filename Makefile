compile-garage:
	javac -d out -sourcepath sources sources/garage/dto/*.java
	javac -d out -sourcepath sources sources/garage/*.java

run-garage:
	java -classpath out garage.Test

compile-m:
	javac -d out --module-source-path modules --module jecl,application,hackerrank

run-m-app: compile-modules
	java --module-path out --module application/app.App

run-m-hackerrank-pangram: compile-m
	java --module-path out --module hackerrank/hackerrank.strings.Pangram

run-m-hackerrank-algs: compile-m
	java --module-path out --module hackerrank/hackerrank.algs.BieberSelfishSquirrels


compile-garage:
	javac -d out -sourcepath sources sources/garage/dto/*.java
	javac -d out -sourcepath sources sources/garage/*.java

run-garage:
	java -classpath out garage.Test

compile-modules:
	javac -d out --module-source-path modules --module jecl,application,hackerrank

run-module-app: compile-modules
	java --module-path out --module application/app.App

run-module-hackerrank: compile-modules
	java --module-path out --module hackerrank/hackerrank.strings.Pangram


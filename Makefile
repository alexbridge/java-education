compile-garage:
	javac -d out -sourcepath sources sources/garage/dto/*.java
	javac -d out -sourcepath sources sources/garage/*.java

run-garage:
	java -classpath out garage.Test

compile-modules:
	javac -d out --module-source-path modules --module jecl,application

run-app-module:
	java --module-path out --module application/app.App


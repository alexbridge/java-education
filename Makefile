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

compile-zoo-feeding:
	javac -d out/modules/zoo.animal.feeding $$(find modules/zoo.animal.feeding -name "*.java")

run-zoo-feeding: compile-zoo-feeding
	java --module-path out/modules --module zoo.animal.feeding/zoo.animal.feeding.Task

jar-zoo-feeding:
	jar -cvf out/jar/modules/zoo.animal.feeding.jar -C modules/zoo.animal.feeding/ .

run-jar-zoo-feeding:
	java -p out/jar/modules -m zoo.animal.feeding/zoo.animal.feeding.Task

desc-zoo-feeding:
	java -p out/jar/modules -d zoo.animal.feeding
	jar -f out/jar/modules/zoo.animal.feeding.jar -d

jdeps-zoo-feeding:
	jdeps -s out/jar/modules/zoo.animal.feeding.jar

list-jar-modules:
	java -p out/jar/modules --list-modules


JAVAC ?= javac
JAVA ?= java
OUT = out
SRC = src/java
JAVA_VERSION ?= $(shell $(JAVAC) -version 2>&1 | sed -E 's/.* ([0-9]+).*/\1/')
PREVIEW_FLAGS ?= --enable-preview --source $(JAVA_VERSION)

.PHONY: clean compile run-all \
	run-value-classes \
	run-class-file-api \
	run-statements-before-super \
	run-module-import-declarations \
	run-primitive-types-in-patterns \
	run-structured-concurrency \
	run-scoped-values \
	run-stream-gatherers \
	run-compact-object-headers \
	run-code-reflection

clean:
	rm -rf $(OUT)

compile:
	mkdir -p $(OUT)
	$(JAVAC) -d $(OUT) $(PREVIEW_FLAGS) $$(find $(SRC)/edu/java28 -name "*.java")

run-value-classes:
	mkdir -p $(OUT)
	$(JAVAC) -d $(OUT) $(SRC)/edu/java28/ValueClasses.java
	$(JAVA) -classpath $(OUT) edu.java28.ValueClasses

run-class-file-api:
	mkdir -p $(OUT)
	$(JAVAC) -d $(OUT) $(SRC)/edu/java28/ClassFileApi.java
	$(JAVA) -classpath $(OUT) edu.java28.ClassFileApi

run-statements-before-super:
	mkdir -p $(OUT)
	$(JAVAC) -d $(OUT) $(SRC)/edu/java28/StatementsBeforeSuper.java
	$(JAVA) -classpath $(OUT) edu.java28.StatementsBeforeSuper

run-module-import-declarations:
	mkdir -p $(OUT)
	$(JAVAC) -d $(OUT) $(SRC)/edu/java28/ModuleImportDeclarations.java
	$(JAVA) -classpath $(OUT) edu.java28.ModuleImportDeclarations

run-primitive-types-in-patterns:
	mkdir -p $(OUT)
	$(JAVAC) -d $(OUT) $(PREVIEW_FLAGS) $(SRC)/edu/java28/PrimitiveTypesInPatterns.java
	$(JAVA) --enable-preview -classpath $(OUT) edu.java28.PrimitiveTypesInPatterns

run-structured-concurrency:
	mkdir -p $(OUT)
	$(JAVAC) -d $(OUT) $(PREVIEW_FLAGS) $(SRC)/edu/java28/StructuredConcurrency.java
	$(JAVA) --enable-preview -classpath $(OUT) edu.java28.StructuredConcurrency

run-scoped-values:
	mkdir -p $(OUT)
	$(JAVAC) -d $(OUT) $(PREVIEW_FLAGS) $(SRC)/edu/java28/ScopedValues.java
	$(JAVA) --enable-preview -classpath $(OUT) edu.java28.ScopedValues

run-stream-gatherers:
	mkdir -p $(OUT)
	$(JAVAC) -d $(OUT) $(SRC)/edu/java28/StreamGatherers.java
	$(JAVA) -classpath $(OUT) edu.java28.StreamGatherers

run-compact-object-headers:
	mkdir -p $(OUT)
	$(JAVAC) -d $(OUT) $(SRC)/edu/java28/CompactObjectHeaders.java
	$(JAVA) -classpath $(OUT) edu.java28.CompactObjectHeaders

run-code-reflection:
	mkdir -p $(OUT)
	$(JAVAC) -d $(OUT) $(SRC)/edu/java28/CodeReflection.java
	$(JAVA) -classpath $(OUT) edu.java28.CodeReflection

run-all: run-value-classes run-class-file-api run-statements-before-super run-module-import-declarations run-primitive-types-in-patterns run-structured-concurrency run-scoped-values run-stream-gatherers run-compact-object-headers run-code-reflection

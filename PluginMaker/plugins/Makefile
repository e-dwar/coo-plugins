.PHONY: clean, run

SOURCES=AddSpaceBetweenLetters.java Alternate.java Reverse.java Main.java

CLASSPATH = ..

CLASSES= ../bin

all: compile run

compile:
	javac -cp $(CLASSPATH) $(SOURCES) -d $(CLASSES)

run:
	java -cp $(CLASSES) Main

clean:
	rm -f -r bin; mkdir bin

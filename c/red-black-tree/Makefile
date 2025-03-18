.PHONY: clean, mrproper
CC=gcc
CFLAGS=-g -Wall

all: main

%.o: %.c
	$(CC) $(CFLAGS) -c -o $@ $<

main: main.o tree.o
	$(CC) $(CFLAGS) -o $@ $+

clean:
	rm -f *.o core.*

mrproper: clean
	rm -f main

valgrind: main
	valgrind -s --leak-check=full ./$<

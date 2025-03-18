.PHONY: all, run, test, clean, valgrind

CC=gcc
CFLAGS=-g -Wall

SRC=src
OBJ=obj
BINDIR=bin
BIN=$(BINDIR)/main
TEST=test
TESTBIN=$(BINDIR)/test

SRCS=$(wildcard $(SRC)/*.c)
OBJS=$(patsubst $(SRC)/%.c, $(OBJ)/%.o, $(SRCS))
OBJSNOMAIN=$(filter-out $(OBJ)/main.o, $(OBJS))
TESTS=$(wildcard $(TEST)/*.c)
TESTBINS=$(patsubst $(TEST)/%.c, $(TESTBIN)/%, $(TESTS))

all: $(BIN)

run: all
	./$(BIN)

$(BIN): $(OBJS) $(BINDIR) 
	$(CC) $(CFLAGS) $(OBJS) -o $@

$(OBJ)/%.o: $(SRC)/%.c $(SRC)/%.h $(OBJ) 
	$(CC) $(CFLAGS) -c $< -o $@

$(OBJ)/%.o: $(SRC)/%.c $(OBJ) 
	$(CC) $(CFLAGS) -c $< -o $@

$(TESTBIN)/%: $(TEST)/%.c $(TESTBIN) 
	$(CC) $(CFLAGS) $< $(OBJSNOMAIN) -o $@ -lcriterion

$(TEST): $(BIN) $(TESTBINS)
	for test in $(TESTBINS) ; do ./$$test ; done

$(BINDIR):
	mkdir $@

$(OBJ):
	mkdir $@

$(TESTBIN):
	mkdir $@

clean:
	rm -rf $(BINDIR)/* $(OBJ)/* $(TESTBIN)/*

valgrind: $(BIN) 
	valgrind -s --leak-check=full ./$<

# Define compiler and flags
JC = javac
JFLAGS = -g

# Define JAR maker
JAR = jar
JARFLAGS = cfe

# Directory for compiled binaries
BIN = bin/

# Define main classes
SERVER_MAIN = DigitalMarketServer
CLIENT_MAIN = DigitalMarketClient

# Name of the jar files
SERVER_JAR = Server.jar
CLIENT_JAR = Client.jar

# Java source files
SOURCES = AccountType.java Admin.java Cart.java Customer.java \
          DigitalMarketClient.java DigitalMarketInterface.java \
          DigitalMarketServer.java Item.java Marketplace.java \
          Member.java AdminFactory.java CustomerFactory.java \
          MemberFactory.java AddItemOperation.java AddUserOperation.java \
          CartOperation.java RemoveItemOperation.java RemoveUserOperation.java \
          RetrieveCartOperation.java UpdateItemOperation.java UserOperation.java

# Java class files
CLASSES = $(SOURCES:.java=.class)

# Default make target
all: compile jars

# Compile the java files
compile:
	mkdir -p $(BIN)
	$(JC) $(JFLAGS) $(SOURCES) -d $(BIN)

# Create JAR files for server and client
jars: compile
	$(JAR) $(JARFLAGS) $(SERVER_JAR) $(SERVER_MAIN) -C $(BIN) .
	$(JAR) $(JARFLAGS) $(CLIENT_JAR) $(CLIENT_MAIN) -C $(BIN) .

# Clean up binaries
clean:
	rm -rf $(BIN) $(SERVER_JAR) $(CLIENT_JAR)

# Run the server
server: $(SERVER_JAR)
	java -jar $(SERVER_JAR)

# Run the client
client: $(CLIENT_JAR)
	java -jar $(CLIENT_JAR)

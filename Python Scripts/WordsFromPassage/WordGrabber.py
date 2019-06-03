#
# Connect to the  server at 'localhost', 10000 send any data,
# the server will respond with the required word codes
# You will find a passage of text in the file backdoor.txt write a script
# which will use that text to build a message using the received word codes.
# Each word code is sent on a new line and contains
# paragraph_number, line_number, word_number
# Send the expected words back to the server to retrieve the flag.
# The server expects all the words in a single transmission.
# The words should have punctuation stripped from them.
# And the words should be separated by newline characters (\n)
#

from array import *
import socket

clientsocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
clientsocket.connect(('localhost', 10000))
clientsocket.send('hello')

data = clientsocket.recv(1024)
print(data)

TwoDArray = []
ParaArray = []

paragraph = 0
lineNum = 0
with open("backdoor.txt", "r") as myfile:
    for line in myfile:
      #print(line[:-1])
      if line == '\n':
        TwoDArray.insert(paragraph, ParaArray)
        paragraph += 1
        lineNum = 0
        ParaArray = []
      else:
      	ParaArray.insert(lineNum, line[:-1].split(' '))
      	lineNum += 1
        
TwoDArray.insert(paragraph, ParaArray)
#print TwoDArray

arr = []
ansStr = ''
data = data[:-1].split('\n')
for line in data:
	arr = line.split(', ')
	para = int(arr[0])
	lin = int(arr[1])
	word = int(arr[2])
	#print "%d & %d & %d" % (para, lin, word)
	ans = TwoDArray[para-1][lin-1][word-1].replace('.', '')
	ans = ans.replace(',', '')
	ans = ans.replace('\"','')
	ansStr += ans + '\n'

print ansStr

clientsocket.send(ansStr)
data = clientsocket.recv(1024)
print(data)
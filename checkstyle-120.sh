#!/usr/bin/bash

# Instructions:
# (1) Put checkstyle-120.sh (this file) and CSC120_style_checks.xml in the same folder as your code 
# (2) In the terminal run $ sh checkstyle-120.sh 
# (3) Style errors will print in the terminal 
# Note: Some style checks are not ones we take points off for, you can ignore those

for c in *.java ; do
	echo "\nStylechecking ${c}:"
	java -jar checkstyle-10.9.3-all.jar -c CSC120_style_checks.xml "${c}"
done
			
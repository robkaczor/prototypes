#!/bin/sh

DEBUG_PORT=5010
SKIP_AUTH="false"
SKIP_SOLR="true"
CMD=`cygpath $JAVA_HOME`/bin/java

while getopts spbtdD: o
do	case "$o" in
    s)  SKIP_SOLR="false"
        ;;
	p)  SKIP_AUTH="true"
		;;
	b)  mvn clean:clean package
		;;
	t)  mvn -DskipTests=false test
	    ;;
	d)	DEBUG=true
	    ;;
	D)  DEBUG=true
	    DEBUG_PORT="$OPTARG"
	    ;;
	esac
done

if [ -n "$DEBUG" ]
then
    CMD="$CMD -DskipAuth=$SKIP_AUTH -Dsolr.skipSave=$SKIP_SOLR -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=$DEBUG_PORT"
fi

JARFILE=`find target -name '*.jar'`
CMD="$CMD -jar $JARFILE"

echo $CMD
eval $CMD


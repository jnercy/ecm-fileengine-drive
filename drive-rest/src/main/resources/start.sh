#!/usr/bin/env bash

TIME_STAMP=`date +%Y%m%d%H%M`
CODE_HOME=/usr/local/software
PROJECTNAME=ecm-drive
cd $CODE_HOME/$PROJECTNAME
pid=`ps -ef |grep $PROJECTNAME |grep -v "grep" |awk '{print $2}'`

if [ -f nohup.out ]; then
    echo  nohup.out already
else
    touch nohup.out
fi

if [ $pid ]; then
    echo "App  is  running  and pid=$pid"
    kill -9 $pid
    echo kill server success!!
    nohup java -Xms1g -Xmx1g -Xmn384m -server -jar $CODE_HOME/$PROJECTNAME/ecm-drive-0.0.1.jar  >nohup.out  2>&1 &
else
  echo $CODE_HOME/$PROJECTNAME/ecm-drive-0.0.1.jar
  nohup java -Xms1g -Xmx1g -Xmn384m -server -jar $CODE_HOME/$PROJECTNAME/ecm-drive-0.0.1.jar  >nohup.out  2>&1 &
fi
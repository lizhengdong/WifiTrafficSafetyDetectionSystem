#!/bin/bash

#service NetworkManager start

wlcard="wlp0s29f7u3"
wirecard="p33p1"
iprouter="192.168.26.1"
logfile="/home/liu/raywifi.log"
runningpath="/tmp/pcapng"
pcapdir="/home/liu/trafficpcapdata"
txtdir="/home/liu/traffictxtdata"

sh /usr/local/raywork/starthostapd.sh $wlcard $wirecard $iprouter $logfile
#sh /usr/local/raywork/gettraffic.sh $runningpath $wlcard
#sh /usr/local/raywork/folderproc.sh $runningpath $logfile $pcapdir $txtdir &
#sh /usr/java/tomcat/bin/startup.sh
#/usr/java/jdk1.7.0_67/bin/java -jar /usr/local/raywork/MySqlInput2.jar &



#!/bin/bash

if [ ! -e $1 ]; then
mkdir $1;
chmod 777 $1;
fi

# deslk="/home/lihui/Desktop/pcapng"
# if [ -e $deslk ]; then
# rm -f $deslk;
# fi
# ln -s $1 $deslk

# tshark -i $2 -a filesize:500 -b files:50 -f '(tcp port 80 and (((ip[2:2] - ((ip[0]&0xf)<<2)) - ((tcp[12]&0xf0)>>2)) != 0)) or (tcp port not 80 and not 443) or (udp and not port 53 and not port 5353 and not port 67 and not port 68)' -w /$1/capture.pcapng &
# tshark -i $2 -a filesize:500 -b files:50 -f '(tcp port 80 and (((ip[2:2] - ((ip[0]&0xf)<<2)) - ((tcp[12]&0xf0)>>2)) != 0)) or (udp and not port 53 and not port 5353 and not port 67 and not port 68)' -w /$1/capture.pcapng &
tshark -i $2 -a filesize:500 -b files:50 -f '(tcp and (((ip[2:2] - ((ip[0]&0xf)<<2)) - ((tcp[12]&0xf0)>>2)) != 0)) or (udp and not port 53 and not port 5353 and not port 67 and not port 68)' -w /$1/capture.pcapng &

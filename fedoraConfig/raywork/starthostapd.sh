#!/bin/sh
#start hostapd
echo ----------------- >> $4
date >> $4
while [ "$(ps -e| grep NetworkManager)" != "" ] ; do
echo stop NetworkManager ... >> $4
service NetworkManager stop
sleep 3
done
date >> $4
echo NetworkManager stopped >> $4
ifconfig $1 up $3 netmask 255.255.255.0
while [ "$(ps -e| grep dhcpd)" == "" ] ; do
echo start dhcpd ... >> $4
dhcpd $1 &
sleep 3
done
date >> $4
echo dhcpd started >> $4
service dnsmasq restart
sysctl net.ipv4.ip_forward=1
iptables --flush
iptables --table nat --flush
iptables --delete-chain
iptables --table nat --delete-chain
iptables -t nat -A POSTROUTING -o $2 -j MASQUERADE
date >> $4
while [ "$(ps -e| grep hostapd)" == "" ] ; do
echo start hostapd ... >> $4
hostapd /etc/hostapd/hostapd.conf &
sleep 3
done
date >> $4
echo hostapd started >> $4

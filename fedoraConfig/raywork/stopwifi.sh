#!/bin/sh
ps -ef|grep java|grep -v grep|awk '{print $2}'|xargs kill -9
ps -ef|grep tshark|grep -v grep|awk '{print $2}'|xargs kill -9
ps -ef|grep hostapd|grep -v grep|awk '{print $2}'|xargs kill -9
ps -ef|grep dhcpd|grep -v grep|awk '{print $2}'|xargs kill -9

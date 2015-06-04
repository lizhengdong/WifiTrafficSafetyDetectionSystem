#A shell to test network status
#Runned every 10 minutes
ip="202.4.130.95"
ping -c 2 $ip
if [ "$?" -ne "0" ]
  then
	echo "network is down at:" >>/usr/local/raywork/testlog.txt
	date >>/usr/local/raywork/testlog.txt
  else
	echo "network is ok at:" >>/usr/local/raywork/testlog.txt
	date >>/usr/local/raywork/testlog.txt
fi

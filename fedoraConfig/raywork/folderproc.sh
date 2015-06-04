
echo pickup start >> $2
while [ "$(ps -e| grep tshark)" != "" ] ; do
sleep 8
ii=`ls -1tr $1| wc -l`
((ii--));
str=`ls -1tr $1 | head -n $ii`
for line in $str ; do
chmod 777 $1/$line
mv $1/$line $3
#tshark -r $3/$line -Tfields -e frame.time -e ip.src -e tcp.srcport -e ip.dst -e tcp.dstport -e eth.dst >> $4/$line.txt
tshark -r $3/$line -t ad -Tfields -e frame.time -e eth.src -e ip.src -e tcp.srcport -e eth.dst -e ip.dst -e tcp.dstport -e ip.proto -e ip.len -e http.host -e http.request.uri >> $4/$line.txt
done 
done
echo pickup stop >> $2





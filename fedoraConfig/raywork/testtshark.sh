desfolder="/home/lihui/Downloads/aftermerge.pcapng"

tshark -r $desfolder -t ad -Tfields -e frame.time -e eth.src -e ip.src -e tcp.srcport -e eth.dst -e ip.dst -e tcp.dstport -e ip.proto -e ip.len -e http.host -e http.request.uri  > /home/lihui/3.txt




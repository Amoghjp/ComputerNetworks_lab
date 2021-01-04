BEGIN{
tcpdropped=0;
udpdropped=0;
tcpcount=0;
udpcount=0;
}
{
if($5=="tcp")
{
tcpcount++;
if($1=="d")
{
tcpdropped++;
}
printf("%s\t%s\n",$5,$11);
}
if($5=="cbr")
{
udpcount++;
if($1=="d")
{
udpdropped++;
}
}
}
END{
printf("The number of tcp packets are = %d\n",tcpcount);
printf("The number of udp packets are = %d\n",udpcount);
printf("The number of tcp packets dropped are = %d\n",tcpdropped);
printf("The number of udp packets dropped are = %d\n",udpdropped);
printf("Packet delivery fraction for TCP = %f\n",(tcpcount-tcpdropped)/tcpcount);
printf("Packet delivery fraction for UDP = %f\n",(udpcount-udpdropped)/udpcount);
}

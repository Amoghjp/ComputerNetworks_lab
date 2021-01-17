set ns [new Simulator]
set tf [open cn3.tr w]
$ns trace-all $tf
set nf [open cn3.nam w]
$ns namtrace-all $nf

proc finish { } {
global ns tf nf
$ns flush-trace
close $tf
close $nf
exec nam cn3.nam &
exec awk -f lab2.awk cn3.tr &
exit 0
}

set s1 [$ns node]
set s2 [$ns node]
set s3 [$ns node]
set s4 [$ns node]
set r1 [$ns node]
set r2 [$ns node]

$s1 label "TCP Source"
$s4 label "TCP Sink"

$s2 label "UDP Source"
$s3 label "UDP Sink"

$ns duplex-link $s1 $r1 1Mb 10ms DropTail
$ns duplex-link $r1 $s2 1Mb 10ms DropTail
$ns duplex-link $r2 $s3 1Mb 10ms DropTail
$ns duplex-link $r2 $s4 1Mb 10ms DropTail
$ns duplex-link $r1 $r2 0.2Mb 10ms DropTail

set tcp [new Agent/TCP]
$ns attach-agent $s1 $tcp
set ftp1 [new Application/FTP]
$ftp1 attach-agent $tcp
set tcpsink [new Agent/TCPSink]
$ns attach-agent $s4 $tcpsink
$ns connect $tcp $tcpsink

set udp [new Agent/UDP]
$ns attach-agent $s2 $udp
set ftp2 [new Application/Traffic/CBR]
$ftp2 attach-agent $udp
set udpsink [new Agent/Null]
$ns attach-agent $s3 $udpsink
$ns connect $udp $udpsink

$ns at 0.1 "$ftp1 start"
$ns at 1.0 "$ftp1 stop"
$ns at 1.1 "$ftp2 start"
$ns at 2.0 "$ftp2 stop"
$ns at 2.5 "finish"
$ns run

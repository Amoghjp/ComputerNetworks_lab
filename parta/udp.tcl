set ns [new Simulator]
set tf [open cn1.tr w]
$ns trace-all $tf
set nf [open cn1.nam w]
$ns namtrace-all $nf
proc finish { } {
global ns tf nf
$ns flush-trace
close $tf
close $nf
exec nam cn1.nam &
exec awk -f cn1awk.awk cn1.tr &
exit 0
}
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]
$n0 label "TCP Source"
$n3 label "TCP Sink"
$ns duplex-link $n0 $n1 80Mb 1ns DropTail
$ns duplex-link $n1 $n2 10Mb 2ns DropTail
$ns duplex-link $n2 $n3 2Mb 3ns DropTail
$ns queue-limit $n1 $n2 50
$ns queue-limit $n2 $n3 5
set udp [new Agent/UDP]
$ns attach-agent $n0 $udp
set ftp [new Application/Traffic/CBR]
$ftp attach-agent $udp
set udpsink [new Agent/Null]
$ns attach-agent $n3 $udpsink
$ns connect $udp $udpsink
$ns at 0.2 "$ftp start"
$ns at 2.5 "$ftp stop"
$ns at 3.0 "finish"
$ns run

set ns [new Simulator]
set tf [open cn2.tr w]
set nf [open cn2.nam w]
$ns namtrace-all $nf

proc finish { } {
global ns tf nf
$ns flush-trace
close $nf
close $tf
exec nam cn2.nam &
exit 0
}

set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]
set n4 [$ns node]

$ns duplex-link $n0 $n1 1Mb 20ms FQ
$ns duplex-link $n1 $n2 1Mb 20ms FQ
$ns duplex-link $n2 $n3 1Mb 20ms FQ
$ns duplex-link $n3 $n4 1Mb 20ms FQ
$ns duplex-link $n4 $n0 1Mb 20ms FQ

set tcp0 [new Agent/TCP]
$tcp0 set class_ 1
$ns attach-agent $n0 $tcp0
set sink0 [new Agent/TCPSink]
$ns attach-agent $n3 $sink0
$ns connect $tcp0 $sink0
set cbr0 [new Application/Traffic/CBR]
$cbr0 set packetSize_ 500
$cbr0 set interval_ 0.01
$cbr0 attach-agent $tcp0
$ns at 0.5 "$cbr0 start"
$ns at 4.5 "$cbr0 stop"
$ns at 5.0 "finish"
$ns run

BEGIN{
sum=0;
}
{
if($1=="r" && $4=="5")
{
printf("%f\t%f\n", sum/$2,$2);
sum=sum+$6;
}
}
END{
}

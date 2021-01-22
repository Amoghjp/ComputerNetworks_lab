import java.util.*;
import java.io.*;
class crc
{
    static int[] Checksum(String gen[],int data[],int x)
    {
        int temp;int m=0;
       for(int i=0;i<x-1;i++)
       {
           m=i;
           temp=data[i];
           for(int k=1;k<gen.length;k++)
           {
             
                 data[m]=data[m]^(temp*Integer.parseInt(gen[k]));
                    m++;
           }
       }
       return data;

    }
    public static void main(String []args)
    {
      Scanner s=new Scanner(System.in);
      System.out.println("Enter the generator polynomial");
      String gen[]=s.nextLine().split("");
      System.out.println("Enter the dataword");
      String d[]=s.nextLine().split("");
      int l=d.length+gen.length-3;
      int data[]=new int[l];
      int data1[]=new int[l];
      int j=0;
      for(int i=1;i<d.length;i++)
      {
            data[j]=Integer.parseInt(d[i]);
             data1[j]=Integer.parseInt(d[i]);
            j++;
      }
       System.out.println();
       System.out.print("Generator:");
        for(int k=1;k<gen.length;k++)
           {
                System.out.print(gen[k]);
           }
        System.out.println();
        System.out.print("Codeword:");
        for(int k=0;k<l;k++)
           {
                System.out.print(data[k]);
           }
        System.out.println();
        int x=d.length;
        data=Checksum(gen,data,x);
        
         System.out.print("Checksum:");
       int mp=gen.length-2;
       for(int p=mp;p<l;p++)
       {
            System.out.print(data[p]);
       }     
       System.out.println();
       for(int i=mp;i<l;i++)
       {
            data1[i]=data[i];
       }
        System.out.print("Sent codeword");
        String y="";
        for(int i=0;i<l;i++)
       {
            y=y+""+String.valueOf(data1[i]);
       }
	   System.out.println(y);
       System.out.println("Enter the recieved codeword");
       String cod[]=s.nextLine().split("");
      int xx=cod.length-1;
      if(xx!=l)
       {
            System.out.println("Length of received word and codeword doesnot match");
            return ;
       }
      int code[]=new int[cod.length-1];j=0;
      for(int i=1;i<cod.length;i++)
      {
            code[j]=Integer.parseInt(cod[i]);
            j++;
      }
       data1=Checksum(gen,code,x);
       System.out.print("Checksum:");
       String z="";
       int found=0;
       for(int p=mp;p<l;p++)
       {    
            if(data1[p]==1 && found==0)
                found=1;
            z=z+""+String.valueOf(data1[p]);
       }
    	System.out.println(z);
        if(found==1)
            System.out.println("Codeword received not properly");  
        else
             System.out.println("Codeword received properly");   
    }
} 

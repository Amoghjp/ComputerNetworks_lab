import java.util.*;
class dvmrp1{
	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		System.out.println("enter the no of vertices");
		int n=sc.nextInt();

		System.out.println("enter the adjacency matrix");
		int a[][]=new int[n+1][n+1];
		for(int i=1;i<=n;i++){
			for(int j=1;j<=n;j++){
				a[i][j]=sc.nextInt();
				if(i==j){
					a[i][j]=0;
					continue;
				}
				if(a[i][j]==0)
					a[i][j]=999;
			}
		}

		int d[]=new int[n+1];

		System.out.println("enter the source node");
		int s=sc.nextInt();

		for(int i=1;i<=n;i++){
			d[i]=999;
		}

		d[s]=0;
		for(int k=1;k<=n-1;k++){
			for(int i=1;i<=n;i++){
				for(int j=1;j<=n;j++){
					if(a[i][j]!=999){

						if(d[j]>(d[i]+a[i][j]))
							d[j]=d[i]+a[i][j];
					}
				}
			}
		}

		for(int i=1;i<=n;i++){
			for(int j=1;j<=n;j++){
				if(a[i][j]!=999){
					if(d[j]>(d[i]+a[i][j])){
						System.out.println("its contains -ve edge cycle");
						return;
					}
				}
			}
		}
		

        for(int i=1;i<=n;i++)
        {
            System.out.println("Distance of source " + s + " to "+ i + " is " + d[i]);
        }
	}
}

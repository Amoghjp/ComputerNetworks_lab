import java.io.*;
import java.net.*;
import java.util.*;
class client1{
	public static void main(String[] args) {
		try{
			Scanner sc=new Scanner(System.in);
			Socket sock=new Socket("localhost",3000);

			System.out.println("enter the filename");
			String fname=sc.nextLine();

			OutputStream os=sock.getOutputStream();
			PrintWriter pw=new PrintWriter(os,true);
			pw.println(fname);

			InputStream is=sock.getInputStream();
			BufferedReader br =new BufferedReader(new InputStreamReader(is));

			String str="";
			while((str=br.readLine())!=null){
				System.out.println(str);
			}

			is.close();
			os.close();
			sock.close();
		}
		catch(Exception e){

		}

	}
}

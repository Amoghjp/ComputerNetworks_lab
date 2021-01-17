import java.io.*;
import java.net.*;
class server1{
	public static void main(String[] args) {
		try{
			ServerSocket ss=new ServerSocket(3000);
			System.out.println("system ready for connection");

			Socket sock=ss.accept();
			System.out.println("connection established");

			InputStream is=sock.getInputStream();
			BufferedReader br=new BufferedReader(new InputStreamReader(is));

			String fname=br.readLine();

			System.out.println("the filename is "+fname);

			BufferedReader br1=new BufferedReader(new FileReader(fname));

			OutputStream os=sock.getOutputStream();
			PrintWriter pw=new PrintWriter(os,true);

			String str="";
			while((str=br1.readLine())!=null){
				pw.println(str);
			}

			is.close();
			os.close();
			sock.close();
			ss.close();

			System.out.println("connection terminated");
		}
		catch(Exception e){

		}
		
	}
}

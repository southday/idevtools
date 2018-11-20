package com.company;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class myHttpServer {
    public static void main(String[] args) throws IOException{
        int port=80;
        if(args.length>0)
                port=Integer.valueOf(args[0]);
        new myHttpServer().start(port);
    }

    /**
     * 创建服务器
     * @param port
     * @throws IOException
     */
    public void start(int port) throws IOException{
        ServerSocket server=new ServerSocket(port);
        System.out.println("创建服务器："+port+";");
        while (true){
            Socket client=server.accept();
            ServerThread serverThread=new ServerThread(client);
            serverThread.start;
        }
    }

    /**
     * 内部类，服务器线程
     */
    class ServerThread extends Thread{
        Socket client;
        public ServerThread(Socket client){
            this.client=client;
        }
        public byte[] getFileByte(String filename) throws IOException{
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            File file=new File(filename);
            FileInputStream fis=new FileInputStream(file);
            byte[] b=new byte[1000];
            int read;
            while((read=fis.read(b))!=-1){
                baos.write(b,0,read);
            }
            fis.close();
            baos.close();
            return baos.toByteArray();
        }

        private String getQueryResourse(String queryurl){
            String queryresourse=null;
            int index=queryurl.indexOf('?');
            if(index!=-1)
                queryresourse=queryurl.substring(0,index);
            else
                queryresourse=queryurl;

            index=queryresourse.lastIndexOf('/');
            if(index+1==queryresourse.length())
                queryresourse+="index.html";
            else{
                String filename=queryresourse.substring(index+1);
                if(!filename.contains("."))
                    queryresourse+=".html";
            }
            return queryresourse;
        }

        private String getHead(String queryresourse){
            String filename="";
            int index=queryresourse.lastIndexOf("/");
            filename=queryresourse.substring(index+1);
            String[] filetypes=filename.split("\\.");
            String filetype=filetypes[filetypes.length-1];
            if(filetype.equals("html"))
                return "HTTP/1.0200OK\n"+"Content-Type:text/html\n" + "Server:myserver\n" + "\n";
            else if(filetype.equals("jpg")||filetype.equals("gif")||filetype.equals("png"))
                return "HTTP/1.0200OK\n"+"Content-Type:image/jpeg\n" + "Server:myserver\n" + "\n";
            else
                return null;
        }
        @Override
        public void run(){
            InputStream is;
            try{
                is-client.getInputStream();
                BufferedReader br=new BufferedReader(new InputStreamReader(is));
                int readint;
                char c;
                byte[] data=null;
                String cmd="";
                String queryurl="";
                int state=0;
                String queryresourse;
                String head;
                while(true){
                    readint=is.read();
                    readint=is.read();
                    c=(char)readint;
                    boolean space=Character.isWhitespace(readint);
                    switch (state){
                        case 0:
                            if(space) continue;
                            state=1;
                        case 1:
                            if(space){
                                state=2;
                                continue;
                            }
                            cmd+=c;
                            continue;
                        case 2:
                            if(space) continue;
                            state=3;
                        case 3:
                            if(space) break;
                            queryurl+=c;
                            continue;
                    }
                    break;
                }
                queryresourse=getQueryResourse(queryurl);
                head=getHead(queryresourse);
                while(true){
                    try{

                    }
                }
            }
        }
    }
}

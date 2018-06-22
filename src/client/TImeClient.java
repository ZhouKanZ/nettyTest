package client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class TImeClient {

    public static void main(String[] args) throws Exception {

        String host = "192.168.1.128";
        int port = 6789;
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        // 默认编码格式UTF-8
        // System.out.println(Charset.defaultCharset());

        try{
            Bootstrap b = new Bootstrap();
            b.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new TImeClientHandler());
                        }
                    });

            conn(host, port, b);
            // wait until the conn is closed,
//            f.channel().closeFuture();

        }catch(Exception e){
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }

    public static void conn(String host, int port, Bootstrap b) throws InterruptedException {
        ChannelFuture f =  b.connect(host,port).sync();
    }

}

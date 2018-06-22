package server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class DiscardServer {

    private int port;

    public DiscardServer(int port){
        this.port = port;
    }

    public void run() throws Exception{

        EventLoopGroup bossGroup = new NioEventLoopGroup(); // 1
        EventLoopGroup workerGroup = new NioEventLoopGroup(); // 1

        try{
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new TimeServerHandler());
                    }
                })
                // option
                .option(ChannelOption.SO_BACKLOG,128)
                // childOption
                .childOption(ChannelOption.SO_KEEPALIVE,true);

        // bind and start to accept incoming connections
        ChannelFuture f = bootstrap.bind(port).sync();

        // wait until the server socket is closed;
        // in this example , this does not happen , but you can do that to gracefully
        // shut down you server.
        f.channel().closeFuture().sync();
        }finally {

            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();

        }

    }


    public static void main(String[] args) throws Exception {

        int port;
        if (args.length > 0){
            port = Integer.parseInt(args[0]);
        }else {
            port = 8080;
        }

        new DiscardServer(port).run();

    }

}

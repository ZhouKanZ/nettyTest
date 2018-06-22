package server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * handles a server-side channel
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // Discard the received data silently
//        super.channelRead(ctx, msg);
        ((ByteBuf)msg).release();
        ByteBuf in = (ByteBuf) msg;
        try{
            while(in.isReadable()){
                System.out.print((char) in.readByte());
                System.out.flush();
            }
        }finally {
            ReferenceCountUtil.release(msg);
        }
        // echo server
        ctx.write(msg);
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        // close the connection when an exeption is raised;
        cause.printStackTrace();
        ctx.close();
    }
//   DiscardServerHandler extends ChannelInboundHandlerAdapter, which is an implementation of
//   ChannelInboundHandler. ChannelInboundHandler provides various event handler methods that you
//   can override. For now, it is just enough to extend ChannelInboundHandlerAdapter
}

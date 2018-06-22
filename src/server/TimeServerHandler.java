package server;

import bean.UnixTime;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import util.LogUtil;

import java.nio.charset.Charset;
import java.util.Arrays;

/**
 *  server handler
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {

    private static final String TAG = "TimeServerHandler";

    public TimeServerHandler() {
        super();
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        LogUtil.d(TAG,"channelRegistered");
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
        LogUtil.d(TAG,"channelUnregistered");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        LogUtil.d(TAG,"channelInactive");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
        LogUtil.d(TAG,"channelReadComplete");
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
        LogUtil.d(TAG,"userEventTriggered");
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        super.channelWritabilityChanged(ctx);
        LogUtil.d(TAG,"channelWritabilityChanged");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        super.channelRead(ctx, msg);
        LogUtil.d(TAG,"channelRead");
        ByteBuf m = (ByteBuf) msg;
        byte[] req = new byte[m.readableBytes()];
        m.readBytes(req);
        String str = new String(req);
        System.out.println(str);
//        m.release();
    }

    /**
     * @desc be invoked when a connection is established and ready to generate a traffic.
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LogUtil.d(TAG,"channelActive");
        ByteBuf byteBuf = ctx.alloc().buffer();
        byteBuf.writeCharSequence("please send register info",Charset.defaultCharset());
        ChannelFuture f = ctx.writeAndFlush(byteBuf);
        f.addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LogUtil.d(TAG,"exceptionCaught");
        cause.printStackTrace();
        ctx.close();
    }
}

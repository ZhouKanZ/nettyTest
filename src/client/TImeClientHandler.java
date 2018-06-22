package client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import util.DataFormUtil;
import util.LogUtil;

public class TImeClientHandler extends ChannelInboundHandlerAdapter {

    private static final String TAG = "TImeClientHandler";

    // 缓冲区
    private ByteBuf buf;

    public TImeClientHandler() {
        super();
    }

    /**
     *  注册
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        LogUtil.d(TAG,"channelRegistered");
    }

    /**
     *  解除注册
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
        LogUtil.d(TAG,"channelUnregistered");
    }

    /**
     *  处于inactive状态
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        LogUtil.d(TAG,"channelInactive");
    }

    /**
     *  channel 读取完成
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
        LogUtil.d(TAG,"channelReadComplete");
    }

    /**
     *
     * @param ctx
     * @param evt
     * @throws Exception
     */
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
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        super.channelActive(ctx);
        System.out.println("active invoked");

        String msg = "{ \"message_type\":\"register_client\",\"client_type\":2,\"mac_address\":\"00:0c:29:e1:d7:c1\"}";
        byte[] src = msg.getBytes();
        int len = src.length;
        byte[] bytes = DataFormUtil.intToBytes(len);
        byte[] output = DataFormUtil.unitByteArray(bytes,src);
        ByteBuf byteBuf = ctx.alloc().buffer();
        byteBuf.writeBytes(output);

        ChannelFuture f= ctx.writeAndFlush(byteBuf);
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {

            }
        });
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        LogUtil.d(TAG,"channelRead");

        ByteBuf b = (ByteBuf) msg;
        System.out.println("receiver :" + b);
        ctx.write("abc");

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        super.exceptionCaught(ctx, cause);
        LogUtil.d(TAG,"exceptionCaught");
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        LogUtil.d(TAG,"handlerAdded");
        // 分配buf的内存！
        buf = ctx.alloc().buffer(4);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        LogUtil.d(TAG,"handlerRemoved");
        // 回收buf
        buf.release();
        buf = null;
    }




}

package nia.chapter2.echoserver;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.buffer.UnpooledDirectByteBuf;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * Listing 2.1 EchoServerHandler
 *
 * @author <a href="mailto:norman.maurer@gmail.com">Norman Maurer</a>
 */
@Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    //channelRead() —Called for each incoming message
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf in = (ByteBuf) msg;
        System.out.println(
                "Server received: " + in.toString(CharsetUtil.UTF_8));
        ctx.write(Unpooled.copiedBuffer("HTTP/1.1 200 OK\n\n\n", CharsetUtil.UTF_8));
        ctx.write(in);
    }
//channelReadComplete() —Notifies the handler that the last call made to channelRead() was the last message in the current batch
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx)
            throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
    }


    //What happens if an exception isn’t caught?
    //
    //Every Channel has an associated ChannelPipeline, which holds a chain of ChannelHandler instances. By default, a handler will forward the invocation of a handler method to the next one in the chain. Therefore, if exceptionCaught() is not implemented somewhere along the chain, exceptions received will travel to the end of the ChannelPipeline and will be logged. For this reason, your application should supply at least one ChannelHandler that implements exceptionCaught().
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
        Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}

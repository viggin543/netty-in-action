package nia.chapter2.echoserver;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
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
public class HttpResponseHandler extends ChannelInboundHandlerAdapter {
    //channelRead() —Called for each incoming message
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("im first in the chain!!!");
        ctx.fireChannelRead( // call next event handler
                Unpooled.wrappedBuffer(
                        Unpooled.copiedBuffer("HTTP/1.1 200 OK\n\n\n", CharsetUtil.UTF_8),
                        (ByteBuf) msg
                )
        );
    }


}

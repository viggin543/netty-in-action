package nia.chapter2.echoclient;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * Listing 2.3 ChannelHandler for the client
 *
 * @author <a href="mailto:norman.maurer@gmail.com">Norman Maurer</a>
 */
@Sharable
public class EchoClientHandler
    extends SimpleChannelInboundHandler<ByteBuf> {
//    channelActive() —Called after the connection to the server is established
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("connected to icndb");
        ctx.writeAndFlush(
                Unpooled.copiedBuffer("GET /jokes/random HTTP/1.1\n" +
                        "Host: api.icndb.com\n" +
                        "User-Agent: curl/7.54.0\n" +
                        "Accept: */*\n\n", CharsetUtil.UTF_8));
    }
    //channelRead0() —Called when a message is received from the server
    @Override
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf in) {
        System.out.println(
                "Client received: " + in.toString(CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
        Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}

package io.netty.example.debug;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author Force-oneself
 * @description DebugServer
 * @date 2022-04-18
 */
public class DebugServer {

    public static void main(String[] args) {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            new ServerBootstrap()
                    .group(boss, worker)
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(
                                    new LoggingHandler(LogLevel.DEBUG)
                            );
                        }
                    }).bind(8080);
        } catch (Exception ignore) {

        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}

package com.sky.netty.nio.groupChat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author sky
 * @description: 群聊客户端处理器
 * @date: 2021-03-02
 */
public class GroupChatClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void messageReceived(ChannelHandlerContext ctx, String s) throws Exception {
        System.out.println(s.trim());
    }
}

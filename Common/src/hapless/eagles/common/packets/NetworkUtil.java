package hapless.eagles.common.packets;

import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * Contains static network utilities
 * Created by Kneesnap on 2/16/2019.
 */
public class NetworkUtil {

    public static void setup(ChannelPipeline pipeline, EventExecutorGroup eventGroup, SimpleChannelInboundHandler<?> handler) {
        pipeline.addLast("lengthDecoder", new LengthFieldBasedFrameDecoder(8192, 0, 4, 0, 4));
        pipeline.addLast("stringDecoder", new ObjectDecoder(ClassResolvers.cacheDisabled(null))); //TODO: Don't use a null cache.
        pipeline.addLast("lengthEncoder", new LengthFieldPrepender(4));
        pipeline.addLast("stringEncoder", new ObjectEncoder());
        pipeline.addLast(eventGroup, handler);
    }
}

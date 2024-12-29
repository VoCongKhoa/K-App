package di.fa.kakeep.grpc.channel;

import io.grpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;

public class GrpcSlowQueryDetectingInterceptor implements ClientInterceptor {
    private static final Logger log = LoggerFactory.getLogger(GrpcSlowQueryDetectingInterceptor.class);

    private final long acceptableTimeMillis;

    public GrpcSlowQueryDetectingInterceptor(long acceptableTimeMillis) {
        this.acceptableTimeMillis = acceptableTimeMillis;
    }

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, Channel channel) {
        return new ForwardingClientCall.SimpleForwardingClientCall<>(channel.newCall(method, callOptions)) {
            @Override
            public void start(Listener<RespT> responseListener, Metadata headers) {
                Instant start = Instant.now();
                Listener<RespT> listener = new ForwardingClientCallListener<>() {
                    @Override
                    protected Listener<RespT> delegate() {
                        return responseListener;
                    }

                    @Override
                    public void onMessage(RespT message) {
                        long collapsedTime = Duration.between(start, Instant.now()).toMillis();
                        if (collapsedTime > acceptableTimeMillis) {
                            log.warn("Slow gRPC call was detected for the call to {} - {}. Collapsed time - {}ms", method.getServiceName(), method.getFullMethodName(), collapsedTime);
                        }

                        super.onMessage(message);
                    }
                };

                super.start(listener, headers);
            }
        };
    }
}

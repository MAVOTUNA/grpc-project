import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.29.0)",
    comments = "Source: signup.proto")
public final class SignUpServiceGrpc {

  private SignUpServiceGrpc() {}

  public static final String SERVICE_NAME = "SignUpService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<Signup.getAllStudRequest,
      Signup.getAllStudResponse> getGetAllStudentsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getAllStudents",
      requestType = Signup.getAllStudRequest.class,
      responseType = Signup.getAllStudResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<Signup.getAllStudRequest,
      Signup.getAllStudResponse> getGetAllStudentsMethod() {
    io.grpc.MethodDescriptor<Signup.getAllStudRequest, Signup.getAllStudResponse> getGetAllStudentsMethod;
    if ((getGetAllStudentsMethod = SignUpServiceGrpc.getGetAllStudentsMethod) == null) {
      synchronized (SignUpServiceGrpc.class) {
        if ((getGetAllStudentsMethod = SignUpServiceGrpc.getGetAllStudentsMethod) == null) {
          SignUpServiceGrpc.getGetAllStudentsMethod = getGetAllStudentsMethod =
              io.grpc.MethodDescriptor.<Signup.getAllStudRequest, Signup.getAllStudResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getAllStudents"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Signup.getAllStudRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Signup.getAllStudResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SignUpServiceMethodDescriptorSupplier("getAllStudents"))
              .build();
        }
      }
    }
    return getGetAllStudentsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<Signup.getAllCourseRequest,
      Signup.getAllCourseResponse> getGetAllCoursesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getAllCourses",
      requestType = Signup.getAllCourseRequest.class,
      responseType = Signup.getAllCourseResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<Signup.getAllCourseRequest,
      Signup.getAllCourseResponse> getGetAllCoursesMethod() {
    io.grpc.MethodDescriptor<Signup.getAllCourseRequest, Signup.getAllCourseResponse> getGetAllCoursesMethod;
    if ((getGetAllCoursesMethod = SignUpServiceGrpc.getGetAllCoursesMethod) == null) {
      synchronized (SignUpServiceGrpc.class) {
        if ((getGetAllCoursesMethod = SignUpServiceGrpc.getGetAllCoursesMethod) == null) {
          SignUpServiceGrpc.getGetAllCoursesMethod = getGetAllCoursesMethod =
              io.grpc.MethodDescriptor.<Signup.getAllCourseRequest, Signup.getAllCourseResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getAllCourses"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Signup.getAllCourseRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  Signup.getAllCourseResponse.getDefaultInstance()))
              .setSchemaDescriptor(new SignUpServiceMethodDescriptorSupplier("getAllCourses"))
              .build();
        }
      }
    }
    return getGetAllCoursesMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SignUpServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SignUpServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SignUpServiceStub>() {
        @java.lang.Override
        public SignUpServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SignUpServiceStub(channel, callOptions);
        }
      };
    return SignUpServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SignUpServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SignUpServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SignUpServiceBlockingStub>() {
        @java.lang.Override
        public SignUpServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SignUpServiceBlockingStub(channel, callOptions);
        }
      };
    return SignUpServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SignUpServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SignUpServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SignUpServiceFutureStub>() {
        @java.lang.Override
        public SignUpServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SignUpServiceFutureStub(channel, callOptions);
        }
      };
    return SignUpServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class SignUpServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getAllStudents(Signup.getAllStudRequest request,
        io.grpc.stub.StreamObserver<Signup.getAllStudResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAllStudentsMethod(), responseObserver);
    }

    /**
     */
    public void getAllCourses(Signup.getAllCourseRequest request,
        io.grpc.stub.StreamObserver<Signup.getAllCourseResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAllCoursesMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetAllStudentsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                Signup.getAllStudRequest,
                Signup.getAllStudResponse>(
                  this, METHODID_GET_ALL_STUDENTS)))
          .addMethod(
            getGetAllCoursesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                Signup.getAllCourseRequest,
                Signup.getAllCourseResponse>(
                  this, METHODID_GET_ALL_COURSES)))
          .build();
    }
  }

  /**
   */
  public static final class SignUpServiceStub extends io.grpc.stub.AbstractAsyncStub<SignUpServiceStub> {
    private SignUpServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SignUpServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SignUpServiceStub(channel, callOptions);
    }

    /**
     */
    public void getAllStudents(Signup.getAllStudRequest request,
        io.grpc.stub.StreamObserver<Signup.getAllStudResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetAllStudentsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAllCourses(Signup.getAllCourseRequest request,
        io.grpc.stub.StreamObserver<Signup.getAllCourseResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetAllCoursesMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class SignUpServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<SignUpServiceBlockingStub> {
    private SignUpServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SignUpServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SignUpServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public Signup.getAllStudResponse getAllStudents(Signup.getAllStudRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetAllStudentsMethod(), getCallOptions(), request);
    }

    /**
     */
    public Signup.getAllCourseResponse getAllCourses(Signup.getAllCourseRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetAllCoursesMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class SignUpServiceFutureStub extends io.grpc.stub.AbstractFutureStub<SignUpServiceFutureStub> {
    private SignUpServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SignUpServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SignUpServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<Signup.getAllStudResponse> getAllStudents(
        Signup.getAllStudRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetAllStudentsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<Signup.getAllCourseResponse> getAllCourses(
        Signup.getAllCourseRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetAllCoursesMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_ALL_STUDENTS = 0;
  private static final int METHODID_GET_ALL_COURSES = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final SignUpServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SignUpServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_ALL_STUDENTS:
          serviceImpl.getAllStudents((Signup.getAllStudRequest) request,
              (io.grpc.stub.StreamObserver<Signup.getAllStudResponse>) responseObserver);
          break;
        case METHODID_GET_ALL_COURSES:
          serviceImpl.getAllCourses((Signup.getAllCourseRequest) request,
              (io.grpc.stub.StreamObserver<Signup.getAllCourseResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class SignUpServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SignUpServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return Signup.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SignUpService");
    }
  }

  private static final class SignUpServiceFileDescriptorSupplier
      extends SignUpServiceBaseDescriptorSupplier {
    SignUpServiceFileDescriptorSupplier() {}
  }

  private static final class SignUpServiceMethodDescriptorSupplier
      extends SignUpServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    SignUpServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (SignUpServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SignUpServiceFileDescriptorSupplier())
              .addMethod(getGetAllStudentsMethod())
              .addMethod(getGetAllCoursesMethod())
              .build();
        }
      }
    }
    return result;
  }
}

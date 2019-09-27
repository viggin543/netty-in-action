package nia.chapter1;

import java.io.IOException;

public class BlockingServer {
    public static void main(String[] args) throws IOException {
        BlockingIoExample blockingIoExample = new BlockingIoExample();
        blockingIoExample.serve(8081);
    }
}

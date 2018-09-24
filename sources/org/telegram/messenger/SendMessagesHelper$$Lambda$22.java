package org.telegram.messenger;

final /* synthetic */ class SendMessagesHelper$$Lambda$22 implements Runnable {
    private final SendMessagesHelper$MediaSendPrepareWorker arg$1;
    private final int arg$2;
    private final SendMessagesHelper$SendingMediaInfo arg$3;

    SendMessagesHelper$$Lambda$22(SendMessagesHelper$MediaSendPrepareWorker sendMessagesHelper$MediaSendPrepareWorker, int i, SendMessagesHelper$SendingMediaInfo sendMessagesHelper$SendingMediaInfo) {
        this.arg$1 = sendMessagesHelper$MediaSendPrepareWorker;
        this.arg$2 = i;
        this.arg$3 = sendMessagesHelper$SendingMediaInfo;
    }

    public void run() {
        SendMessagesHelper.lambda$null$53$SendMessagesHelper(this.arg$1, this.arg$2, this.arg$3);
    }
}

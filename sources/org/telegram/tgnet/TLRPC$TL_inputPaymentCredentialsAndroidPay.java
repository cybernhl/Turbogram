package org.telegram.tgnet;

public class TLRPC$TL_inputPaymentCredentialsAndroidPay extends TLRPC$InputPaymentCredentials {
    public static int constructor = -905587442;

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.payment_token = TLRPC$TL_dataJSON.TLdeserialize(stream, stream.readInt32(exception), exception);
        this.google_transaction_id = stream.readString(exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        this.payment_token.serializeToStream(stream);
        stream.writeString(this.google_transaction_id);
    }
}

package org.telegram.tgnet;

import java.util.ArrayList;
import org.telegram.tgnet.TLRPC.User;

public class TLRPC$TL_account_authorizationForm extends TLObject {
    public static int constructor = -1389486888;
    public ArrayList<TLRPC$SecureValueError> errors = new ArrayList();
    public int flags;
    public String privacy_policy_url;
    public ArrayList<TLRPC$SecureRequiredType> required_types = new ArrayList();
    public ArrayList<User> users = new ArrayList();
    public ArrayList<TLRPC$TL_secureValue> values = new ArrayList();

    public static TLRPC$TL_account_authorizationForm TLdeserialize(AbstractSerializedData stream, int constructor, boolean exception) {
        if (constructor == constructor) {
            TLRPC$TL_account_authorizationForm result = new TLRPC$TL_account_authorizationForm();
            result.readParams(stream, exception);
            return result;
        } else if (!exception) {
            return null;
        } else {
            throw new RuntimeException(String.format("can't parse magic %x in TL_account_authorizationForm", new Object[]{Integer.valueOf(constructor)}));
        }
    }

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.flags = stream.readInt32(exception);
        if (stream.readInt32(exception) == 481674261) {
            int count = stream.readInt32(exception);
            int a = 0;
            while (a < count) {
                TLRPC$SecureRequiredType object = TLRPC$SecureRequiredType.TLdeserialize(stream, stream.readInt32(exception), exception);
                if (object != null) {
                    this.required_types.add(object);
                    a++;
                } else {
                    return;
                }
            }
            if (stream.readInt32(exception) == 481674261) {
                count = stream.readInt32(exception);
                a = 0;
                while (a < count) {
                    TLRPC$TL_secureValue object2 = TLRPC$TL_secureValue.TLdeserialize(stream, stream.readInt32(exception), exception);
                    if (object2 != null) {
                        this.values.add(object2);
                        a++;
                    } else {
                        return;
                    }
                }
                if (stream.readInt32(exception) == 481674261) {
                    count = stream.readInt32(exception);
                    a = 0;
                    while (a < count) {
                        TLRPC$SecureValueError object3 = TLRPC$SecureValueError.TLdeserialize(stream, stream.readInt32(exception), exception);
                        if (object3 != null) {
                            this.errors.add(object3);
                            a++;
                        } else {
                            return;
                        }
                    }
                    if (stream.readInt32(exception) == 481674261) {
                        count = stream.readInt32(exception);
                        a = 0;
                        while (a < count) {
                            User object4 = User.TLdeserialize(stream, stream.readInt32(exception), exception);
                            if (object4 != null) {
                                this.users.add(object4);
                                a++;
                            } else {
                                return;
                            }
                        }
                        if ((this.flags & 1) != 0) {
                            this.privacy_policy_url = stream.readString(exception);
                        }
                    } else if (exception) {
                        throw new RuntimeException(String.format("wrong Vector magic, got %x", new Object[]{Integer.valueOf(magic)}));
                    }
                } else if (exception) {
                    throw new RuntimeException(String.format("wrong Vector magic, got %x", new Object[]{Integer.valueOf(magic)}));
                }
            } else if (exception) {
                throw new RuntimeException(String.format("wrong Vector magic, got %x", new Object[]{Integer.valueOf(magic)}));
            }
        } else if (exception) {
            throw new RuntimeException(String.format("wrong Vector magic, got %x", new Object[]{Integer.valueOf(magic)}));
        }
    }

    public void serializeToStream(AbstractSerializedData stream) {
        int a;
        stream.writeInt32(constructor);
        stream.writeInt32(this.flags);
        stream.writeInt32(481674261);
        int count = this.required_types.size();
        stream.writeInt32(count);
        for (a = 0; a < count; a++) {
            ((TLRPC$SecureRequiredType) this.required_types.get(a)).serializeToStream(stream);
        }
        stream.writeInt32(481674261);
        count = this.values.size();
        stream.writeInt32(count);
        for (a = 0; a < count; a++) {
            ((TLRPC$TL_secureValue) this.values.get(a)).serializeToStream(stream);
        }
        stream.writeInt32(481674261);
        count = this.errors.size();
        stream.writeInt32(count);
        for (a = 0; a < count; a++) {
            ((TLRPC$SecureValueError) this.errors.get(a)).serializeToStream(stream);
        }
        stream.writeInt32(481674261);
        count = this.users.size();
        stream.writeInt32(count);
        for (a = 0; a < count; a++) {
            ((User) this.users.get(a)).serializeToStream(stream);
        }
        if ((this.flags & 1) != 0) {
            stream.writeString(this.privacy_policy_url);
        }
    }
}

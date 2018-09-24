package org.telegram.messenger;

import org.telegram.tgnet.TLRPC$BotInfo;
import org.telegram.tgnet.TLRPC$TL_messages_chatFull;

class MessagesController$3 implements Runnable {
    final /* synthetic */ MessagesController this$0;
    final /* synthetic */ int val$chat_id;
    final /* synthetic */ TLRPC$TL_messages_chatFull val$res;

    MessagesController$3(MessagesController this$0, int i, TLRPC$TL_messages_chatFull tLRPC$TL_messages_chatFull) {
        this.this$0 = this$0;
        this.val$chat_id = i;
        this.val$res = tLRPC$TL_messages_chatFull;
    }

    public void run() {
        MessagesController.access$300(this.this$0, (long) (-this.val$chat_id), this.val$res.full_chat.notify_settings);
        for (int a = 0; a < this.val$res.full_chat.bot_info.size(); a++) {
            DataQuery.getInstance(MessagesController.access$000(this.this$0)).putBotInfo((TLRPC$BotInfo) this.val$res.full_chat.bot_info.get(a));
        }
        MessagesController.access$400(this.this$0).put(this.val$chat_id, this.val$res.full_chat.exported_invite);
        MessagesController.access$500(this.this$0).remove(Integer.valueOf(this.val$chat_id));
        MessagesController.access$600(this.this$0).add(Integer.valueOf(this.val$chat_id));
        this.this$0.putUsers(this.val$res.users, false);
        this.this$0.putChats(this.val$res.chats, false);
        NotificationCenter.getInstance(MessagesController.access$000(this.this$0)).postNotificationName(NotificationCenter.turboRecievedJoinPush, this.val$res.full_chat);
    }
}

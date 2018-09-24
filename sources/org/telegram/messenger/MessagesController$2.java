package org.telegram.messenger;

import java.util.Comparator;
import org.telegram.tgnet.TLRPC$DraftMessage;
import org.telegram.tgnet.TLRPC$TL_dialog;

class MessagesController$2 implements Comparator<TLRPC$TL_dialog> {
    final /* synthetic */ MessagesController this$0;

    MessagesController$2(MessagesController this$0) {
        this.this$0 = this$0;
    }

    public int compare(TLRPC$TL_dialog dialog1, TLRPC$TL_dialog dialog2) {
        if (!dialog1.pinned && dialog2.pinned) {
            return 1;
        }
        if (dialog1.pinned && !dialog2.pinned) {
            return -1;
        }
        if (!dialog1.pinned || !dialog2.pinned) {
            TLRPC$DraftMessage draftMessage = DataQuery.getInstance(MessagesController.access$000(this.this$0)).getDraft(dialog1.id);
            int date1 = (draftMessage == null || draftMessage.date < dialog1.last_message_date) ? dialog1.last_message_date : draftMessage.date;
            draftMessage = DataQuery.getInstance(MessagesController.access$000(this.this$0)).getDraft(dialog2.id);
            int date2 = (draftMessage == null || draftMessage.date < dialog2.last_message_date) ? dialog2.last_message_date : draftMessage.date;
            if (date1 < date2) {
                return 1;
            }
            if (date1 > date2) {
                return -1;
            }
            return 0;
        } else if (dialog1.pinnedNum < dialog2.pinnedNum) {
            return 1;
        } else {
            if (dialog1.pinnedNum > dialog2.pinnedNum) {
                return -1;
            }
            return 0;
        }
    }
}

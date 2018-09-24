package org.telegram.ui;

import java.util.ArrayList;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.MessageObject;
import org.telegram.ui.Components.ShareAlert;

class WebviewActivity$TelegramWebviewProxy$1 implements Runnable {
    final /* synthetic */ WebviewActivity$TelegramWebviewProxy this$1;
    final /* synthetic */ String val$eventName;

    WebviewActivity$TelegramWebviewProxy$1(WebviewActivity$TelegramWebviewProxy this$1, String str) {
        this.this$1 = this$1;
        this.val$eventName = str;
    }

    public void run() {
        if (this.this$1.this$0.getParentActivity() != null) {
            if (BuildVars.LOGS_ENABLED) {
                FileLog.d(this.val$eventName);
            }
            String str = this.val$eventName;
            boolean z = true;
            switch (str.hashCode()) {
                case -1788360622:
                    if (str.equals("share_game")) {
                        z = false;
                        break;
                    }
                    break;
                case 406539826:
                    if (str.equals("share_score")) {
                        z = true;
                        break;
                    }
                    break;
            }
            switch (z) {
                case false:
                    this.this$1.this$0.currentMessageObject.messageOwner.with_my_score = false;
                    break;
                case true:
                    this.this$1.this$0.currentMessageObject.messageOwner.with_my_score = true;
                    break;
            }
            ArrayList<MessageObject> msgObj = new ArrayList();
            msgObj.add(this.this$1.this$0.currentMessageObject);
            this.this$1.this$0.showDialog(new ShareAlert(this.this$1.this$0.getParentActivity(), msgObj, null, false, this.this$1.this$0.linkToCopy, false));
        }
    }
}

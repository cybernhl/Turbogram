package turbogram.Utilities;

import android.os.Handler;
import java.util.ArrayList;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLRPC.User;

public class AnsweringMachine {
    public static ArrayList<MessageObject> ListOfMsgs = new ArrayList();
    public static ArrayList<Long> userSentArray = new ArrayList();

    public static void ProcessMsg(MessageObject messageObject) {
        String str = TurboConfig.answeringMachineMsg;
        if (str.length() > 0) {
            long userid = messageObject.getDialogId();
            User user = MessagesController.getInstance(UserConfig.selectedAccount).getUser(Integer.valueOf((int) userid));
            if (userid > 0 && user != null && !user.bot && !userSentArray.contains(Long.valueOf(userid))) {
                SendMessagesHelper.getInstance(UserConfig.selectedAccount).sendMessage(str, userid, null, null, true, null, null, null);
                userSentArray.add(Long.valueOf(userid));
            }
        }
    }

    public static void ProcessMsgs(ArrayList<MessageObject> listofdialogs) {
        if (TurboConfig.answeringMachine) {
            for (int i = 0; i < listofdialogs.size(); i++) {
                final MessageObject m = (MessageObject) listofdialogs.get(i);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        AnsweringMachine.ProcessMsg(m);
                    }
                }, (long) (i * 1000));
            }
        }
    }

    public static void removeUserFromMachine(long uid) {
        userSentArray.remove(Long.valueOf(uid));
    }
}

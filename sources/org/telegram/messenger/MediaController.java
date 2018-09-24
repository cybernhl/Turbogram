package org.telegram.messenger;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.media.AudioRecord;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaCodecInfo;
import android.media.MediaCodecInfo.CodecCapabilities;
import android.media.MediaCodecList;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.PowerManager.WakeLock;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Video;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.TextureView;
import android.view.View;
import android.widget.FrameLayout;
import com.baranak.turbogramf.R;
import com.google.android.exoplayer2.C0246C;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.upstream.cache.CacheDataSink;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.mohamadamin.persianmaterialdatetimepicker.date.MonthView;
import de.jurihock.voicesmith.FrameType;
import de.jurihock.voicesmith.HeadsetMode;
import de.jurihock.voicesmith.Preferences;
import de.jurihock.voicesmith.dsp.KissFFT;
import de.jurihock.voicesmith.dsp.Math;
import de.jurihock.voicesmith.dsp.processors.NativeResampleProcessor;
import de.jurihock.voicesmith.dsp.processors.NativeTimescaleProcessor;
import de.jurihock.voicesmith.dsp.stft.StftPostprocessor;
import de.jurihock.voicesmith.dsp.stft.StftPreprocessor;
import de.jurihock.voicesmith.io.AudioDevice;
import de.jurihock.voicesmith.io.pcm.PcmInDevice;
import de.jurihock.voicesmith.io.pcm.PcmOutDevice;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Timer;
import org.telegram.messenger.audioinfo.AudioInfo;
import org.telegram.messenger.video.MP4Builder;
import org.telegram.messenger.voip.VoIPService;
import org.telegram.tgnet.TLRPC$Document;
import org.telegram.tgnet.TLRPC$DocumentAttribute;
import org.telegram.tgnet.TLRPC$EncryptedChat;
import org.telegram.tgnet.TLRPC$TL_document;
import org.telegram.tgnet.TLRPC$TL_documentAttributeAnimated;
import org.telegram.tgnet.TLRPC$TL_encryptedChat;
import org.telegram.tgnet.TLRPC.User;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.PipRoundVideoView;
import org.telegram.ui.Components.VideoPlayer;
import org.telegram.ui.PhotoViewer;
import turbogram.Utilities.TurboConfig;

public class MediaController implements SensorEventListener, OnAudioFocusChangeListener, NotificationCenter$NotificationCenterDelegate {
    private static final int AUDIO_FOCUSED = 2;
    private static final int AUDIO_NO_FOCUS_CAN_DUCK = 1;
    private static final int AUDIO_NO_FOCUS_NO_DUCK = 0;
    private static volatile MediaController Instance = null;
    public static final String MIME_TYPE = "video/avc";
    private static final int PROCESSOR_TYPE_INTEL = 2;
    private static final int PROCESSOR_TYPE_MTK = 3;
    private static final int PROCESSOR_TYPE_OTHER = 0;
    private static final int PROCESSOR_TYPE_QCOM = 1;
    private static final int PROCESSOR_TYPE_SEC = 4;
    private static final int PROCESSOR_TYPE_TI = 5;
    private static final float VOLUME_DUCK = 0.2f;
    private static final float VOLUME_NORMAL = 1.0f;
    public static MediaController$AlbumEntry allMediaAlbumEntry;
    public static MediaController$AlbumEntry allPhotosAlbumEntry;
    private static Runnable broadcastPhotosRunnable;
    private static final String[] projectionPhotos = new String[]{"_id", "bucket_id", "bucket_display_name", "_data", "datetaken", "orientation"};
    private static final String[] projectionVideo = new String[]{"_id", "bucket_id", "bucket_display_name", "_data", "datetaken", "duration"};
    private static Runnable refreshGalleryRunnable;
    private Sensor accelerometerSensor;
    private boolean accelerometerVertical;
    private boolean allowStartRecord;
    private float[] analysisFrameBuffer;
    private int audioFocus = 0;
    private AudioInfo audioInfo;
    private VideoPlayer audioPlayer = null;
    private AudioRecord audioRecorder;
    private Activity baseActivity;
    private boolean callInProgress;
    private boolean cancelCurrentVideoConversion = false;
    private int countLess;
    private AspectRatioFrameLayout currentAspectRatioFrameLayout;
    private float currentAspectRatioFrameLayoutRatio;
    private boolean currentAspectRatioFrameLayoutReady;
    private int currentAspectRatioFrameLayoutRotation;
    private float currentPlaybackSpeed = VOLUME_NORMAL;
    private int currentPlaylistNum;
    private TextureView currentTextureView;
    private FrameLayout currentTextureViewContainer;
    private boolean downloadingCurrentMessage;
    private MediaController$ExternalObserver externalObserver;
    private View feedbackView;
    private ByteBuffer fileBuffer;
    private DispatchQueue fileEncodingQueue;
    private BaseFragment flagSecureFragment;
    private boolean forceLoopCurrentPlaylist;
    private HashMap<String, MessageObject> generatingWaveform = new HashMap();
    private float[] gravity = new float[3];
    private float[] gravityFast = new float[3];
    private Sensor gravitySensor;
    private int hasAudioFocus;
    private boolean ignoreOnPause;
    private boolean ignoreProximity;
    private boolean inputFieldHasText;
    private MediaController$InternalObserver internalObserver;
    private boolean isDrawingWasReady;
    private boolean isPaused = false;
    private KissFFT kissFFT = null;
    private int lastChatAccount;
    private long lastChatEnterTime;
    private long lastChatLeaveTime;
    private ArrayList<Long> lastChatVisibleMessages;
    private long lastMediaCheckTime;
    private int lastMessageId;
    private long lastProgress = 0;
    private float lastProximityValue = -100.0f;
    private TLRPC$EncryptedChat lastSecretChat;
    private long lastTimestamp = 0;
    private User lastUser;
    private float[] linearAcceleration = new float[3];
    private Sensor linearSensor;
    private String[] mediaProjections;
    private PipRoundVideoView pipRoundVideoView;
    private int pipSwitchingState;
    private boolean playMusicAgain;
    private MessageObject playingMessageObject;
    private ArrayList<MessageObject> playlist = new ArrayList();
    private StftPostprocessor postprocessor = null;
    private StftPreprocessor preprocessor = null;
    private float previousAccValue;
    private Timer progressTimer = null;
    private final Object progressTimerSync = new Object();
    private boolean proximityHasDifferentValues;
    private Sensor proximitySensor;
    private boolean proximityTouched;
    private WakeLock proximityWakeLock;
    private ChatActivity raiseChat;
    private boolean raiseToEarRecord;
    private int raisedToBack;
    private int raisedToTop;
    private int raisedToTopSign;
    private int recordBufferSize = 1280;
    private ArrayList<ByteBuffer> recordBuffers = new ArrayList();
    private long recordDialogId;
    private DispatchQueue recordQueue = new DispatchQueue("recordQueue");
    private MessageObject recordReplyingMessageObject;
    private Runnable recordRunnable = new MediaController$1(this);
    private short[] recordSamples = new short[1024];
    private Runnable recordStartRunnable;
    private long recordStartTime;
    private long recordTimeCount;
    private TLRPC$TL_document recordingAudio;
    private File recordingAudioFile;
    private int recordingCurrentAccount;
    private NativeResampleProcessor resampleProcessor = null;
    private boolean resumeAudioOnFocusGain;
    private long samplesCount;
    private float seekToProgressPending;
    private int sendAfterDone;
    private SensorManager sensorManager;
    private boolean sensorsStarted;
    private ArrayList<MessageObject> shuffledPlaylist = new ArrayList();
    private MediaController$SmsObserver smsObserver;
    private int startObserverToken;
    private MediaController$StopMediaObserverRunnable stopMediaObserverRunnable;
    private final Object sync = new Object();
    private float[] synthesisFrameBuffer;
    private long timeSinceRaise;
    private NativeTimescaleProcessor timescaleProcessor = null;
    private Runnable turboRecordRunnable = new MediaController$26(this);
    private boolean useFrontSpeaker;
    private boolean videoConvertFirstWrite = true;
    private ArrayList<MessageObject> videoConvertQueue = new ArrayList();
    private final Object videoConvertSync = new Object();
    private VideoPlayer videoPlayer;
    private final Object videoQueueSync = new Object();
    private float[] voiceBuffer;
    private ArrayList<MessageObject> voiceMessagesPlaylist;
    private SparseArray<MessageObject> voiceMessagesPlaylistMap;
    private boolean voiceMessagesPlaylistUnread;

    public static native int isOpusFile(String str);

    private native int startRecord(String str);

    private native void stopRecord();

    private native int writeFrame(ByteBuffer byteBuffer, int i);

    public native byte[] getWaveform(String str);

    public native byte[] getWaveform2(short[] sArr, int i);

    private void readSms() {
    }

    public static void checkGallery() {
        if (VERSION.SDK_INT >= 24 && allPhotosAlbumEntry != null) {
            Utilities.globalQueue.postRunnable(new MediaController$2(allPhotosAlbumEntry.photos.size()), AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
        }
    }

    public static MediaController getInstance() {
        MediaController localInstance = Instance;
        if (localInstance == null) {
            synchronized (MediaController.class) {
                try {
                    localInstance = Instance;
                    if (localInstance == null) {
                        MediaController localInstance2 = new MediaController();
                        try {
                            Instance = localInstance2;
                            localInstance = localInstance2;
                        } catch (Throwable th) {
                            Throwable th2 = th;
                            localInstance = localInstance2;
                            throw th2;
                        }
                    }
                } catch (Throwable th3) {
                    th2 = th3;
                    throw th2;
                }
            }
        }
        return localInstance;
    }

    public MediaController() {
        this.recordQueue.setPriority(10);
        this.fileEncodingQueue = new DispatchQueue("fileEncodingQueue");
        this.fileEncodingQueue.setPriority(10);
        this.recordQueue.postRunnable(new MediaController$3(this));
        Utilities.globalQueue.postRunnable(new MediaController$4(this));
        this.fileBuffer = ByteBuffer.allocateDirect(1920);
        AndroidUtilities.runOnUIThread(new MediaController$5(this));
        this.mediaProjections = new String[]{"_data", "_display_name", "bucket_display_name", "datetaken", "title", "width", MonthView.VIEW_PARAMS_HEIGHT};
        ContentResolver contentResolver = ApplicationLoader.applicationContext.getContentResolver();
        try {
            contentResolver.registerContentObserver(Media.EXTERNAL_CONTENT_URI, true, new MediaController$GalleryObserverExternal(this));
        } catch (Throwable e) {
            FileLog.m1224e(e);
        }
        try {
            contentResolver.registerContentObserver(Media.INTERNAL_CONTENT_URI, true, new MediaController$GalleryObserverInternal(this));
        } catch (Throwable e2) {
            FileLog.m1224e(e2);
        }
        try {
            contentResolver.registerContentObserver(Video.Media.EXTERNAL_CONTENT_URI, true, new MediaController$GalleryObserverExternal(this));
        } catch (Throwable e22) {
            FileLog.m1224e(e22);
        }
        try {
            contentResolver.registerContentObserver(Video.Media.INTERNAL_CONTENT_URI, true, new MediaController$GalleryObserverInternal(this));
        } catch (Throwable e222) {
            FileLog.m1224e(e222);
        }
    }

    public void onAudioFocusChange(int focusChange) {
        if (focusChange == -1) {
            if (isPlayingMessage(getPlayingMessageObject()) && !isMessagePaused()) {
                pauseMessage(this.playingMessageObject);
            }
            this.hasAudioFocus = 0;
            this.audioFocus = 0;
        } else if (focusChange == 1) {
            this.audioFocus = 2;
            if (this.resumeAudioOnFocusGain) {
                this.resumeAudioOnFocusGain = false;
                if (isPlayingMessage(getPlayingMessageObject()) && isMessagePaused()) {
                    playMessage(getPlayingMessageObject());
                }
            }
        } else if (focusChange == -3) {
            this.audioFocus = 1;
        } else if (focusChange == -2) {
            this.audioFocus = 0;
            if (isPlayingMessage(getPlayingMessageObject()) && !isMessagePaused()) {
                pauseMessage(this.playingMessageObject);
                this.resumeAudioOnFocusGain = true;
            }
        }
        setPlayerVolume();
    }

    private void setPlayerVolume() {
        try {
            float volume;
            if (this.audioFocus != 1) {
                volume = VOLUME_NORMAL;
            } else {
                volume = VOLUME_DUCK;
            }
            if (this.audioPlayer != null) {
                this.audioPlayer.setVolume(volume);
            } else if (this.videoPlayer != null) {
                this.videoPlayer.setVolume(volume);
            }
        } catch (Throwable e) {
            FileLog.m1224e(e);
        }
    }

    private void startProgressTimer(MessageObject currentPlayingMessageObject) {
        synchronized (this.progressTimerSync) {
            if (this.progressTimer != null) {
                try {
                    this.progressTimer.cancel();
                    this.progressTimer = null;
                } catch (Throwable e) {
                    FileLog.m1224e(e);
                }
            }
            String fileName = currentPlayingMessageObject.getFileName();
            this.progressTimer = new Timer();
            this.progressTimer.schedule(new MediaController$6(this, currentPlayingMessageObject), 0, 17);
        }
    }

    private void stopProgressTimer() {
        synchronized (this.progressTimerSync) {
            if (this.progressTimer != null) {
                try {
                    this.progressTimer.cancel();
                    this.progressTimer = null;
                } catch (Throwable e) {
                    FileLog.m1224e(e);
                }
            }
        }
    }

    public void cleanup() {
        cleanupPlayer(false, true);
        this.audioInfo = null;
        this.playMusicAgain = false;
        for (int a = 0; a < 3; a++) {
            DownloadController.getInstance(a).cleanup();
        }
        this.videoConvertQueue.clear();
        this.playlist.clear();
        this.shuffledPlaylist.clear();
        this.generatingWaveform.clear();
        this.voiceMessagesPlaylist = null;
        this.voiceMessagesPlaylistMap = null;
        cancelVideoConvert(null);
    }

    public void startMediaObserver() {
        ApplicationLoader.applicationHandler.removeCallbacks(this.stopMediaObserverRunnable);
        this.startObserverToken++;
        try {
            if (this.internalObserver == null) {
                ContentResolver contentResolver = ApplicationLoader.applicationContext.getContentResolver();
                Uri uri = Media.EXTERNAL_CONTENT_URI;
                ContentObserver mediaController$ExternalObserver = new MediaController$ExternalObserver(this);
                this.externalObserver = mediaController$ExternalObserver;
                contentResolver.registerContentObserver(uri, false, mediaController$ExternalObserver);
            }
        } catch (Throwable e) {
            FileLog.m1224e(e);
        }
        try {
            if (this.externalObserver == null) {
                contentResolver = ApplicationLoader.applicationContext.getContentResolver();
                uri = Media.INTERNAL_CONTENT_URI;
                mediaController$ExternalObserver = new MediaController$InternalObserver(this);
                this.internalObserver = mediaController$ExternalObserver;
                contentResolver.registerContentObserver(uri, false, mediaController$ExternalObserver);
            }
        } catch (Throwable e2) {
            FileLog.m1224e(e2);
        }
    }

    public void startSmsObserver() {
        try {
            if (this.smsObserver == null) {
                ContentResolver contentResolver = ApplicationLoader.applicationContext.getContentResolver();
                Uri parse = Uri.parse("content://sms");
                ContentObserver mediaController$SmsObserver = new MediaController$SmsObserver(this);
                this.smsObserver = mediaController$SmsObserver;
                contentResolver.registerContentObserver(parse, false, mediaController$SmsObserver);
            }
            AndroidUtilities.runOnUIThread(new MediaController$7(this), 300000);
        } catch (Throwable e) {
            FileLog.m1224e(e);
        }
    }

    public void stopMediaObserver() {
        if (this.stopMediaObserverRunnable == null) {
            this.stopMediaObserverRunnable = new MediaController$StopMediaObserverRunnable(this, null);
        }
        this.stopMediaObserverRunnable.currentObserverToken = this.startObserverToken;
        ApplicationLoader.applicationHandler.postDelayed(this.stopMediaObserverRunnable, DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
    }

    private void processMediaObserver(Uri uri) {
        Cursor cursor = null;
        try {
            Point size = AndroidUtilities.getRealScreenSize();
            cursor = ApplicationLoader.applicationContext.getContentResolver().query(uri, this.mediaProjections, null, null, "date_added DESC LIMIT 1");
            ArrayList<Long> screenshotDates = new ArrayList();
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    String val = "";
                    String data = cursor.getString(0);
                    String display_name = cursor.getString(1);
                    String album_name = cursor.getString(2);
                    long date = cursor.getLong(3);
                    String title = cursor.getString(4);
                    int photoW = cursor.getInt(5);
                    int photoH = cursor.getInt(6);
                    if ((data != null && data.toLowerCase().contains("screenshot")) || ((display_name != null && display_name.toLowerCase().contains("screenshot")) || ((album_name != null && album_name.toLowerCase().contains("screenshot")) || (title != null && title.toLowerCase().contains("screenshot"))))) {
                        if (photoW == 0 || photoH == 0) {
                            try {
                                Options bmOptions = new Options();
                                bmOptions.inJustDecodeBounds = true;
                                BitmapFactory.decodeFile(data, bmOptions);
                                photoW = bmOptions.outWidth;
                                photoH = bmOptions.outHeight;
                            } catch (Exception e) {
                                screenshotDates.add(Long.valueOf(date));
                            }
                        }
                        if (photoW <= 0 || photoH <= 0 || ((photoW == size.x && photoH == size.y) || (photoH == size.x && photoW == size.y))) {
                            screenshotDates.add(Long.valueOf(date));
                        }
                    }
                }
                cursor.close();
            }
            if (!screenshotDates.isEmpty()) {
                AndroidUtilities.runOnUIThread(new MediaController$8(this, screenshotDates));
            }
            if (cursor != null) {
                try {
                    cursor.close();
                } catch (Exception e2) {
                }
            }
        } catch (Throwable e3) {
            FileLog.m1224e(e3);
            if (cursor != null) {
                try {
                    cursor.close();
                } catch (Exception e4) {
                }
            }
        } catch (Throwable th) {
            if (cursor != null) {
                try {
                    cursor.close();
                } catch (Exception e5) {
                }
            }
        }
    }

    private void checkScreenshots(ArrayList<Long> dates) {
        if (dates != null && !dates.isEmpty() && this.lastChatEnterTime != 0) {
            if (this.lastUser != null || (this.lastSecretChat instanceof TLRPC$TL_encryptedChat)) {
                boolean send = false;
                for (int a = 0; a < dates.size(); a++) {
                    Long date = (Long) dates.get(a);
                    if ((this.lastMediaCheckTime == 0 || date.longValue() > this.lastMediaCheckTime) && date.longValue() >= this.lastChatEnterTime && (this.lastChatLeaveTime == 0 || date.longValue() <= this.lastChatLeaveTime + AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS)) {
                        this.lastMediaCheckTime = Math.max(this.lastMediaCheckTime, date.longValue());
                        send = true;
                    }
                }
                if (!send) {
                    return;
                }
                if (this.lastSecretChat != null) {
                    SecretChatHelper.getInstance(this.lastChatAccount).sendScreenshotMessage(this.lastSecretChat, this.lastChatVisibleMessages, null);
                } else {
                    SendMessagesHelper.getInstance(this.lastChatAccount).sendScreenshotMessage(this.lastUser, this.lastMessageId, null);
                }
            }
        }
    }

    public void setLastVisibleMessageIds(int account, long enterTime, long leaveTime, User user, TLRPC$EncryptedChat encryptedChat, ArrayList<Long> visibleMessages, int visibleMessage) {
        this.lastChatEnterTime = enterTime;
        this.lastChatLeaveTime = leaveTime;
        this.lastChatAccount = account;
        this.lastSecretChat = encryptedChat;
        this.lastUser = user;
        this.lastMessageId = visibleMessage;
        this.lastChatVisibleMessages = visibleMessages;
    }

    public void didReceivedNotification(int id, int account, Object... args) {
        if (id == NotificationCenter.FileDidLoaded || id == NotificationCenter.httpFileDidLoaded) {
            String fileName = args[0];
            if (this.downloadingCurrentMessage && this.playingMessageObject != null && this.playingMessageObject.currentAccount == account && FileLoader.getAttachFileName(this.playingMessageObject.getDocument()).equals(fileName)) {
                this.playMusicAgain = true;
                playMessage(this.playingMessageObject);
            }
        } else if (id == NotificationCenter.messagesDeleted) {
            int channelId = ((Integer) args[1]).intValue();
            ArrayList<Integer> markAsDeletedMessages = args[0];
            if (this.playingMessageObject != null && channelId == this.playingMessageObject.messageOwner.to_id.channel_id && markAsDeletedMessages.contains(Integer.valueOf(this.playingMessageObject.getId()))) {
                cleanupPlayer(true, true);
            }
            if (this.voiceMessagesPlaylist != null && !this.voiceMessagesPlaylist.isEmpty() && channelId == ((MessageObject) this.voiceMessagesPlaylist.get(0)).messageOwner.to_id.channel_id) {
                for (a = 0; a < markAsDeletedMessages.size(); a++) {
                    Integer key = (Integer) markAsDeletedMessages.get(a);
                    messageObject = (MessageObject) this.voiceMessagesPlaylistMap.get(key.intValue());
                    this.voiceMessagesPlaylistMap.remove(key.intValue());
                    if (messageObject != null) {
                        this.voiceMessagesPlaylist.remove(messageObject);
                    }
                }
            }
        } else if (id == NotificationCenter.removeAllMessagesFromDialog) {
            did = ((Long) args[0]).longValue();
            if (this.playingMessageObject != null && this.playingMessageObject.getDialogId() == did) {
                cleanupPlayer(false, true);
            }
        } else if (id == NotificationCenter.musicDidLoaded) {
            did = ((Long) args[0]).longValue();
            if (this.playingMessageObject != null && this.playingMessageObject.isMusic() && this.playingMessageObject.getDialogId() == did) {
                ArrayList<MessageObject> arrayList = args[1];
                this.playlist.addAll(0, arrayList);
                if (SharedConfig.shuffleMusic) {
                    buildShuffledPlayList();
                    this.currentPlaylistNum = 0;
                    return;
                }
                this.currentPlaylistNum += arrayList.size();
            }
        } else if (id == NotificationCenter.didReceivedNewMessages) {
            if (this.voiceMessagesPlaylist != null && !this.voiceMessagesPlaylist.isEmpty()) {
                if (((Long) args[0]).longValue() == ((MessageObject) this.voiceMessagesPlaylist.get(0)).getDialogId()) {
                    ArrayList<MessageObject> arr = args[1];
                    for (a = 0; a < arr.size(); a++) {
                        messageObject = (MessageObject) arr.get(a);
                        if ((messageObject.isVoice() || messageObject.isRoundVideo()) && (!this.voiceMessagesPlaylistUnread || (messageObject.isContentUnread() && !messageObject.isOut()))) {
                            this.voiceMessagesPlaylist.add(messageObject);
                            this.voiceMessagesPlaylistMap.put(messageObject.getId(), messageObject);
                        }
                    }
                }
            }
        } else if (id == NotificationCenter.playerDidStartPlaying) {
            if (!getInstance().isCurrentPlayer(args[0])) {
                getInstance().pauseMessage(getInstance().getPlayingMessageObject());
            }
        }
    }

    protected boolean isRecordingAudio() {
        return (this.recordStartRunnable == null && this.recordingAudio == null) ? false : true;
    }

    private boolean isNearToSensor(float value) {
        return value < 5.0f && value != this.proximitySensor.getMaximumRange();
    }

    public boolean isRecordingOrListeningByProximity() {
        return this.proximityTouched && (isRecordingAudio() || (this.playingMessageObject != null && (this.playingMessageObject.isVoice() || this.playingMessageObject.isRoundVideo())));
    }

    public void onSensorChanged(SensorEvent event) {
        if (this.sensorsStarted && VoIPService.getSharedInstance() == null) {
            if (event.sensor == this.proximitySensor) {
                if (BuildVars.LOGS_ENABLED) {
                    FileLog.m1221d("proximity changed to " + event.values[0]);
                }
                if (this.lastProximityValue == -100.0f) {
                    this.lastProximityValue = event.values[0];
                } else if (this.lastProximityValue != event.values[0]) {
                    this.proximityHasDifferentValues = true;
                }
                if (this.proximityHasDifferentValues) {
                    this.proximityTouched = isNearToSensor(event.values[0]);
                }
            } else if (event.sensor == this.accelerometerSensor) {
                double alpha;
                if (this.lastTimestamp == 0) {
                    alpha = 0.9800000190734863d;
                } else {
                    alpha = 1.0d / (1.0d + (((double) (event.timestamp - this.lastTimestamp)) / 1.0E9d));
                }
                this.lastTimestamp = event.timestamp;
                this.gravity[0] = (float) ((((double) this.gravity[0]) * alpha) + ((1.0d - alpha) * ((double) event.values[0])));
                this.gravity[1] = (float) ((((double) this.gravity[1]) * alpha) + ((1.0d - alpha) * ((double) event.values[1])));
                this.gravity[2] = (float) ((((double) this.gravity[2]) * alpha) + ((1.0d - alpha) * ((double) event.values[2])));
                this.gravityFast[0] = (0.8f * this.gravity[0]) + (0.19999999f * event.values[0]);
                this.gravityFast[1] = (0.8f * this.gravity[1]) + (0.19999999f * event.values[1]);
                this.gravityFast[2] = (0.8f * this.gravity[2]) + (0.19999999f * event.values[2]);
                this.linearAcceleration[0] = event.values[0] - this.gravity[0];
                this.linearAcceleration[1] = event.values[1] - this.gravity[1];
                this.linearAcceleration[2] = event.values[2] - this.gravity[2];
            } else if (event.sensor == this.linearSensor) {
                this.linearAcceleration[0] = event.values[0];
                this.linearAcceleration[1] = event.values[1];
                this.linearAcceleration[2] = event.values[2];
            } else if (event.sensor == this.gravitySensor) {
                float[] fArr = this.gravityFast;
                float[] fArr2 = this.gravity;
                float f = event.values[0];
                fArr2[0] = f;
                fArr[0] = f;
                fArr = this.gravityFast;
                fArr2 = this.gravity;
                f = event.values[1];
                fArr2[1] = f;
                fArr[1] = f;
                fArr = this.gravityFast;
                fArr2 = this.gravity;
                f = event.values[2];
                fArr2[2] = f;
                fArr[2] = f;
            }
            if (event.sensor == this.linearSensor || event.sensor == this.gravitySensor || event.sensor == this.accelerometerSensor) {
                float val = ((this.gravity[0] * this.linearAcceleration[0]) + (this.gravity[1] * this.linearAcceleration[1])) + (this.gravity[2] * this.linearAcceleration[2]);
                if (this.raisedToBack != 6 && ((val > 0.0f && this.previousAccValue > 0.0f) || (val < 0.0f && this.previousAccValue < 0.0f))) {
                    boolean goodValue;
                    int sign;
                    if (val > 0.0f) {
                        goodValue = val > 15.0f;
                        sign = 1;
                    } else {
                        goodValue = val < -15.0f;
                        sign = 2;
                    }
                    if (this.raisedToTopSign == 0 || this.raisedToTopSign == sign) {
                        if (!goodValue || this.raisedToBack != 0 || (this.raisedToTopSign != 0 && this.raisedToTopSign != sign)) {
                            if (!goodValue) {
                                this.countLess++;
                            }
                            if (!(this.raisedToTopSign == sign && this.countLess != 10 && this.raisedToTop == 6 && this.raisedToBack == 0)) {
                                this.raisedToBack = 0;
                                this.raisedToTop = 0;
                                this.raisedToTopSign = 0;
                                this.countLess = 0;
                            }
                        } else if (this.raisedToTop < 6 && !this.proximityTouched) {
                            this.raisedToTopSign = sign;
                            this.raisedToTop++;
                            if (this.raisedToTop == 6) {
                                this.countLess = 0;
                            }
                        }
                    } else if (this.raisedToTop != 6 || !goodValue) {
                        if (!goodValue) {
                            this.countLess++;
                        }
                        if (!(this.countLess != 10 && this.raisedToTop == 6 && this.raisedToBack == 0)) {
                            this.raisedToTop = 0;
                            this.raisedToTopSign = 0;
                            this.raisedToBack = 0;
                            this.countLess = 0;
                        }
                    } else if (this.raisedToBack < 6) {
                        this.raisedToBack++;
                        if (this.raisedToBack == 6) {
                            this.raisedToTop = 0;
                            this.raisedToTopSign = 0;
                            this.countLess = 0;
                            this.timeSinceRaise = System.currentTimeMillis();
                            if (BuildVars.LOGS_ENABLED && BuildVars.DEBUG_PRIVATE_VERSION) {
                                FileLog.m1221d("motion detected");
                            }
                        }
                    }
                }
                this.previousAccValue = val;
                boolean z = this.gravityFast[1] > 2.5f && Math.abs(this.gravityFast[2]) < 4.0f && Math.abs(this.gravityFast[0]) > 1.5f;
                this.accelerometerVertical = z;
            }
            if (this.raisedToBack == 6 && this.accelerometerVertical && this.proximityTouched && !NotificationsController.audioManager.isWiredHeadsetOn()) {
                if (BuildVars.LOGS_ENABLED) {
                    FileLog.m1221d("sensor values reached");
                }
                if (this.playingMessageObject == null && this.recordStartRunnable == null && this.recordingAudio == null && !PhotoViewer.getInstance().isVisible() && ApplicationLoader.isScreenOn && !this.inputFieldHasText && this.allowStartRecord && this.raiseChat != null && !this.callInProgress) {
                    if (!this.raiseToEarRecord) {
                        if (BuildVars.LOGS_ENABLED) {
                            FileLog.m1221d("start record");
                        }
                        this.useFrontSpeaker = true;
                        if (!this.raiseChat.playFirstUnreadVoiceMessage()) {
                            this.raiseToEarRecord = true;
                            this.useFrontSpeaker = false;
                            startRecording(this.raiseChat.getCurrentAccount(), this.raiseChat.getDialogId(), null);
                        }
                        if (this.useFrontSpeaker) {
                            setUseFrontSpeaker(true);
                        }
                        this.ignoreOnPause = true;
                        if (!(!this.proximityHasDifferentValues || this.proximityWakeLock == null || this.proximityWakeLock.isHeld())) {
                            this.proximityWakeLock.acquire();
                        }
                    }
                } else if (this.playingMessageObject != null && ((this.playingMessageObject.isVoice() || this.playingMessageObject.isRoundVideo()) && !this.useFrontSpeaker)) {
                    if (BuildVars.LOGS_ENABLED) {
                        FileLog.m1221d("start listen");
                    }
                    if (!(!this.proximityHasDifferentValues || this.proximityWakeLock == null || this.proximityWakeLock.isHeld())) {
                        this.proximityWakeLock.acquire();
                    }
                    setUseFrontSpeaker(true);
                    startAudioAgain(false);
                    this.ignoreOnPause = true;
                }
                this.raisedToBack = 0;
                this.raisedToTop = 0;
                this.raisedToTopSign = 0;
                this.countLess = 0;
            } else if (this.proximityTouched) {
                if (!(this.playingMessageObject == null || ApplicationLoader.mainInterfacePaused || ((!this.playingMessageObject.isVoice() && !this.playingMessageObject.isRoundVideo()) || this.useFrontSpeaker))) {
                    if (BuildVars.LOGS_ENABLED) {
                        FileLog.m1221d("start listen by proximity only");
                    }
                    if (!(!this.proximityHasDifferentValues || this.proximityWakeLock == null || this.proximityWakeLock.isHeld())) {
                        this.proximityWakeLock.acquire();
                    }
                    setUseFrontSpeaker(true);
                    startAudioAgain(false);
                    this.ignoreOnPause = true;
                }
            } else if (!this.proximityTouched) {
                if (this.raiseToEarRecord) {
                    if (BuildVars.LOGS_ENABLED) {
                        FileLog.m1221d("stop record");
                    }
                    stopRecording(2);
                    this.raiseToEarRecord = false;
                    this.ignoreOnPause = false;
                    if (this.proximityHasDifferentValues && this.proximityWakeLock != null && this.proximityWakeLock.isHeld()) {
                        this.proximityWakeLock.release();
                    }
                } else if (this.useFrontSpeaker) {
                    if (BuildVars.LOGS_ENABLED) {
                        FileLog.m1221d("stop listen");
                    }
                    this.useFrontSpeaker = false;
                    startAudioAgain(true);
                    this.ignoreOnPause = false;
                    if (this.proximityHasDifferentValues && this.proximityWakeLock != null && this.proximityWakeLock.isHeld()) {
                        this.proximityWakeLock.release();
                    }
                }
            }
            if (this.timeSinceRaise != 0 && this.raisedToBack == 6 && Math.abs(System.currentTimeMillis() - this.timeSinceRaise) > 1000) {
                this.raisedToBack = 0;
                this.raisedToTop = 0;
                this.raisedToTopSign = 0;
                this.countLess = 0;
                this.timeSinceRaise = 0;
            }
        }
    }

    private void setUseFrontSpeaker(boolean value) {
        this.useFrontSpeaker = value;
        AudioManager audioManager = NotificationsController.audioManager;
        if (this.useFrontSpeaker) {
            audioManager.setBluetoothScoOn(false);
            audioManager.setSpeakerphoneOn(false);
            return;
        }
        audioManager.setSpeakerphoneOn(true);
    }

    public void startRecordingIfFromSpeaker() {
        if (this.useFrontSpeaker && this.raiseChat != null && this.allowStartRecord) {
            this.raiseToEarRecord = true;
            startRecording(this.raiseChat.getCurrentAccount(), this.raiseChat.getDialogId(), null);
            this.ignoreOnPause = true;
        }
    }

    private void startAudioAgain(boolean paused) {
        int i = 0;
        if (this.playingMessageObject != null) {
            NotificationCenter.getInstance(this.playingMessageObject.currentAccount).postNotificationName(NotificationCenter.audioRouteChanged, new Object[]{Boolean.valueOf(this.useFrontSpeaker)});
            if (this.videoPlayer != null) {
                VideoPlayer videoPlayer = this.videoPlayer;
                if (!this.useFrontSpeaker) {
                    i = 3;
                }
                videoPlayer.setStreamType(i);
                if (paused) {
                    this.videoPlayer.pause();
                    return;
                } else {
                    this.videoPlayer.play();
                    return;
                }
            }
            boolean post;
            if (this.audioPlayer != null) {
                post = true;
            } else {
                post = false;
            }
            MessageObject currentMessageObject = this.playingMessageObject;
            float progress = this.playingMessageObject.audioProgress;
            cleanupPlayer(false, true);
            currentMessageObject.audioProgress = progress;
            playMessage(currentMessageObject);
            if (!paused) {
                return;
            }
            if (post) {
                AndroidUtilities.runOnUIThread(new MediaController$9(this, currentMessageObject), 100);
            } else {
                pauseMessage(currentMessageObject);
            }
        }
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void setInputFieldHasText(boolean value) {
        this.inputFieldHasText = value;
    }

    public void setAllowStartRecord(boolean value) {
        this.allowStartRecord = value;
    }

    public void startRaiseToEarSensors(ChatActivity chatActivity) {
        if (chatActivity == null) {
            return;
        }
        if ((this.accelerometerSensor != null || (this.gravitySensor != null && this.linearAcceleration != null)) && this.proximitySensor != null) {
            this.raiseChat = chatActivity;
            if (!SharedConfig.raiseToSpeak) {
                if (this.playingMessageObject == null) {
                    return;
                }
                if (!(this.playingMessageObject.isVoice() || this.playingMessageObject.isRoundVideo())) {
                    return;
                }
            }
            if (!this.sensorsStarted) {
                float[] fArr = this.gravity;
                float[] fArr2 = this.gravity;
                this.gravity[2] = 0.0f;
                fArr2[1] = 0.0f;
                fArr[0] = 0.0f;
                fArr = this.linearAcceleration;
                fArr2 = this.linearAcceleration;
                this.linearAcceleration[2] = 0.0f;
                fArr2[1] = 0.0f;
                fArr[0] = 0.0f;
                fArr = this.gravityFast;
                fArr2 = this.gravityFast;
                this.gravityFast[2] = 0.0f;
                fArr2[1] = 0.0f;
                fArr[0] = 0.0f;
                this.lastTimestamp = 0;
                this.previousAccValue = 0.0f;
                this.raisedToTop = 0;
                this.raisedToTopSign = 0;
                this.countLess = 0;
                this.raisedToBack = 0;
                Utilities.globalQueue.postRunnable(new MediaController$10(this));
                this.sensorsStarted = true;
            }
        }
    }

    public void stopRaiseToEarSensors(ChatActivity chatActivity, boolean fromChat) {
        if (this.ignoreOnPause) {
            this.ignoreOnPause = false;
            return;
        }
        stopRecording(fromChat ? 2 : 0);
        if (this.sensorsStarted && !this.ignoreOnPause) {
            if ((this.accelerometerSensor != null || (this.gravitySensor != null && this.linearAcceleration != null)) && this.proximitySensor != null && this.raiseChat == chatActivity) {
                this.raiseChat = null;
                this.sensorsStarted = false;
                this.accelerometerVertical = false;
                this.proximityTouched = false;
                this.raiseToEarRecord = false;
                this.useFrontSpeaker = false;
                Utilities.globalQueue.postRunnable(new MediaController$11(this));
                if (this.proximityHasDifferentValues && this.proximityWakeLock != null && this.proximityWakeLock.isHeld()) {
                    this.proximityWakeLock.release();
                }
            }
        }
    }

    public void cleanupPlayer(boolean notify, boolean stopService) {
        cleanupPlayer(notify, stopService, false);
    }

    public void cleanupPlayer(boolean notify, boolean stopService, boolean byVoiceEnd) {
        if (this.audioPlayer != null) {
            try {
                this.audioPlayer.releasePlayer();
            } catch (Throwable e) {
                FileLog.m1224e(e);
            }
            this.audioPlayer = null;
        } else if (this.videoPlayer != null) {
            this.currentAspectRatioFrameLayout = null;
            this.currentTextureViewContainer = null;
            this.currentAspectRatioFrameLayoutReady = false;
            this.currentTextureView = null;
            this.videoPlayer.releasePlayer();
            this.videoPlayer = null;
            try {
                this.baseActivity.getWindow().clearFlags(128);
            } catch (Throwable e2) {
                FileLog.m1224e(e2);
            }
        }
        stopProgressTimer();
        this.lastProgress = 0;
        this.isPaused = false;
        if (!(this.useFrontSpeaker || SharedConfig.raiseToSpeak)) {
            ChatActivity chat = this.raiseChat;
            stopRaiseToEarSensors(this.raiseChat, false);
            this.raiseChat = chat;
        }
        if (this.playingMessageObject != null) {
            if (this.downloadingCurrentMessage) {
                FileLoader.getInstance(this.playingMessageObject.currentAccount).cancelLoadFile(this.playingMessageObject.getDocument());
            }
            MessageObject lastFile = this.playingMessageObject;
            if (notify) {
                this.playingMessageObject.resetPlayingProgress();
                NotificationCenter.getInstance(lastFile.currentAccount).postNotificationName(NotificationCenter.messagePlayingProgressDidChanged, new Object[]{Integer.valueOf(this.playingMessageObject.getId()), Integer.valueOf(0)});
            }
            this.playingMessageObject = null;
            this.downloadingCurrentMessage = false;
            if (notify) {
                NotificationsController.audioManager.abandonAudioFocus(this);
                this.hasAudioFocus = 0;
                int index = -1;
                if (this.voiceMessagesPlaylist != null) {
                    if (byVoiceEnd) {
                        index = this.voiceMessagesPlaylist.indexOf(lastFile);
                        if (index >= 0) {
                            this.voiceMessagesPlaylist.remove(index);
                            this.voiceMessagesPlaylistMap.remove(lastFile.getId());
                            if (this.voiceMessagesPlaylist.isEmpty()) {
                                this.voiceMessagesPlaylist = null;
                                this.voiceMessagesPlaylistMap = null;
                            }
                        }
                    }
                    this.voiceMessagesPlaylist = null;
                    this.voiceMessagesPlaylistMap = null;
                }
                if (this.voiceMessagesPlaylist == null || index >= this.voiceMessagesPlaylist.size()) {
                    if ((lastFile.isVoice() || lastFile.isRoundVideo()) && lastFile.getId() != 0) {
                        startRecordingIfFromSpeaker();
                    }
                    NotificationCenter.getInstance(lastFile.currentAccount).postNotificationName(NotificationCenter.messagePlayingDidReset, new Object[]{Integer.valueOf(lastFile.getId()), Boolean.valueOf(stopService)});
                    this.pipSwitchingState = 0;
                    if (this.pipRoundVideoView != null) {
                        this.pipRoundVideoView.close(true);
                        this.pipRoundVideoView = null;
                    }
                } else {
                    MessageObject nextVoiceMessage = (MessageObject) this.voiceMessagesPlaylist.get(index);
                    playMessage(nextVoiceMessage);
                    if (!(nextVoiceMessage.isRoundVideo() || this.pipRoundVideoView == null)) {
                        this.pipRoundVideoView.close(true);
                        this.pipRoundVideoView = null;
                    }
                }
            }
            if (stopService) {
                ApplicationLoader.applicationContext.stopService(new Intent(ApplicationLoader.applicationContext, MusicPlayerService.class));
            }
        }
    }

    private boolean isSamePlayingMessage(MessageObject messageObject) {
        if (this.playingMessageObject != null && this.playingMessageObject.getDialogId() == messageObject.getDialogId() && this.playingMessageObject.getId() == messageObject.getId()) {
            if ((this.playingMessageObject.eventId == 0) == (messageObject.eventId == 0)) {
                return true;
            }
        }
        return false;
    }

    public boolean seekToProgress(MessageObject messageObject, float progress) {
        if ((this.audioPlayer == null && this.videoPlayer == null) || messageObject == null || this.playingMessageObject == null || !isSamePlayingMessage(messageObject)) {
            return false;
        }
        try {
            if (this.audioPlayer != null) {
                long duration = this.audioPlayer.getDuration();
                if (duration == C0246C.TIME_UNSET) {
                    this.seekToProgressPending = progress;
                } else {
                    int seekTo = (int) (((float) duration) * progress);
                    this.audioPlayer.seekTo((long) seekTo);
                    this.lastProgress = (long) seekTo;
                }
            } else if (this.videoPlayer != null) {
                this.videoPlayer.seekTo((long) (((float) this.videoPlayer.getDuration()) * progress));
            }
            NotificationCenter.getInstance(messageObject.currentAccount).postNotificationName(NotificationCenter.messagePlayingDidSeek, new Object[]{Integer.valueOf(this.playingMessageObject.getId()), Float.valueOf(progress)});
            return true;
        } catch (Throwable e) {
            FileLog.m1224e(e);
            return false;
        }
    }

    public MessageObject getPlayingMessageObject() {
        return this.playingMessageObject;
    }

    public int getPlayingMessageObjectNum() {
        return this.currentPlaylistNum;
    }

    private void buildShuffledPlayList() {
        if (!this.playlist.isEmpty()) {
            ArrayList<MessageObject> all = new ArrayList(this.playlist);
            this.shuffledPlaylist.clear();
            MessageObject messageObject = (MessageObject) this.playlist.get(this.currentPlaylistNum);
            all.remove(this.currentPlaylistNum);
            this.shuffledPlaylist.add(messageObject);
            int count = all.size();
            for (int a = 0; a < count; a++) {
                int index = Utilities.random.nextInt(all.size());
                this.shuffledPlaylist.add(all.get(index));
                all.remove(index);
            }
        }
    }

    public boolean setPlaylist(ArrayList<MessageObject> messageObjects, MessageObject current) {
        return setPlaylist(messageObjects, current, true);
    }

    public boolean setPlaylist(ArrayList<MessageObject> messageObjects, MessageObject current, boolean loadMusic) {
        boolean z = true;
        if (this.playingMessageObject == current) {
            return playMessage(current);
        }
        boolean z2;
        if (loadMusic) {
            z2 = false;
        } else {
            z2 = true;
        }
        this.forceLoopCurrentPlaylist = z2;
        if (this.playlist.isEmpty()) {
            z = false;
        }
        this.playMusicAgain = z;
        this.playlist.clear();
        for (int a = messageObjects.size() - 1; a >= 0; a--) {
            MessageObject messageObject = (MessageObject) messageObjects.get(a);
            if (messageObject.isMusic()) {
                this.playlist.add(messageObject);
            }
        }
        this.currentPlaylistNum = this.playlist.indexOf(current);
        if (this.currentPlaylistNum == -1) {
            this.playlist.clear();
            this.shuffledPlaylist.clear();
            this.currentPlaylistNum = this.playlist.size();
            this.playlist.add(current);
        }
        if (current.isMusic()) {
            if (SharedConfig.shuffleMusic) {
                buildShuffledPlayList();
                this.currentPlaylistNum = 0;
            }
            if (loadMusic) {
                DataQuery.getInstance(current.currentAccount).loadMusic(current.getDialogId(), ((MessageObject) this.playlist.get(0)).getIdWithChannel());
            }
        }
        return playMessage(current);
    }

    public void playNextMessage() {
        playNextMessageWithoutOrder(false);
    }

    public boolean findMessageInPlaylistAndPlay(MessageObject messageObject) {
        int index = this.playlist.indexOf(messageObject);
        if (index == -1) {
            return playMessage(messageObject);
        }
        playMessageAtIndex(index);
        return true;
    }

    public void playMessageAtIndex(int index) {
        if (this.currentPlaylistNum >= 0 && this.currentPlaylistNum < this.playlist.size()) {
            this.currentPlaylistNum = index;
            this.playMusicAgain = true;
            if (this.playingMessageObject != null) {
                this.playingMessageObject.resetPlayingProgress();
            }
            playMessage((MessageObject) this.playlist.get(this.currentPlaylistNum));
        }
    }

    private void playNextMessageWithoutOrder(boolean byStop) {
        ArrayList<MessageObject> currentPlayList = SharedConfig.shuffleMusic ? this.shuffledPlaylist : this.playlist;
        if (byStop && SharedConfig.repeatMode == 2 && !this.forceLoopCurrentPlaylist) {
            cleanupPlayer(false, false);
            MessageObject messageObject = (MessageObject) currentPlayList.get(this.currentPlaylistNum);
            messageObject.audioProgress = 0.0f;
            messageObject.audioProgressSec = 0;
            playMessage(messageObject);
            return;
        }
        boolean last = false;
        if (SharedConfig.playOrderReversed) {
            this.currentPlaylistNum++;
            if (this.currentPlaylistNum >= currentPlayList.size()) {
                this.currentPlaylistNum = 0;
                last = true;
            }
        } else {
            this.currentPlaylistNum--;
            if (this.currentPlaylistNum < 0) {
                this.currentPlaylistNum = currentPlayList.size() - 1;
                last = true;
            }
        }
        if (last && byStop && SharedConfig.repeatMode == 0 && !this.forceLoopCurrentPlaylist) {
            if (this.audioPlayer != null || this.videoPlayer != null) {
                if (this.audioPlayer != null) {
                    try {
                        this.audioPlayer.releasePlayer();
                    } catch (Throwable e) {
                        FileLog.m1224e(e);
                    }
                    this.audioPlayer = null;
                } else if (this.videoPlayer != null) {
                    this.currentAspectRatioFrameLayout = null;
                    this.currentTextureViewContainer = null;
                    this.currentAspectRatioFrameLayoutReady = false;
                    this.currentTextureView = null;
                    this.videoPlayer.releasePlayer();
                    this.videoPlayer = null;
                    try {
                        this.baseActivity.getWindow().clearFlags(128);
                    } catch (Throwable e2) {
                        FileLog.m1224e(e2);
                    }
                }
                stopProgressTimer();
                this.lastProgress = 0;
                this.isPaused = true;
                this.playingMessageObject.audioProgress = 0.0f;
                this.playingMessageObject.audioProgressSec = 0;
                NotificationCenter.getInstance(this.playingMessageObject.currentAccount).postNotificationName(NotificationCenter.messagePlayingProgressDidChanged, new Object[]{Integer.valueOf(this.playingMessageObject.getId()), Integer.valueOf(0)});
                NotificationCenter.getInstance(this.playingMessageObject.currentAccount).postNotificationName(NotificationCenter.messagePlayingPlayStateChanged, new Object[]{Integer.valueOf(this.playingMessageObject.getId())});
            }
        } else if (this.currentPlaylistNum >= 0 && this.currentPlaylistNum < currentPlayList.size()) {
            if (this.playingMessageObject != null) {
                this.playingMessageObject.resetPlayingProgress();
            }
            this.playMusicAgain = true;
            playMessage((MessageObject) currentPlayList.get(this.currentPlaylistNum));
        }
    }

    public void playPreviousMessage() {
        ArrayList<MessageObject> currentPlayList = SharedConfig.shuffleMusic ? this.shuffledPlaylist : this.playlist;
        if (!currentPlayList.isEmpty() && this.currentPlaylistNum >= 0 && this.currentPlaylistNum < currentPlayList.size()) {
            MessageObject currentSong = (MessageObject) currentPlayList.get(this.currentPlaylistNum);
            if (currentSong.audioProgressSec > 10) {
                seekToProgress(currentSong, 0.0f);
                return;
            }
            if (SharedConfig.playOrderReversed) {
                this.currentPlaylistNum--;
                if (this.currentPlaylistNum < 0) {
                    this.currentPlaylistNum = currentPlayList.size() - 1;
                }
            } else {
                this.currentPlaylistNum++;
                if (this.currentPlaylistNum >= currentPlayList.size()) {
                    this.currentPlaylistNum = 0;
                }
            }
            if (this.currentPlaylistNum >= 0 && this.currentPlaylistNum < currentPlayList.size()) {
                this.playMusicAgain = true;
                playMessage((MessageObject) currentPlayList.get(this.currentPlaylistNum));
            }
        }
    }

    protected void checkIsNextMediaFileDownloaded() {
        if (this.playingMessageObject != null && this.playingMessageObject.isMusic()) {
            checkIsNextMusicFileDownloaded(this.playingMessageObject.currentAccount);
        }
    }

    private void checkIsNextVoiceFileDownloaded(int currentAccount) {
        if (this.voiceMessagesPlaylist != null && this.voiceMessagesPlaylist.size() >= 2) {
            MessageObject nextAudio = (MessageObject) this.voiceMessagesPlaylist.get(true);
            File file = null;
            if (nextAudio.messageOwner.attachPath != null && nextAudio.messageOwner.attachPath.length() > 0) {
                file = new File(nextAudio.messageOwner.attachPath);
                if (!file.exists()) {
                    file = null;
                }
            }
            File cacheFile = file != null ? file : FileLoader.getPathToMessage(nextAudio.messageOwner);
            if (cacheFile == null || !cacheFile.exists()) {
                boolean z = false;
            }
            if (cacheFile != null && cacheFile != file && !cacheFile.exists()) {
                FileLoader.getInstance(currentAccount).loadFile(nextAudio.getDocument(), false, 0);
            }
        }
    }

    private void checkIsNextMusicFileDownloaded(int currentAccount) {
        if ((DownloadController.getInstance(currentAccount).getCurrentDownloadMask() & 16) != 0) {
            ArrayList<MessageObject> currentPlayList = SharedConfig.shuffleMusic ? this.shuffledPlaylist : this.playlist;
            if (currentPlayList != null && currentPlayList.size() >= 2) {
                int nextIndex;
                if (SharedConfig.playOrderReversed) {
                    nextIndex = this.currentPlaylistNum + 1;
                    if (nextIndex >= currentPlayList.size()) {
                        nextIndex = 0;
                    }
                } else {
                    nextIndex = this.currentPlaylistNum - 1;
                    if (nextIndex < 0) {
                        nextIndex = currentPlayList.size() - 1;
                    }
                }
                MessageObject nextAudio = (MessageObject) currentPlayList.get(nextIndex);
                if (DownloadController.getInstance(currentAccount).canDownloadMedia(nextAudio)) {
                    File file = null;
                    if (!TextUtils.isEmpty(nextAudio.messageOwner.attachPath)) {
                        file = new File(nextAudio.messageOwner.attachPath);
                        if (!file.exists()) {
                            file = null;
                        }
                    }
                    File cacheFile = file != null ? file : FileLoader.getPathToMessage(nextAudio.messageOwner);
                    if (cacheFile == null || !cacheFile.exists()) {
                        boolean z = false;
                    }
                    if (cacheFile != null && cacheFile != file && !cacheFile.exists() && nextAudio.isMusic()) {
                        FileLoader.getInstance(currentAccount).loadFile(nextAudio.getDocument(), false, 0);
                    }
                }
            }
        }
    }

    public void setVoiceMessagesPlaylist(ArrayList<MessageObject> playlist, boolean unread) {
        this.voiceMessagesPlaylist = playlist;
        if (this.voiceMessagesPlaylist != null) {
            this.voiceMessagesPlaylistUnread = unread;
            this.voiceMessagesPlaylistMap = new SparseArray();
            for (int a = 0; a < this.voiceMessagesPlaylist.size(); a++) {
                MessageObject messageObject = (MessageObject) this.voiceMessagesPlaylist.get(a);
                this.voiceMessagesPlaylistMap.put(messageObject.getId(), messageObject);
            }
        }
    }

    private void checkAudioFocus(MessageObject messageObject) {
        int neededAudioFocus;
        if (!messageObject.isVoice() && !messageObject.isRoundVideo()) {
            neededAudioFocus = 1;
        } else if (this.useFrontSpeaker) {
            neededAudioFocus = 3;
        } else {
            neededAudioFocus = 2;
        }
        if (this.hasAudioFocus != neededAudioFocus) {
            int result;
            this.hasAudioFocus = neededAudioFocus;
            if (neededAudioFocus == 3) {
                result = NotificationsController.audioManager.requestAudioFocus(this, 0, 1);
            } else {
                result = NotificationsController.audioManager.requestAudioFocus(this, 3, neededAudioFocus == 2 ? 3 : 1);
            }
            if (result == 1) {
                this.audioFocus = 2;
            }
        }
    }

    public void setCurrentRoundVisible(boolean visible) {
        if (this.currentAspectRatioFrameLayout != null) {
            if (visible) {
                if (this.pipRoundVideoView != null) {
                    this.pipSwitchingState = 2;
                    this.pipRoundVideoView.close(true);
                    this.pipRoundVideoView = null;
                } else if (this.currentAspectRatioFrameLayout != null) {
                    if (this.currentAspectRatioFrameLayout.getParent() == null) {
                        this.currentTextureViewContainer.addView(this.currentAspectRatioFrameLayout);
                    }
                    this.videoPlayer.setTextureView(this.currentTextureView);
                }
            } else if (this.currentAspectRatioFrameLayout.getParent() != null) {
                this.pipSwitchingState = 1;
                this.currentTextureViewContainer.removeView(this.currentAspectRatioFrameLayout);
            } else {
                if (this.pipRoundVideoView == null) {
                    try {
                        this.pipRoundVideoView = new PipRoundVideoView();
                        this.pipRoundVideoView.show(this.baseActivity, new MediaController$12(this));
                    } catch (Exception e) {
                        this.pipRoundVideoView = null;
                    }
                }
                if (this.pipRoundVideoView != null) {
                    this.videoPlayer.setTextureView(this.pipRoundVideoView.getTextureView());
                }
            }
        }
    }

    public void setTextureView(TextureView textureView, AspectRatioFrameLayout aspectRatioFrameLayout, FrameLayout container, boolean set) {
        boolean z = true;
        if (textureView != null) {
            if (!set && this.currentTextureView == textureView) {
                this.pipSwitchingState = 1;
                this.currentTextureView = null;
                this.currentAspectRatioFrameLayout = null;
                this.currentTextureViewContainer = null;
            } else if (this.videoPlayer != null && textureView != this.currentTextureView) {
                if (aspectRatioFrameLayout == null || !aspectRatioFrameLayout.isDrawingReady()) {
                    z = false;
                }
                this.isDrawingWasReady = z;
                this.currentTextureView = textureView;
                if (this.pipRoundVideoView != null) {
                    this.videoPlayer.setTextureView(this.pipRoundVideoView.getTextureView());
                } else {
                    this.videoPlayer.setTextureView(this.currentTextureView);
                }
                this.currentAspectRatioFrameLayout = aspectRatioFrameLayout;
                this.currentTextureViewContainer = container;
                if (this.currentAspectRatioFrameLayoutReady && this.currentAspectRatioFrameLayout != null) {
                    if (this.currentAspectRatioFrameLayout != null) {
                        this.currentAspectRatioFrameLayout.setAspectRatio(this.currentAspectRatioFrameLayoutRatio, this.currentAspectRatioFrameLayoutRotation);
                    }
                    if (this.currentTextureViewContainer.getVisibility() != 0) {
                        this.currentTextureViewContainer.setVisibility(0);
                    }
                }
            }
        }
    }

    public void setFlagSecure(BaseFragment parentFragment, boolean set) {
        if (set) {
            try {
                parentFragment.getParentActivity().getWindow().setFlags(8192, 8192);
            } catch (Exception e) {
            }
            this.flagSecureFragment = parentFragment;
        } else if (this.flagSecureFragment == parentFragment) {
            try {
                parentFragment.getParentActivity().getWindow().clearFlags(8192);
            } catch (Exception e2) {
            }
            this.flagSecureFragment = null;
        }
    }

    public void setBaseActivity(Activity activity, boolean set) {
        if (set) {
            this.baseActivity = activity;
        } else if (this.baseActivity == activity) {
            this.baseActivity = null;
        }
    }

    public void setFeedbackView(View view, boolean set) {
        if (set) {
            this.feedbackView = view;
        } else if (this.feedbackView == view) {
            this.feedbackView = null;
        }
    }

    public void setPlaybackSpeed(float speed) {
        this.currentPlaybackSpeed = speed;
        if (this.audioPlayer != null) {
            this.audioPlayer.setPlaybackSpeed(this.currentPlaybackSpeed);
        } else if (this.videoPlayer != null) {
            this.videoPlayer.setPlaybackSpeed(this.currentPlaybackSpeed);
        }
        MessagesController.getGlobalMainSettings().edit().putFloat("playbackSpeed", speed).commit();
    }

    public float getPlaybackSpeed() {
        return this.currentPlaybackSpeed;
    }

    public boolean playMessage(MessageObject messageObject) {
        if (messageObject == null) {
            return false;
        }
        if (!(this.audioPlayer == null && this.videoPlayer == null) && isSamePlayingMessage(messageObject)) {
            if (this.isPaused) {
                resumeAudio(messageObject);
            }
            if (!SharedConfig.raiseToSpeak) {
                startRaiseToEarSensors(this.raiseChat);
            }
            return true;
        }
        if (!messageObject.isOut() && messageObject.isContentUnread()) {
            MessagesController.getInstance(messageObject.currentAccount).markMessageContentAsRead(messageObject);
        }
        boolean notify = !this.playMusicAgain;
        if (this.playingMessageObject != null) {
            notify = false;
            if (!this.playMusicAgain) {
                this.playingMessageObject.resetPlayingProgress();
            }
        }
        cleanupPlayer(notify, false);
        this.playMusicAgain = false;
        this.seekToProgressPending = 0.0f;
        File file = null;
        boolean exists = false;
        if (messageObject.messageOwner.attachPath != null && messageObject.messageOwner.attachPath.length() > 0) {
            file = new File(messageObject.messageOwner.attachPath);
            exists = file.exists();
            if (!exists) {
                file = null;
            }
        }
        File cacheFile = file != null ? file : FileLoader.getPathToMessage(messageObject.messageOwner);
        boolean canStream = SharedConfig.streamMedia && messageObject.isMusic() && ((int) messageObject.getDialogId()) != 0;
        if (!(cacheFile == null || cacheFile == file)) {
            exists = cacheFile.exists();
            if (!(exists || canStream)) {
                FileLoader.getInstance(messageObject.currentAccount).loadFile(messageObject.getDocument(), false, 0);
                this.downloadingCurrentMessage = true;
                this.isPaused = false;
                this.lastProgress = 0;
                this.audioInfo = null;
                this.playingMessageObject = messageObject;
                if (this.playingMessageObject.isMusic()) {
                    try {
                        ApplicationLoader.applicationContext.startService(new Intent(ApplicationLoader.applicationContext, MusicPlayerService.class));
                    } catch (Throwable e) {
                        FileLog.m1224e(e);
                    }
                } else {
                    ApplicationLoader.applicationContext.stopService(new Intent(ApplicationLoader.applicationContext, MusicPlayerService.class));
                }
                NotificationCenter.getInstance(this.playingMessageObject.currentAccount).postNotificationName(NotificationCenter.messagePlayingPlayStateChanged, new Object[]{Integer.valueOf(this.playingMessageObject.getId())});
                return true;
            }
        }
        this.downloadingCurrentMessage = false;
        if (messageObject.isMusic()) {
            checkIsNextMusicFileDownloaded(messageObject.currentAccount);
        } else {
            checkIsNextVoiceFileDownloaded(messageObject.currentAccount);
        }
        if (this.currentAspectRatioFrameLayout != null) {
            this.isDrawingWasReady = false;
            this.currentAspectRatioFrameLayout.setDrawingReady(false);
        }
        if (messageObject.isRoundVideo()) {
            this.playlist.clear();
            this.shuffledPlaylist.clear();
            this.videoPlayer = new VideoPlayer();
            this.videoPlayer.setDelegate(new MediaController$13(this));
            this.currentAspectRatioFrameLayoutReady = false;
            if (this.pipRoundVideoView == null) {
                if (MessagesController.getInstance(messageObject.currentAccount).isDialogVisible(messageObject.getDialogId())) {
                    if (this.currentTextureView != null) {
                        this.videoPlayer.setTextureView(this.currentTextureView);
                    }
                    this.videoPlayer.preparePlayer(Uri.fromFile(cacheFile), "other");
                    this.videoPlayer.setStreamType(this.useFrontSpeaker ? 0 : 3);
                    if (this.currentPlaybackSpeed > VOLUME_NORMAL) {
                        this.videoPlayer.setPlaybackSpeed(this.currentPlaybackSpeed);
                    }
                    this.videoPlayer.play();
                }
            }
            if (this.pipRoundVideoView == null) {
                try {
                    this.pipRoundVideoView = new PipRoundVideoView();
                    this.pipRoundVideoView.show(this.baseActivity, new MediaController$14(this));
                } catch (Exception e2) {
                    this.pipRoundVideoView = null;
                }
            }
            if (this.pipRoundVideoView != null) {
                this.videoPlayer.setTextureView(this.pipRoundVideoView.getTextureView());
            }
            this.videoPlayer.preparePlayer(Uri.fromFile(cacheFile), "other");
            if (this.useFrontSpeaker) {
            }
            this.videoPlayer.setStreamType(this.useFrontSpeaker ? 0 : 3);
            if (this.currentPlaybackSpeed > VOLUME_NORMAL) {
                this.videoPlayer.setPlaybackSpeed(this.currentPlaybackSpeed);
            }
            this.videoPlayer.play();
        } else {
            if (this.pipRoundVideoView != null) {
                this.pipRoundVideoView.close(true);
                this.pipRoundVideoView = null;
            }
            try {
                this.audioPlayer = new VideoPlayer();
                this.audioPlayer.setDelegate(new MediaController$15(this, messageObject));
                if (exists) {
                    if (!(messageObject.mediaExists || cacheFile == file)) {
                        AndroidUtilities.runOnUIThread(new MediaController$16(this, messageObject));
                    }
                    this.audioPlayer.preparePlayer(Uri.fromFile(cacheFile), "other");
                } else {
                    TLRPC$Document document = messageObject.getDocument();
                    this.audioPlayer.preparePlayer(Uri.parse("tg://" + messageObject.getFileName() + ("?account=" + messageObject.currentAccount + "&id=" + document.id + "&hash=" + document.access_hash + "&dc=" + document.dc_id + "&size=" + document.size + "&mime=" + URLEncoder.encode(document.mime_type, "UTF-8") + "&name=" + URLEncoder.encode(FileLoader.getDocumentFileName(document), "UTF-8"))), "other");
                }
                if (messageObject.isVoice()) {
                    if (this.currentPlaybackSpeed > VOLUME_NORMAL) {
                        this.audioPlayer.setPlaybackSpeed(this.currentPlaybackSpeed);
                    }
                    this.audioInfo = null;
                    this.playlist.clear();
                    this.shuffledPlaylist.clear();
                } else {
                    try {
                        this.audioInfo = AudioInfo.getAudioInfo(cacheFile);
                    } catch (Throwable e3) {
                        FileLog.m1224e(e3);
                    }
                }
                this.audioPlayer.setStreamType(this.useFrontSpeaker ? 0 : 3);
                this.audioPlayer.play();
            } catch (Throwable e32) {
                FileLog.m1224e(e32);
                NotificationCenter instance = NotificationCenter.getInstance(messageObject.currentAccount);
                int i = NotificationCenter.messagePlayingPlayStateChanged;
                Object[] objArr = new Object[1];
                objArr[0] = Integer.valueOf(this.playingMessageObject != null ? this.playingMessageObject.getId() : 0);
                instance.postNotificationName(i, objArr);
                if (this.audioPlayer != null) {
                    this.audioPlayer.releasePlayer();
                    this.audioPlayer = null;
                    this.isPaused = false;
                    this.playingMessageObject = null;
                    this.downloadingCurrentMessage = false;
                }
                return false;
            }
        }
        checkAudioFocus(messageObject);
        setPlayerVolume();
        this.isPaused = false;
        this.lastProgress = 0;
        this.playingMessageObject = messageObject;
        if (!SharedConfig.raiseToSpeak) {
            startRaiseToEarSensors(this.raiseChat);
        }
        startProgressTimer(this.playingMessageObject);
        NotificationCenter.getInstance(messageObject.currentAccount).postNotificationName(NotificationCenter.messagePlayingDidStarted, new Object[]{messageObject});
        long duration;
        if (this.videoPlayer != null) {
            try {
                if (this.playingMessageObject.audioProgress != 0.0f) {
                    duration = this.audioPlayer.getDuration();
                    if (duration == C0246C.TIME_UNSET) {
                        duration = ((long) this.playingMessageObject.getDuration()) * 1000;
                    }
                    this.videoPlayer.seekTo((long) ((int) (((float) duration) * this.playingMessageObject.audioProgress)));
                }
            } catch (Throwable e22) {
                this.playingMessageObject.audioProgress = 0.0f;
                this.playingMessageObject.audioProgressSec = 0;
                NotificationCenter.getInstance(messageObject.currentAccount).postNotificationName(NotificationCenter.messagePlayingProgressDidChanged, new Object[]{Integer.valueOf(this.playingMessageObject.getId()), Integer.valueOf(0)});
                FileLog.m1224e(e22);
            }
        } else if (this.audioPlayer != null) {
            try {
                if (this.playingMessageObject.audioProgress != 0.0f) {
                    duration = this.audioPlayer.getDuration();
                    if (duration == C0246C.TIME_UNSET) {
                        duration = ((long) this.playingMessageObject.getDuration()) * 1000;
                    }
                    this.audioPlayer.seekTo((long) ((int) (((float) duration) * this.playingMessageObject.audioProgress)));
                }
            } catch (Throwable e222) {
                this.playingMessageObject.resetPlayingProgress();
                NotificationCenter.getInstance(messageObject.currentAccount).postNotificationName(NotificationCenter.messagePlayingProgressDidChanged, new Object[]{Integer.valueOf(this.playingMessageObject.getId()), Integer.valueOf(0)});
                FileLog.m1224e(e222);
            }
        }
        if (this.playingMessageObject.isMusic()) {
            try {
                ApplicationLoader.applicationContext.startService(new Intent(ApplicationLoader.applicationContext, MusicPlayerService.class));
            } catch (Throwable e322) {
                FileLog.m1224e(e322);
            }
        } else {
            ApplicationLoader.applicationContext.stopService(new Intent(ApplicationLoader.applicationContext, MusicPlayerService.class));
        }
        return true;
    }

    public void stopAudio() {
        if ((this.audioPlayer != null || this.videoPlayer != null) && this.playingMessageObject != null) {
            try {
                if (this.audioPlayer != null) {
                    this.audioPlayer.pause();
                } else if (this.videoPlayer != null) {
                    this.videoPlayer.pause();
                }
            } catch (Throwable e) {
                FileLog.m1224e(e);
            }
            try {
                if (this.audioPlayer != null) {
                    this.audioPlayer.releasePlayer();
                    this.audioPlayer = null;
                } else if (this.videoPlayer != null) {
                    this.currentAspectRatioFrameLayout = null;
                    this.currentTextureViewContainer = null;
                    this.currentAspectRatioFrameLayoutReady = false;
                    this.currentTextureView = null;
                    this.videoPlayer.releasePlayer();
                    this.videoPlayer = null;
                    try {
                        this.baseActivity.getWindow().clearFlags(128);
                    } catch (Throwable e2) {
                        FileLog.m1224e(e2);
                    }
                }
            } catch (Throwable e22) {
                FileLog.m1224e(e22);
            }
            stopProgressTimer();
            this.playingMessageObject = null;
            this.downloadingCurrentMessage = false;
            this.isPaused = false;
            ApplicationLoader.applicationContext.stopService(new Intent(ApplicationLoader.applicationContext, MusicPlayerService.class));
        }
    }

    public AudioInfo getAudioInfo() {
        return this.audioInfo;
    }

    public void toggleShuffleMusic(int type) {
        boolean oldShuffle = SharedConfig.shuffleMusic;
        SharedConfig.toggleShuffleMusic(type);
        if (oldShuffle == SharedConfig.shuffleMusic) {
            return;
        }
        if (SharedConfig.shuffleMusic) {
            buildShuffledPlayList();
            this.currentPlaylistNum = 0;
        } else if (this.playingMessageObject != null) {
            this.currentPlaylistNum = this.playlist.indexOf(this.playingMessageObject);
            if (this.currentPlaylistNum == -1) {
                this.playlist.clear();
                this.shuffledPlaylist.clear();
                cleanupPlayer(true, true);
            }
        }
    }

    public boolean isCurrentPlayer(VideoPlayer player) {
        return this.videoPlayer == player || this.audioPlayer == player;
    }

    public boolean pauseMessage(MessageObject messageObject) {
        if ((this.audioPlayer == null && this.videoPlayer == null) || messageObject == null || this.playingMessageObject == null || !isSamePlayingMessage(messageObject)) {
            return false;
        }
        stopProgressTimer();
        try {
            if (this.audioPlayer != null) {
                this.audioPlayer.pause();
            } else if (this.videoPlayer != null) {
                this.videoPlayer.pause();
            }
            this.isPaused = true;
            NotificationCenter.getInstance(this.playingMessageObject.currentAccount).postNotificationName(NotificationCenter.messagePlayingPlayStateChanged, new Object[]{Integer.valueOf(this.playingMessageObject.getId())});
            return true;
        } catch (Throwable e) {
            FileLog.m1224e(e);
            this.isPaused = false;
            return false;
        }
    }

    public boolean resumeAudio(MessageObject messageObject) {
        if ((this.audioPlayer == null && this.videoPlayer == null) || messageObject == null || this.playingMessageObject == null || !isSamePlayingMessage(messageObject)) {
            return false;
        }
        try {
            startProgressTimer(this.playingMessageObject);
            if (this.audioPlayer != null) {
                this.audioPlayer.play();
            } else if (this.videoPlayer != null) {
                this.videoPlayer.play();
            }
            checkAudioFocus(messageObject);
            this.isPaused = false;
            NotificationCenter.getInstance(this.playingMessageObject.currentAccount).postNotificationName(NotificationCenter.messagePlayingPlayStateChanged, new Object[]{Integer.valueOf(this.playingMessageObject.getId())});
            return true;
        } catch (Throwable e) {
            FileLog.m1224e(e);
            return false;
        }
    }

    public boolean isRoundVideoDrawingReady() {
        return this.currentAspectRatioFrameLayout != null && this.currentAspectRatioFrameLayout.isDrawingReady();
    }

    public ArrayList<MessageObject> getPlaylist() {
        return this.playlist;
    }

    public boolean isPlayingMessage(MessageObject messageObject) {
        boolean z = true;
        if ((this.audioPlayer == null && this.videoPlayer == null) || messageObject == null || this.playingMessageObject == null) {
            return false;
        }
        if (this.playingMessageObject.eventId != 0 && this.playingMessageObject.eventId == messageObject.eventId) {
            if (this.downloadingCurrentMessage) {
                z = false;
            }
            return z;
        } else if (!isSamePlayingMessage(messageObject)) {
            return false;
        } else {
            if (this.downloadingCurrentMessage) {
                z = false;
            }
            return z;
        }
    }

    public boolean isMessagePaused() {
        return this.isPaused || this.downloadingCurrentMessage;
    }

    public boolean isDownloadingCurrentMessage() {
        return this.downloadingCurrentMessage;
    }

    public void setReplyingMessage(MessageObject reply_to_msg) {
        this.recordReplyingMessageObject = reply_to_msg;
    }

    public void startRecording(int currentAccount, long dialog_id, MessageObject reply_to_msg) {
        long j;
        boolean paused = false;
        if (!(this.playingMessageObject == null || !isPlayingMessage(this.playingMessageObject) || isMessagePaused())) {
            paused = true;
            pauseMessage(this.playingMessageObject);
        }
        try {
            this.feedbackView.performHapticFeedback(3, 2);
        } catch (Exception e) {
        }
        DispatchQueue dispatchQueue = this.recordQueue;
        Runnable mediaController$17 = new MediaController$17(this, currentAccount, dialog_id, reply_to_msg);
        this.recordStartRunnable = mediaController$17;
        if (paused) {
            j = 500;
        } else {
            j = 50;
        }
        dispatchQueue.postRunnable(mediaController$17, j);
    }

    public void generateWaveform(MessageObject messageObject) {
        String id = messageObject.getId() + "_" + messageObject.getDialogId();
        String path = FileLoader.getPathToMessage(messageObject.messageOwner).getAbsolutePath();
        if (!this.generatingWaveform.containsKey(id)) {
            this.generatingWaveform.put(id, messageObject);
            Utilities.globalQueue.postRunnable(new MediaController$18(this, path, id));
        }
    }

    private void stopRecordingInternal(int send) {
        if (send != 0) {
            this.fileEncodingQueue.postRunnable(new MediaController$19(this, this.recordingAudio, this.recordingAudioFile, send));
        }
        try {
            if (this.audioRecorder != null) {
                this.audioRecorder.release();
                this.audioRecorder = null;
            }
        } catch (Throwable e) {
            FileLog.m1224e(e);
        }
        this.recordingAudio = null;
        this.recordingAudioFile = null;
    }

    public void stopRecording(int send) {
        if (this.recordStartRunnable != null) {
            this.recordQueue.cancelRunnable(this.recordStartRunnable);
            this.recordStartRunnable = null;
        }
        this.recordQueue.postRunnable(new MediaController$20(this, send));
    }

    public static void saveFile(String fullPath, Context context, int type, String name, String mime) {
        Throwable e;
        if (fullPath != null) {
            File file = null;
            if (!(fullPath == null || fullPath.length() == 0)) {
                file = new File(fullPath);
                if (!file.exists() || AndroidUtilities.isInternalUri(Uri.fromFile(file))) {
                    file = null;
                }
            }
            if (file != null) {
                File sourceFile = file;
                boolean[] cancelled = new boolean[]{false};
                if (sourceFile.exists()) {
                    AlertDialog progressDialog = null;
                    if (!(context == null || type == 0)) {
                        try {
                            AlertDialog progressDialog2 = new AlertDialog(context, 2);
                            try {
                                progressDialog2.setMessage(LocaleController.getString("Loading", R.string.Loading));
                                progressDialog2.setCanceledOnTouchOutside(false);
                                progressDialog2.setCancelable(true);
                                progressDialog2.setOnCancelListener(new MediaController$21(cancelled));
                                progressDialog2.show();
                                progressDialog = progressDialog2;
                            } catch (Exception e2) {
                                e = e2;
                                progressDialog = progressDialog2;
                                FileLog.m1224e(e);
                                new Thread(new MediaController$22(type, name, sourceFile, cancelled, progressDialog, mime)).start();
                            }
                        } catch (Exception e3) {
                            e = e3;
                            FileLog.m1224e(e);
                            new Thread(new MediaController$22(type, name, sourceFile, cancelled, progressDialog, mime)).start();
                        }
                    }
                    new Thread(new MediaController$22(type, name, sourceFile, cancelled, progressDialog, mime)).start();
                }
            }
        }
    }

    public static boolean isWebp(Uri uri) {
        boolean z = false;
        InputStream inputStream = null;
        try {
            inputStream = ApplicationLoader.applicationContext.getContentResolver().openInputStream(uri);
            byte[] header = new byte[12];
            if (inputStream.read(header, 0, 12) == 12) {
                String str = new String(header);
                if (str != null) {
                    str = str.toLowerCase();
                    if (str.startsWith("riff") && str.endsWith("webp")) {
                        z = true;
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (Throwable e2) {
                                FileLog.m1224e(e2);
                            }
                        }
                        return z;
                    }
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable e22) {
                    FileLog.m1224e(e22);
                }
            }
        } catch (Throwable e) {
            FileLog.m1224e(e);
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable e222) {
                    FileLog.m1224e(e222);
                }
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable e2222) {
                    FileLog.m1224e(e2222);
                }
            }
        }
        return z;
    }

    public static boolean isGif(Uri uri) {
        boolean z = false;
        InputStream inputStream = null;
        try {
            inputStream = ApplicationLoader.applicationContext.getContentResolver().openInputStream(uri);
            byte[] header = new byte[3];
            if (inputStream.read(header, 0, 3) == 3) {
                String str = new String(header);
                if (str != null && str.equalsIgnoreCase("gif")) {
                    z = true;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable e2) {
                            FileLog.m1224e(e2);
                        }
                    }
                    return z;
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable e22) {
                    FileLog.m1224e(e22);
                }
            }
        } catch (Throwable e) {
            FileLog.m1224e(e);
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable e222) {
                    FileLog.m1224e(e222);
                }
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable e2222) {
                    FileLog.m1224e(e2222);
                }
            }
        }
        return z;
    }

    public static String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals(Param.CONTENT)) {
            Cursor cursor = null;
            try {
                cursor = ApplicationLoader.applicationContext.getContentResolver().query(uri, new String[]{"_display_name"}, null, null, null);
                if (cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex("_display_name"));
                }
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Throwable e) {
                FileLog.m1224e(e);
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        if (result != null) {
            return result;
        }
        result = uri.getPath();
        int cut = result.lastIndexOf(47);
        if (cut != -1) {
            return result.substring(cut + 1);
        }
        return result;
    }

    public static String copyFileToCache(Uri uri, String ext) {
        Throwable e;
        Throwable th;
        InputStream inputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            String name = FileLoader.fixFileName(getFileName(uri));
            if (name == null) {
                int id = SharedConfig.getLastLocalId();
                SharedConfig.saveConfig();
                name = String.format(Locale.US, "%d.%s", new Object[]{Integer.valueOf(id), ext});
            }
            File f = new File(FileLoader.getDirectory(4), "sharing/");
            f.mkdirs();
            File f2 = new File(f, name);
            if (AndroidUtilities.isInternalUri(Uri.fromFile(f2))) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable e2) {
                        FileLog.m1224e(e2);
                    }
                }
                if (fileOutputStream == null) {
                    return null;
                }
                try {
                    fileOutputStream.close();
                    return null;
                } catch (Throwable e22) {
                    FileLog.m1224e(e22);
                    return null;
                }
            }
            inputStream = ApplicationLoader.applicationContext.getContentResolver().openInputStream(uri);
            FileOutputStream output = new FileOutputStream(f2);
            try {
                byte[] buffer = new byte[CacheDataSink.DEFAULT_BUFFER_SIZE];
                while (true) {
                    int len = inputStream.read(buffer);
                    if (len == -1) {
                        break;
                    }
                    output.write(buffer, 0, len);
                }
                String absolutePath = f2.getAbsolutePath();
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable e222) {
                        FileLog.m1224e(e222);
                    }
                }
                if (output != null) {
                    try {
                        output.close();
                    } catch (Throwable e2222) {
                        FileLog.m1224e(e2222);
                    }
                }
                fileOutputStream = output;
                return absolutePath;
            } catch (Exception e3) {
                e = e3;
                fileOutputStream = output;
                try {
                    FileLog.m1224e(e);
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable e22222) {
                            FileLog.m1224e(e22222);
                        }
                    }
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Throwable e222222) {
                            FileLog.m1224e(e222222);
                        }
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable e2222222) {
                            FileLog.m1224e(e2222222);
                        }
                    }
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Throwable e22222222) {
                            FileLog.m1224e(e22222222);
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = output;
                if (inputStream != null) {
                    inputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
        } catch (Exception e4) {
            e = e4;
            FileLog.m1224e(e);
            if (inputStream != null) {
                inputStream.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            return null;
        }
    }

    public static void loadGalleryPhotosAlbums(int guid) {
        Thread thread = new Thread(new MediaController$23(guid));
        thread.setPriority(1);
        thread.start();
    }

    private static void broadcastNewPhotos(int guid, ArrayList<MediaController$AlbumEntry> mediaAlbumsSorted, ArrayList<MediaController$AlbumEntry> photoAlbumsSorted, Integer cameraAlbumIdFinal, MediaController$AlbumEntry allMediaAlbumFinal, MediaController$AlbumEntry allPhotosAlbumFinal, int delay) {
        if (broadcastPhotosRunnable != null) {
            AndroidUtilities.cancelRunOnUIThread(broadcastPhotosRunnable);
        }
        Runnable mediaController$24 = new MediaController$24(guid, mediaAlbumsSorted, photoAlbumsSorted, cameraAlbumIdFinal, allMediaAlbumFinal, allPhotosAlbumFinal);
        broadcastPhotosRunnable = mediaController$24;
        AndroidUtilities.runOnUIThread(mediaController$24, (long) delay);
    }

    public void scheduleVideoConvert(MessageObject messageObject) {
        scheduleVideoConvert(messageObject, false);
    }

    public boolean scheduleVideoConvert(MessageObject messageObject, boolean isEmpty) {
        if (messageObject == null || messageObject.videoEditedInfo == null) {
            return false;
        }
        if (isEmpty && !this.videoConvertQueue.isEmpty()) {
            return false;
        }
        if (isEmpty) {
            new File(messageObject.messageOwner.attachPath).delete();
        }
        this.videoConvertQueue.add(messageObject);
        if (this.videoConvertQueue.size() == 1) {
            startVideoConvertFromQueue();
        }
        return true;
    }

    public void cancelVideoConvert(MessageObject messageObject) {
        if (messageObject == null) {
            synchronized (this.videoConvertSync) {
                this.cancelCurrentVideoConversion = true;
            }
        } else if (!this.videoConvertQueue.isEmpty()) {
            int a = 0;
            while (a < this.videoConvertQueue.size()) {
                MessageObject object = (MessageObject) this.videoConvertQueue.get(a);
                if (object.getId() != messageObject.getId() || object.currentAccount != messageObject.currentAccount) {
                    a++;
                } else if (a == 0) {
                    synchronized (this.videoConvertSync) {
                        this.cancelCurrentVideoConversion = true;
                    }
                    return;
                } else {
                    this.videoConvertQueue.remove(a);
                    return;
                }
            }
        }
    }

    private boolean startVideoConvertFromQueue() {
        if (this.videoConvertQueue.isEmpty()) {
            return false;
        }
        synchronized (this.videoConvertSync) {
            this.cancelCurrentVideoConversion = false;
        }
        MessageObject messageObject = (MessageObject) this.videoConvertQueue.get(0);
        Intent intent = new Intent(ApplicationLoader.applicationContext, VideoEncodingService.class);
        intent.putExtra("path", messageObject.messageOwner.attachPath);
        intent.putExtra("currentAccount", messageObject.currentAccount);
        if (messageObject.messageOwner.media.document != null) {
            for (int a = 0; a < messageObject.messageOwner.media.document.attributes.size(); a++) {
                if (((TLRPC$DocumentAttribute) messageObject.messageOwner.media.document.attributes.get(a)) instanceof TLRPC$TL_documentAttributeAnimated) {
                    intent.putExtra("gif", true);
                    break;
                }
            }
        }
        if (messageObject.getId() != 0) {
            try {
                ApplicationLoader.applicationContext.startService(intent);
            } catch (Throwable e) {
                FileLog.m1224e(e);
            }
        }
        MediaController$VideoConvertRunnable.runConversion(messageObject);
        return true;
    }

    @SuppressLint({"NewApi"})
    public static MediaCodecInfo selectCodec(String mimeType) {
        MediaCodecInfo lastCodecInfo;
        int numCodecs = MediaCodecList.getCodecCount();
        MediaCodecInfo lastCodecInfo2 = null;
        for (int i = 0; i < numCodecs; i++) {
            MediaCodecInfo codecInfo = MediaCodecList.getCodecInfoAt(i);
            if (codecInfo.isEncoder()) {
                for (String type : codecInfo.getSupportedTypes()) {
                    if (type.equalsIgnoreCase(mimeType)) {
                        lastCodecInfo2 = codecInfo;
                        String name = lastCodecInfo2.getName();
                        if (name == null) {
                            continue;
                        } else if (!name.equals("OMX.SEC.avc.enc")) {
                            lastCodecInfo = lastCodecInfo2;
                            return lastCodecInfo2;
                        } else if (name.equals("OMX.SEC.AVC.Encoder")) {
                            lastCodecInfo = lastCodecInfo2;
                            return lastCodecInfo2;
                        }
                    }
                }
                continue;
            }
        }
        lastCodecInfo = lastCodecInfo2;
        return lastCodecInfo2;
    }

    private static boolean isRecognizedFormat(int colorFormat) {
        switch (colorFormat) {
            case 19:
            case 20:
            case 21:
            case 39:
            case 2130706688:
                return true;
            default:
                return false;
        }
    }

    @SuppressLint({"NewApi"})
    public static int selectColorFormat(MediaCodecInfo codecInfo, String mimeType) {
        CodecCapabilities capabilities = codecInfo.getCapabilitiesForType(mimeType);
        int lastColorFormat = 0;
        for (int colorFormat : capabilities.colorFormats) {
            if (isRecognizedFormat(colorFormat)) {
                lastColorFormat = colorFormat;
                if (!codecInfo.getName().equals("OMX.SEC.AVC.Encoder") || colorFormat != 19) {
                    return colorFormat;
                }
            }
        }
        int i = lastColorFormat;
        return lastColorFormat;
    }

    private int findTrack(MediaExtractor extractor, boolean audio) {
        int numTracks = extractor.getTrackCount();
        for (int i = 0; i < numTracks; i++) {
            String mime = extractor.getTrackFormat(i).getString("mime");
            if (audio) {
                if (mime.startsWith("audio/")) {
                    return i;
                }
            } else if (mime.startsWith("video/")) {
                return i;
            }
        }
        return -5;
    }

    private void didWriteData(MessageObject messageObject, File file, boolean last, boolean error) {
        boolean firstWrite = this.videoConvertFirstWrite;
        if (firstWrite) {
            this.videoConvertFirstWrite = false;
        }
        AndroidUtilities.runOnUIThread(new MediaController$25(this, error, last, messageObject, file, firstWrite));
    }

    private long readAndWriteTracks(MessageObject messageObject, MediaExtractor extractor, MP4Builder mediaMuxer, BufferInfo info, long start, long end, File file, boolean needAudio) throws Exception {
        int videoTrackIndex = findTrack(extractor, false);
        int audioTrackIndex = needAudio ? findTrack(extractor, true) : -1;
        int muxerVideoTrackIndex = -1;
        int muxerAudioTrackIndex = -1;
        boolean inputDone = false;
        int maxBufferSize = 0;
        if (videoTrackIndex >= 0) {
            extractor.selectTrack(videoTrackIndex);
            MediaFormat trackFormat = extractor.getTrackFormat(videoTrackIndex);
            muxerVideoTrackIndex = mediaMuxer.addTrack(trackFormat, false);
            maxBufferSize = trackFormat.getInteger("max-input-size");
            if (start > 0) {
                extractor.seekTo(start, 0);
            } else {
                extractor.seekTo(0, 0);
            }
        }
        if (audioTrackIndex >= 0) {
            extractor.selectTrack(audioTrackIndex);
            trackFormat = extractor.getTrackFormat(audioTrackIndex);
            muxerAudioTrackIndex = mediaMuxer.addTrack(trackFormat, true);
            maxBufferSize = Math.max(trackFormat.getInteger("max-input-size"), maxBufferSize);
            if (start > 0) {
                extractor.seekTo(start, 0);
            } else {
                extractor.seekTo(0, 0);
            }
        }
        ByteBuffer buffer = ByteBuffer.allocateDirect(maxBufferSize);
        if (audioTrackIndex < 0 && videoTrackIndex < 0) {
            return -1;
        }
        long startTime = -1;
        checkConversionCanceled();
        while (!inputDone) {
            int muxerTrackIndex;
            checkConversionCanceled();
            boolean eof = false;
            info.size = extractor.readSampleData(buffer, 0);
            int index = extractor.getSampleTrackIndex();
            if (index == videoTrackIndex) {
                muxerTrackIndex = muxerVideoTrackIndex;
            } else if (index == audioTrackIndex) {
                muxerTrackIndex = muxerAudioTrackIndex;
            } else {
                muxerTrackIndex = -1;
            }
            if (muxerTrackIndex != -1) {
                if (VERSION.SDK_INT < 21) {
                    buffer.position(0);
                    buffer.limit(info.size);
                }
                if (index != audioTrackIndex) {
                    byte[] array = buffer.array();
                    if (array != null) {
                        int offset = buffer.arrayOffset();
                        int len = offset + buffer.limit();
                        int writeStart = -1;
                        int a = offset;
                        while (a <= len - 4) {
                            if ((array[a] == (byte) 0 && array[a + 1] == (byte) 0 && array[a + 2] == (byte) 0 && array[a + 3] == (byte) 1) || a == len - 4) {
                                if (writeStart != -1) {
                                    int l = (a - writeStart) - (a != len + -4 ? 4 : 0);
                                    array[writeStart] = (byte) (l >> 24);
                                    array[writeStart + 1] = (byte) (l >> 16);
                                    array[writeStart + 2] = (byte) (l >> 8);
                                    array[writeStart + 3] = (byte) l;
                                    writeStart = a;
                                } else {
                                    writeStart = a;
                                }
                            }
                            a++;
                        }
                    }
                }
                if (info.size >= 0) {
                    info.presentationTimeUs = extractor.getSampleTime();
                } else {
                    info.size = 0;
                    eof = true;
                }
                if (info.size > 0 && !eof) {
                    if (index == videoTrackIndex && start > 0 && startTime == -1) {
                        startTime = info.presentationTimeUs;
                    }
                    if (end < 0 || info.presentationTimeUs < end) {
                        info.offset = 0;
                        info.flags = extractor.getSampleFlags();
                        if (mediaMuxer.writeSampleData(muxerTrackIndex, buffer, info, false)) {
                            didWriteData(messageObject, file, false, false);
                        }
                    } else {
                        eof = true;
                    }
                }
                if (!eof) {
                    extractor.advance();
                }
            } else if (index == -1) {
                eof = true;
            } else {
                extractor.advance();
            }
            if (eof) {
                inputDone = true;
            }
        }
        if (videoTrackIndex >= 0) {
            extractor.unselectTrack(videoTrackIndex);
        }
        if (audioTrackIndex < 0) {
            return startTime;
        }
        extractor.unselectTrack(audioTrackIndex);
        return startTime;
    }

    private void checkConversionCanceled() {
        synchronized (this.videoConvertSync) {
            boolean cancelConversion = this.cancelCurrentVideoConversion;
        }
        if (cancelConversion) {
            throw new RuntimeException("canceled conversion");
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean convertVideo(org.telegram.messenger.MessageObject r96) {
        /*
        r95 = this;
        r0 = r96;
        r6 = r0.videoEditedInfo;
        r0 = r6.originalPath;
        r91 = r0;
        r0 = r96;
        r6 = r0.videoEditedInfo;
        r0 = r6.startTime;
        r82 = r0;
        r0 = r96;
        r6 = r0.videoEditedInfo;
        r0 = r6.endTime;
        r18 = r0;
        r0 = r96;
        r6 = r0.videoEditedInfo;
        r0 = r6.resultWidth;
        r78 = r0;
        r0 = r96;
        r6 = r0.videoEditedInfo;
        r0 = r6.resultHeight;
        r76 = r0;
        r0 = r96;
        r6 = r0.videoEditedInfo;
        r0 = r6.rotationValue;
        r80 = r0;
        r0 = r96;
        r6 = r0.videoEditedInfo;
        r0 = r6.originalWidth;
        r67 = r0;
        r0 = r96;
        r6 = r0.videoEditedInfo;
        r0 = r6.originalHeight;
        r66 = r0;
        r0 = r96;
        r6 = r0.videoEditedInfo;
        r0 = r6.framerate;
        r51 = r0;
        r0 = r96;
        r6 = r0.videoEditedInfo;
        r0 = r6.bitrate;
        r28 = r0;
        r79 = 0;
        r10 = r96.getDialogId();
        r6 = (int) r10;
        if (r6 != 0) goto L_0x00e5;
    L_0x0059:
        r60 = 1;
    L_0x005b:
        r20 = new java.io.File;
        r0 = r96;
        r6 = r0.messageOwner;
        r6 = r6.attachPath;
        r0 = r20;
        r0.<init>(r6);
        if (r91 != 0) goto L_0x006d;
    L_0x006a:
        r91 = "";
    L_0x006d:
        r6 = android.os.Build.VERSION.SDK_INT;
        r10 = 18;
        if (r6 >= r10) goto L_0x00e9;
    L_0x0073:
        r0 = r76;
        r1 = r78;
        if (r0 <= r1) goto L_0x00e9;
    L_0x0079:
        r0 = r78;
        r1 = r67;
        if (r0 == r1) goto L_0x00e9;
    L_0x007f:
        r0 = r76;
        r1 = r66;
        if (r0 == r1) goto L_0x00e9;
    L_0x0085:
        r85 = r76;
        r76 = r78;
        r78 = r85;
        r80 = 90;
        r79 = 270; // 0x10e float:3.78E-43 double:1.334E-321;
    L_0x008f:
        r6 = org.telegram.messenger.ApplicationLoader.applicationContext;
        r10 = "videoconvert";
        r11 = 0;
        r74 = r6.getSharedPreferences(r10, r11);
        r56 = new java.io.File;
        r0 = r56;
        r1 = r91;
        r0.<init>(r1);
        r6 = r96.getId();
        if (r6 == 0) goto L_0x011d;
    L_0x00a8:
        r6 = "isPreviousOk";
        r10 = 1;
        r0 = r74;
        r59 = r0.getBoolean(r6, r10);
        r6 = r74.edit();
        r10 = "isPreviousOk";
        r11 = 0;
        r6 = r6.putBoolean(r10, r11);
        r6.commit();
        r6 = r56.canRead();
        if (r6 == 0) goto L_0x00c9;
    L_0x00c7:
        if (r59 != 0) goto L_0x011d;
    L_0x00c9:
        r6 = 1;
        r10 = 1;
        r0 = r95;
        r1 = r96;
        r2 = r20;
        r0.didWriteData(r1, r2, r6, r10);
        r6 = r74.edit();
        r10 = "isPreviousOk";
        r11 = 1;
        r6 = r6.putBoolean(r10, r11);
        r6.commit();
        r6 = 0;
    L_0x00e4:
        return r6;
    L_0x00e5:
        r60 = 0;
        goto L_0x005b;
    L_0x00e9:
        r6 = android.os.Build.VERSION.SDK_INT;
        r10 = 20;
        if (r6 <= r10) goto L_0x008f;
    L_0x00ef:
        r6 = 90;
        r0 = r80;
        if (r0 != r6) goto L_0x0100;
    L_0x00f5:
        r85 = r76;
        r76 = r78;
        r78 = r85;
        r80 = 0;
        r79 = 270; // 0x10e float:3.78E-43 double:1.334E-321;
        goto L_0x008f;
    L_0x0100:
        r6 = 180; // 0xb4 float:2.52E-43 double:8.9E-322;
        r0 = r80;
        if (r0 != r6) goto L_0x010b;
    L_0x0106:
        r79 = 180; // 0xb4 float:2.52E-43 double:8.9E-322;
        r80 = 0;
        goto L_0x008f;
    L_0x010b:
        r6 = 270; // 0x10e float:3.78E-43 double:1.334E-321;
        r0 = r80;
        if (r0 != r6) goto L_0x008f;
    L_0x0111:
        r85 = r76;
        r76 = r78;
        r78 = r85;
        r80 = 0;
        r79 = 90;
        goto L_0x008f;
    L_0x011d:
        r6 = 1;
        r0 = r95;
        r0.videoConvertFirstWrite = r6;
        r47 = 0;
        r86 = java.lang.System.currentTimeMillis();
        if (r78 == 0) goto L_0x096c;
    L_0x012a:
        if (r76 == 0) goto L_0x096c;
    L_0x012c:
        r63 = 0;
        r49 = 0;
        r53 = new android.media.MediaCodec$BufferInfo;	 Catch:{ Exception -> 0x098c }
        r53.<init>();	 Catch:{ Exception -> 0x098c }
        r64 = new org.telegram.messenger.video.Mp4Movie;	 Catch:{ Exception -> 0x098c }
        r64.<init>();	 Catch:{ Exception -> 0x098c }
        r0 = r64;
        r1 = r20;
        r0.setCacheFile(r1);	 Catch:{ Exception -> 0x098c }
        r0 = r64;
        r1 = r80;
        r0.setRotation(r1);	 Catch:{ Exception -> 0x098c }
        r0 = r64;
        r1 = r78;
        r2 = r76;
        r0.setSize(r1, r2);	 Catch:{ Exception -> 0x098c }
        r6 = new org.telegram.messenger.video.MP4Builder;	 Catch:{ Exception -> 0x098c }
        r6.<init>();	 Catch:{ Exception -> 0x098c }
        r0 = r64;
        r1 = r60;
        r63 = r6.createMovie(r0, r1);	 Catch:{ Exception -> 0x098c }
        r50 = new android.media.MediaExtractor;	 Catch:{ Exception -> 0x098c }
        r50.<init>();	 Catch:{ Exception -> 0x098c }
        r0 = r50;
        r1 = r91;
        r0.setDataSource(r1);	 Catch:{ Exception -> 0x0923, all -> 0x051d }
        r95.checkConversionCanceled();	 Catch:{ Exception -> 0x0923, all -> 0x051d }
        r0 = r78;
        r1 = r67;
        if (r0 != r1) goto L_0x0183;
    L_0x0173:
        r0 = r76;
        r1 = r66;
        if (r0 != r1) goto L_0x0183;
    L_0x0179:
        if (r79 != 0) goto L_0x0183;
    L_0x017b:
        r0 = r96;
        r6 = r0.videoEditedInfo;	 Catch:{ Exception -> 0x0923, all -> 0x051d }
        r6 = r6.roundVideo;	 Catch:{ Exception -> 0x0923, all -> 0x051d }
        if (r6 == 0) goto L_0x090b;
    L_0x0183:
        r6 = 0;
        r0 = r95;
        r1 = r50;
        r90 = r0.findTrack(r1, r6);	 Catch:{ Exception -> 0x0923, all -> 0x051d }
        r6 = -1;
        r0 = r28;
        if (r0 == r6) goto L_0x024f;
    L_0x0191:
        r6 = 1;
        r0 = r95;
        r1 = r50;
        r26 = r0.findTrack(r1, r6);	 Catch:{ Exception -> 0x0923, all -> 0x051d }
    L_0x019a:
        if (r90 < 0) goto L_0x0204;
    L_0x019c:
        r4 = 0;
        r41 = 0;
        r57 = 0;
        r70 = 0;
        r92 = -1;
        r68 = 0;
        r55 = 0;
        r34 = 0;
        r84 = 0;
        r94 = -5;
        r27 = -5;
        r75 = 0;
        r6 = android.os.Build.MANUFACTURER;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r61 = r6.toLowerCase();	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r10 = 18;
        if (r6 >= r10) goto L_0x04c5;
    L_0x01bf:
        r6 = "video/avc";
        r30 = selectCodec(r6);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6 = "video/avc";
        r0 = r30;
        r32 = selectColorFormat(r0, r6);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        if (r32 != 0) goto L_0x0253;
    L_0x01d1:
        r6 = new java.lang.RuntimeException;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r10 = "no supported color format";
        r6.<init>(r10);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        throw r6;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
    L_0x01da:
        r39 = move-exception;
    L_0x01db:
        org.telegram.messenger.FileLog.m1224e(r39);	 Catch:{ Exception -> 0x0923, all -> 0x051d }
        r47 = 1;
    L_0x01e0:
        r0 = r50;
        r1 = r90;
        r0.unselectTrack(r1);	 Catch:{ Exception -> 0x0923, all -> 0x051d }
        if (r70 == 0) goto L_0x01ec;
    L_0x01e9:
        r70.release();	 Catch:{ Exception -> 0x0923, all -> 0x051d }
    L_0x01ec:
        if (r57 == 0) goto L_0x01f1;
    L_0x01ee:
        r57.release();	 Catch:{ Exception -> 0x0923, all -> 0x051d }
    L_0x01f1:
        if (r4 == 0) goto L_0x01f9;
    L_0x01f3:
        r4.stop();	 Catch:{ Exception -> 0x0923, all -> 0x051d }
        r4.release();	 Catch:{ Exception -> 0x0923, all -> 0x051d }
    L_0x01f9:
        if (r41 == 0) goto L_0x0201;
    L_0x01fb:
        r41.stop();	 Catch:{ Exception -> 0x0923, all -> 0x051d }
        r41.release();	 Catch:{ Exception -> 0x0923, all -> 0x051d }
    L_0x0201:
        r95.checkConversionCanceled();	 Catch:{ Exception -> 0x0923, all -> 0x051d }
    L_0x0204:
        if (r50 == 0) goto L_0x0209;
    L_0x0206:
        r50.release();
    L_0x0209:
        if (r63 == 0) goto L_0x020e;
    L_0x020b:
        r63.finishMovie();	 Catch:{ Exception -> 0x095b }
    L_0x020e:
        r6 = org.telegram.messenger.BuildVars.LOGS_ENABLED;
        if (r6 == 0) goto L_0x0993;
    L_0x0212:
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r10 = "time = ";
        r6 = r6.append(r10);
        r10 = java.lang.System.currentTimeMillis();
        r10 = r10 - r86;
        r6 = r6.append(r10);
        r6 = r6.toString();
        org.telegram.messenger.FileLog.m1221d(r6);
        r49 = r50;
    L_0x0231:
        r6 = r74.edit();
        r10 = "isPreviousOk";
        r11 = 1;
        r6 = r6.putBoolean(r10, r11);
        r6.commit();
        r6 = 1;
        r0 = r95;
        r1 = r96;
        r2 = r20;
        r3 = r47;
        r0.didWriteData(r1, r2, r6, r3);
        r6 = 1;
        goto L_0x00e4;
    L_0x024f:
        r26 = -1;
        goto L_0x019a;
    L_0x0253:
        r31 = r30.getName();	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6 = "OMX.qcom.";
        r0 = r31;
        r6 = r0.contains(r6);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        if (r6 == 0) goto L_0x0487;
    L_0x0262:
        r75 = 1;
        r6 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r10 = 16;
        if (r6 != r10) goto L_0x0282;
    L_0x026a:
        r6 = "lge";
        r0 = r61;
        r6 = r0.equals(r6);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        if (r6 != 0) goto L_0x0280;
    L_0x0275:
        r6 = "nokia";
        r0 = r61;
        r6 = r0.equals(r6);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        if (r6 == 0) goto L_0x0282;
    L_0x0280:
        r84 = 1;
    L_0x0282:
        r6 = org.telegram.messenger.BuildVars.LOGS_ENABLED;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        if (r6 == 0) goto L_0x02bb;
    L_0x0286:
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6.<init>();	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r10 = "codec = ";
        r6 = r6.append(r10);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r10 = r30.getName();	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6 = r6.append(r10);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r10 = " manufacturer = ";
        r6 = r6.append(r10);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r0 = r61;
        r6 = r6.append(r0);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r10 = "device = ";
        r6 = r6.append(r10);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r10 = android.os.Build.MODEL;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6 = r6.append(r10);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6 = r6.toString();	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        org.telegram.messenger.FileLog.m1221d(r6);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
    L_0x02bb:
        r6 = org.telegram.messenger.BuildVars.LOGS_ENABLED;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        if (r6 == 0) goto L_0x02d8;
    L_0x02bf:
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6.<init>();	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r10 = "colorFormat = ";
        r6 = r6.append(r10);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r0 = r32;
        r6 = r6.append(r0);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6 = r6.toString();	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        org.telegram.messenger.FileLog.m1221d(r6);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
    L_0x02d8:
        r77 = r76;
        r72 = 0;
        r6 = r78 * r76;
        r6 = r6 * 3;
        r29 = r6 / 2;
        if (r75 != 0) goto L_0x04ca;
    L_0x02e4:
        r6 = r76 % 16;
        if (r6 == 0) goto L_0x02f8;
    L_0x02e8:
        r6 = r76 % 16;
        r6 = 16 - r6;
        r77 = r77 + r6;
        r6 = r77 - r76;
        r72 = r78 * r6;
        r6 = r72 * 5;
        r6 = r6 / 4;
        r29 = r29 + r6;
    L_0x02f8:
        r0 = r50;
        r1 = r90;
        r0.selectTrack(r1);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r0 = r50;
        r1 = r90;
        r89 = r0.getTrackFormat(r1);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r24 = 0;
        if (r26 < 0) goto L_0x0330;
    L_0x030b:
        r0 = r50;
        r1 = r26;
        r0.selectTrack(r1);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r0 = r50;
        r1 = r26;
        r25 = r0.getTrackFormat(r1);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6 = "max-input-size";
        r0 = r25;
        r62 = r0.getInteger(r6);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r24 = java.nio.ByteBuffer.allocateDirect(r62);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6 = 1;
        r0 = r63;
        r1 = r25;
        r27 = r0.addTrack(r1, r6);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
    L_0x0330:
        r10 = 0;
        r6 = (r82 > r10 ? 1 : (r82 == r10 ? 0 : -1));
        if (r6 <= 0) goto L_0x0513;
    L_0x0336:
        r6 = 0;
        r0 = r50;
        r1 = r82;
        r0.seekTo(r1, r6);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
    L_0x033e:
        r6 = "video/avc";
        r0 = r78;
        r1 = r76;
        r69 = android.media.MediaFormat.createVideoFormat(r6, r0, r1);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6 = "color-format";
        r0 = r69;
        r1 = r32;
        r0.setInteger(r6, r1);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6 = "bitrate";
        if (r28 <= 0) goto L_0x054c;
    L_0x0358:
        r0 = r69;
        r1 = r28;
        r0.setInteger(r6, r1);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6 = "frame-rate";
        if (r51 == 0) goto L_0x0551;
    L_0x0364:
        r0 = r69;
        r1 = r51;
        r0.setInteger(r6, r1);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6 = "i-frame-interval";
        r10 = 10;
        r0 = r69;
        r0.setInteger(r6, r10);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r10 = 18;
        if (r6 >= r10) goto L_0x038f;
    L_0x037b:
        r6 = "stride";
        r10 = r78 + 32;
        r0 = r69;
        r0.setInteger(r6, r10);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6 = "slice-height";
        r0 = r69;
        r1 = r76;
        r0.setInteger(r6, r1);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
    L_0x038f:
        r6 = "video/avc";
        r41 = android.media.MediaCodec.createEncoderByType(r6);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6 = 0;
        r10 = 0;
        r11 = 1;
        r0 = r41;
        r1 = r69;
        r0.configure(r1, r6, r10, r11);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r10 = 18;
        if (r6 < r10) goto L_0x03b6;
    L_0x03a6:
        r58 = new org.telegram.messenger.video.InputSurface;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6 = r41.createInputSurface();	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r0 = r58;
        r0.<init>(r6);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r58.makeCurrent();	 Catch:{ Exception -> 0x098e, all -> 0x051d }
        r57 = r58;
    L_0x03b6:
        r41.start();	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6 = "mime";
        r0 = r89;
        r6 = r0.getString(r6);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r4 = android.media.MediaCodec.createDecoderByType(r6);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r10 = 18;
        if (r6 < r10) goto L_0x0555;
    L_0x03cc:
        r71 = new org.telegram.messenger.video.OutputSurface;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r71.<init>();	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r70 = r71;
    L_0x03d3:
        r6 = r70.getSurface();	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r10 = 0;
        r11 = 0;
        r0 = r89;
        r4.configure(r0, r6, r10, r11);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r4.start();	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r22 = 2500; // 0x9c4 float:3.503E-42 double:1.235E-320;
        r35 = 0;
        r44 = 0;
        r42 = 0;
        r6 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r10 = 21;
        if (r6 >= r10) goto L_0x0401;
    L_0x03ef:
        r35 = r4.getInputBuffers();	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r44 = r41.getOutputBuffers();	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r10 = 18;
        if (r6 >= r10) goto L_0x0401;
    L_0x03fd:
        r42 = r41.getInputBuffers();	 Catch:{ Exception -> 0x01da, all -> 0x051d }
    L_0x0401:
        r95.checkConversionCanceled();	 Catch:{ Exception -> 0x01da, all -> 0x051d }
    L_0x0404:
        if (r68 != 0) goto L_0x01e0;
    L_0x0406:
        r95.checkConversionCanceled();	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        if (r55 != 0) goto L_0x0452;
    L_0x040b:
        r46 = 0;
        r52 = r50.getSampleTrackIndex();	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r0 = r52;
        r1 = r90;
        if (r0 != r1) goto L_0x057a;
    L_0x0417:
        r10 = 2500; // 0x9c4 float:3.503E-42 double:1.235E-320;
        r5 = r4.dequeueInputBuffer(r10);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        if (r5 < 0) goto L_0x043c;
    L_0x041f:
        r6 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r10 = 21;
        if (r6 >= r10) goto L_0x0566;
    L_0x0425:
        r54 = r35[r5];	 Catch:{ Exception -> 0x01da, all -> 0x051d }
    L_0x0427:
        r6 = 0;
        r0 = r50;
        r1 = r54;
        r7 = r0.readSampleData(r1, r6);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        if (r7 >= 0) goto L_0x056c;
    L_0x0432:
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r10 = 4;
        r4.queueInputBuffer(r5, r6, r7, r8, r10);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r55 = 1;
    L_0x043c:
        if (r46 == 0) goto L_0x0452;
    L_0x043e:
        r10 = 2500; // 0x9c4 float:3.503E-42 double:1.235E-320;
        r5 = r4.dequeueInputBuffer(r10);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        if (r5 < 0) goto L_0x0452;
    L_0x0446:
        r10 = 0;
        r11 = 0;
        r12 = 0;
        r14 = 4;
        r8 = r4;
        r9 = r5;
        r8.queueInputBuffer(r9, r10, r11, r12, r14);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r55 = 1;
    L_0x0452:
        if (r34 != 0) goto L_0x0606;
    L_0x0454:
        r36 = 1;
    L_0x0456:
        r43 = 1;
    L_0x0458:
        if (r36 != 0) goto L_0x045c;
    L_0x045a:
        if (r43 == 0) goto L_0x0404;
    L_0x045c:
        r95.checkConversionCanceled();	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r10 = 2500; // 0x9c4 float:3.503E-42 double:1.235E-320;
        r0 = r41;
        r1 = r53;
        r45 = r0.dequeueOutputBuffer(r1, r10);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6 = -1;
        r0 = r45;
        if (r0 != r6) goto L_0x060a;
    L_0x046e:
        r43 = 0;
    L_0x0470:
        r6 = -1;
        r0 = r45;
        if (r0 != r6) goto L_0x0458;
    L_0x0475:
        if (r34 != 0) goto L_0x0458;
    L_0x0477:
        r10 = 2500; // 0x9c4 float:3.503E-42 double:1.235E-320;
        r0 = r53;
        r37 = r4.dequeueOutputBuffer(r0, r10);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6 = -1;
        r0 = r37;
        if (r0 != r6) goto L_0x0783;
    L_0x0484:
        r36 = 0;
        goto L_0x0458;
    L_0x0487:
        r6 = "OMX.Intel.";
        r0 = r31;
        r6 = r0.contains(r6);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        if (r6 == 0) goto L_0x0496;
    L_0x0492:
        r75 = 2;
        goto L_0x0282;
    L_0x0496:
        r6 = "OMX.MTK.VIDEO.ENCODER.AVC";
        r0 = r31;
        r6 = r0.equals(r6);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        if (r6 == 0) goto L_0x04a5;
    L_0x04a1:
        r75 = 3;
        goto L_0x0282;
    L_0x04a5:
        r6 = "OMX.SEC.AVC.Encoder";
        r0 = r31;
        r6 = r0.equals(r6);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        if (r6 == 0) goto L_0x04b6;
    L_0x04b0:
        r75 = 4;
        r84 = 1;
        goto L_0x0282;
    L_0x04b6:
        r6 = "OMX.TI.DUCATI1.VIDEO.H264E";
        r0 = r31;
        r6 = r0.equals(r6);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        if (r6 == 0) goto L_0x0282;
    L_0x04c1:
        r75 = 5;
        goto L_0x0282;
    L_0x04c5:
        r32 = 2130708361; // 0x7f000789 float:1.701803E38 double:1.0527098025E-314;
        goto L_0x02bb;
    L_0x04ca:
        r6 = 1;
        r0 = r75;
        if (r0 != r6) goto L_0x04ec;
    L_0x04cf:
        r6 = r61.toLowerCase();	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r10 = "lge";
        r6 = r6.equals(r10);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        if (r6 != 0) goto L_0x02f8;
    L_0x04dc:
        r6 = r78 * r76;
        r6 = r6 + 2047;
        r0 = r6 & -2048;
        r88 = r0;
        r6 = r78 * r76;
        r72 = r88 - r6;
        r29 = r29 + r72;
        goto L_0x02f8;
    L_0x04ec:
        r6 = 5;
        r0 = r75;
        if (r0 == r6) goto L_0x02f8;
    L_0x04f1:
        r6 = 3;
        r0 = r75;
        if (r0 != r6) goto L_0x02f8;
    L_0x04f6:
        r6 = "baidu";
        r0 = r61;
        r6 = r0.equals(r6);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        if (r6 == 0) goto L_0x02f8;
    L_0x0501:
        r6 = r76 % 16;
        r6 = 16 - r6;
        r77 = r77 + r6;
        r6 = r77 - r76;
        r72 = r78 * r6;
        r6 = r72 * 5;
        r6 = r6 / 4;
        r29 = r29 + r6;
        goto L_0x02f8;
    L_0x0513:
        r10 = 0;
        r6 = 0;
        r0 = r50;
        r0.seekTo(r10, r6);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        goto L_0x033e;
    L_0x051d:
        r6 = move-exception;
        r49 = r50;
    L_0x0520:
        if (r49 == 0) goto L_0x0525;
    L_0x0522:
        r49.release();
    L_0x0525:
        if (r63 == 0) goto L_0x052a;
    L_0x0527:
        r63.finishMovie();	 Catch:{ Exception -> 0x0966 }
    L_0x052a:
        r10 = org.telegram.messenger.BuildVars.LOGS_ENABLED;
        if (r10 == 0) goto L_0x054b;
    L_0x052e:
        r10 = new java.lang.StringBuilder;
        r10.<init>();
        r11 = "time = ";
        r10 = r10.append(r11);
        r12 = java.lang.System.currentTimeMillis();
        r12 = r12 - r86;
        r10 = r10.append(r12);
        r10 = r10.toString();
        org.telegram.messenger.FileLog.m1221d(r10);
    L_0x054b:
        throw r6;
    L_0x054c:
        r28 = 921600; // 0xe1000 float:1.291437E-39 double:4.55331E-318;
        goto L_0x0358;
    L_0x0551:
        r51 = 25;
        goto L_0x0364;
    L_0x0555:
        r71 = new org.telegram.messenger.video.OutputSurface;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r0 = r71;
        r1 = r78;
        r2 = r76;
        r3 = r79;
        r0.<init>(r1, r2, r3);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r70 = r71;
        goto L_0x03d3;
    L_0x0566:
        r54 = r4.getInputBuffer(r5);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        goto L_0x0427;
    L_0x056c:
        r6 = 0;
        r8 = r50.getSampleTime();	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r10 = 0;
        r4.queueInputBuffer(r5, r6, r7, r8, r10);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r50.advance();	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        goto L_0x043c;
    L_0x057a:
        r6 = -1;
        r0 = r26;
        if (r0 == r6) goto L_0x05fd;
    L_0x057f:
        r0 = r52;
        r1 = r26;
        if (r0 != r1) goto L_0x05fd;
    L_0x0585:
        r6 = 0;
        r0 = r50;
        r1 = r24;
        r6 = r0.readSampleData(r1, r6);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r0 = r53;
        r0.size = r6;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r10 = 21;
        if (r6 >= r10) goto L_0x05a7;
    L_0x0598:
        r6 = 0;
        r0 = r24;
        r0.position(r6);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r0 = r53;
        r6 = r0.size;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r0 = r24;
        r0.limit(r6);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
    L_0x05a7:
        r0 = r53;
        r6 = r0.size;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        if (r6 < 0) goto L_0x05f5;
    L_0x05ad:
        r10 = r50.getSampleTime();	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r0 = r53;
        r0.presentationTimeUs = r10;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r50.advance();	 Catch:{ Exception -> 0x01da, all -> 0x051d }
    L_0x05b8:
        r0 = r53;
        r6 = r0.size;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        if (r6 <= 0) goto L_0x043c;
    L_0x05be:
        r10 = 0;
        r6 = (r18 > r10 ? 1 : (r18 == r10 ? 0 : -1));
        if (r6 < 0) goto L_0x05cc;
    L_0x05c4:
        r0 = r53;
        r10 = r0.presentationTimeUs;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6 = (r10 > r18 ? 1 : (r10 == r18 ? 0 : -1));
        if (r6 >= 0) goto L_0x043c;
    L_0x05cc:
        r6 = 0;
        r0 = r53;
        r0.offset = r6;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6 = r50.getSampleFlags();	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r0 = r53;
        r0.flags = r6;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6 = 0;
        r0 = r63;
        r1 = r27;
        r2 = r24;
        r3 = r53;
        r6 = r0.writeSampleData(r1, r2, r3, r6);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        if (r6 == 0) goto L_0x043c;
    L_0x05e8:
        r6 = 0;
        r10 = 0;
        r0 = r95;
        r1 = r96;
        r2 = r20;
        r0.didWriteData(r1, r2, r6, r10);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        goto L_0x043c;
    L_0x05f5:
        r6 = 0;
        r0 = r53;
        r0.size = r6;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r55 = 1;
        goto L_0x05b8;
    L_0x05fd:
        r6 = -1;
        r0 = r52;
        if (r0 != r6) goto L_0x043c;
    L_0x0602:
        r46 = 1;
        goto L_0x043c;
    L_0x0606:
        r36 = 0;
        goto L_0x0456;
    L_0x060a:
        r6 = -3;
        r0 = r45;
        if (r0 != r6) goto L_0x061b;
    L_0x060f:
        r6 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r10 = 21;
        if (r6 >= r10) goto L_0x0470;
    L_0x0615:
        r44 = r41.getOutputBuffers();	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        goto L_0x0470;
    L_0x061b:
        r6 = -2;
        r0 = r45;
        if (r0 != r6) goto L_0x0634;
    L_0x0620:
        r65 = r41.getOutputFormat();	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6 = -5;
        r0 = r94;
        if (r0 != r6) goto L_0x0470;
    L_0x0629:
        r6 = 0;
        r0 = r63;
        r1 = r65;
        r94 = r0.addTrack(r1, r6);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        goto L_0x0470;
    L_0x0634:
        if (r45 >= 0) goto L_0x0652;
    L_0x0636:
        r6 = new java.lang.RuntimeException;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r10 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r10.<init>();	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r11 = "unexpected result from encoder.dequeueOutputBuffer: ";
        r10 = r10.append(r11);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r0 = r45;
        r10 = r10.append(r0);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r10 = r10.toString();	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6.<init>(r10);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        throw r6;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
    L_0x0652:
        r6 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r10 = 21;
        if (r6 >= r10) goto L_0x067f;
    L_0x0658:
        r40 = r44[r45];	 Catch:{ Exception -> 0x01da, all -> 0x051d }
    L_0x065a:
        if (r40 != 0) goto L_0x0688;
    L_0x065c:
        r6 = new java.lang.RuntimeException;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r10 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r10.<init>();	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r11 = "encoderOutputBuffer ";
        r10 = r10.append(r11);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r0 = r45;
        r10 = r10.append(r0);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r11 = " was null";
        r10 = r10.append(r11);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r10 = r10.toString();	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6.<init>(r10);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        throw r6;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
    L_0x067f:
        r0 = r41;
        r1 = r45;
        r40 = r0.getOutputBuffer(r1);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        goto L_0x065a;
    L_0x0688:
        r0 = r53;
        r6 = r0.size;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r10 = 1;
        if (r6 <= r10) goto L_0x06b1;
    L_0x068f:
        r0 = r53;
        r6 = r0.flags;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6 = r6 & 2;
        if (r6 != 0) goto L_0x06c5;
    L_0x0697:
        r6 = 1;
        r0 = r63;
        r1 = r94;
        r2 = r40;
        r3 = r53;
        r6 = r0.writeSampleData(r1, r2, r3, r6);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        if (r6 == 0) goto L_0x06b1;
    L_0x06a6:
        r6 = 0;
        r10 = 0;
        r0 = r95;
        r1 = r96;
        r2 = r20;
        r0.didWriteData(r1, r2, r6, r10);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
    L_0x06b1:
        r0 = r53;
        r6 = r0.flags;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6 = r6 & 4;
        if (r6 == 0) goto L_0x077f;
    L_0x06b9:
        r68 = 1;
    L_0x06bb:
        r6 = 0;
        r0 = r41;
        r1 = r45;
        r0.releaseOutputBuffer(r1, r6);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        goto L_0x0470;
    L_0x06c5:
        r6 = -5;
        r0 = r94;
        if (r0 != r6) goto L_0x06b1;
    L_0x06ca:
        r0 = r53;
        r6 = r0.size;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r0 = new byte[r6];	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r33 = r0;
        r0 = r53;
        r6 = r0.offset;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r0 = r53;
        r10 = r0.size;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6 = r6 + r10;
        r0 = r40;
        r0.limit(r6);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r0 = r53;
        r6 = r0.offset;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r0 = r40;
        r0.position(r6);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r0 = r40;
        r1 = r33;
        r0.get(r1);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r81 = 0;
        r73 = 0;
        r0 = r53;
        r6 = r0.size;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r23 = r6 + -1;
    L_0x06fa:
        if (r23 < 0) goto L_0x074d;
    L_0x06fc:
        r6 = 3;
        r0 = r23;
        if (r0 <= r6) goto L_0x074d;
    L_0x0701:
        r6 = r33[r23];	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r10 = 1;
        if (r6 != r10) goto L_0x077b;
    L_0x0706:
        r6 = r23 + -1;
        r6 = r33[r6];	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        if (r6 != 0) goto L_0x077b;
    L_0x070c:
        r6 = r23 + -2;
        r6 = r33[r6];	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        if (r6 != 0) goto L_0x077b;
    L_0x0712:
        r6 = r23 + -3;
        r6 = r33[r6];	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        if (r6 != 0) goto L_0x077b;
    L_0x0718:
        r6 = r23 + -3;
        r81 = java.nio.ByteBuffer.allocate(r6);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r0 = r53;
        r6 = r0.size;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r10 = r23 + -3;
        r6 = r6 - r10;
        r73 = java.nio.ByteBuffer.allocate(r6);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6 = 0;
        r10 = r23 + -3;
        r0 = r81;
        r1 = r33;
        r6 = r0.put(r1, r6, r10);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r10 = 0;
        r6.position(r10);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6 = r23 + -3;
        r0 = r53;
        r10 = r0.size;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r11 = r23 + -3;
        r10 = r10 - r11;
        r0 = r73;
        r1 = r33;
        r6 = r0.put(r1, r6, r10);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r10 = 0;
        r6.position(r10);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
    L_0x074d:
        r6 = "video/avc";
        r0 = r78;
        r1 = r76;
        r65 = android.media.MediaFormat.createVideoFormat(r6, r0, r1);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        if (r81 == 0) goto L_0x0770;
    L_0x075a:
        if (r73 == 0) goto L_0x0770;
    L_0x075c:
        r6 = "csd-0";
        r0 = r65;
        r1 = r81;
        r0.setByteBuffer(r6, r1);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6 = "csd-1";
        r0 = r65;
        r1 = r73;
        r0.setByteBuffer(r6, r1);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
    L_0x0770:
        r6 = 0;
        r0 = r63;
        r1 = r65;
        r94 = r0.addTrack(r1, r6);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        goto L_0x06b1;
    L_0x077b:
        r23 = r23 + -1;
        goto L_0x06fa;
    L_0x077f:
        r68 = 0;
        goto L_0x06bb;
    L_0x0783:
        r6 = -3;
        r0 = r37;
        if (r0 == r6) goto L_0x0458;
    L_0x0788:
        r6 = -2;
        r0 = r37;
        if (r0 != r6) goto L_0x07b0;
    L_0x078d:
        r65 = r4.getOutputFormat();	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6 = org.telegram.messenger.BuildVars.LOGS_ENABLED;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        if (r6 == 0) goto L_0x0458;
    L_0x0795:
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6.<init>();	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r10 = "newFormat = ";
        r6 = r6.append(r10);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r0 = r65;
        r6 = r6.append(r0);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6 = r6.toString();	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        org.telegram.messenger.FileLog.m1221d(r6);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        goto L_0x0458;
    L_0x07b0:
        if (r37 >= 0) goto L_0x07ce;
    L_0x07b2:
        r6 = new java.lang.RuntimeException;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r10 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r10.<init>();	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r11 = "unexpected result from decoder.dequeueOutputBuffer: ";
        r10 = r10.append(r11);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r0 = r37;
        r10 = r10.append(r0);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r10 = r10.toString();	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6.<init>(r10);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        throw r6;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
    L_0x07ce:
        r6 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r10 = 18;
        if (r6 < r10) goto L_0x088a;
    L_0x07d4:
        r0 = r53;
        r6 = r0.size;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        if (r6 == 0) goto L_0x0886;
    L_0x07da:
        r38 = 1;
    L_0x07dc:
        r10 = 0;
        r6 = (r18 > r10 ? 1 : (r18 == r10 ? 0 : -1));
        if (r6 <= 0) goto L_0x07fa;
    L_0x07e2:
        r0 = r53;
        r10 = r0.presentationTimeUs;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6 = (r10 > r18 ? 1 : (r10 == r18 ? 0 : -1));
        if (r6 < 0) goto L_0x07fa;
    L_0x07ea:
        r55 = 1;
        r34 = 1;
        r38 = 0;
        r0 = r53;
        r6 = r0.flags;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6 = r6 | 4;
        r0 = r53;
        r0.flags = r6;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
    L_0x07fa:
        r10 = 0;
        r6 = (r82 > r10 ? 1 : (r82 == r10 ? 0 : -1));
        if (r6 <= 0) goto L_0x083c;
    L_0x0800:
        r10 = -1;
        r6 = (r92 > r10 ? 1 : (r92 == r10 ? 0 : -1));
        if (r6 != 0) goto L_0x083c;
    L_0x0806:
        r0 = r53;
        r10 = r0.presentationTimeUs;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6 = (r10 > r82 ? 1 : (r10 == r82 ? 0 : -1));
        if (r6 >= 0) goto L_0x08a1;
    L_0x080e:
        r38 = 0;
        r6 = org.telegram.messenger.BuildVars.LOGS_ENABLED;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        if (r6 == 0) goto L_0x083c;
    L_0x0814:
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6.<init>();	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r10 = "drop frame startTime = ";
        r6 = r6.append(r10);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r0 = r82;
        r6 = r6.append(r0);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r10 = " present time = ";
        r6 = r6.append(r10);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r0 = r53;
        r10 = r0.presentationTimeUs;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6 = r6.append(r10);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6 = r6.toString();	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        org.telegram.messenger.FileLog.m1221d(r6);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
    L_0x083c:
        r0 = r37;
        r1 = r38;
        r4.releaseOutputBuffer(r0, r1);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        if (r38 == 0) goto L_0x0867;
    L_0x0845:
        r48 = 0;
        r70.awaitNewImage();	 Catch:{ Exception -> 0x08a8, all -> 0x051d }
    L_0x084a:
        if (r48 != 0) goto L_0x0867;
    L_0x084c:
        r6 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r10 = 18;
        if (r6 < r10) goto L_0x08af;
    L_0x0852:
        r6 = 0;
        r0 = r70;
        r0.drawImage(r6);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r0 = r53;
        r10 = r0.presentationTimeUs;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r12 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r10 = r10 * r12;
        r0 = r57;
        r0.setPresentationTime(r10);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r57.swapBuffers();	 Catch:{ Exception -> 0x01da, all -> 0x051d }
    L_0x0867:
        r0 = r53;
        r6 = r0.flags;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r6 = r6 & 4;
        if (r6 == 0) goto L_0x0458;
    L_0x086f:
        r36 = 0;
        r6 = org.telegram.messenger.BuildVars.LOGS_ENABLED;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        if (r6 == 0) goto L_0x087b;
    L_0x0875:
        r6 = "decoder stream end";
        org.telegram.messenger.FileLog.m1221d(r6);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
    L_0x087b:
        r6 = android.os.Build.VERSION.SDK_INT;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r10 = 18;
        if (r6 < r10) goto L_0x08f1;
    L_0x0881:
        r41.signalEndOfInputStream();	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        goto L_0x0458;
    L_0x0886:
        r38 = 0;
        goto L_0x07dc;
    L_0x088a:
        r0 = r53;
        r6 = r0.size;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        if (r6 != 0) goto L_0x089a;
    L_0x0890:
        r0 = r53;
        r10 = r0.presentationTimeUs;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r12 = 0;
        r6 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1));
        if (r6 == 0) goto L_0x089e;
    L_0x089a:
        r38 = 1;
    L_0x089c:
        goto L_0x07dc;
    L_0x089e:
        r38 = 0;
        goto L_0x089c;
    L_0x08a1:
        r0 = r53;
        r0 = r0.presentationTimeUs;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r92 = r0;
        goto L_0x083c;
    L_0x08a8:
        r39 = move-exception;
        r48 = 1;
        org.telegram.messenger.FileLog.m1224e(r39);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        goto L_0x084a;
    L_0x08af:
        r10 = 2500; // 0x9c4 float:3.503E-42 double:1.235E-320;
        r0 = r41;
        r5 = r0.dequeueInputBuffer(r10);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        if (r5 < 0) goto L_0x08e5;
    L_0x08b9:
        r6 = 1;
        r0 = r70;
        r0.drawImage(r6);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r8 = r70.getFrame();	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r9 = r42[r5];	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r9.clear();	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r10 = r32;
        r11 = r78;
        r12 = r76;
        r13 = r72;
        r14 = r84;
        org.telegram.messenger.Utilities.convertVideoFrame(r8, r9, r10, r11, r12, r13, r14);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r12 = 0;
        r0 = r53;
        r14 = r0.presentationTimeUs;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r16 = 0;
        r10 = r41;
        r11 = r5;
        r13 = r29;
        r10.queueInputBuffer(r11, r12, r13, r14, r16);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        goto L_0x0867;
    L_0x08e5:
        r6 = org.telegram.messenger.BuildVars.LOGS_ENABLED;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        if (r6 == 0) goto L_0x0867;
    L_0x08e9:
        r6 = "input buffer not available";
        org.telegram.messenger.FileLog.m1221d(r6);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        goto L_0x0867;
    L_0x08f1:
        r10 = 2500; // 0x9c4 float:3.503E-42 double:1.235E-320;
        r0 = r41;
        r5 = r0.dequeueInputBuffer(r10);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        if (r5 < 0) goto L_0x0458;
    L_0x08fb:
        r12 = 0;
        r13 = 1;
        r0 = r53;
        r14 = r0.presentationTimeUs;	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        r16 = 4;
        r10 = r41;
        r11 = r5;
        r10.queueInputBuffer(r11, r12, r13, r14, r16);	 Catch:{ Exception -> 0x01da, all -> 0x051d }
        goto L_0x0458;
    L_0x090b:
        r6 = -1;
        r0 = r28;
        if (r0 == r6) goto L_0x0958;
    L_0x0910:
        r21 = 1;
    L_0x0912:
        r11 = r95;
        r12 = r96;
        r13 = r50;
        r14 = r63;
        r15 = r53;
        r16 = r82;
        r11.readAndWriteTracks(r12, r13, r14, r15, r16, r18, r20, r21);	 Catch:{ Exception -> 0x0923, all -> 0x051d }
        goto L_0x0204;
    L_0x0923:
        r39 = move-exception;
        r49 = r50;
    L_0x0926:
        r47 = 1;
        org.telegram.messenger.FileLog.m1224e(r39);	 Catch:{ all -> 0x0989 }
        if (r49 == 0) goto L_0x0930;
    L_0x092d:
        r49.release();
    L_0x0930:
        if (r63 == 0) goto L_0x0935;
    L_0x0932:
        r63.finishMovie();	 Catch:{ Exception -> 0x0961 }
    L_0x0935:
        r6 = org.telegram.messenger.BuildVars.LOGS_ENABLED;
        if (r6 == 0) goto L_0x0231;
    L_0x0939:
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r10 = "time = ";
        r6 = r6.append(r10);
        r10 = java.lang.System.currentTimeMillis();
        r10 = r10 - r86;
        r6 = r6.append(r10);
        r6 = r6.toString();
        org.telegram.messenger.FileLog.m1221d(r6);
        goto L_0x0231;
    L_0x0958:
        r21 = 0;
        goto L_0x0912;
    L_0x095b:
        r39 = move-exception;
        org.telegram.messenger.FileLog.m1224e(r39);
        goto L_0x020e;
    L_0x0961:
        r39 = move-exception;
        org.telegram.messenger.FileLog.m1224e(r39);
        goto L_0x0935;
    L_0x0966:
        r39 = move-exception;
        org.telegram.messenger.FileLog.m1224e(r39);
        goto L_0x052a;
    L_0x096c:
        r6 = r74.edit();
        r10 = "isPreviousOk";
        r11 = 1;
        r6 = r6.putBoolean(r10, r11);
        r6.commit();
        r6 = 1;
        r10 = 1;
        r0 = r95;
        r1 = r96;
        r2 = r20;
        r0.didWriteData(r1, r2, r6, r10);
        r6 = 0;
        goto L_0x00e4;
    L_0x0989:
        r6 = move-exception;
        goto L_0x0520;
    L_0x098c:
        r39 = move-exception;
        goto L_0x0926;
    L_0x098e:
        r39 = move-exception;
        r57 = r58;
        goto L_0x01db;
    L_0x0993:
        r49 = r50;
        goto L_0x0231;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.telegram.messenger.MediaController.convertVideo(org.telegram.messenger.MessageObject):boolean");
    }

    private void changeVoice(int fType) {
        Preferences preferences = new Preferences(ApplicationLoader.applicationContext);
        FrameType frameType = FrameType.Small;
        switch (fType) {
            case 2:
            case 3:
                frameType = FrameType.Medium;
                break;
            case 4:
                frameType = FrameType.Small;
                break;
            case 5:
                frameType = FrameType.Default;
                break;
        }
        int frameSize = preferences.getFrameSize(frameType, 16000);
        int hopSize = preferences.getHopSize(frameType, 16000) * 2;
        this.voiceBuffer = new float[frameSize];
        try {
            AudioDevice input = new PcmInDevice(ApplicationLoader.applicationContext, HeadsetMode.WIRED_HEADPHONES);
            AudioDevice output = new PcmOutDevice(ApplicationLoader.applicationContext, HeadsetMode.WIRED_HEADPHONES);
            if (fType == 5) {
                float tau = Math.pow(2.0f, ((float) TurboConfig.voiceChangerTranspose) / 12.0f);
                int analysisHopSize = Math.round(((float) hopSize) / tau);
                int synthesisFrameSize = Math.round(((float) frameSize) / tau);
                this.preprocessor = new StftPreprocessor(input, frameSize, analysisHopSize, true);
                this.timescaleProcessor = new NativeTimescaleProcessor(frameSize, analysisHopSize, hopSize);
                this.kissFFT = new KissFFT(frameSize);
                this.resampleProcessor = new NativeResampleProcessor(frameSize, synthesisFrameSize);
                this.postprocessor = new StftPostprocessor(output, synthesisFrameSize, analysisHopSize, false);
                this.analysisFrameBuffer = new float[frameSize];
                this.synthesisFrameBuffer = new float[synthesisFrameSize];
            } else if (fType == 2 || fType == 3 || fType == 4) {
                this.preprocessor = new StftPreprocessor(input, frameSize, hopSize, true);
                this.postprocessor = new StftPostprocessor(output, frameSize, hopSize, true);
            }
        } catch (IOException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }
}

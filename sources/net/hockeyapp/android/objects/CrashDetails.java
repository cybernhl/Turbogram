package net.hockeyapp.android.objects;

import android.content.Context;
import android.text.TextUtils;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;
import net.hockeyapp.android.utils.HockeyLog;
import net.hockeyapp.android.utils.JSONDateUtils;
import org.json.JSONException;

public class CrashDetails {
    private static final String FIELD_APP_CRASH_DATE = "Date";
    private static final String FIELD_APP_PACKAGE = "Package";
    private static final String FIELD_APP_START_DATE = "Start Date";
    private static final String FIELD_APP_VERSION_CODE = "Version Code";
    private static final String FIELD_APP_VERSION_NAME = "Version Name";
    private static final String FIELD_CRASH_REPORTER_KEY = "CrashReporter Key";
    private static final String FIELD_DEVICE_MANUFACTURER = "Manufacturer";
    private static final String FIELD_DEVICE_MODEL = "Model";
    private static final String FIELD_FORMAT = "Format";
    private static final String FIELD_FORMAT_VALUE = "Xamarin";
    private static final String FIELD_OS_BUILD = "Android Build";
    private static final String FIELD_OS_VERSION = "Android";
    private static final String FIELD_THREAD_NAME = "Thread";
    private static final String FIELD_XAMARIN_CAUSED_BY = "Xamarin caused by: ";
    private Date appCrashDate;
    private String appPackage;
    private Date appStartDate;
    private String appVersionCode;
    private String appVersionName;
    private final String crashIdentifier;
    private String deviceManufacturer;
    private String deviceModel;
    private String format;
    private Boolean isXamarinException;
    private String osBuild;
    private String osVersion;
    private String reporterKey;
    private String threadName;
    private String throwableStackTrace;

    public CrashDetails(String crashIdentifier) {
        this.crashIdentifier = crashIdentifier;
        this.isXamarinException = Boolean.valueOf(false);
        this.throwableStackTrace = "";
    }

    public CrashDetails(String crashIdentifier, Throwable throwable) {
        this(crashIdentifier);
        this.isXamarinException = Boolean.valueOf(false);
        Writer stackTraceResult = new StringWriter();
        ThrowableExtension.printStackTrace(throwable, new PrintWriter(stackTraceResult));
        this.throwableStackTrace = stackTraceResult.toString();
    }

    public CrashDetails(String crashIdentifier, Throwable throwable, String managedExceptionString, Boolean isManagedException) {
        this(crashIdentifier);
        Writer stackTraceResult = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stackTraceResult);
        this.isXamarinException = Boolean.valueOf(true);
        setFormat(FIELD_FORMAT_VALUE);
        if (isManagedException.booleanValue()) {
            printWriter.print(FIELD_XAMARIN_CAUSED_BY);
            ThrowableExtension.printStackTrace(throwable, printWriter);
        } else if (TextUtils.isEmpty(managedExceptionString)) {
            ThrowableExtension.printStackTrace(throwable, printWriter);
        } else {
            ThrowableExtension.printStackTrace(throwable, printWriter);
            printWriter.print(FIELD_XAMARIN_CAUSED_BY);
            printWriter.print(managedExceptionString);
        }
        this.throwableStackTrace = stackTraceResult.toString();
    }

    public static CrashDetails fromFile(File file) throws IOException, JSONException {
        return fromReader(file.getName().substring(0, file.getName().indexOf(".stacktrace")), new FileReader(file));
    }

    public static CrashDetails fromReader(String crashIdentifier, Reader in) throws IOException, JSONException {
        BufferedReader bufferedReader = new BufferedReader(in);
        CrashDetails result = new CrashDetails(crashIdentifier);
        boolean headersProcessed = false;
        StringBuilder stackTraceBuilder = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                result.setThrowableStackTrace(stackTraceBuilder.toString());
                return result;
            } else if (headersProcessed) {
                stackTraceBuilder.append(readLine).append("\n");
            } else if (readLine.isEmpty()) {
                headersProcessed = true;
            } else {
                int colonIndex = readLine.indexOf(":");
                if (colonIndex < 0) {
                    HockeyLog.error("Malformed header line when parsing crash details: \"" + readLine + "\"");
                }
                String headerName = readLine.substring(0, colonIndex).trim();
                String headerValue = readLine.substring(colonIndex + 1, readLine.length()).trim();
                if (headerName.equals(FIELD_CRASH_REPORTER_KEY)) {
                    result.setReporterKey(headerValue);
                } else if (headerName.equals(FIELD_APP_START_DATE)) {
                    try {
                        result.setAppStartDate(JSONDateUtils.toDate(headerValue));
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                } else if (headerName.equals(FIELD_APP_CRASH_DATE)) {
                    try {
                        result.setAppCrashDate(JSONDateUtils.toDate(headerValue));
                    } catch (JSONException e2) {
                        throw new RuntimeException(e2);
                    }
                } else if (headerName.equals(FIELD_OS_VERSION)) {
                    result.setOsVersion(headerValue);
                } else if (headerName.equals(FIELD_OS_BUILD)) {
                    result.setOsBuild(headerValue);
                } else if (headerName.equals(FIELD_DEVICE_MANUFACTURER)) {
                    result.setDeviceManufacturer(headerValue);
                } else if (headerName.equals("Model")) {
                    result.setDeviceModel(headerValue);
                } else if (headerName.equals(FIELD_APP_PACKAGE)) {
                    result.setAppPackage(headerValue);
                } else if (headerName.equals(FIELD_APP_VERSION_NAME)) {
                    result.setAppVersionName(headerValue);
                } else if (headerName.equals(FIELD_APP_VERSION_CODE)) {
                    result.setAppVersionCode(headerValue);
                } else if (headerName.equals(FIELD_THREAD_NAME)) {
                    result.setThreadName(headerValue);
                } else if (headerName.equals(FIELD_FORMAT)) {
                    result.setFormat(headerValue);
                }
            }
        }
    }

    public void writeCrashReport(Context context) {
        try {
            writeCrashReport(new File(context.getFilesDir(), this.crashIdentifier + ".stacktrace"));
        } catch (JSONException e) {
            HockeyLog.error("Could not write crash report with error " + e.toString());
        }
    }

    public void writeCrashReport(File file) throws JSONException {
        Throwable e;
        Throwable th;
        HockeyLog.debug("Writing unhandled exception to: " + file.getAbsolutePath());
        BufferedWriter writer = null;
        try {
            BufferedWriter writer2 = new BufferedWriter(new FileWriter(file));
            try {
                writeHeader(writer2, FIELD_APP_PACKAGE, this.appPackage);
                writeHeader(writer2, FIELD_APP_VERSION_CODE, this.appVersionCode);
                writeHeader(writer2, FIELD_APP_VERSION_NAME, this.appVersionName);
                writeHeader(writer2, FIELD_OS_VERSION, this.osVersion);
                writeHeader(writer2, FIELD_OS_BUILD, this.osBuild);
                writeHeader(writer2, FIELD_DEVICE_MANUFACTURER, this.deviceManufacturer);
                writeHeader(writer2, "Model", this.deviceModel);
                writeHeader(writer2, FIELD_THREAD_NAME, this.threadName);
                writeHeader(writer2, FIELD_CRASH_REPORTER_KEY, this.reporterKey);
                writeHeader(writer2, FIELD_APP_START_DATE, JSONDateUtils.toString(this.appStartDate));
                writeHeader(writer2, FIELD_APP_CRASH_DATE, JSONDateUtils.toString(this.appCrashDate));
                if (this.isXamarinException.booleanValue()) {
                    writeHeader(writer2, FIELD_FORMAT, FIELD_FORMAT_VALUE);
                }
                writer2.write("\n");
                writer2.write(this.throwableStackTrace);
                writer2.flush();
                if (writer2 != null) {
                    try {
                        writer2.close();
                    } catch (Throwable e1) {
                        HockeyLog.error("Error saving crash report!", e1);
                        writer = writer2;
                        return;
                    }
                }
                writer = writer2;
            } catch (IOException e2) {
                e = e2;
                writer = writer2;
                try {
                    HockeyLog.error("Error saving crash report!", e);
                    if (writer != null) {
                        try {
                            writer.close();
                        } catch (Throwable e12) {
                            HockeyLog.error("Error saving crash report!", e12);
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (writer != null) {
                        try {
                            writer.close();
                        } catch (Throwable e122) {
                            HockeyLog.error("Error saving crash report!", e122);
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                writer = writer2;
                if (writer != null) {
                    writer.close();
                }
                throw th;
            }
        } catch (IOException e3) {
            e = e3;
            HockeyLog.error("Error saving crash report!", e);
            if (writer != null) {
                writer.close();
            }
        }
    }

    private void writeHeader(Writer writer, String name, String value) throws IOException {
        writer.write(name + ": " + value + "\n");
    }

    public String getCrashIdentifier() {
        return this.crashIdentifier;
    }

    public String getReporterKey() {
        return this.reporterKey;
    }

    public void setReporterKey(String reporterKey) {
        this.reporterKey = reporterKey;
    }

    public Date getAppStartDate() {
        return this.appStartDate;
    }

    public void setAppStartDate(Date appStartDate) {
        this.appStartDate = appStartDate;
    }

    public Date getAppCrashDate() {
        return this.appCrashDate;
    }

    public void setAppCrashDate(Date appCrashDate) {
        this.appCrashDate = appCrashDate;
    }

    public String getOsVersion() {
        return this.osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getOsBuild() {
        return this.osBuild;
    }

    public void setOsBuild(String osBuild) {
        this.osBuild = osBuild;
    }

    public String getDeviceManufacturer() {
        return this.deviceManufacturer;
    }

    public void setDeviceManufacturer(String deviceManufacturer) {
        this.deviceManufacturer = deviceManufacturer;
    }

    public String getDeviceModel() {
        return this.deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getAppPackage() {
        return this.appPackage;
    }

    public void setAppPackage(String appPackage) {
        this.appPackage = appPackage;
    }

    public String getAppVersionName() {
        return this.appVersionName;
    }

    public void setAppVersionName(String appVersionName) {
        this.appVersionName = appVersionName;
    }

    public String getAppVersionCode() {
        return this.appVersionCode;
    }

    public void setAppVersionCode(String appVersionCode) {
        this.appVersionCode = appVersionCode;
    }

    public String getThreadName() {
        return this.threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getThrowableStackTrace() {
        return this.throwableStackTrace;
    }

    public void setThrowableStackTrace(String throwableStackTrace) {
        this.throwableStackTrace = throwableStackTrace;
    }

    public Boolean getIsXamarinException() {
        return this.isXamarinException;
    }

    public void setIsXamarinException(Boolean isXamarinException) {
        this.isXamarinException = isXamarinException;
    }

    public String getFormat() {
        return this.format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}

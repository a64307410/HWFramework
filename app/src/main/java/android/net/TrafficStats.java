package android.net;

import android.content.Context;
import android.net.INetworkStatsService.Stub;
import android.os.Process;
import android.os.RemoteException;
import android.os.ServiceManager;
import com.android.server.NetworkManagementSocketTagger;
import dalvik.system.SocketTagger;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;

public class TrafficStats {
    public static final long GB_IN_BYTES = 1073741824;
    public static final long KB_IN_BYTES = 1024;
    public static final long MB_IN_BYTES = 1048576;
    public static final long PB_IN_BYTES = 1125899906842624L;
    public static final int TAG_SYSTEM_BACKUP = -253;
    public static final int TAG_SYSTEM_DOWNLOAD = -255;
    public static final int TAG_SYSTEM_MEDIA = -254;
    public static final int TAG_SYSTEM_RESTORE = -252;
    public static final long TB_IN_BYTES = 1099511627776L;
    private static final int TYPE_RX_BYTES = 0;
    private static final int TYPE_RX_PACKETS = 1;
    private static final int TYPE_TCP_RX_PACKETS = 4;
    private static final int TYPE_TCP_TX_PACKETS = 5;
    private static final int TYPE_TX_BYTES = 2;
    private static final int TYPE_TX_PACKETS = 3;
    public static final int UID_REMOVED = -4;
    public static final int UID_TETHERING = -5;
    public static final int UNSUPPORTED = -1;
    private static NetworkStats sActiveProfilingStart;
    private static Object sProfilingLock;
    private static INetworkStatsService sStatsService;

    static {
        /* JADX: method processing error */
/*
        Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: android.net.TrafficStats.<clinit>():void
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:113)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:256)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:281)
	at jadx.api.JavaClass.decompile(JavaClass.java:59)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:161)
Caused by: jadx.core.utils.exceptions.DecodeException:  in method: android.net.TrafficStats.<clinit>():void
	at jadx.core.dex.instructions.InsnDecoder.decodeInsns(InsnDecoder.java:46)
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:98)
	... 5 more
Caused by: java.lang.IllegalArgumentException: bogus opcode: 0073
	at com.android.dx.io.OpcodeInfo.get(OpcodeInfo.java:1197)
	at com.android.dx.io.OpcodeInfo.getFormat(OpcodeInfo.java:1212)
	at com.android.dx.io.instructions.DecodedInstruction.decode(DecodedInstruction.java:72)
	at jadx.core.dex.instructions.InsnDecoder.decodeInsns(InsnDecoder.java:43)
	... 6 more
*/
        /*
        // Can't load method instructions.
        */
        throw new UnsupportedOperationException("Method not decompiled: android.net.TrafficStats.<clinit>():void");
    }

    private static native long nativeGetIfaceStat(String str, int i);

    private static native long nativeGetTotalStat(int i);

    private static native long nativeGetUidStat(int i, int i2);

    private static synchronized INetworkStatsService getStatsService() {
        INetworkStatsService iNetworkStatsService;
        synchronized (TrafficStats.class) {
            if (sStatsService == null) {
                sStatsService = Stub.asInterface(ServiceManager.getService(Context.NETWORK_STATS_SERVICE));
            }
            iNetworkStatsService = sStatsService;
        }
        return iNetworkStatsService;
    }

    public static void setThreadStatsTag(int tag) {
        NetworkManagementSocketTagger.setThreadSocketStatsTag(tag);
    }

    public static void setThreadStatsTagBackup() {
        setThreadStatsTag(TAG_SYSTEM_BACKUP);
    }

    public static void setThreadStatsTagRestore() {
        setThreadStatsTag(TAG_SYSTEM_RESTORE);
    }

    public static int getThreadStatsTag() {
        return NetworkManagementSocketTagger.getThreadSocketStatsTag();
    }

    public static void clearThreadStatsTag() {
        NetworkManagementSocketTagger.setThreadSocketStatsTag(UNSUPPORTED);
    }

    public static void setThreadStatsUid(int uid) {
        NetworkManagementSocketTagger.setThreadSocketStatsUid(uid);
    }

    public static void clearThreadStatsUid() {
        NetworkManagementSocketTagger.setThreadSocketStatsUid(UNSUPPORTED);
    }

    public static void tagSocket(Socket socket) throws SocketException {
        SocketTagger.get().tag(socket);
    }

    public static void untagSocket(Socket socket) throws SocketException {
        SocketTagger.get().untag(socket);
    }

    public static void tagDatagramSocket(DatagramSocket socket) throws SocketException {
        SocketTagger.get().tag(socket);
    }

    public static void untagDatagramSocket(DatagramSocket socket) throws SocketException {
        SocketTagger.get().untag(socket);
    }

    public static void startDataProfiling(Context context) {
        synchronized (sProfilingLock) {
            if (sActiveProfilingStart != null) {
                throw new IllegalStateException("already profiling data");
            }
            sActiveProfilingStart = getDataLayerSnapshotForUid(context);
        }
    }

    public static NetworkStats stopDataProfiling(Context context) {
        NetworkStats profilingDelta;
        synchronized (sProfilingLock) {
            if (sActiveProfilingStart == null) {
                throw new IllegalStateException("not profiling data");
            }
            profilingDelta = NetworkStats.subtract(getDataLayerSnapshotForUid(context), sActiveProfilingStart, null, null);
            sActiveProfilingStart = null;
        }
        return profilingDelta;
    }

    public static void incrementOperationCount(int operationCount) {
        incrementOperationCount(getThreadStatsTag(), operationCount);
    }

    public static void incrementOperationCount(int tag, int operationCount) {
        try {
            getStatsService().incrementOperationCount(Process.myUid(), tag, operationCount);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public static void closeQuietly(INetworkStatsSession session) {
        if (session != null) {
            try {
                session.close();
            } catch (RuntimeException rethrown) {
                throw rethrown;
            } catch (Exception e) {
            }
        }
    }

    public static long getMobileTxPackets() {
        long total = 0;
        String[] mobileIfaces = getMobileIfaces();
        for (int i = TYPE_RX_BYTES; i < mobileIfaces.length; i += TYPE_RX_PACKETS) {
            total += getTxPackets(mobileIfaces[i]);
        }
        return total;
    }

    public static long getMobileRxPackets() {
        long total = 0;
        String[] mobileIfaces = getMobileIfaces();
        for (int i = TYPE_RX_BYTES; i < mobileIfaces.length; i += TYPE_RX_PACKETS) {
            total += getRxPackets(mobileIfaces[i]);
        }
        return total;
    }

    public static long getMobileTxBytes() {
        long total = 0;
        String[] mobileIfaces = getMobileIfaces();
        for (int i = TYPE_RX_BYTES; i < mobileIfaces.length; i += TYPE_RX_PACKETS) {
            total += getTxBytes(mobileIfaces[i]);
        }
        return total;
    }

    public static long getMobileRxBytes() {
        long total = 0;
        String[] mobileIfaces = getMobileIfaces();
        for (int i = TYPE_RX_BYTES; i < mobileIfaces.length; i += TYPE_RX_PACKETS) {
            total += getRxBytes(mobileIfaces[i]);
        }
        return total;
    }

    public static long getMobileTcpRxPackets() {
        long total = 0;
        String[] mobileIfaces = getMobileIfaces();
        int length = mobileIfaces.length;
        for (int i = TYPE_RX_BYTES; i < length; i += TYPE_RX_PACKETS) {
            long stat = nativeGetIfaceStat(mobileIfaces[i], TYPE_TCP_RX_PACKETS);
            if (stat != -1) {
                total += stat;
            }
        }
        return total;
    }

    public static long getMobileTcpTxPackets() {
        long total = 0;
        String[] mobileIfaces = getMobileIfaces();
        int length = mobileIfaces.length;
        for (int i = TYPE_RX_BYTES; i < length; i += TYPE_RX_PACKETS) {
            long stat = nativeGetIfaceStat(mobileIfaces[i], TYPE_TCP_TX_PACKETS);
            if (stat != -1) {
                total += stat;
            }
        }
        return total;
    }

    public static long getTxPackets(String iface) {
        return nativeGetIfaceStat(iface, TYPE_TX_PACKETS);
    }

    public static long getRxPackets(String iface) {
        return nativeGetIfaceStat(iface, TYPE_RX_PACKETS);
    }

    public static long getTxBytes(String iface) {
        return nativeGetIfaceStat(iface, TYPE_TX_BYTES);
    }

    public static long getRxBytes(String iface) {
        return nativeGetIfaceStat(iface, TYPE_RX_BYTES);
    }

    public static long getTotalTxPackets() {
        return nativeGetTotalStat(TYPE_TX_PACKETS);
    }

    public static long getTotalRxPackets() {
        return nativeGetTotalStat(TYPE_RX_PACKETS);
    }

    public static long getTotalTxBytes() {
        return nativeGetTotalStat(TYPE_TX_BYTES);
    }

    public static long getTotalRxBytes() {
        return nativeGetTotalStat(TYPE_RX_BYTES);
    }

    public static long getUidTxBytes(int uid) {
        int callingUid = Process.myUid();
        if (callingUid == Process.SYSTEM_UID || callingUid == uid) {
            return nativeGetUidStat(uid, TYPE_TX_BYTES);
        }
        return -1;
    }

    public static long getUidRxBytes(int uid) {
        int callingUid = Process.myUid();
        if (callingUid == Process.SYSTEM_UID || callingUid == uid) {
            return nativeGetUidStat(uid, TYPE_RX_BYTES);
        }
        return -1;
    }

    public static long getUidTxPackets(int uid) {
        int callingUid = Process.myUid();
        if (callingUid == Process.SYSTEM_UID || callingUid == uid) {
            return nativeGetUidStat(uid, TYPE_TX_PACKETS);
        }
        return -1;
    }

    public static long getUidRxPackets(int uid) {
        int callingUid = Process.myUid();
        if (callingUid == Process.SYSTEM_UID || callingUid == uid) {
            return nativeGetUidStat(uid, TYPE_RX_PACKETS);
        }
        return -1;
    }

    @Deprecated
    public static long getUidTcpTxBytes(int uid) {
        return -1;
    }

    @Deprecated
    public static long getUidTcpRxBytes(int uid) {
        return -1;
    }

    @Deprecated
    public static long getUidUdpTxBytes(int uid) {
        return -1;
    }

    @Deprecated
    public static long getUidUdpRxBytes(int uid) {
        return -1;
    }

    @Deprecated
    public static long getUidTcpTxSegments(int uid) {
        return -1;
    }

    @Deprecated
    public static long getUidTcpRxSegments(int uid) {
        return -1;
    }

    @Deprecated
    public static long getUidUdpTxPackets(int uid) {
        return -1;
    }

    @Deprecated
    public static long getUidUdpRxPackets(int uid) {
        return -1;
    }

    private static NetworkStats getDataLayerSnapshotForUid(Context context) {
        try {
            return getStatsService().getDataLayerSnapshotForUid(Process.myUid());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public static String[] getMobileIfaces() {
        try {
            return getStatsService().getMobileIfaces();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }
}

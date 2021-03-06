package com.android.internal.telephony;

import android.net.LinkProperties;
import android.net.NetworkCapabilities;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.telephony.CellInfo;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.VoLteServiceState;
import java.util.List;

public interface ITelephonyRegistry extends IInterface {

    public static abstract class Stub extends Binder implements ITelephonyRegistry {
        private static final String DESCRIPTOR = "com.android.internal.telephony.ITelephonyRegistry";
        static final int TRANSACTION_addOnSubscriptionsChangedListener = 1;
        static final int TRANSACTION_listen = 3;
        static final int TRANSACTION_listenForSubscriber = 4;
        static final int TRANSACTION_notifyCallForwardingChanged = 10;
        static final int TRANSACTION_notifyCallForwardingChangedForSubscriber = 11;
        static final int TRANSACTION_notifyCallState = 5;
        static final int TRANSACTION_notifyCallStateForPhoneId = 6;
        static final int TRANSACTION_notifyCarrierNetworkChange = 29;
        static final int TRANSACTION_notifyCellInfo = 21;
        static final int TRANSACTION_notifyCellInfoForSubscriber = 25;
        static final int TRANSACTION_notifyCellLocation = 18;
        static final int TRANSACTION_notifyCellLocationForSubscriber = 19;
        static final int TRANSACTION_notifyDataActivity = 12;
        static final int TRANSACTION_notifyDataActivityForSubscriber = 13;
        static final int TRANSACTION_notifyDataConnection = 14;
        static final int TRANSACTION_notifyDataConnectionFailed = 16;
        static final int TRANSACTION_notifyDataConnectionFailedForSubscriber = 17;
        static final int TRANSACTION_notifyDataConnectionForSubscriber = 15;
        static final int TRANSACTION_notifyDisconnectCause = 23;
        static final int TRANSACTION_notifyMessageWaitingChangedForPhoneId = 9;
        static final int TRANSACTION_notifyOemHookRawEventForSubscriber = 27;
        static final int TRANSACTION_notifyOtaspChanged = 20;
        static final int TRANSACTION_notifyPreciseCallState = 22;
        static final int TRANSACTION_notifyPreciseDataConnectionFailed = 24;
        static final int TRANSACTION_notifyServiceStateForPhoneId = 7;
        static final int TRANSACTION_notifySignalStrengthForPhoneId = 8;
        static final int TRANSACTION_notifySubscriptionInfoChanged = 28;
        static final int TRANSACTION_notifyVoLteServiceStateChanged = 26;
        static final int TRANSACTION_removeOnSubscriptionsChangedListener = 2;

        private static class Proxy implements ITelephonyRegistry {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            public void addOnSubscriptionsChangedListener(String pkg, IOnSubscriptionsChangedListener callback) throws RemoteException {
                IBinder iBinder = null;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    if (callback != null) {
                        iBinder = callback.asBinder();
                    }
                    _data.writeStrongBinder(iBinder);
                    this.mRemote.transact(Stub.TRANSACTION_addOnSubscriptionsChangedListener, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void removeOnSubscriptionsChangedListener(String pkg, IOnSubscriptionsChangedListener callback) throws RemoteException {
                IBinder iBinder = null;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    if (callback != null) {
                        iBinder = callback.asBinder();
                    }
                    _data.writeStrongBinder(iBinder);
                    this.mRemote.transact(Stub.TRANSACTION_removeOnSubscriptionsChangedListener, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void listen(String pkg, IPhoneStateListener callback, int events, boolean notifyNow) throws RemoteException {
                IBinder iBinder = null;
                int i = 0;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(pkg);
                    if (callback != null) {
                        iBinder = callback.asBinder();
                    }
                    _data.writeStrongBinder(iBinder);
                    _data.writeInt(events);
                    if (notifyNow) {
                        i = Stub.TRANSACTION_addOnSubscriptionsChangedListener;
                    }
                    _data.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_listen, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void listenForSubscriber(int subId, String pkg, IPhoneStateListener callback, int events, boolean notifyNow) throws RemoteException {
                IBinder iBinder = null;
                int i = 0;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(pkg);
                    if (callback != null) {
                        iBinder = callback.asBinder();
                    }
                    _data.writeStrongBinder(iBinder);
                    _data.writeInt(events);
                    if (notifyNow) {
                        i = Stub.TRANSACTION_addOnSubscriptionsChangedListener;
                    }
                    _data.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_listenForSubscriber, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void notifyCallState(int state, String incomingNumber) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    _data.writeString(incomingNumber);
                    this.mRemote.transact(Stub.TRANSACTION_notifyCallState, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void notifyCallStateForPhoneId(int phoneId, int subId, int state, String incomingNumber) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(phoneId);
                    _data.writeInt(subId);
                    _data.writeInt(state);
                    _data.writeString(incomingNumber);
                    this.mRemote.transact(Stub.TRANSACTION_notifyCallStateForPhoneId, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void notifyServiceStateForPhoneId(int phoneId, int subId, ServiceState state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(phoneId);
                    _data.writeInt(subId);
                    if (state != null) {
                        _data.writeInt(Stub.TRANSACTION_addOnSubscriptionsChangedListener);
                        state.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_notifyServiceStateForPhoneId, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void notifySignalStrengthForPhoneId(int phoneId, int subId, SignalStrength signalStrength) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(phoneId);
                    _data.writeInt(subId);
                    if (signalStrength != null) {
                        _data.writeInt(Stub.TRANSACTION_addOnSubscriptionsChangedListener);
                        signalStrength.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_notifySignalStrengthForPhoneId, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void notifyMessageWaitingChangedForPhoneId(int phoneId, int subId, boolean mwi) throws RemoteException {
                int i = 0;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(phoneId);
                    _data.writeInt(subId);
                    if (mwi) {
                        i = Stub.TRANSACTION_addOnSubscriptionsChangedListener;
                    }
                    _data.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_notifyMessageWaitingChangedForPhoneId, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void notifyCallForwardingChanged(boolean cfi) throws RemoteException {
                int i = 0;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (cfi) {
                        i = Stub.TRANSACTION_addOnSubscriptionsChangedListener;
                    }
                    _data.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_notifyCallForwardingChanged, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void notifyCallForwardingChangedForSubscriber(int subId, boolean cfi) throws RemoteException {
                int i = 0;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    if (cfi) {
                        i = Stub.TRANSACTION_addOnSubscriptionsChangedListener;
                    }
                    _data.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_notifyCallForwardingChangedForSubscriber, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void notifyDataActivity(int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    this.mRemote.transact(Stub.TRANSACTION_notifyDataActivity, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void notifyDataActivityForSubscriber(int subId, int state) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(state);
                    this.mRemote.transact(Stub.TRANSACTION_notifyDataActivityForSubscriber, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void notifyDataConnection(int state, boolean isDataConnectivityPossible, String reason, String apn, String apnType, LinkProperties linkProperties, NetworkCapabilities networkCapabilities, int networkType, boolean roaming) throws RemoteException {
                int i = Stub.TRANSACTION_addOnSubscriptionsChangedListener;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    int i2;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(state);
                    if (isDataConnectivityPossible) {
                        i2 = Stub.TRANSACTION_addOnSubscriptionsChangedListener;
                    } else {
                        i2 = 0;
                    }
                    _data.writeInt(i2);
                    _data.writeString(reason);
                    _data.writeString(apn);
                    _data.writeString(apnType);
                    if (linkProperties != null) {
                        _data.writeInt(Stub.TRANSACTION_addOnSubscriptionsChangedListener);
                        linkProperties.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (networkCapabilities != null) {
                        _data.writeInt(Stub.TRANSACTION_addOnSubscriptionsChangedListener);
                        networkCapabilities.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(networkType);
                    if (!roaming) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_notifyDataConnection, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void notifyDataConnectionForSubscriber(int subId, int state, boolean isDataConnectivityPossible, String reason, String apn, String apnType, LinkProperties linkProperties, NetworkCapabilities networkCapabilities, int networkType, boolean roaming) throws RemoteException {
                int i = Stub.TRANSACTION_addOnSubscriptionsChangedListener;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    int i2;
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeInt(state);
                    if (isDataConnectivityPossible) {
                        i2 = Stub.TRANSACTION_addOnSubscriptionsChangedListener;
                    } else {
                        i2 = 0;
                    }
                    _data.writeInt(i2);
                    _data.writeString(reason);
                    _data.writeString(apn);
                    _data.writeString(apnType);
                    if (linkProperties != null) {
                        _data.writeInt(Stub.TRANSACTION_addOnSubscriptionsChangedListener);
                        linkProperties.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (networkCapabilities != null) {
                        _data.writeInt(Stub.TRANSACTION_addOnSubscriptionsChangedListener);
                        networkCapabilities.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(networkType);
                    if (!roaming) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_notifyDataConnectionForSubscriber, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void notifyDataConnectionFailed(String reason, String apnType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(reason);
                    _data.writeString(apnType);
                    this.mRemote.transact(Stub.TRANSACTION_notifyDataConnectionFailed, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void notifyDataConnectionFailedForSubscriber(int subId, String reason, String apnType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeString(reason);
                    _data.writeString(apnType);
                    this.mRemote.transact(Stub.TRANSACTION_notifyDataConnectionFailedForSubscriber, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void notifyCellLocation(Bundle cellLocation) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (cellLocation != null) {
                        _data.writeInt(Stub.TRANSACTION_addOnSubscriptionsChangedListener);
                        cellLocation.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_notifyCellLocation, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void notifyCellLocationForSubscriber(int subId, Bundle cellLocation) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    if (cellLocation != null) {
                        _data.writeInt(Stub.TRANSACTION_addOnSubscriptionsChangedListener);
                        cellLocation.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_notifyCellLocationForSubscriber, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void notifyOtaspChanged(int otaspMode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(otaspMode);
                    this.mRemote.transact(Stub.TRANSACTION_notifyOtaspChanged, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void notifyCellInfo(List<CellInfo> cellInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeTypedList(cellInfo);
                    this.mRemote.transact(Stub.TRANSACTION_notifyCellInfo, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void notifyPreciseCallState(int ringingCallState, int foregroundCallState, int backgroundCallState) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(ringingCallState);
                    _data.writeInt(foregroundCallState);
                    _data.writeInt(backgroundCallState);
                    this.mRemote.transact(Stub.TRANSACTION_notifyPreciseCallState, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void notifyDisconnectCause(int disconnectCause, int preciseDisconnectCause) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(disconnectCause);
                    _data.writeInt(preciseDisconnectCause);
                    this.mRemote.transact(Stub.TRANSACTION_notifyDisconnectCause, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void notifyPreciseDataConnectionFailed(String reason, String apnType, String apn, String failCause) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(reason);
                    _data.writeString(apnType);
                    _data.writeString(apn);
                    _data.writeString(failCause);
                    this.mRemote.transact(Stub.TRANSACTION_notifyPreciseDataConnectionFailed, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void notifyCellInfoForSubscriber(int subId, List<CellInfo> cellInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeTypedList(cellInfo);
                    this.mRemote.transact(Stub.TRANSACTION_notifyCellInfoForSubscriber, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void notifyVoLteServiceStateChanged(VoLteServiceState lteState) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (lteState != null) {
                        _data.writeInt(Stub.TRANSACTION_addOnSubscriptionsChangedListener);
                        lteState.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(Stub.TRANSACTION_notifyVoLteServiceStateChanged, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void notifyOemHookRawEventForSubscriber(int subId, byte[] rawData) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(subId);
                    _data.writeByteArray(rawData);
                    this.mRemote.transact(Stub.TRANSACTION_notifyOemHookRawEventForSubscriber, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void notifySubscriptionInfoChanged() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(Stub.TRANSACTION_notifySubscriptionInfoChanged, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            public void notifyCarrierNetworkChange(boolean active) throws RemoteException {
                int i = 0;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (active) {
                        i = Stub.TRANSACTION_addOnSubscriptionsChangedListener;
                    }
                    _data.writeInt(i);
                    this.mRemote.transact(Stub.TRANSACTION_notifyCarrierNetworkChange, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ITelephonyRegistry asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ITelephonyRegistry)) {
                return new Proxy(obj);
            }
            return (ITelephonyRegistry) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            int _arg0;
            int _arg1;
            String _arg3;
            String _arg4;
            switch (code) {
                case TRANSACTION_addOnSubscriptionsChangedListener /*1*/:
                    data.enforceInterface(DESCRIPTOR);
                    addOnSubscriptionsChangedListener(data.readString(), com.android.internal.telephony.IOnSubscriptionsChangedListener.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_removeOnSubscriptionsChangedListener /*2*/:
                    data.enforceInterface(DESCRIPTOR);
                    removeOnSubscriptionsChangedListener(data.readString(), com.android.internal.telephony.IOnSubscriptionsChangedListener.Stub.asInterface(data.readStrongBinder()));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_listen /*3*/:
                    data.enforceInterface(DESCRIPTOR);
                    listen(data.readString(), com.android.internal.telephony.IPhoneStateListener.Stub.asInterface(data.readStrongBinder()), data.readInt(), data.readInt() != 0);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_listenForSubscriber /*4*/:
                    data.enforceInterface(DESCRIPTOR);
                    listenForSubscriber(data.readInt(), data.readString(), com.android.internal.telephony.IPhoneStateListener.Stub.asInterface(data.readStrongBinder()), data.readInt(), data.readInt() != 0);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_notifyCallState /*5*/:
                    data.enforceInterface(DESCRIPTOR);
                    notifyCallState(data.readInt(), data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_notifyCallStateForPhoneId /*6*/:
                    data.enforceInterface(DESCRIPTOR);
                    notifyCallStateForPhoneId(data.readInt(), data.readInt(), data.readInt(), data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_notifyServiceStateForPhoneId /*7*/:
                    ServiceState serviceState;
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = data.readInt();
                    _arg1 = data.readInt();
                    if (data.readInt() != 0) {
                        serviceState = (ServiceState) ServiceState.CREATOR.createFromParcel(data);
                    } else {
                        serviceState = null;
                    }
                    notifyServiceStateForPhoneId(_arg0, _arg1, serviceState);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_notifySignalStrengthForPhoneId /*8*/:
                    SignalStrength signalStrength;
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = data.readInt();
                    _arg1 = data.readInt();
                    if (data.readInt() != 0) {
                        signalStrength = (SignalStrength) SignalStrength.CREATOR.createFromParcel(data);
                    } else {
                        signalStrength = null;
                    }
                    notifySignalStrengthForPhoneId(_arg0, _arg1, signalStrength);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_notifyMessageWaitingChangedForPhoneId /*9*/:
                    data.enforceInterface(DESCRIPTOR);
                    notifyMessageWaitingChangedForPhoneId(data.readInt(), data.readInt(), data.readInt() != 0);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_notifyCallForwardingChanged /*10*/:
                    data.enforceInterface(DESCRIPTOR);
                    notifyCallForwardingChanged(data.readInt() != 0);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_notifyCallForwardingChangedForSubscriber /*11*/:
                    data.enforceInterface(DESCRIPTOR);
                    notifyCallForwardingChangedForSubscriber(data.readInt(), data.readInt() != 0);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_notifyDataActivity /*12*/:
                    data.enforceInterface(DESCRIPTOR);
                    notifyDataActivity(data.readInt());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_notifyDataActivityForSubscriber /*13*/:
                    data.enforceInterface(DESCRIPTOR);
                    notifyDataActivityForSubscriber(data.readInt(), data.readInt());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_notifyDataConnection /*14*/:
                    LinkProperties linkProperties;
                    NetworkCapabilities networkCapabilities;
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = data.readInt();
                    boolean _arg12 = data.readInt() != 0;
                    String _arg2 = data.readString();
                    _arg3 = data.readString();
                    _arg4 = data.readString();
                    if (data.readInt() != 0) {
                        linkProperties = (LinkProperties) LinkProperties.CREATOR.createFromParcel(data);
                    } else {
                        linkProperties = null;
                    }
                    if (data.readInt() != 0) {
                        networkCapabilities = (NetworkCapabilities) NetworkCapabilities.CREATOR.createFromParcel(data);
                    } else {
                        networkCapabilities = null;
                    }
                    notifyDataConnection(_arg0, _arg12, _arg2, _arg3, _arg4, linkProperties, networkCapabilities, data.readInt(), data.readInt() != 0);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_notifyDataConnectionForSubscriber /*15*/:
                    LinkProperties linkProperties2;
                    NetworkCapabilities networkCapabilities2;
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = data.readInt();
                    _arg1 = data.readInt();
                    boolean _arg22 = data.readInt() != 0;
                    _arg3 = data.readString();
                    _arg4 = data.readString();
                    String _arg5 = data.readString();
                    if (data.readInt() != 0) {
                        linkProperties2 = (LinkProperties) LinkProperties.CREATOR.createFromParcel(data);
                    } else {
                        linkProperties2 = null;
                    }
                    if (data.readInt() != 0) {
                        networkCapabilities2 = (NetworkCapabilities) NetworkCapabilities.CREATOR.createFromParcel(data);
                    } else {
                        networkCapabilities2 = null;
                    }
                    notifyDataConnectionForSubscriber(_arg0, _arg1, _arg22, _arg3, _arg4, _arg5, linkProperties2, networkCapabilities2, data.readInt(), data.readInt() != 0);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_notifyDataConnectionFailed /*16*/:
                    data.enforceInterface(DESCRIPTOR);
                    notifyDataConnectionFailed(data.readString(), data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_notifyDataConnectionFailedForSubscriber /*17*/:
                    data.enforceInterface(DESCRIPTOR);
                    notifyDataConnectionFailedForSubscriber(data.readInt(), data.readString(), data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_notifyCellLocation /*18*/:
                    Bundle bundle;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        bundle = null;
                    }
                    notifyCellLocation(bundle);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_notifyCellLocationForSubscriber /*19*/:
                    Bundle bundle2;
                    data.enforceInterface(DESCRIPTOR);
                    _arg0 = data.readInt();
                    if (data.readInt() != 0) {
                        bundle2 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        bundle2 = null;
                    }
                    notifyCellLocationForSubscriber(_arg0, bundle2);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_notifyOtaspChanged /*20*/:
                    data.enforceInterface(DESCRIPTOR);
                    notifyOtaspChanged(data.readInt());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_notifyCellInfo /*21*/:
                    data.enforceInterface(DESCRIPTOR);
                    notifyCellInfo(data.createTypedArrayList(CellInfo.CREATOR));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_notifyPreciseCallState /*22*/:
                    data.enforceInterface(DESCRIPTOR);
                    notifyPreciseCallState(data.readInt(), data.readInt(), data.readInt());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_notifyDisconnectCause /*23*/:
                    data.enforceInterface(DESCRIPTOR);
                    notifyDisconnectCause(data.readInt(), data.readInt());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_notifyPreciseDataConnectionFailed /*24*/:
                    data.enforceInterface(DESCRIPTOR);
                    notifyPreciseDataConnectionFailed(data.readString(), data.readString(), data.readString(), data.readString());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_notifyCellInfoForSubscriber /*25*/:
                    data.enforceInterface(DESCRIPTOR);
                    notifyCellInfoForSubscriber(data.readInt(), data.createTypedArrayList(CellInfo.CREATOR));
                    reply.writeNoException();
                    return true;
                case TRANSACTION_notifyVoLteServiceStateChanged /*26*/:
                    VoLteServiceState voLteServiceState;
                    data.enforceInterface(DESCRIPTOR);
                    if (data.readInt() != 0) {
                        voLteServiceState = (VoLteServiceState) VoLteServiceState.CREATOR.createFromParcel(data);
                    } else {
                        voLteServiceState = null;
                    }
                    notifyVoLteServiceStateChanged(voLteServiceState);
                    reply.writeNoException();
                    return true;
                case TRANSACTION_notifyOemHookRawEventForSubscriber /*27*/:
                    data.enforceInterface(DESCRIPTOR);
                    notifyOemHookRawEventForSubscriber(data.readInt(), data.createByteArray());
                    reply.writeNoException();
                    return true;
                case TRANSACTION_notifySubscriptionInfoChanged /*28*/:
                    data.enforceInterface(DESCRIPTOR);
                    notifySubscriptionInfoChanged();
                    reply.writeNoException();
                    return true;
                case TRANSACTION_notifyCarrierNetworkChange /*29*/:
                    data.enforceInterface(DESCRIPTOR);
                    notifyCarrierNetworkChange(data.readInt() != 0);
                    reply.writeNoException();
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }
    }

    void addOnSubscriptionsChangedListener(String str, IOnSubscriptionsChangedListener iOnSubscriptionsChangedListener) throws RemoteException;

    void listen(String str, IPhoneStateListener iPhoneStateListener, int i, boolean z) throws RemoteException;

    void listenForSubscriber(int i, String str, IPhoneStateListener iPhoneStateListener, int i2, boolean z) throws RemoteException;

    void notifyCallForwardingChanged(boolean z) throws RemoteException;

    void notifyCallForwardingChangedForSubscriber(int i, boolean z) throws RemoteException;

    void notifyCallState(int i, String str) throws RemoteException;

    void notifyCallStateForPhoneId(int i, int i2, int i3, String str) throws RemoteException;

    void notifyCarrierNetworkChange(boolean z) throws RemoteException;

    void notifyCellInfo(List<CellInfo> list) throws RemoteException;

    void notifyCellInfoForSubscriber(int i, List<CellInfo> list) throws RemoteException;

    void notifyCellLocation(Bundle bundle) throws RemoteException;

    void notifyCellLocationForSubscriber(int i, Bundle bundle) throws RemoteException;

    void notifyDataActivity(int i) throws RemoteException;

    void notifyDataActivityForSubscriber(int i, int i2) throws RemoteException;

    void notifyDataConnection(int i, boolean z, String str, String str2, String str3, LinkProperties linkProperties, NetworkCapabilities networkCapabilities, int i2, boolean z2) throws RemoteException;

    void notifyDataConnectionFailed(String str, String str2) throws RemoteException;

    void notifyDataConnectionFailedForSubscriber(int i, String str, String str2) throws RemoteException;

    void notifyDataConnectionForSubscriber(int i, int i2, boolean z, String str, String str2, String str3, LinkProperties linkProperties, NetworkCapabilities networkCapabilities, int i3, boolean z2) throws RemoteException;

    void notifyDisconnectCause(int i, int i2) throws RemoteException;

    void notifyMessageWaitingChangedForPhoneId(int i, int i2, boolean z) throws RemoteException;

    void notifyOemHookRawEventForSubscriber(int i, byte[] bArr) throws RemoteException;

    void notifyOtaspChanged(int i) throws RemoteException;

    void notifyPreciseCallState(int i, int i2, int i3) throws RemoteException;

    void notifyPreciseDataConnectionFailed(String str, String str2, String str3, String str4) throws RemoteException;

    void notifyServiceStateForPhoneId(int i, int i2, ServiceState serviceState) throws RemoteException;

    void notifySignalStrengthForPhoneId(int i, int i2, SignalStrength signalStrength) throws RemoteException;

    void notifySubscriptionInfoChanged() throws RemoteException;

    void notifyVoLteServiceStateChanged(VoLteServiceState voLteServiceState) throws RemoteException;

    void removeOnSubscriptionsChangedListener(String str, IOnSubscriptionsChangedListener iOnSubscriptionsChangedListener) throws RemoteException;
}

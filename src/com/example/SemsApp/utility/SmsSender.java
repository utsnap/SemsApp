package com.example.SemsApp.utility;

import android.telephony.SmsManager;

/**
 * Created by Administrator on 14. 3. 23.
 * 기계에 문자를 보내는 기능을 구현한다.
 */
public class SmsSender {
	private static final SmsManager SMS_MANAGER = SmsManager.getDefault();

	public static final void sendGet(final String phoneNumber) {
		Command.GET.sendSms(phoneNumber);
	}

	public static final void sendGetNum(final String phoneNumber) {
		Command.GET_NUM.sendSms(phoneNumber);
	}

	public static final void sendGetTime(final String phoneNumber) {
		Command.GET_TIME.sendSms(phoneNumber);
	}

	public static final void sendGetLimit1(final String phoneNumber) {
		Command.GET_LIMT1.sendSms(phoneNumber);
	}

	public static final void sendGetKind1(final String phoneNumber) {
		Command.GET_KIND1.sendSms(phoneNumber);
	}
	public static final void sendGetUse1(final String phoneNumber) {
		Command.GET_USE1.sendSms(phoneNumber);
	}
	public static final void sendInfo(final String phoneNumber) {
		Command.INFO.sendSms(phoneNumber);
	}


	private enum Command {
		GET,
		GET_NUM,
		GET_TIME,
		GET_LIMT1,
		GET_KIND1,
		GET_USE1,
		INFO;

		void sendSms(final String phoneNumber) {
			SMS_MANAGER.sendTextMessage(phoneNumber, null, this.toString(), null, null);
		}

		@Override
		public String toString() {
			return super.toString().replace("_", " ");
		}
	}
}

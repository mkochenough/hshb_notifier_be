package de.hackerspacebremen.valueobjects;

public final class PushProperties {

	private boolean gcmEnabled;
	
	private String gcmApiKey;
	
	private boolean apnsEnabled;
	
	private boolean mpnsEnabled;
	
	private boolean mailEnabled;
	
	private String apnsCertificate;
	
	private String apnsPassword;
	
	public PushProperties(){
		gcmEnabled = false;
		gcmApiKey = "";
		apnsEnabled = false;
		mpnsEnabled = false;
		mailEnabled = false;
		apnsCertificate = "";
		apnsPassword = "";
	}

	public boolean isGcmEnabled() {
		return gcmEnabled;
	}

	public void setGcmEnabled(final boolean gcmEnabled) {
		this.gcmEnabled = gcmEnabled;
	}

	public boolean isApnsEnabled() {
		return apnsEnabled;
	}

	public void setApnsEnabled(final boolean apnsEnabled) {
		this.apnsEnabled = apnsEnabled;
	}

	public boolean isMpnsEnabled() {
		return mpnsEnabled;
	}

	public void setMpnsEnabled(final boolean mpnsEnabled) {
		this.mpnsEnabled = mpnsEnabled;
	}

	public String getGcmApiKey() {
		return gcmApiKey;
	}

	public void setGcmApiKey(final String gcmApiKey) {
		this.gcmApiKey = gcmApiKey;
	}

	public boolean isMailEnabled() {
		return mailEnabled;
	}

	public void setMailEnabled(final boolean mailEnabled) {
		this.mailEnabled = mailEnabled;
	}

	public String getApnsCertificate() {
		return apnsCertificate;
	}

	public void setApnsCertificate(String apnsCertificate) {
		this.apnsCertificate = apnsCertificate;
	}

	public String getApnsPassword() {
		return apnsPassword;
	}

	public void setApnsPassword(String apnsPassword) {
		this.apnsPassword = apnsPassword;
	}
}

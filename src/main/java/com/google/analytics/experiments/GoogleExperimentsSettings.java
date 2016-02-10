package com.google.analytics.experiments;

/**
 * @author <a href="mailto:d@davemaple.com">David Maple</a>
 */
public class GoogleExperimentsSettings {
    private final String _serviceAccountEmail;
    private final String _p12Path;
    private final String _googleAnalyticsAccountId;
    private final String _googleAnalyticsTrackingId;
    private final String _googleAnalyticsViewId;

    public GoogleExperimentsSettings(final String serviceAccountEmail,
                                     final String p12Path,
                                     final String googleAnalyticsAccountId,
                                     final String googleAnalyticsTrackingId,
                                     final String googleAnalyticsViewId) {
        _serviceAccountEmail = serviceAccountEmail;
        _p12Path = p12Path;
        _googleAnalyticsAccountId = googleAnalyticsAccountId;
        _googleAnalyticsTrackingId = googleAnalyticsTrackingId;
        _googleAnalyticsViewId = googleAnalyticsViewId;
    }

    public String getServiceAccountEmail() {
        return _serviceAccountEmail;
    }

    public String getP12Path() {
        return _p12Path;
    }

    public String getGoogleAnalyticsAccountId() {
        return _googleAnalyticsAccountId;
    }

    public String getGoogleAnalyticsTrackingId() {
        return _googleAnalyticsTrackingId;
    }

    public String getGoogleAnalyticsViewId() {
        return _googleAnalyticsViewId;
    }

    @Override
    public String toString() {
        return "GoogleExperimentsSettings{" +
                "_serviceAccountEmail='" + _serviceAccountEmail + '\'' +
                ", _p12Path='" + _p12Path + '\'' +
                ", _googleAnalyticsAccountId='" + _googleAnalyticsAccountId + '\'' +
                ", _googleAnalyticsTrackingId='" + _googleAnalyticsTrackingId + '\'' +
                ", _googleAnalyticsViewId='" + _googleAnalyticsViewId + '\'' +
                '}';
    }
}

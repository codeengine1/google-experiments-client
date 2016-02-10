package com.google.analytics.experiments;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.*;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;
import java.util.Collections;

/**
 * @author <a href="mailto:d@davemaple.com">David Maple</a>
 */
public class GoogleExperimentsClient {
    private static final String GOOGLE_ANALYTICS_READ_ONLY_SCOPE = "https://www.googleapis.com/auth/analytics.readonly";
    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleExperimentsClient.class);
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private final GoogleExperimentsSettings _settings;

    public GoogleExperimentsClient(GoogleExperimentsSettings settings) {
        _settings = settings;
    }

    public GoogleExperimentsResult fetchExperiments() {
        try {
            final URL p12Url = this.getClass().getResource(_settings.getP12Path());

            final GoogleCredential credential = new GoogleCredential.Builder()
                    .setTransport(HTTP_TRANSPORT)
                    .setJsonFactory(JSON_FACTORY)
                    .setServiceAccountId(_settings.getServiceAccountEmail())
                    .setServiceAccountScopes(Collections.singleton(GOOGLE_ANALYTICS_READ_ONLY_SCOPE))
                    .setServiceAccountPrivateKeyFromP12File(new File(p12Url.toURI()))
                    .build();

            final String experimentsUrl = String.format(
                    "https://www.googleapis.com/analytics/v3/management/accounts/" +
                            "%s/webproperties/%s/profiles/%s/experiments",
                    _settings.getGoogleAnalyticsAccountId(),
                    _settings.getGoogleAnalyticsTrackingId(),
                    _settings.getGoogleAnalyticsViewId()
            );

            HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory(credential);
            GenericUrl url = new GenericUrl(experimentsUrl);
            HttpRequest request = requestFactory.buildGetRequest(url);

            HttpResponse response = request.execute();
            final String json = response.parseAsString();

            if (json == null || json.trim().isEmpty()) {
                return null;
            }

            GoogleExperimentsResult result = ObjectMapper.readValue(json, GoogleExperimentsResult.class);
            result.setJson(json);
            return result;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            return null;
        }
    }
}

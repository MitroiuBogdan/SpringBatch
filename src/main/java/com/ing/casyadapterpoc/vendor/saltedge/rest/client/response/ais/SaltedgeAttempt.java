package com.ing.casyadapterpoc.vendor.saltedge.rest.client.response.ais;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@Setter
@NoArgsConstructor
@Data
public class SaltedgeAttempt {

    private String id;
    private boolean finished;
    @JsonProperty("api_mode")
    private String apiMode;
    @JsonProperty("api_version")
    private String apiVersion;
    private String locale;
    @JsonProperty("json_present")
    private boolean userPresent;
    @JsonProperty("customer_last_logged_at")
    private String customerLastLoggedAt;
    @JsonProperty("remote_ip")
    private String remoteIp;
    @JsonProperty("finished_recent")
    private boolean finishedRecent;
    private boolean partial;
    @JsonProperty("automatic_refresh")
    private boolean automaticRefresh;
    @JsonProperty("daily_refresh")
    private boolean dailyRefresh;
    private boolean categorize;
    @JsonProperty("custom_fields")
    private Object customFields;
    @JsonProperty("device_type")
    private String deviceType;
    @JsonProperty("user_agent")
    private String userAgent;
    @JsonProperty("exclude_accounts")
    private List<String> excludeAccounts;
    @JsonProperty("fetch_scopes")
    private List<String> fetchScopes;
    @JsonProperty("from_date")
    private String fromDate;
    @JsonProperty("to_date")
    private String toDate;
    private boolean interactive;
    @JsonProperty("store_credentials")
    private boolean storeCredentials;
    @JsonProperty("include_natures")
    private List<String> includeNatures;
    @JsonProperty("show_consent_confirmation")
    private boolean showConsentConfirmation;
    @JsonProperty("consent_id")
    private String consentId;
    @JsonProperty("fail_at")
    private String failAt;
    @JsonProperty("fail_message")
    private String failMessage;
    @JsonProperty("fail_error_class")
    private String failErrorClass;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("success_at")
    private String successAt;
    @JsonProperty("last_stage")
    private SaltedgeStage lastStage;

}

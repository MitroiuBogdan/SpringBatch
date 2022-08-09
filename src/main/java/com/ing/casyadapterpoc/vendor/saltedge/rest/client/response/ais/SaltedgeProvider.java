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
public class SaltedgeProvider {

    private String id;
    private String code;
    private String name;
    private String mode;
    private String status;
    @JsonProperty("automatic_fetch")
    private boolean automaticFetch;
    private boolean interactive;
    private String instruction;
    @JsonProperty("refresh_timeout")
    private int refreshTimeout;
    @JsonProperty("customer_notified_on_sign_in")
    private boolean customerNotifiedOnSignIn;
    @JsonProperty("home_url")
    private String homeUrl;
    @JsonProperty("login_url")
    private String loginUrl;
    @JsonProperty("logo_url")
    private String logoUrl;
    @JsonProperty("country_code")
    private String countryCode;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("updated_at")
    private String updatedAt;
    private String timezone;
    @JsonProperty("holder_info")
    private List<String> holderInfo;
    @JsonProperty("max_consent_days")
    private String maxConsentDays;
    @JsonProperty("identification_mode")
    private String identificationMode;
    @JsonProperty("max_interactive_delay")
    private int maxInteractiveDelay;
    @JsonProperty("optional_interactivity")
    private boolean optionalInteractivity;
    @JsonProperty("max_fetch_interval")
    private int maxFetchInterval;
    @JsonProperty("supported_fetch_scopes")
    private List<String> supportedFetchScopes;
    @JsonProperty("supported_account_extra_fields")
    private List<String> supportedAccountExtraFields;
    @JsonProperty("supported_transaction_extra_fields")
    private List<String> supportedTransactionExtraFields;
    @JsonProperty("supported_account_natures")
    private List<String> supportedAccountNatures;
    @JsonProperty("supported_account_types")
    private List<String> supportedAccountTypes;
    private boolean regulated;
    @JsonProperty("identification_codes")
    private List<String> identificationCodes;
    @JsonProperty("bic_codes")
    private List<String> bicCodes;
    @JsonProperty("supported_iframe_embedding")
    private boolean supportedIframeEmbedding;
    @JsonProperty("payment_templates")
    private List<String> paymentTemplates;
    @JsonProperty("no_funds_rejection_supported")
    private boolean noFundsRejectionSupported;
    @JsonProperty("custom_pendings")
    private boolean customPendings;
    @JsonProperty("custom_pending_period")
    private int customPendingPeriod;
    @JsonProperty("required_fields")
    private List<SaltedgeField> requiredFields;
    @JsonProperty("interactive_fields")
    private List<SaltedgeField> interactiveFields;
}

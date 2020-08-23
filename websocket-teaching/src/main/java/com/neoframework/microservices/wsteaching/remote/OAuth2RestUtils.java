
package com.neoframework.microservices.wsteaching.remote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// import org.springframework.core.ParameterizedTypeReference;
// import org.springframework.http.HttpEntity;
// import org.springframework.http.HttpHeaders;
// import org.springframework.http.HttpMethod;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.oauth2.client.OAuth2RestTemplate;
// import org.springframework.util.Assert;
// import org.springframework.util.LinkedMultiValueMap;
// import org.springframework.util.MultiValueMap;

/**
 * 远程服务调用.
 */
public class OAuth2RestUtils {

    private static final Logger logger = LoggerFactory.getLogger(OAuth2RestUtils.class);

    // /**
    //  * 跨服务调用：积分行为上报及计算、处理.
    //  *
    //  * @param oAuth2RestTemplate
    //  * @param reportVo
    //  * @param rule
    //  * @return
    //  */
    // public static SimpleResult doPointRuleCollect(OAuth2RestTemplate oAuth2RestTemplate, StatReportVo reportVo,
    //         PointRule rule) {
    //     Assert.notNull(oAuth2RestTemplate, "oAuth2RestTemplate must be provided");
    //     try {
    //         if (reportVo == null || ((reportVo.getAccessList() == null || reportVo.getAccessList().size() == 0)
    //                 && (reportVo.getResourceActionList() == null || reportVo.getResourceActionList().size() == 0))) {
    //             new SimpleResult(SimpleResult.STATUS_FAILED, "parameter missing");
    //         }
    //
    //         ParameterizedTypeReference<SimpleResult> typeRef = new ParameterizedTypeReference<SimpleResult>() {};
    //         String url = "http://bonus-service/bonus/point/rule/reportCollect";
    //
    //         HttpHeaders headers = new HttpHeaders();
    //         headers.add("checkToken", "d$^#&*jjHHE97KLO8$*n");
    //         MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    //         String reportVoListString = JsonMapper.nonDefaultMapper().toJson(reportVo);
    //         params.add("reportVo", reportVoListString);
    //         if (rule != null) {
    //             String ruleString = JsonMapper.nonDefaultMapper().toJson(rule);
    //             params.add("rule", ruleString);
    //         }
    //         HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
    //
    //         ResponseEntity<SimpleResult> exchange =
    //                 oAuth2RestTemplate.exchange(url, HttpMethod.POST, requestEntity, typeRef);
    //         return exchange.getBody();
    //     } catch (Exception e) {
    //         logger.error("doPointRuleCollect error {}", e);
    //     }
    //     return new SimpleResult(SimpleResult.STATUS_FAILED, "exception");
    // }

}
